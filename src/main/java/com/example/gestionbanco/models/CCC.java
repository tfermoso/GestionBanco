package com.example.gestionbanco.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class CCC {
    public double saldoDeCuenta;
    public String nombreDelTitular;
    public long numeroDeCuenta;
    public static int idCuenta;
    public List<HashMap<String,String>> movimientos;

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
}
