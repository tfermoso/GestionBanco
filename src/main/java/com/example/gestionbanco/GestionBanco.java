package com.example.gestionbanco;

import com.example.gestionbanco.models.Banco;
import com.example.gestionbanco.models.Persona;
import com.example.gestionbanco.persistencia.Fichero;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
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
        Banco banco;
        /*
        banco.crearCuenta("Juan",200);
        banco.ingresar("Juan",400);
        banco.ingresar("Juan",500);
         */
        Fichero fichero=new Fichero();
        //fichero.guardarDatos(banco,"datos-banco.json");
        banco=fichero.leerDatos("datos-banco.json");
        launch();
    }
}