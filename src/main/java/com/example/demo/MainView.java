package com.example.demo;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route
public class MainView extends VerticalLayout {

    public MainView(MyService service) {
        Button b = new Button("Click me!");
        b.addClickListener( e->
                add(new Paragraph("Hello from the service: " + service.sayHi()))
        );
        add(b);
    }
}
