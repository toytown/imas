package com.imas.web.main;

import org.apache.wicket.markup.html.CSSPackageResource;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebResponse;

public abstract class AbstractWepTemplatePage extends WebPage {

    
    public AbstractWepTemplatePage() {
        super();
        add(CSSPackageResource.getHeaderContribution("css/layout.css"));        
    }

    @Override
    protected void setHeaders(WebResponse response) {
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache, max-age=0, must-revalidate, no-store");

    }
    
    @Override
    protected void onBeforeRender() {
        super.onBeforeRender();
    }


}
