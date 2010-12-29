package com.imas.web.themes;

import java.util.ArrayList;
import java.util.List;


public class Themes {

	public static final List<UITheme> themes = new ArrayList<UITheme>();
	
	static {
		themes.add(RedmondTheme.getInstance());
		themes.add(LeFrogTheme.getInstance());
		themes.add(CupertinoTheme.getInstance());
        themes.add(AristoTheme.getInstance());
        themes.add(UniformTheme.getInstance());        
	}
}
