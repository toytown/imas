package com.imas.web.main;

import org.apache.wicket.markup.html.CSSPackageResource;
import org.apache.wicket.markup.html.JavascriptPackageResource;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebResponse;

import com.imas.web.components.html.buttons.CustomBusyIndicator;
import com.imas.web.components.html.buttons.WepIndicatingAjaxSubmitButton;
import com.imas.web.components.html.datepicker.IMASDatePicker;

public abstract class IMASWebTemplatePage extends WebPage {

    private CustomBusyIndicator busyIndicator = new CustomBusyIndicator();
    public IMASWebTemplatePage() {
        super();
        add(CSSPackageResource.getHeaderContribution("css/styles.css"));
        add(CSSPackageResource.getHeaderContribution(IMASDatePicker.class, "style/calendar-win2k-1.css"));
        add(CSSPackageResource.getHeaderContribution(WepIndicatingAjaxSubmitButton.class, "css/buttons.css"));
        add(JavascriptPackageResource.getHeaderContribution("js/jquery-1.4.4.js"));
        add(JavascriptPackageResource.getHeaderContribution("js/jquery.corner.js"));
        add(JavascriptPackageResource.getHeaderContribution("js/jquery.bubble.js"));
        add(JavascriptPackageResource.getHeaderContribution("js/main.js"));

        add(busyIndicator);
    }

    @Override
    protected void setHeaders(WebResponse response) {
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache, max-age=0, must-revalidate, no-store");

    }

    @Override
    protected void onBeforeRender() {
        super.onBeforeRender();

        // avoids adding the busy indicator several times on a page
//        if (!hasBeenRendered()) {
//            add(busyIndicator);
//        }
    }

}
