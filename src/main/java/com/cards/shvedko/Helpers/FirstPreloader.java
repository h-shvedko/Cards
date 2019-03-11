package com.cards.shvedko.Helpers;

import javafx.application.Platform;
import javafx.application.Preloader;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class FirstPreloader extends Preloader {
    private ProgressBar bar;
    private Stage stage;
    private Stage splashScreen;

    private Scene createPreloaderScene() {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("Settings/splash.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new Scene(root, Color.TRANSPARENT);
    }

    public void start(Stage stage) throws Exception {
        stage.setTitle("Splash");
        stage.setScene(createPreloaderScene());
        stage.show();
    }

    @Override
    public void handleStateChangeNotification(StateChangeNotification evt) {
        if (evt.getType() == StateChangeNotification.Type.BEFORE_START) {
            stage.hide();
        }
    }

    public void runPreloader(Stage stage) throws Exception {
        this.stage = stage;
        Platform.setImplicitExit(false);
        try {
            stage.setTitle("Splash");
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Settings/splash.fxml"));
            Scene scene = new Scene(root, Color.TRANSPARENT);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}