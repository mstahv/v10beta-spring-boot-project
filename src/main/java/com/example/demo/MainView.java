package com.example.demo;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.BodySize;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;
import org.springframework.beans.factory.annotation.Autowired;

@Route("")
// TODO for flow team: next two lines are obsolete. Lumo should be default if nothing else set, BodySize is this in every example I have seen
@Theme(Lumo.class)
@BodySize(height = "100vh", width = "100vw")
public class MainView extends VerticalLayout {

    public MainView(MyService service, PersonRepository repo) {
        // TODO for flow team: next two lines are obsolete, this discussion has been done before
        setSpacing(true);
        setMargin(true);
        Button b = new Button("Click me!");
        b.addClickListener( e->
                add(new Paragraph("Hello from the service: " + repo.count()))
        );
        add(b);
    }
}
