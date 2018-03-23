package com.example.demo;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.Grid.SelectionMode;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.Route;
import org.vaadin.matti.PointField;

@Route(value = "person", layout = MainLayout.class)
public class PersonView extends VerticalLayout {

    TextField name = new TextField("Name");
    TextField email = new TextField("Email");
    DatePicker birthday = new DatePicker();
    PointField location = new PointField();

    Button save = new Button("Save");

    FormLayout form = new FormLayout(name, email, birthday, location, save);

    public PersonView(MyService service, PersonRepository repo) {
        setWidth("100%");
        Grid<Person> grid = new Grid<>();
        grid.addColumn(Person::getName).setHeader("Name");
        grid.addColumn(Person::getEmail).setHeader("Email");
        grid.setItems(repo.findAll());

        grid.setSelectionMode(SelectionMode.SINGLE);

        Binder<Person> binder = new Binder<>(Person.class);
        binder.bindInstanceFields(this);

        form.setVisible(false);

        grid.asSingleSelect().addValueChangeListener(e -> {
            if (e.getValue() == null) {
                binder.setBean(null);
                form.setVisible(false);
            } else {
                Person p = e.getValue();
                // Get fresh entity, Grid somehow seems to store the old in some cache :-(.
                // Beta bug, will be fixed later, but anyways this is a good habit in a 
                // real world app with lot of users changing the same data -> otherwise you might get optimisted locking exceptions or lost data more often.
                p = repo.findById(p.getId()).get();
                binder.setBean(p);
                form.setVisible(true);
            }
        });

        save.addClickListener(e -> {
            final Person bean = binder.getBean();
            repo.saveAndFlush(bean);
            // refresh the listing
            grid.setItems(repo.findAll());
            // Workaround for a beta bug, also calling refresh all etc don't work
            grid.getDataCommunicator().refresh(bean);
        });

        add(grid, form);
    }
}
