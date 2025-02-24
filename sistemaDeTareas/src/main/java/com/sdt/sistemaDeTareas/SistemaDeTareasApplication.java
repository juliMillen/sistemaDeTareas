package com.sdt.sistemaDeTareas;

import com.sdt.sistemaDeTareas.presentacion.SistemaTareasFx;
import javafx.application.Application;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SistemaDeTareasApplication {

	public static void main(String[] args) {

		//SpringApplication.run(SistemaDeTareasApplication.class, args);
		Application.launch(SistemaTareasFx.class, args);
	}

}
