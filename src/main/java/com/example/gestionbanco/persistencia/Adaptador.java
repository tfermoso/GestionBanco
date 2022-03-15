package com.example.gestionbanco.persistencia;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

class PersonaAdapter extends TypeAdapter<Persona> {
    @Override
    public Persona read(JsonReader reader) throws IOException {
     return new Persona("Juan",4);
    }

    @Override
    public void write(JsonWriter writer, Persona persona) throws IOException {

    }
}
