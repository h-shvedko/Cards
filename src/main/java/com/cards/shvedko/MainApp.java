package com.cards.shvedko;

import com.cards.shvedko.Controller.A_Controller;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

import java.io.IOException;

public class MainApp extends Application {

    public static Stage stage;

    private Stage splashScreen;

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
        Thread.sleep(10000);
        Platform.runLater(new Runnable(){
            @Override
            public void run() {
                splashScreen.close();
            }
        });
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
        primaryStage.setResizable(false);
        primaryStage.sizeToScene();
        primaryStage.show();
    }
}
