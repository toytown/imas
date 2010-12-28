package com.imas.web.common.template;

import org.apache.wicket.Session;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;

import com.imas.web.main.IMASSession;
import com.imas.web.main.MainPage;

public class BaseTemplatePage extends WebPage {
	
	
	public BaseTemplatePage() {
		add(new Label("title", getTitle()));
	
		//navigation tool
		//add( new BookmarkablePageLink("Suchen", MainPage.class));
		//add( new BookmarkablePageLink("Inserieren", EditPage.class));
		
		
		WebMarkupContainer authenticatedMenu = new WebMarkupContainer("authenticatedMenu") {
            private static final long serialVersionUID = 1L;

            public boolean isVisible() {
				IMASSession sessionNew = (IMASSession) Session.get();
				return (sessionNew.getUser() != null);
			}
		};
		
		authenticatedMenu.add(new Label("loginName", "test"));
		//authenticatedMenu.add(new BookmarkablePageLink("userDetailLink", UserPage.class));
		
		authenticatedMenu.add(new Link<String>("logoffLink") {
            private static final long serialVersionUID = 1L;

            public void onClick() {
			    IMASSession session = (IMASSession) Session.get();
				session.invalidate();				
				setResponsePage(MainPage.class);
			}
		});		
		
		add(authenticatedMenu);

		
	}
	
	public String getTitle() {
		return "";
	}
}
