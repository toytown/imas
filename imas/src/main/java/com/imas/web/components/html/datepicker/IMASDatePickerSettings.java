package com.imas.web.components.html.datepicker;

import java.util.Locale;

import org.apache.wicket.ResourceReference;

/**
 * Common, application-wide settings for {@link IMASDatePicker}
 */
public class IMASDatePickerSettings extends DatePickerSettings {

    private static final long serialVersionUID = 1L;
    private static final ResourceReference CALENDAR_ICON_RES = new ResourceReference(DatePickerSettings.class, "calendar_icon_wep.gif");
    
    private static IMASDatePickerSettings IMAS_DATE_PICKER = new IMASDatePickerSettings();
    
    private IMASDatePickerSettings() {}
    
    @Override
    public ResourceReference getIcon() {
        return CALENDAR_ICON_RES;
    }
    @Override
    public String getIfFormat(Locale locale) {
        return "%d.%m.%Y";
    }
    
    public static IMASDatePickerSettings getInstance() {
        
        if (IMAS_DATE_PICKER == null) {
            IMAS_DATE_PICKER = new IMASDatePickerSettings();
        }
        
        return IMAS_DATE_PICKER;
    }
}
