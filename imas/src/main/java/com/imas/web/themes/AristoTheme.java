package com.imas.web.themes;

import org.apache.wicket.ResourceReference;

public class AristoTheme extends UITheme {

    private static final long serialVersionUID = 1L;

    public static final ResourceReference THEME = new ResourceReference(Themes.class, "aristo/jquery-ui-1.8.5.custom.css");
    
    private static AristoTheme instance;
    
    private AristoTheme() {
        super("aristo");
    }

    public static AristoTheme getInstance() {
        if(instance==null) {
            instance = new AristoTheme();
        }
        return instance;
    }
    
    @Override
    public ResourceReference getTheme() {
        return THEME;
    }    
}
