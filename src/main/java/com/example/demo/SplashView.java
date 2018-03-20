package com.example.demo;

import com.vaadin.flow.templatemodel.TemplateModel;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.router.Route;

@Tag("splash-view")
@HtmlImport("frontend://splash-view.html")
@Route(value="", layout = MainLayout.class)
public class SplashView extends PolymerTemplate<SplashView.SplashViewModel> {

    public interface SplashViewModel extends TemplateModel {

    }
}
