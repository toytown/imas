package com.imas.web.components.html.buttons;

import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.MarkupStream;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.IModel;
import org.apache.wicket.util.string.AppendingStringBuffer;

/**
 * Extension of {@link Link}, that renders as a button defined in WEP styleguide 
 *
 */
public abstract class WepButton<T> extends Link<T> {

    private static final long serialVersionUID = 1L;
    private boolean emphasized;
    
    public WepButton(String id) {
        super(id);
    }

    public WepButton(String id, IModel<T> model) {
        super(id, model);
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
    public WepButton<T> setEmphasized(boolean emphasized) {
        this.emphasized = emphasized;
        return this;
    }
}
