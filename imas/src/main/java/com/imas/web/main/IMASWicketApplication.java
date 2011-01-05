package com.imas.web.main;

import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.target.coding.HybridUrlCodingStrategy;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;

public class IMASWicketApplication extends WebApplication {
    //implements IThemableApplication {

    private static boolean springInjectorInstatiated = false;
    
    public IMASWicketApplication() {
        super();
    }


    @Override
    public void init() {
        mount(new HybridUrlCodingStrategy("/404", PageNotFound.class));

        // this installs the component injector that will inject newly created
        // components with their spring dependencies
        if (!springInjectorInstatiated) {
            addComponentInstantiationListener(new SpringComponentInjector(this));
            springInjectorInstatiated = true;
        }        
        
    }
    
    /**
     * @see org.apache.wicket.Application#getHomePage()
     */
    public Class<GeneralLayoutTestPage> getHomePage() {
        return GeneralLayoutTestPage.class;
    }

    /*
    @Override
    public ResourceReference getTheme(Session session) {
        return RedmondTheme.getInstance().getTheme();
    }
    */



}