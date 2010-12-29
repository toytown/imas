package com.imas.web.themes;

import org.apache.wicket.ResourceReference;

/**
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class LeFrogTheme extends UITheme {

	private static final long serialVersionUID = 1L;
	
	public static final ResourceReference THEME = new ResourceReference(Themes.class, "lefrog/jquery-ui-1.8.4.custom.css");
	
	private static LeFrogTheme instance;
	
	
	/**
	 * @param name
	 */
	private LeFrogTheme() {
		super("Le-Frog");
	}

	/* (non-Javadoc)
	 * @see es.liberty.fwkdemo.web.jquery.themes.UITheme#getTheme()
	 */
	@Override
	public ResourceReference getTheme() {
		return THEME;
	}

	public static LeFrogTheme getInstance() {
		if(instance==null) {
			instance = new LeFrogTheme();
		}
		return instance;
	}

}
