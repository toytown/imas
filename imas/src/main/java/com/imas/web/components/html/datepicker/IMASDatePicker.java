package com.imas.web.components.html.datepicker;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Component;
import org.apache.wicket.ResourceReference;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.MarkupStream;
import org.apache.wicket.markup.html.WebComponent;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.html.resources.JavaScriptReference;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.convert.IConverter;
import org.apache.wicket.util.convert.converters.DateConverter;
import org.apache.wicket.util.string.AppendingStringBuffer;


/**
 * Datepicker component.
 * <p>
 * Link your datepicker to a textfield like this:
 * </p>
 * <p>
 * (Java)
 * 
 * <pre>
 * TextField dateField = new TextField(&quot;dateField&quot;, Date.class);
 * add(dateField);
 * add(new DatePicker(&quot;dateFieldPicker&quot;, dateField));
 * </pre>
 * 
 * (html)
 * 
 * <pre>
 *         &lt;input type=&quot;text&quot; wicket:id=&quot;dateField&quot; size=&quot;10&quot; /&gt;
 *         &lt;span wicket:id=&quot;dateFieldPicker&quot; /&gt;
 * </pre>
 * 
 * </p>
 * <p>
 * Your target doesn't have to be a text field however, attach to any tag that
 * is supported by JSCalendar.
 * </p>
 * <p>
 * Customize the looks, localization etc of the datepicker by providing a custom
 * {@link wicket.extensions.markup.html.datepicker.DatePickerSettings} object.
 * </p>
 * <p>
 * This component is based on Dynarch's JSCalendar component, which can be found
 * at <a href="http://www.dynarch.com/">the Dynarch site</a>.
 * </p>
 * 
 * @see wicket.extensions.markup.html.datepicker.DatePickerSettings
 * @author Eelco Hillenius
 * @author Mihai Bazon (creator of the JSCalendar component)
 */
public class IMASDatePicker extends Panel
{
    private static final long serialVersionUID = 1L;

    /**
     * Attribute modifier that modifies/ adds an attribute with value of the
     * given component's path.
     */
    private final static class PathAttributeModifier extends AttributeModifier
    {
        private static final long serialVersionUID = 1L;

        /**
         * Construct.
         * 
         * @param attribute
         *            the attribute to modify
         * @param pathProvider
         *            the component that provides the path
         */
        public PathAttributeModifier(String attribute, final Component pathProvider)
        {
            super(attribute, true, new Model<String>()
            {
                private static final long serialVersionUID = 1L;

                public String getObject()
                {
                    if (pathProvider.getOutputMarkupId())
                    {
                        return pathProvider.getMarkupId();
                    }
                    // do this lazily, so we know for sure we have the whole
                    // path including the page etc.
                    return pathProvider.getPath();
                }
            });
        }
    }

    /**
     * Button that triggers the popup.
     */
    private final static class TriggerButton extends WebMarkupContainer
    {
        private static final long serialVersionUID = 1L;

        /**
         * Construct.
         * 
         * @param id
         *            component id
         * @param resourceReference
         *            button icon reference
         */
        public TriggerButton(final String id, final ResourceReference resourceReference)
        {
            super(id);
            add(new PathAttributeModifier("id", this));
            IModel<String> srcReplacement = new Model<String>()
            {
                private static final long serialVersionUID = 1L;

                public String getObject()
                {
                    return urlFor(resourceReference).toString();
                };
            };
            add(new AttributeModifier("src", true, srcReplacement));
        }
    }

    /**
     * Outputs the Javascript initialization code.
     */
    private final class InitScript extends WebComponent
    {
        private static final long serialVersionUID = 1L;

        /**
         * Construct.
         * 
         * @param id
         *            component id
         */
        public InitScript(String id)
        {
            super(id);
        }

        /**
         * @see wicket.Component#onComponentTagBody(wicket.markup.MarkupStream,
         *      wicket.markup.ComponentTag)
         */
        protected void onComponentTagBody(final MarkupStream markupStream,
                final ComponentTag openTag)
        {
            replaceComponentTagBody(markupStream, openTag, getInitScript());
        }
    }

    /** the receiving component. */
    private final Component target;

    /** the button that triggers the popup. */
    private TriggerButton triggerButton;

    /** settings for the javascript datepicker component. */
    private final DatePickerSettings settings;

    private DateConverter dateConverter;

    /**
     * Construct with a default button and style.
     * 
     * @param id
     *            the component id
     * @param target
     *            the receiving component
     */
    public IMASDatePicker(String id, Component target)
    {
        this(id, target, new DatePickerSettings());
    }

    /**
     * Construct with a default button and style.
     * 
     * @param id
     *            the component id
     * @param label
     *            the label for target component.
     * @param target
     *            the receiving component
     */
    public IMASDatePicker(String id, Component label, Component target)
    {
        this(id, label, target, new DatePickerSettings());
    }

    /**
     * Construct.
     * 
     * @param id
     *            the component id
     * @param target
     *            the receiving component
     * @param settings
     *            datepicker properties
     */
    public IMASDatePicker(final String id, final Component target, final DatePickerSettings settings)
    {
        this(id, null, target, settings);
    }

    /**
     * Construct.
     * 
     * @param id
     *            the component id
     * @param label
     *            the label component (may be null)
     * @param target
     *            the receiving component
     * @param settings
     *            datepicker properties
     */
    public IMASDatePicker(final String id, final Component label, final Component target,
            final DatePickerSettings settings)
    {
        super(id);

        if (settings == null)
        {
            throw new IllegalArgumentException(
                    "Settings must be non null when using this constructor");
        }

        this.settings = settings;

        if (target == null)
        {
            throw new IllegalArgumentException("Target must be not null");
        }

        target.add(new PathAttributeModifier("id", target));
        this.target = target;

        if (label != null)
        {
            label.add(new PathAttributeModifier("for", target));
        }
        add(triggerButton = new TriggerButton("trigger", settings.getIcon()));
        add(new InitScript("script"));
        add(new JavaScriptReference("calendarMain", IMASDatePicker.class, "calendar.js"));
        add(new JavaScriptReference("calendarSetup", IMASDatePicker.class, "calendar-setup.js"));
        add(new JavaScriptReference("calendarLanguage", new Model<ResourceReference>()
        {
            private static final long serialVersionUID = 1L;

            public ResourceReference getObject()
            {
                return settings.getLanguage(IMASDatePicker.this.getLocale());
            }
        }));
//        add(new StyleSheetReference("calendarStyle", settings.getStyle()));
    }

    /**
     * Sets the date converter to use for generating the javascript format
     * string. If this is not set or set to null the default DateConverter will
     * be used.
     * 
     * @param dateConverter
     */
    public void setDateConverter(DateConverter dateConverter)
    {
        this.dateConverter = dateConverter;
    }

    /**
     * Gets the initilization javascript.
     * 
     * @return the initilization javascript
     */
    private CharSequence getInitScript()
    {
        String targetId = target.getOutputMarkupId() ? target.getMarkupId() : target.getPath();
        AppendingStringBuffer b = new AppendingStringBuffer("\nCalendar.setup(\n{");
        b.append("\n\t\tinputField : \"").append(targetId).append("\",");
        b.append("\n\t\tbutton : \"").append(triggerButton.getPath()).append("\",");

        String pattern = null;
        if (dateConverter == null)
        {
            IConverter converter = target.getConverter(Date.class);
            if (converter instanceof DateConverter)
            {
                dateConverter = (DateConverter)converter;
            }
            
            if (dateConverter == null) {
                dateConverter = new DateConverter();
            }
        }
        DateFormat df = dateConverter.getDateFormat(target.getLocale());
        if (df instanceof SimpleDateFormat)
        {
            pattern = ((SimpleDateFormat)df).toPattern();
        }
        b.append(settings.toScript(target.getLocale(), pattern));
        int last = b.length() - 1;
        if (',' == b.charAt(last))
        {
            b.deleteCharAt(last);
        }
        b.append("\n});");
        return b;
    }
}
