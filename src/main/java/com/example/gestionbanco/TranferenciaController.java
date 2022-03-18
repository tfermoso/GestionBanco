package com.example.gestionbanco;

import com.example.gestionbanco.models.Banco;
import com.example.gestionbanco.models.CCC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseButton;

public class TranferenciaController {
    @FXML
    protected TextField txtOrigen,txtDestino,txtCantidad;
    @FXML
    protected TableView tblCuentas;
    @FXML
    protected TableColumn cTitular,cCuenta,cSaldo;
    ObservableList<CCC> list;
    private Banco banco;
    public BancoController bancoController;

    public Banco getBanco() {
        return banco;
    }


    public void setBanco(Banco banco) {
        this.banco = banco;
    }

    public void initialize() {
        cTitular.setCellValueFactory(new PropertyValueFactory("nombreDelTitular"));
        cCuenta.setCellValueFactory(new PropertyValueFactory("numeroDeCuenta"));
        cSaldo.setCellValueFactory(new PropertyValueFactory("saldoDeCuenta"));
        tblCuentas.setRowFactory(tv -> {
            TableRow<CCC> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (! row.isEmpty() && event.getButton()== MouseButton.PRIMARY
                        && event.getClickCount() == 1) {
                    CCC clickedRow = row.getItem();
                    txtOrigen.setText(clickedRow.getNombreDelTitular());
                    txtCantidad.requestFocus();
                    /*
                    if(txtOrigen.getText().equals("")){
                        txtOrigen.setText(clickedRow.getNombreDelTitular());
                    }else{
                        txtDestino.setText(clickedRow.getNombreDelTitular());
                    }
                     */

                }else if(! row.isEmpty() && event.getButton()== MouseButton.SECONDARY
                        && event.getClickCount() == 1){
                    CCC clickedRow = row.getItem();
                    txtDestino.setText(clickedRow.getNombreDelTitular());
                    txtCantidad.requestFocus();
                }
            });
            return row ;
        });
        txtCantidad.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                btnTransferencia();
            }
        });
    }

    public void inicializar(){
        list = FXCollections.observableArrayList();
        for (int i = 0; i < banco.getCuentas().size(); i++) {
            list.add(banco.getCuentas().get(i));
        }
        tblCuentas.setItems(list);



    }

    @FXML
    protected void btnTransferencia(){
       String origen=txtOrigen.getText();
       String destino=txtDestino.getText();
       Double cantidad= Double.valueOf(txtCantidad.getText());
       banco.transferencia(origen,destino,cantidad);
       tblCuentas.refresh();
       txtDestino.setText("");
       txtOrigen.setText("");
       txtCantidad.setText("");
    }
}
