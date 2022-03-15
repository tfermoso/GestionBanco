package com.example.gestionbanco.persistencia;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Fichero {
    Gson gson;

    public Fichero() {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Persona.class, new PersonaAdapter());
        gson = builder.create();

    }

    public void guardarDatos(Persona banco) {
        String json=gson.toJson(banco);
        System.out.println(json);
    }

}
