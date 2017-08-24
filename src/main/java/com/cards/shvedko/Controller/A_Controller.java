package com.cards.shvedko.Controller;

import com.cards.shvedko.MainApp;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.util.logging.Level;
import java.util.logging.Logger;

abstract public class A_Controller implements Initializable {

    public static String errorMsg;

    public void goToPage(String fxml) {
        try {
            Parent page = FXMLLoader.load(getClass().getClassLoader().getResource(fxml), null, new JavaFXBuilderFactory());
            Scene scene = MainApp.stage.getScene();
            if (scene == null) {
                scene = new Scene(page);
                MainApp.stage.setScene(scene);
            } else {
                MainApp.stage.getScene().setRoot(page);
            }
            MainApp.stage.sizeToScene();
            MainApp.stage.setResizable(false);
        } catch (Exception ex) {
            Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void closeWindow(Button btn) {
        Stage stage = (Stage) btn.getScene().getWindow();
        stage.close();
    }

    public void crashAppeared(String message){
        MainPageController.errorMsg = message;
        goToPage("mainPage.fxml");
    }
}
