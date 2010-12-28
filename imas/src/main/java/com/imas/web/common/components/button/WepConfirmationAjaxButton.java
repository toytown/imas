package com.imas.web.common.components.button;

import org.apache.wicket.ajax.IAjaxCallDecorator;
import org.apache.wicket.ajax.calldecorator.AjaxPreprocessingCallDecorator;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.model.IModel;

public abstract class WepConfirmationAjaxButton<T> extends AjaxLink<T> {

    private IModel<T> confirm = null; 
    
    public WepConfirmationAjaxButton(final String id, final IModel<T> model) {
        super(id, model);
    }

    public WepConfirmationAjaxButton(final String id) {
        super(id);
    }

    public WepConfirmationAjaxButton(String id, IModel<T> confirm, IModel<T> label) {
        super(id, confirm);
        this.setModel(label);
        this.confirm = confirm;
    }

    @Override
    protected IAjaxCallDecorator getAjaxCallDecorator() {
        return new AjaxPreprocessingCallDecorator(super.getAjaxCallDecorator()) {

            @Override
            public CharSequence preDecorateScript(CharSequence script) {
                return "if(!confirm('" + confirm.getObject() + "')) return false;" + script;
            }
        };
    }

 }
