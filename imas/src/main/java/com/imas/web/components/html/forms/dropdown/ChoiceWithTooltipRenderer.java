package com.imas.web.components.html.forms.dropdown;

import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.util.lang.PropertyResolver;

/**
 * Extension of {@link ChoiceRenderer} to support tooltips over choice options
 *
 */
public class ChoiceWithTooltipRenderer<T> extends ChoiceRenderer<T> {
    private static final long serialVersionUID = 607273576223971644L;
    private String tooltipExpression;

    public ChoiceWithTooltipRenderer(String displayExpression, String idExpression, String tooltipExpression) {
        super(displayExpression, idExpression);
        this.tooltipExpression = tooltipExpression;
    }

    public String getTooltipValue(T object) {
        Object returnValue = object;
        if ((tooltipExpression != null) && (object != null)) {
            returnValue = PropertyResolver.getValue(tooltipExpression, object);
        }

        if (returnValue == null) {
            return "";
        }

        return returnValue.toString();
    }
}
