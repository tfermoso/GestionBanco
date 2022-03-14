package com.example.gestionbanco;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class GestionBanco extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GestionBanco.class.getResource("banco-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        stage.setTitle("Gestión Banco");
        stage.setScene(scene);
        BancoController controller = (BancoController) fxmlLoader.getController();
        controller.stage=stage;
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}