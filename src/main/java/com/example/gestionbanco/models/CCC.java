package com.example.gestionbanco.models;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class CCC {
    public double saldoDeCuenta;
    public String nombreDelTitular;
    public long numeroDeCuenta;
    public static int idCuenta;
    public List<HashMap<String,String>> movimientos;


    public CCC(double saldoDeCuenta, String nombreDelTitular, long numeroDeCuenta, List<HashMap<String, String>> movimientos) {
        this.saldoDeCuenta = saldoDeCuenta;
        this.nombreDelTitular = nombreDelTitular;
        this.numeroDeCuenta = numeroDeCuenta;
        this.movimientos = movimientos;
    }

    public CCC() {
    }

    public CCC(String nombreDelTitular, double saldoDeCuenta) {
        this.saldoDeCuenta = saldoDeCuenta;
        this.nombreDelTitular = nombreDelTitular;
        this.numeroDeCuenta= new Random().nextLong(1000000);
        movimientos=new ArrayList<>();
    }

    public void setIngreso(double cantidad){
        saldoDeCuenta+=cantidad;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String mensaje = LocalDateTime.now().format(formatter)+ "#ingreso# "+cantidad;
        HashMap<String,String> mov=new HashMap<>();
        mov.put("ingreso",mensaje);
        movimientos.add(mov);
    }

    public void setReintegro(double cantidad){
        saldoDeCuenta-=cantidad;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String mensaje = LocalDateTime.now().format(formatter)+ "#retirada# "+cantidad;
        HashMap<String,String> mov=new HashMap<>();
        mov.put("reintegro",mensaje);
        movimientos.add(mov);
    }

    public void traspaso(CCC c, double cantidad){
        setReintegro(cantidad);
        c.setIngreso(cantidad);
    }

    public static void RealizarTransferenciaEntreCuentas(CCC c1,CCC c2,double cantidad){
        c1.setReintegro(cantidad);
        c2.setIngreso(cantidad);
    }

    public String datosCuenta() {
        return  "Titular:" + nombreDelTitular + '\n' +
                "saldoDeCuenta:" + saldoDeCuenta  +"\n"+
                "numeroDeCuenta=" + numeroDeCuenta  + "\n";
    }

    public String getNombreDelTitular() {
        return nombreDelTitular;
    }

    public List<HashMap<String, String>> getMovimientos() {
        return movimientos;
    }

    public double getSaldoDeCuenta() {
        return saldoDeCuenta;
    }

    public long getNumeroDeCuenta() {
        return numeroDeCuenta;
    }

    public static int getIdCuenta() {
        return idCuenta;
    }

    public void setSaldoDeCuenta(double saldoDeCuenta) {
        this.saldoDeCuenta = saldoDeCuenta;
    }

    public void setNombreDelTitular(String nombreDelTitular) {
        this.nombreDelTitular = nombreDelTitular;
    }

    public void setNumeroDeCuenta(long numeroDeCuenta) {
        this.numeroDeCuenta = numeroDeCuenta;
    }

    public static void setIdCuenta(int idCuenta) {
        CCC.idCuenta = idCuenta;
    }

    public void setMovimientos(List<HashMap<String, String>> movimientos) {
        this.movimientos = movimientos;
    }
}