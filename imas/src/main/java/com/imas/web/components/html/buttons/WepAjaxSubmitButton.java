package com.imas.web.components.html.buttons;

import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.MarkupStream;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.IModel;
import org.apache.wicket.util.string.AppendingStringBuffer;
import org.apache.wicket.util.string.Strings;

public abstract class WepAjaxSubmitButton extends AjaxButton {

    private static final long serialVersionUID = 1L;
    private boolean emphasized;
    
    public WepAjaxSubmitButton(String id) {
        super(id);
    }

    public WepAjaxSubmitButton(String id, Form<?> form) {
        super(id, form);
    }

    public WepAjaxSubmitButton(String id, IModel<String> model, Form<?> form) {
        super(id, model, form);
    }

    public WepAjaxSubmitButton(String id, IModel<String> model) {
        super(id, model);
    }

    /**
     * @inheritDoc
     * @see org.apache.wicket.Component#onComponentTag(org.apache.wicket.markup.ComponentTag)
     */
    @Override
    protected void onComponentTag(ComponentTag tag) {
        super.onComponentTag(tag);

        if (!isEnabled()) {
            disable(tag);
        } else {
            tag.setName("a");
            String value = getDefaultModelObjectAsString();
            tag.put("title", value);
            tag.put("href", "#");
            tag.put("class", ( emphasized ? IWepButton.CSS_EMPHASIZED : IWepButton.CSS_STANDARD ));
        }
    }

    @Override
    protected void onComponentTagBody(MarkupStream markupStream, ComponentTag openTag) {
        final AppendingStringBuffer buffer = new AppendingStringBuffer("<span");
        if (!Strings.isEmpty(getAdditionalButtonStyle())) {
            buffer.append(" style=\"" + getAdditionalButtonStyle() + "\"");
        }
        buffer.append(">").append(getDefaultModelObjectAsString()).append("</span>");
        replaceComponentTagBody(markupStream, openTag, buffer);
    }
    
    protected String getAdditionalButtonStyle() {
        return "";
    }

    protected void disable(final ComponentTag tag) {
        tag.remove("onclick");
        tag.put("class", ( emphasized ? IWepButton.CSS_EMPHASIZED : IWepButton.CSS_STANDARD ) + " disabled_button");
    }

    /**
     * set this button as emphasized, i.e. it will be a bit darker, than non-emhasized ones
     * @param emphasized
     * @return this
     */
    public WepAjaxSubmitButton setEmphasized(boolean emphasized) {
        this.emphasized = emphasized;
        return this;
    }
}
