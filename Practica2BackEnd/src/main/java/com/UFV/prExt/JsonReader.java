package com.UFV.prExt;

import java.io.*;
import java.util.ArrayList;

public class JsonReader {

    //Método para leer el archivo JSON
    public static String readFileAsString(String filePath) {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content.toString();
    }

/*
   //Metodo para escribir en el archibo json
   public void writeJsonUsuario(ArrayList<Usuario> usuarios) throws IOException {
       Gson gson = new GsonBuilder().create();
       FileWriter writer = new FileWriter("usuarios.json");
       JsonWriter jsonWriter = new JsonWriter(writer);
       jsonWriter.setIndent("  ");
       JsonArray jsonArray = gson.toJsonTree(usuarios).getAsJsonArray();
       System.out.println(jsonArray.toString());
       gson.toJson(jsonArray, jsonWriter);
       jsonWriter.close();
   }*/

}
