package com.cards.shvedko.Controller.Modals;

import com.cards.shvedko.Controller.A_Controller;
import com.cards.shvedko.Model.Decks;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class ModalRemoveDeckQuestionController extends A_Controller {
    @FXML
    public Label labelWithQuestion;
    @FXML
    protected Button agree;
    @FXML
    protected Button close;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);

        labelWithQuestion.setText("Вы действительно хотите удалить колоду " + ((Decks)globalUserData).getName());
    }

    public void handleCloseButton(ActionEvent actionEvent) {
        answer = false;
        closeWindow(close);
    }

    public void handleAgreeButton(ActionEvent actionEvent) {
        answer = true;
        closeWindow(agree);
    }

    @Override
    protected void handleCancelButtonAction() {

    }

    @Override
    protected void handleSubmitButtonAction() {

    }
}
