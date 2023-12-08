package com.example.appbanco.controladores;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AppBanco extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/appbanco/FXML/Login.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        com.example.appbanco.Controladores.ControladorLogin controladorLogin = loader.getController();
        controladorLogin.setStage(primaryStage);
        primaryStage.show();
    }
}