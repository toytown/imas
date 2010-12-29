package com.imas.web.themes;

import org.apache.wicket.ResourceReference;

public class UniformTheme extends UITheme {

    public static final ResourceReference THEME = new ResourceReference(Themes.class, "uniform/uniform.default.css");
    
    private static final long serialVersionUID = 1L;

    private static UniformTheme instance;
    
    private UniformTheme() {
        super("uniform");
    }

    public static UniformTheme getInstance() {
        if(instance==null) {
            instance = new UniformTheme();
        }
        return instance;
    }
    
    @Override
    public ResourceReference getTheme() {
        return THEME;
    }

}
