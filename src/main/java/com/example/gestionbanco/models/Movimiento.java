package com.example.gestionbanco.models;

import java.lang.annotation.Documented;
import java.time.LocalDateTime;
import java.util.Date;

public class Movimiento {
    private LocalDateTime fecha;
    private String tipo;
    private Double cantidad;

    public Movimiento(LocalDateTime fecha, String tipo, Double cantidad) {
        this.fecha = fecha;
        this.tipo = tipo;
        this.cantidad = cantidad;
    }

    public Movimiento(String tipo, Double cantidad) {
        this.tipo = tipo;
        this.cantidad = cantidad;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }
}
