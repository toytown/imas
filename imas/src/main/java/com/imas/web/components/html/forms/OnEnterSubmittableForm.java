package com.imas.web.components.html.forms;

import org.apache.wicket.markup.html.IHeaderResponse;
import org.apache.wicket.markup.html.form.Form;

/**
 * Base form providing the ability to submit a form on pressing enter in any of form's input fields when using the javascript buttons.
 *
 */
public abstract class OnEnterSubmittableForm<T> extends Form<T> {

    private static final long serialVersionUID = 1L;
    private static final String JS = "jQuery('#%s :input').bind('keyup', function(event){if(event.keyCode == 13) {jQuery('#%s').click();}});";
    
    public OnEnterSubmittableForm(String id) {
        super(id);
    }
    
    @Override
    public void renderHead(IHeaderResponse response) {
        super.renderHead(response);
        response.renderOnLoadJavascript(String.format(JS, getFormMarkupId(), getSubmitButtonMarkupId()));
    }

    /**
     * returns the form markup ID, where the jQuery handler should be bound to
     * @return form markup ID
     */
    protected abstract String getFormMarkupId();
    
    /**
     * returns the form submit button markup ID, which the jQuery handler should trigger to submit the form
     * @return form submit button markup ID
     */
    protected abstract String getSubmitButtonMarkupId();
}
