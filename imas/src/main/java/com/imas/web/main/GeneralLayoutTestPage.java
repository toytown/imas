package com.imas.web.main;

import java.io.File;
import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormSubmitBehavior;
import org.apache.wicket.datetime.DateConverter;
import org.apache.wicket.datetime.PatternDateConverter;
import org.apache.wicket.datetime.markup.html.form.DateTextField;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.EmptyDataProvider;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.imas.model.Advertisement;
import com.imas.model.CategoryType;
import com.imas.model.HeatingType;
import com.imas.services.interfaces.CommonService;
import com.imas.valueobjects.AdvertisementSearchFilter;
import com.imas.web.components.html.buttons.WepButton;
import com.imas.web.components.html.buttons.WepIndicatingAjaxSubmitButton;
import com.imas.web.components.html.datepicker.DatePickerSettings;
import com.imas.web.components.html.datepicker.IMASDatePicker;
import com.imas.web.components.html.datepicker.IMASDatePickerSettings;
import com.imas.web.components.html.forms.dropdown.ConfigurableDropDownChoice;
import com.imas.web.components.html.forms.multiselect.MultipleSelect;
import com.imas.web.components.html.link.DownloadIconLink;
import com.imas.web.components.html.pagination.ResultTablePagingNavigator;
import com.imas.web.components.html.wizard.WizardComponent;
import com.imas.web.components.html.wizard.WizardModel;
import com.imas.web.components.html.wizard.WizardStep;
import com.imas.web.search.dataprovider.AdvertisementSearchDataProvider;

public class GeneralLayoutTestPage extends IMASWebTemplatePage {

    @SpringBean
    private CommonService commonService;

    private WebMarkupContainer searchResultContainer = new WebMarkupContainer("searchResultContainer");
    private ResultTablePagingNavigator<Advertisement> pagingNavigator;
    
    public GeneralLayoutTestPage() {
        super();

        searchResultContainer.setOutputMarkupId(true);
        searchResultContainer.setOutputMarkupPlaceholderTag(true);
        
        //normal button
        WepButton<String> btnNoAjax = new WepButton<String>("noAjaxBtn", new Model<String>("Non ajax")) {
            private static final long serialVersionUID = 1L;

            @Override
            public void onClick() {
            }

        };

        //search
        AdvertisementSearchFilter filter = new AdvertisementSearchFilter();

        final IDataProvider<Advertisement> emptyDataprovider = new EmptyDataProvider<Advertisement>();
        DataView<Advertisement> resultView = new DataView<Advertisement>("resultView", emptyDataprovider) {
            private static final long serialVersionUID = 1L;

            @Override
            protected void populateItem(Item<Advertisement> item) {
            }

        };
        resultView.setOutputMarkupId(true);
        pagingNavigator = new ResultTablePagingNavigator<Advertisement>("navigator", resultView, emptyDataprovider, 5);
        searchResultContainer.add(pagingNavigator.setVisible(false));        
        searchResultContainer.add(resultView);
        add(searchResultContainer);

        //Form
        CompoundPropertyModel<AdvertisementSearchFilter> searchModel = new CompoundPropertyModel<AdvertisementSearchFilter>(filter);
        final Form<AdvertisementSearchFilter> searchForm = new Form<AdvertisementSearchFilter>("searchForm", searchModel);
        add(searchForm);

        //ajaxbutton
        WepIndicatingAjaxSubmitButton indicatingBtn = new WepIndicatingAjaxSubmitButton("ajaxBtn", new Model<String>("ajaxSubmit Button"), searchForm);



        TextField<String> searchTextField = new TextField<String>("name");
        searchForm.add(searchTextField);

        //indicatingBtn.add(new CustomBusyIndicator());
        indicatingBtn.add(new AjaxFormSubmitBehavior(searchForm, "onClick") {

            private static final long serialVersionUID = 1L;

            @Override
            protected void onSubmit(AjaxRequestTarget target) {
                AdvertisementSearchFilter filter = searchForm.getModelObject();
                filter.setPerformSearch(true);
                AdvertisementSearchDataProvider advertisementDataProvider = new AdvertisementSearchDataProvider(filter);
                DataView<Advertisement> resultView = new DataView<Advertisement>("resultView", advertisementDataProvider, 5) {

                    private static final long serialVersionUID = 1L;

                    @Override
                    protected void populateItem(Item<Advertisement> item) {
                        Advertisement advert = item.getModel().getObject();
                        item.add(new Label("description", advert.getAreaCode()));
                    }

                };
                resultView.modelChanged();
                resultView.setOutputMarkupId(true);
                resultView.setItemsPerPage(5);
                searchResultContainer.addOrReplace(resultView);
                
                pagingNavigator = new ResultTablePagingNavigator<Advertisement>("navigator", resultView, advertisementDataProvider, 5);
                searchResultContainer.addOrReplace(pagingNavigator.setVisible(true).setOutputMarkupId(true));
                
                target.addComponent(searchResultContainer.setVisible(true));
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
        final DateTextField billingPeriodFrom = new DateTextField("billingDate", null, dateConverter);
        searchForm.add(billingPeriodFrom);
        searchForm.add(new IMASDatePicker("billingPeriodFromDatePicker", billingPeriodFrom, dpSettings));

        indicatingBtn.setEmphasized(true);
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

        //Dropdown - Heating Types
        List<HeatingType> heatingTypes = commonService.getHeatingTypes();
        ConfigurableDropDownChoice<HeatingType> heatingTypeDropDown = new ConfigurableDropDownChoice<HeatingType>(
                "heatingTypes", heatingTypes, new ChoiceRenderer<HeatingType>("description", "id"));
        //categoryTypesDropDown.setPrependEmptyOption(true);

        //MultipleSelect
        MultipleSelect<CategoryType> categoryTypes = new MultipleSelect<CategoryType>("categoryTypes", commonService.getCategoryTypes(),
                new ChoiceRenderer<CategoryType>("description", "id"));
        searchForm.add(categoryTypes);


        searchForm.add(heatingTypeDropDown);
        searchForm.setOutputMarkupId(true);
        searchForm.setOutputMarkupPlaceholderTag(true);

        //wizard
        final WizardModel wizardModel = new WizardModel() {

            private static final long serialVersionUID = 1L;

            @Override
            public boolean isSaveAvailable() {
                return true;
            }

            @Override
            public void cancel() {
                super.cancel();
                setResponsePage(GeneralLayoutTestPage.class);
            }

            @Override
            public void finish() {
                super.finish();

                //do some saving of the model here
            }
        };

        wizardModel.add(new WizardStep(new Model<String>("Title- Step1"), new Model<String>("Step1")));
        wizardModel.add(new WizardStep(new Model<String>("Title- Step2"), new Model<String>("Step2")));
        wizardModel.add(new WizardStep(new Model<String>("Title- Step3"), new Model<String>("Step3")));

        //wizardModel.add(new WizardStep(new Model<String>("Title- Step2"), new Model<String>("Step2")));

        wizardModel.setCancelVisible(true);

        TestWizard testWizard = new TestWizard("testWizard", wizardModel);
        // disable Save button
        testWizard.getButtonBar().getSaveButton().setVisibilityAllowed(false);
        testWizard.disableFeedbackPanelErrorMessages();

        add(testWizard);

    }

    private static class TestWizard extends WizardComponent {

        private static final long serialVersionUID = 1L;

        public TestWizard(String id, WizardModel wizardModel) {
            super(id, wizardModel, true);
        }
    }

}
