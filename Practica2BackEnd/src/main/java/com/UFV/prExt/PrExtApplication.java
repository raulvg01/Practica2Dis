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
			SpringApplication.run(PrExtApplication.class, args);
		}
}
