package com.imas.web.components.html.buttons;

import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.MarkupStream;
import org.apache.wicket.model.IModel;
import org.apache.wicket.util.string.AppendingStringBuffer;

/**
 * Extension of {@link AjaxLink}, that renders as a button defined in WEP styleguide.
 *  
 */
public abstract class WepAjaxButton<T> extends AjaxLink<T> {

    private static final long serialVersionUID = 1L;
    private boolean emphasized;
    
    public WepAjaxButton(String id, IModel<T> model) {
        super(id, model);
    }

    public WepAjaxButton(String id) {
        super(id);
    }
    
    /**
     * @inheritDoc
     * @see org.apache.wicket.Component#onComponentTag(org.apache.wicket.markup.ComponentTag)
     */
    @Override
    protected void onComponentTag(ComponentTag tag) {
        super.onComponentTag(tag);

        if (!isLinkEnabled()) {
            disableLink(tag);
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
        final AppendingStringBuffer buffer = new AppendingStringBuffer("<span>" + getDefaultModelObjectAsString() + "</span>");
        replaceComponentTagBody(markupStream, openTag, buffer);
    }

    @Override
    protected void disableLink(final ComponentTag tag) {
        tag.remove("onclick");
        tag.put("class", ( emphasized ? IWepButton.CSS_EMPHASIZED : IWepButton.CSS_STANDARD ) + " disabled_button");
    }

    /**
     * set this button as emphasized, i.e. it will be a bit darker, than non-emhasized ones
     * @param emphasized
     * @return this
     */
    public WepAjaxButton<T> setEmphasized(boolean emphasized) {
        this.emphasized = emphasized;
        return this;
    }

}
