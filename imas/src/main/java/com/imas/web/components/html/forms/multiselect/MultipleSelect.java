package com.imas.web.components.html.forms.multiselect;

import java.util.Collection;
import java.util.List;

import org.apache.wicket.Page;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.MarkupStream;
import org.apache.wicket.markup.html.form.CheckBoxMultipleChoice;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.markup.html.form.ListMultipleChoice;
import org.apache.wicket.model.IModel;
import org.apache.wicket.util.convert.IConverter;
import org.apache.wicket.util.string.AppendingStringBuffer;
import org.apache.wicket.util.string.Strings;
import org.apache.wicket.version.undo.Change;

/**
 * <p>
 * A multiple select component rendered as a box with scrollable list of checkboxes. 
 * </p>
 * Compared to {@link CheckBoxMultipleChoice} the user can optionally set a css class for both rendered checkboxes and their labels 
 * by overriding {@link #getCheckBoxCssClass()} and {@link #getCheckBoxCssClass()} 
 *
 * @param <T> The model object type
 */
public class MultipleSelect<T> extends ListMultipleChoice<T> {

    private static final long serialVersionUID = 1L;
    
    /** suffix change record. */
    private class SuffixChange extends Change {
        private static final long serialVersionUID = 1L;

        final String prevSuffix;

        SuffixChange(String prevSuffix) {
            this.prevSuffix = prevSuffix;
        }

        /**
         * @see org.apache.wicket.version.undo.Change#undo()
         */
        @Override
        public void undo() {
            setSuffix(prevSuffix);
        }

        /**
         * @see java.lang.Object#toString()
         */
        @Override
        public String toString() {
            return "SuffixChange[component: " + getPath() + ", suffix: "
                    + prevSuffix + "]";
        }
    }

    /**
     * Prefix change record.
     */
    private class PrefixChange extends Change {
        private static final long serialVersionUID = 1L;

        private final String prevPrefix;

        /**
         * Construct.
         * 
         * @param prevSuffix
         */
        PrefixChange(String prevSuffix) {
            prevPrefix = prevSuffix;
        }

        /**
         * @see org.apache.wicket.version.undo.Change#undo()
         */
        @Override
        public void undo() {
            setPrefix(prevPrefix);
        }

        /**
         * @see java.lang.Object#toString()
         */
        @Override
        public String toString() {
            return "PrefixChange[component: " + getPath() + ", prefix: "
                    + prevPrefix + "]";
        }
    }

    private String prefix = "";
    private String suffix = "<br/>\n";
    
    public MultipleSelect(String id) {
        super(id);
    }

    public MultipleSelect(String id, IModel<? extends List<? extends T>> choices, IChoiceRenderer<? super T> renderer) {
        super(id, choices, renderer);
    }

    public MultipleSelect(String id, IModel<? extends List<? extends T>> choices) {
        super(id, choices);
    }

    public MultipleSelect(String id, IModel<Collection<T>> model, IModel<? extends List<? extends T>> choices, IChoiceRenderer<? super T> renderer) {
        super(id, model, choices, renderer);
    }

    public MultipleSelect(String id, IModel<Collection<T>> model, IModel<? extends List<? extends T>> choices) {
        super(id, model, choices);
    }

    public MultipleSelect(String id, IModel<Collection<T>> model, List<? extends T> choices, IChoiceRenderer<? super T> renderer) {
        super(id, model, choices, renderer);
    }

    public MultipleSelect(String id, IModel<Collection<T>> model, List<? extends T> choices) {
        super(id, model, choices);
    }

    public MultipleSelect(String id, List<? extends T> choices, IChoiceRenderer<? super T> renderer) {
        super(id, choices, renderer);
    }

    public MultipleSelect(String id, List<? extends T> choices) {
        super(id, choices);
    }

    /**
     * @return Prefix to use before choice
     */
    public String getPrefix()
    {
        return prefix;
    }

    /**
     * @param prefix
     *            Prefix to use before choice
     * @return this
     */
    public final MultipleSelect<T> setPrefix(final String prefix) {
        // Tell the page that this component's prefix was changed
        final Page page = findPage();
        if (page != null) {
            addStateChange(new PrefixChange(this.prefix));
        }

        this.prefix = prefix;
        return this;
    }

    /**
     * @return Separator to use between radio options
     */
    public String getSuffix() {
        return suffix;
    }

    /**
     * @param suffix
     *            Separator to use between radio options
     * @return this
     */
    public final MultipleSelect<T> setSuffix(final String suffix) {
        // Tell the page that this component's suffix was changed
        final Page page = findPage();
        if (page != null) {
            addStateChange(new SuffixChange(this.suffix));
        }

        this.suffix = suffix;
        return this;
    }

    /**
     * @see org.apache.wicket.markup.html.form.ListMultipleChoice#onComponentTag(org.apache.wicket.markup.ComponentTag)
     */
    @Override
    protected void onComponentTag(ComponentTag tag) {
        super.onComponentTag(tag);
        // No longer applicable, breaks XHTML validation.
        tag.remove("multiple");
        tag.remove("size");
        tag.remove("disabled");
        tag.remove("name");
    }

    /**
     * @see org.apache.wicket.Component#onComponentTagBody(org.apache.wicket.markup.MarkupStream,
     *      org.apache.wicket.markup.ComponentTag)
     */
    @Override
    protected final void onComponentTagBody(final MarkupStream markupStream, final ComponentTag openTag) {
        // Iterate through choices
        final List<? extends T> choices = getChoices();

        // Buffer to hold generated body
        final AppendingStringBuffer buffer = new AppendingStringBuffer(70 * (choices.size() + 1));

        // Value of this choice
        final String selected = getValue();

        // Loop through choices
        for (int index = 0; index < choices.size(); index++) {
            // Get next choice
            final T choice = choices.get(index);

            Object displayValue = getChoiceRenderer().getDisplayValue(choice);
            Class<?> objectClass = displayValue == null ? null : displayValue.getClass();
            // Get label for choice
            String label = "";
            if (objectClass != null && objectClass != String.class) {
                IConverter converter = getConverter(objectClass);
                label = converter.convertToString(displayValue, getLocale());
            } else if (displayValue != null) {
                label = displayValue.toString();
            }

            // If there is a display value for the choice, then we know that the
            // choice is automatic in some way. If label is /null/ then we know
            // that the choice is a manually created checkbox tag at some random
            // location in the page markup!
            if (label != null) {
                // Append option suffix
                buffer.append(getPrefix());

                String id = getChoiceRenderer().getIdValue(choice, index);
                final String idAttr = getMarkupId() + "-" + getInputName() + "_" + id;

                // Add checkbox element
                buffer.append("<input name=\"").append(getInputName()).append("\"")
                      .append(" type=\"checkbox\"");
                
                if(!Strings.isEmpty(getCheckBoxCssClass())) {
                      buffer.append(" class=\"").append(getCheckBoxCssClass()).append("\"");
                }
                
                buffer.append((isSelected(choice, index, selected) ? " checked=\"checked\"" : ""))
                      .append((isEnabledInHierarchy() && !isDisabled(choice, index, selected) ? "" : " disabled=\"disabled\""))
                      .append(" value=\"").append(id).append("\" id=\"").append(idAttr)
                      .append("\"/>");

                // Add label for checkbox
                String display = label;
                if (localizeDisplayValues()) {
                    display = getLocalizer().getString(label, this, label);
                }

                CharSequence escaped = display;
                if (getEscapeModelStrings()) {
                    escaped = Strings.escapeMarkup(display);
                }

                buffer.append("<label for=\"")
                      .append(idAttr).append("\"");
                if(!Strings.isEmpty(getCheckBoxLabelCssClass())) {
                      buffer.append(" class=\"").append(getCheckBoxLabelCssClass()).append("\"");
                }                
                buffer.append(">").append(escaped).append("</label>");

                // Append option suffix
                buffer.append(getSuffix());
            }
        }

        // Replace body
        replaceComponentTagBody(markupStream, openTag, buffer);
    }

    /**
     * Override it if you need to set a specific css class attribute to the generated item checkbox
     */
    protected String getCheckBoxCssClass() {
        return "checkbox";
    }

    /**
     * Override it if you need to set a specific css class attribute to the generated item checkbox label
     */
    protected String getCheckBoxLabelCssClass() {
        return "checkbox";
    }
}
