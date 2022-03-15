package com.example.gestionbanco.persistencia;


import com.example.gestionbanco.models.Persona;
import com.example.gestionbanco.interfaces.iFichero;
import com.example.gestionbanco.models.Banco;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class Fichero implements iFichero {



    @Override
    public void guardarDatos(Banco banco, String fichero) {
        try {
            ObjectMapper mapper = new ObjectMapper();

            mapper.writeValue(new File(fichero), new Persona("Juan",45));
        }
        catch (IOException e) {
            System.out.println(e.toString());

        }
    }

    @Override
    public Banco leerDatos(String fichero) {
        return null;
    }
}
