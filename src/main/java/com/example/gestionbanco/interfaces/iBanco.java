package com.example.gestionbanco.interfaces;

public interface iBanco {
    public void crearCuenta(String titular,double saldoInicial);
    public void ingresar(String titular,double saldo);
    public void retirar(String titular,double cantidad);
    public void transferencia(String origen,String destino,double cantidad);
    public String consultarMovimientos(String titular);
    public String verCuenta(String nombre);
}
