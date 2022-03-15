package com.example.gestionbanco.persistencia;


import com.example.gestionbanco.interfaces.iFichero;
import com.example.gestionbanco.models.Banco;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class Fichero implements iFichero {



    @Override
    public void guardarDatos(Banco banco, String fichero) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            String json = mapper
                    .writerWithDefaultPrettyPrinter()
                    .writeValueAsString(new Banco());

            System.out.println(json);
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
