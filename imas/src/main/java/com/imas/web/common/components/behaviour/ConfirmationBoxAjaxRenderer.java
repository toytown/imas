package com.imas.web.common.components.behaviour;

import org.apache.wicket.ajax.calldecorator.AjaxCallDecorator;

public class ConfirmationBoxAjaxRenderer extends AjaxCallDecorator {

    private static final long serialVersionUID = 1L;

    /** Message to be displayed in the confirm box. */
    private String message;

    /**
    * Constructor.
    * @param message Message to be shown in the confirm box.
    */
    public ConfirmationBoxAjaxRenderer(final String message) {
        super();
        this.message = message;
    }

    /**
    * @param script existing script around the component.
    * @return modified string.
    * @see org.apache.wicket.ajax.calldecorator.AjaxCallDecorator#decorateScript(java.lang.CharSequence)
    */
    public CharSequence decorateScript(final CharSequence script) {
        return "if(!confirm('" + message + "')) return false;" + script;
    }
}
