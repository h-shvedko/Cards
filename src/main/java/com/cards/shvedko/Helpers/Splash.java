package com.cards.shvedko.Helpers;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Splash {

    private Stage splashScreen;

    public Splash() throws Exception {
        Platform.runLater(new Runnable(){
            @Override
            public void run() {
                try {
                    splashScreen = new Stage(StageStyle.TRANSPARENT);
                    splashScreen.setTitle("Splash");
                    Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Settings/splash.fxml"));
                    Scene scene = new Scene(root, Color.TRANSPARENT);
                    splashScreen.setScene(scene);
                    splashScreen.show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        Thread.sleep(5000);
        Platform.runLater(new Runnable(){
            @Override
            public void run() {
                splashScreen.close();
            }
        });
    }

    public void init() throws Exception {

    }
}