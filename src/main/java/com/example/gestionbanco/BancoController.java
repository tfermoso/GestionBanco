package com.example.gestionbanco;

import com.example.gestionbanco.models.Banco;
import com.example.gestionbanco.persistencia.Fichero;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BancoController {


    Stage stage;
    @FXML
    protected TextField txtNombre,txtSaldo;

    @FXML
    protected Label lblMensajes,lblcuenta;

    @FXML
    protected AnchorPane panelCrearCuenta;

    private Banco banco;

    public BancoController() {
        Fichero fichero=new Fichero();
        banco=fichero.leerDatos("datos-banco.json");
    }

    @FXML
    protected void showHelp(){
        lblMensajes.setText("Programa de gestión de un banco v.1");
        System.out.println(txtNombre.getText());
        System.out.println(txtSaldo.getText());
    }

    @FXML
    protected void btnCrearCuenta(){
        String nombre=txtNombre.getText();
        try{
            Double cantidad=Double.valueOf(txtSaldo.getText());
            banco.crearCuenta(nombre,cantidad);
        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Valor no válido");
            alert.showAndWait();
            txtSaldo.setText("");
        }

    }

    @FXML
    protected void menuVerCuentas(){
        panelCrearCuenta.setVisible(false);
    }

    @FXML
    protected void btnMostrarCuenta(ActionEvent event){
        String nombre = txtNombre.getText();
        lblcuenta.setText(banco.verCuenta(nombre));

        //Node source = (Node) event.getSource();
        //Stage stage = (Stage) source.getScene().getWindow();
        //stage.close();
        //stage.close();
    }

    @FXML
    protected void btnSalir(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText(null);
        alert.setTitle("Info");
        alert.setContentText("¿Realmente quiere salir?");
        alert.showAndWait();
        Fichero fichero=new Fichero();
        fichero.guardarDatos(banco,"datos-banco.json");
        stage.close();
    }
}