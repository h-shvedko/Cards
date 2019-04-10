package com.cards.shvedko.Controller.ManageCards.Add;

import com.cards.shvedko.Controller.A_Controller;
import com.cards.shvedko.Model.A_Models;
import com.cards.shvedko.ModelDAO.CardsDAO;
import javafx.event.ActionEvent;

import javax.persistence.PersistenceException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddCardController extends A_Controller {

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);
    }

    @Override
    public void handleCancelButton(ActionEvent actionEvent) {
        this.goToPage("ManageCards/Add/addCardDialog.fxml", A_Controller.CHOOSE_TYPE_OF_CARD_PAGE_TITLE, "");
    }

    @Override
    public CardsDAO handleAddButton(ActionEvent actionEvent) {

        if (compareForeignValue() && compareNativeValue()) {
            showQuiestion(actionEvent, "Do really want to save this card? You haven't changed anything in native and foreign words!");
        }

        CardsDAO cardsDAO = null;
        if (answer) {
            cardsDAO = super.handleAddButton(actionEvent);

            if (cardsDAO.validate(cardsDAO.cards)) {
                try {
                    if (!cardsDAO.save()) {
                        throw new Exception(cardsDAO.errorMsg);
                    }
                    showSuccess(actionEvent);
                } catch (PersistenceException | SQLException pEx){
                    checkAndResolvePrimaryKeyIssue(actionEvent, pEx);
                } catch (Exception ex) {
                    crashAppeared(ex.getMessage(), actionEvent);
                }
            } else {
                showErrors(cardsDAO);
            }
        }

        return cardsDAO;
    }

    @Override
    protected void handleCancelButtonAction() {

    }

    @Override
    protected void handleSubmitButtonAction() {

    }
}