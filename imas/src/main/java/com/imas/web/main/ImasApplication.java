package com.imas.web.main;

import org.apache.wicket.WicketRuntimeException;
import org.apache.wicket.protocol.http.WebApplication;

public class ImasApplication extends WebApplication {

	public ImasApplication() {
	}

	public static ImasApplication get() {
		WebApplication webApplication = WebApplication.get();

		if (webApplication instanceof ImasApplication == false) {
			throw new WicketRuntimeException("The application attached to the current thread is not a " + ImasApplication.class.getSimpleName());
		}

		return (ImasApplication) webApplication;
	}

	/**
	 * @see org.apache.wicket.Application#getHomePage()
	 */
	public Class<MainPage> getHomePage() {
		return MainPage.class;
	}

}
