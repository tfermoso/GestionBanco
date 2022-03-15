package com.example.gestionbanco.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class CCC {
    private double saldoDeCuenta;
    private String nombreDelTitular;
    private long numeroDeCuenta;
    private static int idCuenta;
    private List<HashMap<String,String>> movimientos;

    public CCC(String nombreDelTitular,double saldoDeCuenta) {
        this.saldoDeCuenta = saldoDeCuenta;
        this.nombreDelTitular = nombreDelTitular;
        this.numeroDeCuenta= new Random().nextLong(1000000);
        movimientos=new ArrayList<>();
    }

    public void setIngreso(double cantidad){
        saldoDeCuenta+=cantidad;
        String mensaje = LocalDateTime.now().toString()+ ":ingreso: "+cantidad;
        HashMap<String,String> mov=new HashMap<>();
        mov.put("ingreso",mensaje);
        movimientos.add(mov);
    }

    public String getTitular() {
        return nombreDelTitular;
    }

    public void setReintegro(double cantidad){
        saldoDeCuenta-=cantidad;
        String mensaje = LocalDateTime.now().toString()+ ":retirada: "+cantidad;
        HashMap<String,String> mov=new HashMap<>();
        mov.put("reintegro",mensaje);
        movimientos.add(mov);
    }

    public double getSaldoCuenta() {
        return saldoDeCuenta;
    }
    public void traspaso(CCC c, double cantidad){
        setReintegro(cantidad);
        c.setIngreso(cantidad);
    }

    public static void RealizarTransferenciaEntreCuentas(CCC c1,CCC c2,double cantidad){
        c1.setReintegro(cantidad);
        c2.setIngreso(cantidad);
    }

    public String getDatosCuenta() {
        return  "Titular:" + nombreDelTitular + '\n' +
                "saldoDeCuenta:" + saldoDeCuenta  +"\n"+
                "numeroDeCuenta=" + numeroDeCuenta  + "\n";
    }

    public List<HashMap<String, String>> getMovimientos() {
        return movimientos;
    }

    public double getSaldoDeCuenta() {
        return saldoDeCuenta;
    }

    public void setSaldoDeCuenta(double saldoDeCuenta) {
        this.saldoDeCuenta = saldoDeCuenta;
    }

    public String getNombreDelTitular() {
        return nombreDelTitular;
    }

    public void setNombreDelTitular(String nombreDelTitular) {
        this.nombreDelTitular = nombreDelTitular;
    }

    public long getNumeroDeCuenta() {
        return numeroDeCuenta;
    }

    public void setNumeroDeCuenta(long numeroDeCuenta) {
        this.numeroDeCuenta = numeroDeCuenta;
    }

    public static int getIdCuenta() {
        return idCuenta;
    }

    public static void setIdCuenta(int idCuenta) {
        CCC.idCuenta = idCuenta;
    }

    public void setMovimientos(List<HashMap<String, String>> movimientos) {
        this.movimientos = movimientos;
    }
}
