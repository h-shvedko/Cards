package com.cards.shvedko;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.h2.engine.User;
import org.hibernate.SessionFactory;

import java.util.logging.Level;
import java.util.logging.Logger;

public class MainApp extends Application {
    private static org.hibernate.service.ServiceRegistry serviceRegistry;
    private static SessionFactory sessionFactory;

    public static Stage stage;
    private User loggedUser;

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


//    @Override public void start(Stage primaryStage) {
//        try {
//            stage = primaryStage;
//            gotoLogin();
//            primaryStage.show();
//        } catch (Exception ex) {
//            Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//
//    public User getLoggedUser() {
//        return loggedUser;
//    }
//
//    public void gotoAddVerb() {
//        try {
//            replaceSceneContent("addCardVerb.fxml");
//        } catch (Exception ex) {
//            Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//
//    private void gotoLogin() {
//        try {
//            replaceSceneContent("authentication.fxml");
//        } catch (Exception ex) {
//            Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//
//    private void replaceSceneContent(String fxml) throws Exception {
//        Parent page = FXMLLoader.load(getClass().getClassLoader().getResource(fxml), null, new JavaFXBuilderFactory());
//        Scene scene = stage.getScene();
//        if (scene == null) {
//            scene = new Scene(page, 600, 400);
////            scene.getStylesheets().add(MainApp.class.getResource("demo.css").toExternalForm());
//            stage.setScene(scene);
//            stage.setResizable(false);
//        } else {
//            stage.getScene().setRoot(page);
//        }
//        stage.sizeToScene();
//    }


//    public void start(Stage primaryStage) throws Exception {
//        BorderPane bp = new BorderPane();
//        bp.setPadding(new Insets(10, 50, 50, 50));
//        Scene scene = new Scene(bp);
//        primaryStage.setScene(scene);
//        primaryStage.setResizable(false);
//        primaryStage.show();
//    }
    //    public boolean userLogging(String userId, String password){
//        if (Authenticator.validate(userId, password)) {
//            loggedUser = User.of(userId);
//            gotoProfile();
//            return true;
//        } else {
//            return false;
//        }
//    }

//    public void userLogout(){
//        loggedUser = null;
//        gotoLogin();
//    }


    //        MainApp.createSessionFactory();
//        Session session = sessionFactory.openSession();
//
//        Transaction t = session.beginTransaction();
//
//        Cards e1 = new Cards();
//        e1.setId(1);
//        e1.setName("test");
//        e1.setValue("bezahlen");
//        e1.setExample("bezahlen");
//        e1.setTypeId(1);
//        e1.setCategoryId(1);
//        e1.setIsVisible(1);
//
//        session.persist(e1);
//
//        t.commit();
//
//        List<Cards> cardList = session.createQuery("from Cards").list();
//
//        for (Cards card : cardList) {
//            System.out.println(card.getValue());
//        }
//
//        session.close();
//        System.out.println("successfully saved");

//
//    public static SessionFactory createSessionFactory() {
//        Configuration configuration = new Configuration();
//        configuration.configure();
//        serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
//                configuration.getProperties()).build();
//        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
//        return sessionFactory;
//    }

//    @Override
//    public void start(Stage primaryStage) throws Exception{
//        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("authentication.fxml"));
//        primaryStage.setTitle("Authentication");
//        primaryStage.setScene(new Scene(root, 600, 400));
//        primaryStage.setResizable(false);
//        primaryStage.show();
//    }
}
