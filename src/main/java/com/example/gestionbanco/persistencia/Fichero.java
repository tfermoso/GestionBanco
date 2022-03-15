package com.example.gestionbanco.persistencia;

import com.example.gestionbanco.interfaces.iFichero;
import com.example.gestionbanco.models.Banco;
import com.example.gestionbanco.models.Persona;
import com.google.gson.Gson;

import java.io.*;

public class Fichero  implements iFichero {
    Gson gson;

    public Fichero() {
        /*
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Persona.class, new PersonaAdapter());
        gson = builder.create();
         */
        gson=new Gson();

    }


    @Override
    public void guardarDatos(Banco banco, String fichero)  {
        String json=gson.toJson(banco);
        File file=new File(fichero);
        FileWriter writer= null;
        try {
            writer = new FileWriter(file);
            writer.write(json);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Banco leerDatos(String fichero) {
        File file=new File(fichero);
        Banco banco=null;
        FileReader reader= null;
        try {
            reader = new FileReader(file);
            BufferedReader buffer=new BufferedReader(reader);
            String datos;
            while((datos=buffer.readLine())!=null){
                banco =gson.fromJson(datos,Banco.class);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return banco;
    }
}
