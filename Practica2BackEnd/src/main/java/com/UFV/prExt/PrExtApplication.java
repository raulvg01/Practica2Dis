package com.UFV.prExt;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.List;

@SpringBootApplication
public class PrExtApplication {

		public static void main(String[] args) {
			try {
				// Crear un objeto Gson con el deserializador personalizado
				Gson gson = new GsonBuilder()
						.registerTypeAdapter(TIA_ZonasBasicas.class, new JsonDeserializer())
						.create();

				// Leer el archivo JSON utilizando FileReader
				FileReader reader = new FileReader("ruta_del_archivo.json");

				// Obtener el tipo de lista de TIA_ZonasBasicas utilizando TypeToken
				Type listaType = new TypeToken<List<TIA_ZonasBasicas>>() {
				}.getType();

				// Deserializar el JSON en una lista de TIA_ZonasBasicas
				List<TIA_ZonasBasicas> listaZonasBasicas = gson.fromJson(reader, listaType);

				// Cerrar el FileReader
				reader.close();

				// Comprobar que la deserialización fue exitosa


			} catch (Exception e) {
				e.printStackTrace();
			}

		}
}
