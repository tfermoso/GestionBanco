package com.example.gestionbanco.interfaces;

import com.example.gestionbanco.models.Banco;

import java.io.IOException;

public interface iFichero {
    public void guardarDatos(Banco banco, String fichero);
    public Banco leerDatos(String fichero) throws IOException;
}
