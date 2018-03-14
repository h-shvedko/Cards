package com.cards.shvedko;

import com.cards.shvedko.Controller.A_Controller;
import com.cards.shvedko.Helpers.Splash;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

import java.io.IOException;

public class MainApp extends Application {

    public static Stage stage;

    private Stage splashScreen;

    private ProgressBar loading;

    private static MainApp instance;

    public MainApp() {
        instance = this;
    }

    public static MainApp getInstance() {
        return instance;
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void init() throws Exception {
        Splash splash = new Splash();
        splash.init();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        MainApp.stage = primaryStage;
        MainApp.stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {
                if(A_Controller.stage != null){
                    A_Controller.stage.close();
                    A_Controller.stage = null;
                    MainApp.stage.setOpacity(1);
                } else {
                    MainApp.stage.close();
                }

            }
        });
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("authentication.fxml"));
        primaryStage.setTitle("Authentication");
        primaryStage.setScene(new Scene(root));
        primaryStage.sizeToScene();
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
