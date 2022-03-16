package com.example.gestionbanco;

import com.example.gestionbanco.models.Banco;
import com.example.gestionbanco.models.CCC;
import com.example.gestionbanco.persistencia.Fichero;
import com.example.gestionbanco.persistencia.FicheroJackson;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
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
    protected AnchorPane panelCrearCuenta,paneVerCuenta;
    @FXML
    protected TableView tblCuentas;
    @FXML
    protected TableColumn cTitular,cCuenta,cSaldo;

    private Banco banco;

    public BancoController() {
        FicheroJackson fichero=new FicheroJackson();
        banco=fichero.leerDatos("datos-banco.json");
    }

    public void showList(){
        ObservableList<CCC> list = FXCollections.observableArrayList();
        for (int i = 0; i < banco.getCuentas().size(); i++) {
            list.add(banco.getCuentas().get(i));
        }
        cTitular.setCellValueFactory(new PropertyValueFactory("nombreDelTitular"));
        cCuenta.setCellValueFactory(new PropertyValueFactory("numeroDeCuenta"));
        cSaldo.setCellValueFactory(new PropertyValueFactory("saldoDeCuenta"));
        tblCuentas.setItems(list);
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
        paneVerCuenta.setVisible(true);
        panelCrearCuenta.setVisible(false);
        showList();
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
    protected void mostrarPanelCrearCuenta(){
        paneVerCuenta.setVisible(false);
        panelCrearCuenta.setVisible(true);
    }

    @FXML
    protected void btnSalir(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText(null);
        alert.setTitle("Info");
        alert.setContentText("¿Realmente quiere salir?");
        alert.showAndWait();
        FicheroJackson fichero=new FicheroJackson();
        fichero.guardarDatos(banco,"datos-banco.json");
        stage.close();
    }
}