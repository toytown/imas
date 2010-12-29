package com.imas.web.themes;

import org.apache.wicket.ResourceReference;

/**
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class CupertinoTheme extends UITheme {

	private static final long serialVersionUID = 1L;
	
	public static final ResourceReference THEME = new ResourceReference(Themes.class, "cupertino/jquery-ui-1.8.4.custom.css");
	
	private static CupertinoTheme instance;
	
	
	/**
	 * @param name
	 */
	private CupertinoTheme() {
		super("Cupertino");
	}

	/* (non-Javadoc)
	 * @see es.liberty.fwkdemo.web.jquery.themes.UITheme#getTheme()
	 */
	@Override
	public ResourceReference getTheme() {
		return THEME;
	}

	public static CupertinoTheme getInstance() {
		if(instance==null) {
			instance = new CupertinoTheme();
		}
		return instance;
	}

}
