package com.imas.web.main;

import java.io.File;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormSubmitBehavior;
import org.apache.wicket.datetime.DateConverter;
import org.apache.wicket.datetime.PatternDateConverter;
import org.apache.wicket.datetime.markup.html.form.DateTextField;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.Model;

import com.imas.web.components.html.buttons.WepButton;
import com.imas.web.components.html.buttons.WepIndicatingAjaxSubmitButton;
import com.imas.web.components.html.datepicker.DatePickerSettings;
import com.imas.web.components.html.datepicker.IMASDatePicker;
import com.imas.web.components.html.datepicker.IMASDatePickerSettings;
import com.imas.web.components.html.link.DownloadIconLink;

public class GeneralLayoutTest extends IMASWebTemplatePage {

    public GeneralLayoutTest() {
        super();
        
        //normal button
        WepButton<String> btnNoAjax = new WepButton<String>("noAjaxBtn", new Model<String>("Non ajax")) {
            private static final long serialVersionUID = 1L;

            @Override
            public void onClick() {
                
                
            }
            
        };
        
        //ajaxbutton
        WepIndicatingAjaxSubmitButton indicatingBtn = new WepIndicatingAjaxSubmitButton("ajaxBtn", new Model<String>("ajaxSubmit Button") ) {

            private static final long serialVersionUID = 1L;
            
        };
        
        //form
        Form<String> searchForm = new Form<String>("searchForm");
        add(searchForm);
        
        
        //indicatingBtn.add(new CustomBusyIndicator());
        indicatingBtn.add(new AjaxFormSubmitBehavior("onClick") {

            private static final long serialVersionUID = 1L;

            @Override
            protected void onSubmit(AjaxRequestTarget target) {
               try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
                
            }
            
            @Override
            protected void onError(AjaxRequestTarget target) {
                // TODO Auto-generated method stub
                
            }
        });
                
        //date picker
        String DATE_PATTERN = "yyyy-MM-dd";
        DateConverter dateConverter = new PatternDateConverter(DATE_PATTERN, true);
        DatePickerSettings dpSettings = IMASDatePickerSettings.getInstance();
        final DateTextField billingPeriodFrom = new DateTextField("billingPeriodFrom", null, dateConverter);
        searchForm.add(billingPeriodFrom);
        searchForm.add(new IMASDatePicker("billingPeriodFromDatePicker", billingPeriodFrom, dpSettings));
        
        searchForm.add(indicatingBtn);
        searchForm.add(btnNoAjax);
        add(searchForm);
        
        //download button
        DownloadIconLink downloadIconLink = new DownloadIconLink("download", new File("test.txt")) {
			
			private static final long serialVersionUID = 1L;

			@Override
			public void doBeforeClick() {
				// TODO Auto-generated method stub
				
			}
		};
		
		searchForm.add(downloadIconLink);
		
		//MultipleSelect
		
    }

}
