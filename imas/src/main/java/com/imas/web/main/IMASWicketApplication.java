package com.imas.web.main;

import org.apache.wicket.protocol.http.WebApplication;

public class IMASWicketApplication extends WebApplication {
    //implements IThemableApplication {


    public IMASWicketApplication() {
        super();
    }


    /**
     * @see org.apache.wicket.Application#getHomePage()
     */
    public Class<GeneralLayoutTest> getHomePage() {
        return GeneralLayoutTest.class;
    }

    /*
    @Override
    public ResourceReference getTheme(Session session) {
        return RedmondTheme.getInstance().getTheme();
    }
    */



}