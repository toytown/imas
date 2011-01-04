package com.imas.web.components.html.palette;

import java.util.Collection;
import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.IClusterable;
import org.apache.wicket.ResourceReference;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormSubmitBehavior;
import org.apache.wicket.markup.html.IHeaderContributor;
import org.apache.wicket.markup.html.IHeaderResponse;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.markup.html.form.ListMultipleChoice;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.model.StringResourceModel;
import org.apache.wicket.util.string.Strings;

import com.imas.web.components.html.buttons.WepAjaxSubmitButton;
import com.imas.web.components.html.buttons.WepIndicatingAjaxSubmitButton;
import com.imas.web.components.html.forms.dropdown.ConfigurableDropDownChoice;
import com.imas.web.components.html.forms.validation.ValidationErrorLabel;

/**
 * Palette component.
 * @param <T> type of searched object in the palette, i.e. BusinessCase
 * @param <O> type of the search option in the "Search by" combobox
 */
public class SearchablePalette<T, O> extends Panel implements IHeaderContributor {

    private static final long serialVersionUID = 1L;

    /** reference to the palette's css resource */
    private static final ResourceReference CSS = new ResourceReference(SearchablePalette.class, "palette.css");
    private static final String BUTTON_STYLE = "width: 7px;";
    
    private ListMultipleChoice<T> selectedOptionsListChoice;
    private IModel<List<T>> selectedOptionsModel;
    private IModel<List<T>> availableOptionsModel;
    private Form<SearchablePaletteModel<T,O>> paletteForm;
    
    /**
     * @param id id of component
     * @param selectedOptionsModel model storing the selected options (right pane)
     * @param availableOptionsModel model providing the available options (left pane)
     * @param choiceRenderer choice renderer
     * @param maxRows number of shown rows in a pane
     * @param searchOptionsList list of search options for "search by" combobox
     * @param searchOptionsRenderer options renderer for search options for "search by" combobox
     * @param searchCallback implementation of {@link SearchCallback}
     * @param availableOptionsHeader header for the available options pane
     * @param selectedOptionsHeader header for the selected options pane
     */
    @SuppressWarnings("unchecked")
    public SearchablePalette(String id, final IModel<List<T>> selectedOptionsModel, final IModel<List<T>> availableOptionsModel,
            IChoiceRenderer<T> choiceRenderer, int maxRows, List<O> searchOptionsList, IChoiceRenderer<O> searchOptionsRenderer, 
            final SearchCallback<T, O> searchCallback, String availableOptionsHeader, String selectedOptionsHeader) {
        
        super(id);
        
        this.selectedOptionsModel = selectedOptionsModel;
        this.availableOptionsModel = availableOptionsModel;
        
        final IModel<SearchablePaletteModel<T,O>> formModel = new CompoundPropertyModel<SearchablePaletteModel<T,O>>(new SearchablePaletteModel<T,O>());
        paletteForm = new Form<SearchablePaletteModel<T,O>>("paletteForm", formModel);
        
        paletteForm.add(new Label("availableOptionsHeader", availableOptionsHeader));
        
        // available options choice
        final ListMultipleChoice<T> availableOptionsListChoice = new ListMultipleChoice<T>("availableOptions", availableOptionsModel, choiceRenderer) {
            private static final long serialVersionUID = 1L;

            @Override
            protected CharSequence getDefaultChoice(Object selected) {
                return "";
            }
        };
        availableOptionsListChoice.setMaxRows(maxRows);
        availableOptionsListChoice.setMarkupId("palette_availableOptions");
        paletteForm.add(availableOptionsListChoice.setOutputMarkupId(true));
        
        paletteForm.add(new Label("selectedOptionsHeader", selectedOptionsHeader));
        
        // selected options choice
        selectedOptionsListChoice = new ListMultipleChoice<T>("selectedOptions", selectedOptionsModel, choiceRenderer) {
            private static final long serialVersionUID = 1L;

            @Override
            protected CharSequence getDefaultChoice(Object selected) {
                return "";
            }
        };
        selectedOptionsListChoice.setMaxRows(maxRows);
        selectedOptionsListChoice.setMarkupId("palette_selectedOptions");
        paletteForm.add(selectedOptionsListChoice.setOutputMarkupId(true));
        final ValidationErrorLabel<T> selectedOptionsValidationLabel = new ValidationErrorLabel<T>("selectedOptions.error", (FormComponent<T>)selectedOptionsListChoice);
        paletteForm.add(selectedOptionsValidationLabel.setMarkupId("selectedOptions.error").setOutputMarkupId(true));
        
        // add button
        paletteForm.add(new WepAjaxSubmitButton("addButton", new ResourceModel("lbl_arrowRight", ">"), paletteForm) {
            private static final long serialVersionUID = 1L;

            @Override
            protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
                Collection<T> selected = availableOptionsListChoice.getModelObject();
                if (selected == null || selected.isEmpty()) {
                    return;
                }
                selectedOptionsModel.getObject().addAll(selected);
                availableOptionsModel.getObject().removeAll(selected);
                
                target.addComponent(selectedOptionsListChoice);
                target.addComponent(selectedOptionsValidationLabel);
                target.addComponent(availableOptionsListChoice);
            }
            @Override
            protected String getAdditionalButtonStyle() {
                return BUTTON_STYLE;
            }
        }.setMarkupId("palette_addButton"));
        
        // add all button
        paletteForm.add(new WepAjaxSubmitButton("addAllButton", new ResourceModel("lbl_dblArrowRight", ">>"), paletteForm) {
            private static final long serialVersionUID = 1L;

            @Override
            public void onSubmit(AjaxRequestTarget target, Form<?> form) {
                if (availableOptionsModel.getObject().isEmpty()) {
                    return;
                }
                selectedOptionsModel.getObject().addAll(availableOptionsModel.getObject());
                availableOptionsModel.getObject().clear();
                
                target.addComponent(selectedOptionsListChoice);
                target.addComponent(selectedOptionsValidationLabel);
                target.addComponent(availableOptionsListChoice);
            }
            @Override
            protected String getAdditionalButtonStyle() {
                return BUTTON_STYLE;
            }
        });
        
        // remove button
        paletteForm.add(new WepAjaxSubmitButton("removeButton", new ResourceModel("lbl_arrowLeft", "<"), paletteForm) {
            private static final long serialVersionUID = 1L;

            @Override
            public void onSubmit(AjaxRequestTarget target, Form<?> form) {
                Collection<T> selected = selectedOptionsListChoice.getModelObject();
                if (selected == null || selected.isEmpty()) {
                    return;
                }
                selectedOptionsModel.getObject().removeAll(selected);
                availableOptionsModel.getObject().addAll(selected);
                
                target.addComponent(selectedOptionsListChoice);
                target.addComponent(availableOptionsListChoice);
            }
            @Override
            protected String getAdditionalButtonStyle() {
                return BUTTON_STYLE;
            }
        }.setMarkupId("palette_removeButton"));
        
        // remove all button
        paletteForm.add(new WepAjaxSubmitButton("removeAllButton", new ResourceModel("lbl_dblArrowLeft", "<<"), paletteForm) {
            private static final long serialVersionUID = 1L;

            @Override
            public void onSubmit(AjaxRequestTarget target, Form<?> form) {
                availableOptionsModel.getObject().addAll(selectedOptionsModel.getObject());
                selectedOptionsModel.getObject().clear();
                
                target.addComponent(selectedOptionsListChoice);
                target.addComponent(availableOptionsListChoice);
            }

            @Override
            protected String getAdditionalButtonStyle() {
                return BUTTON_STYLE;
            }
        });
        
        add(paletteForm);
        
        Form<SearchablePaletteModel<T,O>> searchForm = new Form<SearchablePaletteModel<T,O>>("searchForm", formModel);
        
        final TextField<String> searchString = new TextField<String>("searchString");
        searchForm.add(searchString.setLabel(new ResourceModel("lbl_searchStr")));
        final ValidationErrorLabel<String> validationErrorLabel = new ValidationErrorLabel<String>("searchString.error", searchString);
        searchForm.add(validationErrorLabel.setOutputMarkupId(true));
        
        WepIndicatingAjaxSubmitButton bcSearchBtn = new WepIndicatingAjaxSubmitButton("searchButton", new ResourceModel("btn.search", "Search"), searchForm);
        bcSearchBtn.add(new AjaxFormSubmitBehavior(searchForm,  "onclick") {

            private static final long serialVersionUID = 1L;

            @Override
            protected void onSubmit(AjaxRequestTarget target) {
                if (Strings.isEmpty(searchString.getInput())) {
                    searchString.error(new StringResourceModel("msg_searchStrRequired", SearchablePalette.this, null).getObject());
                    return;
                }
                
                SearchCallbackResult<T> callbackResult = searchCallback.search(formModel.getObject().getSearchString(), formModel.getObject().getSearchOption()); 
                List<T> foundOptions = callbackResult.getResultList();
                // filter out the options, that are already in selected list
                foundOptions.removeAll(selectedOptionsModel.getObject());
                availableOptionsModel.setObject(foundOptions);
                
                if (callbackResult.hasBeenLimited()) {
                    Object[] msgParams = {callbackResult.getTotalNumber(), callbackResult.getResultList().size()};
                    searchString.error(new StringResourceModel("msg_searchResultLimited", SearchablePalette.this, null, msgParams).getObject());
                }
                
                target.addComponent(availableOptionsListChoice);
                //update the validation error label
                target.addComponent(validationErrorLabel);
            }

            @Override
            protected void onError(AjaxRequestTarget target) {
                target.addComponent(validationErrorLabel);
                target.appendJavascript("showErrors();highlightErrors();");
            }
            
        });
        
        bcSearchBtn.setMarkupId("searchButton");        
        searchForm.add(bcSearchBtn);
        searchForm.add(new ConfigurableDropDownChoice<O>("searchOption", searchOptionsList, searchOptionsRenderer));
        
        add(searchForm);
    }
    
    /**
     * resets all the fields of palette (search field, available and selected options)
     */
    public void reset() {
        availableOptionsModel.getObject().clear();
        selectedOptionsModel.getObject().clear();
        paletteForm.visitChildren(FormComponent.class, new IVisitor<Component>() {
            @Override
            public Object component(Component component) {
                ((FormComponent<?>) component).clearInput();
                return Component.IVisitor.CONTINUE_TRAVERSAL;
            }
        });
        paletteForm.getModelObject().setSearchString("");
    }
    
    /**
     * @return a ListChoice component representing selected options
     */
    public ListMultipleChoice<T> getSelectedOptionsListChoice() {
        return selectedOptionsListChoice;
    }
    
    /**
     * Renders header contributions
     */
    @Override
    public void renderHead(IHeaderResponse response) {
        response.renderCSSReference(CSS);
    }

    /**
     * Interface to be implemented by users of SearchablePalette to provide the search functionality 
     * @param <T> type of searched object in the palette, i.e. BusinessCase
     * @param <O> type of the search option in the "Search by" combobox
     */
    public interface SearchCallback<T, O> extends IClusterable {
        public SearchCallbackResult<T> search(String searchString, O searchBy);
    }
    
    /**
     * Object encapsulating the result list of {@link SearchCallback} holding additionally also a total number of results 
     * @param <T> type of searched object in the palette, i.e. BusinessCase
     */
    public static class SearchCallbackResult<T> implements IClusterable {
        private static final long serialVersionUID = 1l;
        private List<T> resultList;
        private Long totalNumber;
        private int maxResults;
        
        public SearchCallbackResult(List<T> resultList, Long totalNumber, int maxResults) {
            this.resultList = resultList;
            this.totalNumber = totalNumber;
            this.maxResults = maxResults;
        }
        
        /**
         * Checks, whether the result list has been limited. 
         * Basically if the total number of records is greater than the size of {@link #resultList}
         * @return true, if the result list has been limited
         */
        public boolean hasBeenLimited() {
            return totalNumber.intValue() > maxResults;
        }

        public List<T> getResultList() {
            return resultList;
        }

        public void setResultList(List<T> resultList) {
            this.resultList = resultList;
        }

        public Long getTotalNumber() {
            return totalNumber;
        }

        public void setTotalNumber(Long totalNumber) {
            this.totalNumber = totalNumber;
        }

        public void setMaxResults(int maxResults) {
            this.maxResults = maxResults;
        }

        public int getMaxResults() {
            return maxResults;
        }
    }
    
    class SearchablePaletteModel<E, SO> implements IClusterable {
        private static final long serialVersionUID = 1L;
        private String searchString;
        private SO searchOption;
        private E availableOptions;
        private E selectedOptions;
        
        public SearchablePaletteModel() {
            
        }

        public String getSearchString() {
            return searchString;
        }

        public void setSearchString(String searchString) {
            this.searchString = searchString;
        }

        public SO getSearchOption() {
            return searchOption;
        }

        public void setSearchOption(SO searchOption) {
            this.searchOption = searchOption;
        }

        public E getAvailableOptions() {
            return availableOptions;
        }

        public void setAvailableOptions(E availableOptions) {
            this.availableOptions = availableOptions;
        }

        public E getSelectedOptions() {
            return selectedOptions;
        }

        public void setSelectedOptions(E selectedOptions) {
            this.selectedOptions = selectedOptions;
        }
        
    }
}
