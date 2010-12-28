package com.imas.web.main;

import org.apache.wicket.ResourceReference;
import org.apache.wicket.Session;
import org.apache.wicket.protocol.http.WebApplication;
import org.odlabs.wiquery.core.commons.IWiQuerySettings;
import org.odlabs.wiquery.core.commons.WiQuerySettings;
import org.odlabs.wiquery.ui.themes.IThemableApplication;
import org.odlabs.wiquery.ui.themes.WiQueryCoreThemeResourceReference;

public class IMASWicketApplication extends WebApplication {

    public IMASWicketApplication() {
        super();    }

    public static final ResourceReference THEME = new WiQueryCoreThemeResourceReference("redmond");

    /**
	 * @see org.apache.wicket.Application#getHomePage()
	 */
	public Class<MainPage> getHomePage() {
		return MainPage.class;
	}

}
