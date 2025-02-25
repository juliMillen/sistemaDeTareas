package com.sdt.sistemaDeTareas.controlador;
import com.sdt.sistemaDeTareas.modelo.Tarea;
import com.sdt.sistemaDeTareas.servicio.TareaServicio;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.cell.PropertyValueFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.control.*;

import javax.swing.*;


@Component
public class TareaControl implements Initializable {
    private static final Logger logger = LoggerFactory.getLogger(TareaControl.class);

    @Autowired
    private TareaServicio tServicio;

    @FXML
    private TableView<Tarea> tablaID;

    @FXML
    private TableColumn<Tarea,Integer> idTareaColumn;

    @FXML
    private TableColumn<Tarea,String> nombreTareaColumn;

    @FXML
    private TableColumn<Tarea, String> responsableColumn;

    @FXML
    private TableColumn<Tarea, String> estadoColumn;
    
    private final ObservableList<Tarea> tareaLista = FXCollections.observableArrayList();

    @FXML
    private TextField txtTarea;

    @FXML
    private TextField txtResponsable;

    @FXML
    private TextField txtEstado;

    @Autowired
    private TareaServicio tareaServicio;

    private Integer idTareaNuevo;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
      tablaID.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
      configurarColumnas();
      listarTareas();
    }

    private void configurarColumnas() {
        idTareaColumn.setCellValueFactory(new PropertyValueFactory<>("idTarea"));
        nombreTareaColumn.setCellValueFactory(new PropertyValueFactory<>("nombreTarea"));
        responsableColumn.setCellValueFactory(new PropertyValueFactory<>("responsableTarea"));
        estadoColumn.setCellValueFactory(new PropertyValueFactory<>("estadoTarea"));
    }

    private void listarTareas() {
        tareaLista.clear();
        tareaLista.addAll(tServicio.listarTareas());
        tablaID.setItems(tareaLista);
        tablaID.refresh();
    }

    public void modificarTarea(){
        if(idTareaNuevo == null){
            mostrarMensaje("Informacion","Debe seleccionar una tarea");
            return;
        }

        if(txtTarea.getText().isEmpty()){
            mostrarMensaje("Informacion","Debe ingresar una tarea");
            txtTarea.requestFocus();
            return;
        }

        Tarea nuevaTarea = new Tarea();
        recolectarDatosForm(nuevaTarea);
        tareaServicio.agregarTarea(nuevaTarea);
        mostrarMensaje("Informacion","Tarea modificada correctamente");
    }

    public void eliminarTarea(){
        Tarea aEliminar = tablaID.getSelectionModel().getSelectedItem();
        if(aEliminar != null){
            logger.info("Registro a eliminar: " + aEliminar.toString());
            tareaLista.remove(aEliminar);
            tareaServicio.eliminarTarea(aEliminar.getIdTarea());
            mostrarMensaje("Informacion","Tarea eliminada correctamente");
            limpiarFormulario();
            listarTareas();
        }else{
        mostrarMensaje("Informacion","Debe seleccionar la tarea que quiere eliminar");
        }
    }

    public void seleccionarTarea(){
        Tarea tarea = tablaID.getSelectionModel().getSelectedItem();
        if(tarea != null){
            idTareaNuevo = tarea.getIdTarea();
            txtTarea.setText(tarea.getNombreTarea());
            txtResponsable.setText(tarea.getResponsableTarea());
            txtEstado.setText(tarea.getEstadoTarea());
        }
        else{
            mostrarMensaje("Error","Seleccione una tarea");
        }
    }

    public void agregarTarea(){
        if(txtTarea.getText().isEmpty()){
            mostrarMensaje("Error validacion","Debe completar el espacio");
            txtTarea.requestFocus();
            return;
        }else{
            Tarea tarea = new Tarea();
            recolectarDatosForm(tarea);
            tareaServicio.agregarTarea(tarea);
            mostrarMensaje("Informacion","Tarea agregada correctamente");
            limpiarFormulario();
            listarTareas();
        }
    }

    public void limpiarFormulario() {
        idTareaNuevo = null;
        txtTarea.clear();
        txtResponsable.clear();
        txtEstado.clear();
    }

    private void recolectarDatosForm(Tarea tarea) {
        if(idTareaNuevo != null) {
            tarea.setIdTarea(idTareaNuevo);
            tarea.setNombreTarea(txtTarea.getText());
            tarea.setResponsableTarea(txtResponsable.getText());
            tarea.setEstadoTarea(txtEstado.getText());
        }
    }

    private void mostrarMensaje(String titulo, String mensaje) {
    Alert alerta = new Alert(Alert.AlertType.INFORMATION);
    alerta.setTitle(titulo);
    alerta.setHeaderText(null);
    alerta.setContentText(mensaje);
    alerta.showAndWait();
    }

    @FXML
    private void salir(){
        Platform.exit();
    }
}
