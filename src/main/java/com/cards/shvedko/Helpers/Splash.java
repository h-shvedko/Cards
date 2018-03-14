package com.cards.shvedko.Helpers;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Splash {

    private Stage splashScreen;

    public void init() throws Exception {
        Platform.runLater(new Runnable(){
            @Override
            public void run() {
                try {
                    splashScreen = new Stage(StageStyle.TRANSPARENT);
                    splashScreen.setTitle("Splash");
                    Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("splash.fxml"));
                    Scene scene = new Scene(root, Color.TRANSPARENT);
                    splashScreen.setScene(scene);
                    splashScreen.show();
                } catch (IOException e) {
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
}