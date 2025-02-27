package com.sdt.sistemaDeTareas.presentacion;

import com.sdt.sistemaDeTareas.SistemaDeTareasApplication;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;

public class SistemaTareasFx extends Application {

    private ConfigurableApplicationContext applicationContext;
    /*public static void main(String[] args) {
        launch(args);
    }*/

    @Override
    public void init(){
        this.applicationContext = new SpringApplicationBuilder(SistemaDeTareasApplication.class).run();
    }
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(SistemaDeTareasApplication.class.getResource("/template/index.fxml"));
        loader.setControllerFactory(applicationContext::getBean);
        Scene escena = new Scene(loader.load());
        stage.setScene(escena);
        stage.show();
    }

    @Override
    public void stop(){
        applicationContext.close();
        Platform.exit();
    }
}
