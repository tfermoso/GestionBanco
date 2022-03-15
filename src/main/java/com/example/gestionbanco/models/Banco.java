package com.example.gestionbanco.models;


import com.example.gestionbanco.interfaces.iBanco;

import java.util.ArrayList;

public class Banco implements iBanco {
    private ArrayList<CCC> cuentas;
    private String telefono;


    public Banco() {
        cuentas=new ArrayList<>();
    }


    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public void crearCuenta(String titular, double saldoInicial) {
        CCC c1=new CCC(titular,saldoInicial);
        cuentas.add(c1);
    }

    @Override
    public void ingresar(String titular, double saldo) {
        cuentas.forEach(ccc -> {
            if(ccc.getTitular().equals(titular)){
                ccc.setIngreso(saldo);
            }
        });
    }

    @Override
    public void retirar(String titular, double cantidad) {
        if(getCuenta(titular)!=null){
            getCuenta(titular).setReintegro(cantidad);
        }
    }

    @Override
    public void transferencia(String origen, String destino, double cantidad) {
        CCC c1=getCuenta(origen);
        CCC c2=getCuenta(destino);
        if(c1!=null && c2!=null){
            CCC.RealizarTransferenciaEntreCuentas(c1,c2,cantidad);
        }
    }

    @Override
    public String consultarMovimientos(String titular) {
        CCC c=getCuenta(titular);
        String mov="";
        for (int i = 0; i < c.getMovimientos().size(); i++) {
            mov+=c.getMovimientos().get(i).get("ingreso");
        }
        return mov;
    }

    @Override
    public String verCuenta(String nombre) {
         return  getCuenta(nombre).getDatosCuenta();
    }

    private CCC getCuenta(String titular){
        /*
        for (int i = 0; i <cuentas.size(); i++) {
            if(cuentas.get(i).getTitular().equals(titular)){
                return cuentas.get(i);
            }
        }
        */
        for (CCC c:cuentas) {
            if(c.getTitular().equals(titular)){
                return c;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "Banco{" +
                "cuentas=" + cuentas +
                ", telefono='" + telefono + '\'' +
                '}';
    }

    public ArrayList<CCC> getCuentas() {
        return cuentas;
    }
}
