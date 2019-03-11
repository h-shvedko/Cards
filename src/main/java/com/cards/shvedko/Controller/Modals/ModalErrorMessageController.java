package com.cards.shvedko.Controller.Modals;

import com.cards.shvedko.Controller.A_Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

import static javafx.scene.control.OverrunStyle.WORD_ELLIPSIS;

public class ModalErrorMessageController extends A_Controller {
    @FXML
    private Button close;
    @FXML
    private Label messageField;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);
        String message = (String) globalUserData;
        messageField.setTextOverrun(WORD_ELLIPSIS);
        messageField.setText(message);
    }

    public void handleCloseButton(ActionEvent actionEvent) {
        closeWindow(close);
        this.goToPage("mainPage.fxml", A_Controller.MAIN_PAGE_TITLE, "");
    }

    @Override
    protected void handleCancelButtonAction() {

    }

    @Override
    protected void handleSubmitButtonAction() {

    }
}
