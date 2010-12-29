package com.imas.web.main;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.CSSPackageResource;
import org.apache.wicket.markup.html.JavascriptPackageResource;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.Model;
import org.odlabs.wiquery.core.events.Event;
import org.odlabs.wiquery.core.events.MouseEvent;
import org.odlabs.wiquery.core.events.WiQueryEventBehavior;
import org.odlabs.wiquery.core.javascript.JsScope;
import org.odlabs.wiquery.ui.button.ButtonBehavior;
import org.odlabs.wiquery.ui.button.ButtonCheckSet;
import org.odlabs.wiquery.ui.button.ButtonElement;
import org.odlabs.wiquery.ui.button.ButtonIcon;
import org.odlabs.wiquery.ui.button.ButtonRadioSet;
import org.odlabs.wiquery.ui.core.JsScopeUiEvent;
import org.odlabs.wiquery.ui.dialog.Dialog;
import org.odlabs.wiquery.ui.tabs.Tabs;
import org.odlabs.wiquery.ui.themes.ThemeUiHelper;

public class MainPage extends WebPage {


    // Wicket components
    private Tabs tabs;
    
    protected void addHeaderContributors() {
        add(JavascriptPackageResource.getHeaderContribution("js/jquery.uniform.js"));
        add(CSSPackageResource.getHeaderContribution("css/uniform.default.css"));
        add(JavascriptPackageResource.getHeaderContribution("js/main.js"));
    }
    
	public MainPage() {
		super();
		
		addHeaderContributors();
		
		WebMarkupContainer divTitle = new WebMarkupContainer("divTitle");
        ThemeUiHelper.titleComponent(divTitle);
        add(divTitle);

        
		tabs = new Tabs("tabs");
        add(tabs);
        
        // Add some possible actions
		
        final Dialog dialog = new Dialog("dialog");
        add(dialog);
        
        Button button = new Button("open-dialog");
        button.add(new WiQueryEventBehavior(new Event(MouseEvent.DBLCLICK) {
                
                        /**
             * 
             */
            private static final long serialVersionUID = 1L;

                        @Override
                        public JsScope callback() {
                                return JsScope.quickScope(dialog.open().render());
                        }
                
                }));
        add(button);
        
        
        //FORMS
        
        Form<String> formButtons = new Form<String>("formButtons");
        add(formButtons);
        
        
        //Text Box
        TextField<String> textFieldLong = new TextField<String>("textFieldLong");
        formButtons.add(textFieldLong);
        
        // Behavior button
        AjaxLink<String> wicketLink = new AjaxLink<String> ("wicketLink") {
            private static final long serialVersionUID = 1L;

            /**
             * {@inheritDoc}
             * @see org.apache.wicket.ajax.markup.html.AjaxLink#onClick(org.apache.wicket.ajax.AjaxRequestTarget)
             */
            @Override
            public void onClick(AjaxRequestTarget target) {
                target.appendJavascript("alert('Ajax link');");
            }
        };
        ButtonBehavior buttonBehavior = new ButtonBehavior();
        buttonBehavior.setLabel("An ajax link with wicket");
        wicketLink.add(buttonBehavior);
        formButtons.add(wicketLink);
        
        // Classic buttons
        
        formButtons.add(new Button("firstButton").add(new ButtonBehavior().setLabel("A button element")));
        formButtons.add(new Button("secondButton").add(new ButtonBehavior()));
        formButtons.add(new Button("thirdButton").add(new ButtonBehavior().setLabel("An anchor")));
        
        WebMarkupContainer fourthButton = new WebMarkupContainer("fourthButton");
        ButtonBehavior buttonBehavior4 = new ButtonBehavior();
        buttonBehavior4.setLabel("A label set via Wicket");
        buttonBehavior4.setIcons(new ButtonIcon("ui-icon-gear", "ui-icon-triangle-1-s"));
        fourthButton.add(buttonBehavior4);
        fourthButton.add(new WiQueryEventBehavior(new Event(MouseEvent.CLICK) {
            private static final long serialVersionUID = 1L;

            /**
             * {@inheritDoc}
             * @see org.odlabs.wiquery.core.events.Event#callback()
             */
            @Override
            public JsScope callback() {
                return JsScopeUiEvent.quickScope("alert('you click me');");
            }
        }));
        formButtons.add(fourthButton);
        
        // Ajax button
        AjaxButton ajaxButton = new AjaxButton("fifthButton", formButtons) {
            private static final long serialVersionUID = 1L;

            /**
             * {@inheritDoc}
             * @see org.apache.wicket.ajax.markup.html.form.AjaxButton#onSubmit(org.apache.wicket.ajax.AjaxRequestTarget, org.apache.wicket.markup.html.form.Form)
             */
            @Override
            protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
                target.appendJavascript("alert('Ajax call !!');");
            }
        };
        ajaxButton.add(new ButtonBehavior());
        formButtons.add(ajaxButton);
        
        // Radio & check set
        List<ButtonElement<String>> elements = new ArrayList<ButtonElement<String>>();
        elements.add(new ButtonElement<String>(new Model<String>("button1"), new Model<String>("Button One")));
        elements.add(new ButtonElement<String>(new Model<String>("button2"), new Model<String>("Button Two")));
        elements.add(new ButtonElement<String>(new Model<String>("button3"), new Model<String>("Button Three")));
        
        formButtons.add(new ButtonRadioSet<String>("radioSet", elements));
        formButtons.add(new ButtonCheckSet<String>("checkSet", elements));        
	}
	

}
