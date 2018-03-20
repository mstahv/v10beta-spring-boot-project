package com.example.demo;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.BodySize;
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;

@Theme(Lumo.class)
@BodySize(height = "100vh", width = "100vw")
public class MainLayout extends VerticalLayout implements RouterLayout {
	
	public MainLayout() {
		setMargin(true);
		add(new Paragraph("This is mainlayout"));
		RouterLink personsView = new RouterLink("Persons", PersonView.class);
		RouterLink splasView = new RouterLink("Splash view", SplashView.class);
		HorizontalLayout horizontalLayout = new HorizontalLayout(splasView, personsView);
		horizontalLayout.setSpacing(true);
		add(horizontalLayout);
	}
    
}
