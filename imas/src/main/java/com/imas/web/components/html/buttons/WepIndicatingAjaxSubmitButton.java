package com.imas.web.components.html.buttons;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.IAjaxCallDecorator;
import org.apache.wicket.ajax.IAjaxIndicatorAware;
import org.apache.wicket.extensions.ajax.markup.html.AjaxIndicatorAppender;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.MarkupStream;
import org.apache.wicket.markup.html.form.AbstractSubmitLink;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.IModel;
import org.apache.wicket.util.string.AppendingStringBuffer;

public class WepIndicatingAjaxSubmitButton extends AbstractSubmitLink implements IAjaxIndicatorAware {

    private static final long serialVersionUID = 1L;
    private static final String CSS_EMPHASIZED = "emphasized_button";
	private static final String CSS_STANDARD = "standard_button";
	private final AjaxIndicatorAppender indicatorAppender = new AjaxIndicatorAppender();

	private boolean emphasized;
	private IModel<String> confirmationMessageModel;
	
	
	public WepIndicatingAjaxSubmitButton(String id) {
		super(id);
		this.add(indicatorAppender);
	}

	public WepIndicatingAjaxSubmitButton(String id, IModel<?> model) {
		super(id, model);
		this.add(indicatorAppender);
	}

	public WepIndicatingAjaxSubmitButton(String id, IModel<?> model, Form<?> form) {
		super(id, model, form);
		this.add(indicatorAppender);
	}

	/**
	 * Returns the {@link IAjaxCallDecorator} that will be used to modify the
	 * generated javascript. This is the preferred way of changing the
	 * javascript in the onclick handler
	 * 
	 * @return call decorator used to modify the generated javascript or null
	 *         for none
	 */
	protected IAjaxCallDecorator getAjaxCallDecorator() {
		return new IAjaxCallDecorator() {
            
            private static final long serialVersionUID = 1L;

            @Override
            public CharSequence decorateScript(CharSequence script) {
                if (confirmationMessageModel != null && confirmationMessageModel.getObject() != null) {
                    return "if(!confirm('" + confirmationMessageModel.getObject() + "')) return false;" + script;
                } else {
                    return null;
                }
            }
            
            @Override
            public CharSequence decorateOnSuccessScript(CharSequence script) {
                // TODO Auto-generated method stub
                return null;
            }
            
            @Override
            public CharSequence decorateOnFailureScript(CharSequence script) {
                // TODO Auto-generated method stub
                return null;
            }
        };
	}

	/**
	 * Final implementation of the Button's onSubmit. AjaxSubmitLinks have there
	 * own onSubmit which is called.
	 * 
	 * @see org.apache.wicket.markup.html.form.Button#onSubmit()
	 */
	public final void onSubmit() {
	}


	/**
	 * Listener method invoked on form submit with errors
	 * 
	 * @param target
	 * @param form
	 * 
	 */
	protected void onError(AjaxRequestTarget target, Form<?> form) {

	}

	/**
	 * @inheritDoc
	 * @see org.apache.wicket.Component#onComponentTag(org.apache.wicket.markup.ComponentTag)
	 */
	@Override
	protected void onComponentTag(ComponentTag tag) {
		super.onComponentTag(tag);

		super.onComponentTag(tag);

		if (isLinkEnabled()) {
			if (tag.getName().toLowerCase().equals("a")) {
				tag.put("href", "#");
			}
		} else {
			disableLink(tag);
		}

		if (!isLinkEnabled()) {
			disableLink(tag);
		} else {
			tag.setName("a");
			String value = getDefaultModelObjectAsString();
			tag.put("title", value);
			tag.put("href", "#");
			tag.put("class", (emphasized ? CSS_EMPHASIZED : CSS_STANDARD));
		}
	}

	@Override
	protected void onComponentTagBody(MarkupStream markupStream, ComponentTag openTag) {
		final AppendingStringBuffer buffer = new AppendingStringBuffer("<span>" + getDefaultModelObjectAsString() + "</span>");
		replaceComponentTagBody(markupStream, openTag, buffer);
	}

	public WepIndicatingAjaxSubmitButton setEmphasized(boolean emphasized) {
		this.emphasized = emphasized;
		return this;
	}

	public boolean isEmphasized() {
		return emphasized;
	}

	@Override
    public String getAjaxIndicatorMarkupId() {
        return CustomBusyIndicator.MARKUP_ID;
    }

    public IModel<String> getConfirmationMessageModel() {
        return confirmationMessageModel;
    }

    public void setConfirmationMessageModel(IModel<String> confirmationMessageModel) {
        this.confirmationMessageModel = confirmationMessageModel;
    }
}
