package com.cards.shvedko;

import com.cards.shvedko.Controller.A_Controller;
import com.cards.shvedko.Helpers.FirstPreloader;
import com.cards.shvedko.Helpers.Splash;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.util.Objects;

public class MainApp extends Application {

    public static Stage stage;

    private Stage splashScreen;

    private ProgressBar loading;

    private static FirstPreloader firstPreloader;

    private static MainApp instance;

    public MainApp() {
        instance = this;
        firstPreloader = new FirstPreloader();
    }

    public static MainApp getInstance() {
        return instance;
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void init() throws Exception {
        new Splash();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        MainApp.stage = primaryStage;
//        firstPreloader.runPreloader(MainApp.stage);
//        Thread.sleep(5000);
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
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Settings/authentication.fxml"));
        primaryStage.setTitle("Authentication");
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent e) {
                Platform.exit();
                System.exit(0);
            }
        });
        primaryStage.sizeToScene();
        primaryStage.setResizable(false);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getClassLoader().getResource("css/style.css")).toExternalForm());
        primaryStage.show();
    }
}
