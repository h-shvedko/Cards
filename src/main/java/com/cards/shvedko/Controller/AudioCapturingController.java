package com.cards.shvedko.Controller;

import com.cards.shvedko.Helpers.AudioCapturing;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class AudioCapturingController extends A_Controller {

    @FXML
    public Button start;
    @FXML
    public Button stop;

    private static AudioCapturing audio;

    public void initialize(URL location, ResourceBundle resources) {

    }

    @Override
    protected void handleCancelButtonAction() {

    }

    @Override
    protected void handleSubmitButtonAction() {

    }

    public void handleCreateButton(ActionEvent actionEvent) {
        String nameOfAudio = audio.name;
        audio.stopCapture();

        //TODO: save into DB
    }

    public void handleStartButton(ActionEvent actionEvent) {
        audio = new AudioCapturing();
        audio.captureAudio();
    }
}
