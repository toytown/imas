package com.imas.web.main;

import org.apache.wicket.WicketRuntimeException;
import org.apache.wicket.protocol.http.WebApplication;

public class IMASApplication extends WebApplication {

	public IMASApplication() {
	}

	public static IMASApplication get() {
		WebApplication webApplication = WebApplication.get();

		if (webApplication instanceof IMASApplication == false) {
			throw new WicketRuntimeException("The application attached to the current thread is not a " + IMASApplication.class.getSimpleName());
		}

		return (IMASApplication) webApplication;
	}

	/**
	 * @see org.apache.wicket.Application#getHomePage()
	 */
	public Class<MainPage> getHomePage() {
		return MainPage.class;
	}

}
