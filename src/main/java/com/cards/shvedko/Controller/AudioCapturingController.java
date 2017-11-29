package com.cards.shvedko.Controller;

import com.cards.shvedko.Helpers.AudioCapturing;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AudioCapturingController extends A_Controller {

    @FXML
    public Button start;
    @FXML
    public Button stop;

    private static AudioCapturing audio;

    public void initialize(URL location, ResourceBundle resources) {
        stop.setDisable(true);
        start.setDisable(false);
        cancelButton.setDisable(false);
    }

    @Override
    protected void handleCancelButtonAction() {

    }

    @Override
    protected void handleSubmitButtonAction() {

    }

    public void handleCreateButton(ActionEvent actionEvent) {
        String nameOfAudio = audio.name;
        stop.setDisable(true);
        start.setDisable(false);
        cancelButton.setDisable(false);
        audio.stopCapture();
        A_Controller.globalAudioFileData = nameOfAudio;
        A_Controller.globalCardData.put((String) A_Controller.globalUserData, (String) A_Controller.globalAudioFileData);
        A_Controller.globalUserData = "";
    }

    public void handleStartButton(ActionEvent actionEvent) {
        stop.setDisable(false);
        start.setDisable(true);
        cancelButton.setDisable(true);
        audio = new AudioCapturing();
        audio.captureAudio();
    }
}
