package com.imas.web.common.components.button;

import org.apache.wicket.extensions.ajax.markup.html.IndicatingAjaxLink;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.MarkupStream;
import org.apache.wicket.model.IModel;
import org.apache.wicket.util.string.AppendingStringBuffer;

public abstract class WepIndicatingAjaxButton<T> extends IndicatingAjaxLink<T> {

	private boolean emphasized;

	public WepIndicatingAjaxButton(String id) {
		super(id);
	}

	public WepIndicatingAjaxButton(String id, IModel<T> model) {
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
			tag.put("href", "#");
			tag.put("class", (emphasized ? IWepButton.CSS_EMPHASIZED : IWepButton.CSS_STANDARD));
		}
	}

	@Override
	protected void onComponentTagBody(MarkupStream markupStream, ComponentTag openTag) {
		final AppendingStringBuffer buffer = new AppendingStringBuffer("<span>" + getDefaultModelObjectAsString() + "</span>");
		replaceComponentTagBody(markupStream, openTag, buffer);
	}

	public WepIndicatingAjaxButton<T> setEmphasized(boolean emphasized) {
		this.emphasized = emphasized;
		return this;
	}
	
	@Override
    public String getAjaxIndicatorMarkupId() {
        return null;//WepBusyIndicator.MARKUP_ID;
    }

}
