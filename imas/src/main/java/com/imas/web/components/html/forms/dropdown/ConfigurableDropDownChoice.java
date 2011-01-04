package com.imas.web.components.html.forms.dropdown;

import java.util.List;

import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.model.IModel;
import org.apache.wicket.util.convert.IConverter;
import org.apache.wicket.util.string.AppendingStringBuffer;
import org.apache.wicket.util.string.Strings;


/**
 * Override of {@link org.apache.wicket.markup.html.form.DropDownChoice}, that enables:
 * <ul>
 * <li> disabling auto prepending of an empty value on top of generated combobox {@link #prependEmptyOption(boolean)}. Default is no prepend
 * <li> setting the message resources key that is appended on top of list, when auto prepending of empty option is enabled {@link #setNullKey(String)}
 * </ul>
 * Above mentioned methods support method chaining, so it makes the code less extensive.
 * @inheritDoc
 * @see org.apache.wicket.markup.html.form.DropDownChoice
 */
public class ConfigurableDropDownChoice<T> extends org.apache.wicket.markup.html.form.DropDownChoice<T> {
    
    private static final long serialVersionUID = -975945066534479732L;

    private static final String SELECT_ALL_KEY = "opt_all";
    
    private String nullKey = SELECT_ALL_KEY;
    private boolean prependEmptyOption;

    public ConfigurableDropDownChoice(String id, IModel<? extends List<? extends T>> choices, IChoiceRenderer<? super T> renderer) {
        super(id, choices, renderer);
    }

    public ConfigurableDropDownChoice(String id, IModel<? extends List<? extends T>> choices) {
        super(id, choices);
    }

    public ConfigurableDropDownChoice(String id, IModel<T> model, IModel<? extends List<? extends T>> choices,
            IChoiceRenderer<? super T> renderer) {
        super(id, model, choices, renderer);
    }

    public ConfigurableDropDownChoice(String id, IModel<T> model, IModel<? extends List<? extends T>> choices) {
        super(id, model, choices);
    }

    public ConfigurableDropDownChoice(String id, IModel<T> model, List<? extends T> data, IChoiceRenderer<? super T> renderer) {
        super(id, model, data, renderer);
    }

    public ConfigurableDropDownChoice(String id, IModel<T> model, List<? extends T> choices) {
        super(id, model, choices);
    }

    public ConfigurableDropDownChoice(String id, List<? extends T> data, IChoiceRenderer<? super T> renderer) {
        super(id, data, renderer);
    }

    public ConfigurableDropDownChoice(String id, List<? extends T> choices) {
        super(id, choices);
    }

    public ConfigurableDropDownChoice(String id) {
        super(id);
    }
    
    /**
     * enables prepending of an empty option on top of combobox
     * @param prepend 
     * @return this
     * @see {@link #setNullKey(String)}, {@link #setNullValid(boolean)}
     */
    public ConfigurableDropDownChoice<T> setPrependEmptyOption(boolean prepend) {
        this.prependEmptyOption = prepend;
        setNullValid(prepend);
        return this;
    }
    
    @Override
    protected CharSequence getDefaultChoice(Object selected) {
        if (prependEmptyOption) {
            return super.getDefaultChoice(selected);
        }
        return "";
    }

    /**
     * sets the resource bundle key, that has to be displayed as a null value. When not set, the default value is "opt_all"
     * @param nullKey resource bundle key
     * @return this
     * @see {@link #setPrependEmptyOption(boolean)}, {@link #setNullValid(boolean)}
     */
    public ConfigurableDropDownChoice<T> setNullKey(String nullKey) {
        this.nullKey = nullKey;
        return this;
    }

    @Override
    public String getNullKey() {
        return nullKey;
    }
    
    @Override
    protected String getNullValidKey() {
        return nullKey;
    }
    
    /**
     * overridden method to support tooltips
     */
    @SuppressWarnings("unchecked")    
    @Override
    protected void appendOptionHtml(AppendingStringBuffer buffer, T choice, int index, String selected) {
        
        IChoiceRenderer<? super T> renderer = getChoiceRenderer();
        
        if (!(renderer instanceof ChoiceWithTooltipRenderer)) {
            super.appendOptionHtml(buffer, choice, index, selected);
            return;
        }
        
        T objectValue = (T)renderer.getDisplayValue(choice);
        Class<T> objectClass = (Class<T>)(objectValue == null ? null : objectValue.getClass());

        String displayValue = "";
        if (objectClass != null && objectClass != String.class)
        {
            final IConverter converter = getConverter(objectClass);

            displayValue = converter.convertToString(objectValue, getLocale());
        }
        else if (objectValue != null)
        {
            displayValue = objectValue.toString();
        }
        buffer.append("\n<option ");
        if (isSelected(choice, index, selected))
        {
            buffer.append("selected=\"selected\" ");
        }
        if (isDisabled(choice, index, selected))
        {
            buffer.append("disabled=\"disabled\" ");
        }
        
        String tooltip = ((ChoiceWithTooltipRenderer<T>)renderer).getTooltipValue(choice);
        if (!com.mysql.jdbc.StringUtils.isNullOrEmpty(tooltip)) {
            buffer.append("title=\"" + Strings.escapeMarkup(tooltip) + "\" ");
        }
        
        buffer.append("value=\"");
        buffer.append(Strings.escapeMarkup(renderer.getIdValue(choice, index)));
        buffer.append("\">");

        String display = displayValue;
        if (localizeDisplayValues())
        {
            display = getLocalizer().getString(displayValue, this, displayValue);
        }
        CharSequence escaped = display;
        if (getEscapeModelStrings())
        {
            escaped = escapeOptionHtml(display);
        }
        buffer.append(escaped);
        buffer.append("</option>");
    }
    
}
