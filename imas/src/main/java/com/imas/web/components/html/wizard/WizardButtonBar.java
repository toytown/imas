package com.imas.web.components.html.wizard;

import org.apache.wicket.extensions.wizard.IDefaultButtonProvider;
import org.apache.wicket.extensions.wizard.IWizardModel;
import org.apache.wicket.extensions.wizard.Wizard;
import org.apache.wicket.markup.html.form.IFormSubmittingComponent;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.ResourceModel;

import com.imas.web.components.html.wizard.buttons.BackButton;
import com.imas.web.components.html.wizard.buttons.CancelButton;
import com.imas.web.components.html.wizard.buttons.ContinueButton;
import com.imas.web.components.html.wizard.buttons.FinishButton;
import com.imas.web.components.html.wizard.buttons.SaveButton;
import com.imas.web.components.html.wizard.buttons.WizardButton;

public class WizardButtonBar extends Panel implements IDefaultButtonProvider {
    private static final long serialVersionUID = 1L;
    private SaveButton saveButton;
    private CancelButton cancelButton;
    private BackButton backButton;
    private ContinueButton continueButton;
    private FinishButton finishButton;
   
    public WizardButtonBar(String id, Wizard wizard) {
        super(id);
        add(saveButton = new SaveButton(WizardButton.BUTTON_SAVE, new ResourceModel("btn.save", "Save"), wizard));
        add(cancelButton = new CancelButton(WizardButton.BUTTON_CANCEL, new ResourceModel("btn.cancel", "Cancel"), wizard));
        add(backButton = new BackButton(WizardButton.BUTTON_BACK, new ResourceModel("btn.back", "Back"), wizard));
        add(continueButton = new ContinueButton(WizardButton.BUTTON_CONTINUE, new ResourceModel("btn.continue", "Continue"), wizard));
        add(finishButton = new FinishButton(WizardButton.BUTTON_FINISH, new ResourceModel("btn.finish", "Finish"), wizard));
    }
    
    protected WizardButtonBar(String id) {
        super(id);
    }

    /**
     * @see org.apache.wicket.extensions.wizard.IDefaultButtonProvider#getDefaultButton(org.apache.wicket.extensions.wizard.IWizardModel)
     */
    public IFormSubmittingComponent getDefaultButton(IWizardModel model) {
        if (model.isNextAvailable()) {
            return (WizardButton) get(WizardButton.BUTTON_CONTINUE);
        } 
        return (WizardButton)get(WizardButton.BUTTON_FINISH);
    }
    
    @Override
    protected void onBeforeRender() {
        if (finishButton != null) {
            finishButton.setVisible(finishButton.isEnabled());
            continueButton.setVisible(!finishButton.isVisible());
            saveButton.setVisible(!finishButton.isVisible());
        }
        super.onBeforeRender();
    }

    public SaveButton getSaveButton() {
        return saveButton;
    }

    public void setSaveButton(SaveButton saveButton) {
        this.saveButton = saveButton;
    }

    public CancelButton getCancelButton() {
        return cancelButton;
    }

    public void setCancelButton(CancelButton cancelButton) {
        this.cancelButton = cancelButton;
    }

    public BackButton getBackButton() {
        return backButton;
    }

    public void setBackButton(BackButton backButton) {
        this.backButton = backButton;
    }

    public ContinueButton getContinueButton() {
        return continueButton;
    }

    public void setContinueButton(ContinueButton continueButton) {
        this.continueButton = continueButton;
    }

    public void setFinishButton(FinishButton finishButton) {
        this.finishButton = finishButton;
    }

    public FinishButton getFinishButton() {
        return finishButton;
    }
}
