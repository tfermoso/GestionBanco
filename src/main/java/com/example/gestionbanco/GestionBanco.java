package com.example.gestionbanco;

import com.example.gestionbanco.models.Banco;
import com.example.gestionbanco.models.CCC;
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
        Fichero fichero=new Fichero();
        /*
        Banco banco=new Banco();
        banco.crearCuenta("Juan",34);
        banco.ingresar("Juan",1000);
        banco.crearCuenta("Lucia",1000);
        banco.transferencia("Juan","Lucia",500);
        fichero.guardarDatos(banco,"datos-banco.json");
         */
        Banco banco=fichero.leerDatos("datos-banco.json");
        controller.setBanco(banco);
        controller.stage=stage;
        stage.show();
    }

    public static void main(String[] args) {

        launch();
    }
}