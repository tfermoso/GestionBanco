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
    public void write(JsonWriter jsonWriter, Persona persona) throws IOException {
        // Comienza la serialización de flujo en objetos
        jsonWriter.beginObject();
        // Especifique el valor clave de Json para comenzar con letras mayúsculas
        jsonWriter.name("Name").value(persona.getNombre());
        jsonWriter.name("Age").value(persona.getEdad());

        // Fin de la serialización de transmisión
        jsonWriter.endObject();
    }
}
