package com.example.gestionbanco;

import com.example.gestionbanco.models.Banco;
import com.example.gestionbanco.models.CCC;
import com.example.gestionbanco.persistencia.Fichero;
import com.example.gestionbanco.persistencia.FicheroJackson;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.converter.DoubleStringConverter;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class BancoController {


    Stage stage;
    @FXML
    protected TextField txtNombre,txtSaldo,txtCantidad;
    @FXML
    protected Label lblMensajes,lblcuenta,lblCuentaSeleccionada;
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
        System.out.println("creando controlador");

    }

    public void initialize() {
        System.out.println("Inicializando");
        showList();

        tblCuentas.setRowFactory(tv -> {
            TableRow<CCC> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (! row.isEmpty() && event.getButton()== MouseButton.PRIMARY
                        && event.getClickCount() == 2) {
                    CCC clickedRow = row.getItem();
                    lblcuenta.setText(clickedRow.datosCuenta());
                    lblCuentaSeleccionada.setText(clickedRow.getNombreDelTitular());
                }
            });
            return row ;
        });
    }

    private void limpiarTabla(){
        ObservableList<CCC> list = FXCollections.observableArrayList();
        list.clear();
        tblCuentas.setItems(list);
    }
    private void llenarTabla(){
        ObservableList<CCC> list = FXCollections.observableArrayList();
        for (int i = 0; i < banco.getCuentas().size(); i++) {
            list.add(banco.getCuentas().get(i));
        }
        tblCuentas.setItems(list);
    }

    public void showList(){
        ObservableList<CCC> list = FXCollections.observableArrayList();
        for (int i = 0; i < banco.getCuentas().size(); i++) {
            list.add(banco.getCuentas().get(i));
        }
        tblCuentas.setEditable(true);
        cTitular.setCellValueFactory(new PropertyValueFactory<CCC, String>("nombreDelTitular"));
        cTitular.setCellFactory(TextFieldTableCell.forTableColumn());
        cCuenta.setCellValueFactory(new PropertyValueFactory("numeroDeCuenta"));
        cSaldo.setCellValueFactory(new PropertyValueFactory("saldoDeCuenta"));
        tblCuentas.setItems(list);
        cTitular.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent>() {
            @Override
            public void handle(TableColumn.CellEditEvent cellEditEvent) {
                ((CCC)cellEditEvent.getRowValue()).setNombreDelTitular(cellEditEvent.getNewValue().toString());
            }
        });
        DoubleStringConverter converter = new DoubleStringConverter();
        cSaldo.setCellFactory(TextFieldTableCell.<CCC, Double>forTableColumn(converter));
        cSaldo.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent>() {
            @Override
            public void handle(TableColumn.CellEditEvent cellEditEvent) {
                ((CCC)cellEditEvent.getRowValue()).setSaldoDeCuenta(Double.parseDouble(cellEditEvent.getNewValue().toString()));
            }
        });
    }
    @FXML
    protected void itemHacerTransferencia(){

        FXMLLoader loader = new FXMLLoader(getClass().getResource("transferencia-view.fxml"));
        try {
            Parent root = loader.load();
            TranferenciaController tranferenciaController=loader.getController();
            tranferenciaController.setBanco(banco);
            tranferenciaController.inicializar();
            tranferenciaController.bancoController=this;
            Scene scene=new Scene(root);
            Stage stage=new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }

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
    protected void btnOperar(ActionEvent evt){

        if(lblCuentaSeleccionada.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText(null);
            alert.setTitle("Info");
            alert.setContentText("Seleccione una cuenta");
            alert.showAndWait();
        }else{
            String titular=lblCuentaSeleccionada.getText();
            String cantidad=txtCantidad.getText();
            if(cantidad.equals("")){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setHeaderText(null);
                alert.setTitle("Info");
                alert.setContentText("Indique la cantiadad");
                alert.showAndWait();
            }else{

                if(((Button)evt.getSource()).getText().equals("Ingresar")){

                    banco.ingresar(titular,Double.valueOf(cantidad));
                }else{
                    banco.retirar(titular,Double.valueOf(cantidad));
                }
                txtCantidad.setText("");
                tblCuentas.refresh();
            }
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