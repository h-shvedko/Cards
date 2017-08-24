package com.cards.shvedko.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ModalQuestionController extends A_Controller {
    @FXML
    protected Button agree;
    @FXML
    protected Button close;

    public void handleCloseButton(ActionEvent actionEvent) {
        answer = false;
        closeWindow(close);
    }

    public void handleAgreeButton(ActionEvent actionEvent) {
        answer = true;
        closeWindow(agree);
    }
}
