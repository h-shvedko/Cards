package com.cards.shvedko;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.hibernate.SessionFactory;

public class MainApp extends Application {

    public static Stage stage;

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
    public void start(Stage primaryStage) throws Exception {
        MainApp.stage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("authentication.fxml"));
        primaryStage.setTitle("Authentication");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.sizeToScene();
        primaryStage.show();
    }
}
