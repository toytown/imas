package com.imas.web.main;

import org.apache.wicket.ResourceReference;
import org.apache.wicket.Session;
import org.apache.wicket.protocol.http.WebApplication;
import org.odlabs.wiquery.ui.themes.IThemableApplication;

import com.imas.web.themes.RedmondTheme;

public class IMASWicketApplication extends WebApplication implements IThemableApplication {

    public IMASWicketApplication() {
        super();
    }


    /**
     * @see org.apache.wicket.Application#getHomePage()
     */
    public Class<MainPage> getHomePage() {
        return MainPage.class;
    }

    @Override
    public ResourceReference getTheme(Session session) {
        return RedmondTheme.getInstance().getTheme();
    }



}