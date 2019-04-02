package com.cards.shvedko.Controller.Modals;

import com.cards.shvedko.Controller.A_Controller;
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
    public Button remove;
    @FXML
    public Button cancelRemoving;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);
        String textForLabel = globalDeckData.getName();
        labelWithQuestion.setText("Вы действительно хотите удалить колоду " + textForLabel);
    }

    public void handleCloseButton(ActionEvent actionEvent) {
        answer = false;
        closeWindow(remove);
    }

    public void handleAgreeButton(ActionEvent actionEvent) {
        answer = true;
        closeWindow(remove);
    }

    @Override
    protected void handleCancelButtonAction() {

    }

    @Override
    protected void handleSubmitButtonAction() {

    }
}
