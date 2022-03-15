package com.example.gestionbanco.interfaces;

import com.example.gestionbanco.models.Banco;

public interface iFichero {
    public void guardarDatos(Banco banco, String fichero);
    public Banco leerDatos(String fichero);
}
