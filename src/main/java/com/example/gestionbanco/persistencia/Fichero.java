package com.example.gestionbanco.persistencia;


import com.example.gestionbanco.interfaces.iFichero;
import com.example.gestionbanco.models.Banco;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;

public class Fichero implements iFichero {



    @Override
    public void guardarDatos(Banco banco, String fichero) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            String json = mapper.writeValueAsString(banco);
            File file=new File(fichero);
            FileWriter writer =new FileWriter(file);
            writer.write(json);
            writer.close();

        }
        catch (IOException e) {
            System.out.println(e.toString());

        }
    }

    @Override
    public Banco leerDatos(String fichero)  {
        File file=new File(fichero);
        Banco banco=null;
        try{
            FileReader reader=new FileReader(file);
            BufferedReader buffer=new BufferedReader(reader);

            String datos;
            if ((datos=buffer.readLine())!=null){
                    banco=new ObjectMapper().readValue(datos, Banco.class);
                }
        } catch (IOException e) {
                e.printStackTrace();
            }

        return banco;
    }
}
