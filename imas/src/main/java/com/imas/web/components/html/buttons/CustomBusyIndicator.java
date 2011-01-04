package com.imas.web.components.html.buttons;

import org.apache.wicket.Component;
import org.apache.wicket.RequestCycle;
import org.apache.wicket.ResourceReference;
import org.apache.wicket.Response;
import org.apache.wicket.extensions.ajax.markup.html.AjaxIndicatorAppender;

public class CustomBusyIndicator extends AjaxIndicatorAppender {
    private static final long serialVersionUID = 1L;

    private static final ResourceReference BUSY_IND_RES = new ResourceReference(CustomBusyIndicator.class, "img/busy_big.gif");
    private static final ResourceReference STD_BTN_RES = new ResourceReference(CustomBusyIndicator.class, "img/bg_standard_button_span.gif");    
    private static final ResourceReference LINK_BTN_RES = new ResourceReference(CustomBusyIndicator.class, "img/bg_standard_button_a.gif");
    public static final String MARKUP_ID = "busy-ajax-indicator";
    private static final String BUSY_MSG = "Please wait....";
    
    public CustomBusyIndicator() {
        super();
    }

    @Override
    public void onRendered(Component component) {
        final Response r = component.getResponse();

        r.write("<div style=\"display:none;\" id=\"" + getMarkupId() + "\">\n");
        r.write("\t<div id=\"lightbox\" style=\"background-color:#FFFFFF;height: 1px; min-height: 100%; left: 0; opacity: 0.6; filter: alpha(opacity=60); position:absolute; top: 0; width: 100%; z-index: 998;\"\n");
        r.write("</div>\n");
        r.write("<div id=\"popUpMessage\" style=\"background:url('" + getIndicatorUrl()
                + "') no-repeat scroll right center transparent; z-index: 999; opacity: 1.0; width: 150px;\">\n");
        r.write("<p style=\"color:#000000;\">" + getBusyMessage() + "</p>\n");
        r.write("<a onclick=\"stopSearch();\" style=\"background:url('" + getLinkButtonStyle()
                + "') no-repeat scroll right top transparent; float: left;\" class=\"standard_button\">\n");
        r.write("<span style=\"color:#000000;background:url('" + getSpanButtonStyle()
                + "') no-repeat scroll 0 0 transparent\">Cancel</span>\n");
        r.write("</a>\n");
        r.write("</div>\n");
        r.write("</div>\n");
        r.write("<script type=\"text/javascript\">");
        // override the window functions only if the busy indicator is
        // rendered. therefore the following was moved from the html page to
        // here
        // *** 1st overwritten window function ***
        r.write("window.onresize = function() {\n");
        r.write("resizeBusyIndicator();\n");
        r.write("movePopupMessage('popUpMessage');\n}\n");
        // *** 2nd overwritten window function
        r.write("window.onscroll = function() {\n");
        r.write("resizeBusyIndicator();\n");
        r.write("movePopupMessage('popUpMessage');\n}\n");
        // move popup message to center of the screen
        r.write("movePopupMessage('popUpMessage');\n");
        r.write("</script>\n");
    }

    public String getMarkupId() {
        return MARKUP_ID;
    }

    /**
     * @return url of the animated indicator image
     */
    protected CharSequence getIndicatorUrl() {
        return RequestCycle.get().urlFor(BUSY_IND_RES);        
    }

    /**
     * @return url of the button image which is applied to the span tag
     */
    protected CharSequence getSpanButtonStyle() {
        return RequestCycle.get().urlFor(STD_BTN_RES);
    }

    /**
     * @return url of the button image which is applied to the link tag
     */
    protected CharSequence getLinkButtonStyle() {
         return RequestCycle.get().urlFor(LINK_BTN_RES);
    }

    protected String getBusyMessage() {
        return BUSY_MSG;
    }
}
