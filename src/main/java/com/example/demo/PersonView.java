package com.example.demo;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value="person", layout = MainLayout.class)
public class PersonView extends VerticalLayout {

    public PersonView(MyService service, PersonRepository repo) {
        setSpacing(true);
        setMargin(true);
        Button b = new Button("Click me!");
        b.addClickListener( e->
                add(new Paragraph("Hello from the service: " + repo.count()))
        );
        add(b);
    }
}
