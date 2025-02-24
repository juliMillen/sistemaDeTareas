package com.sdt.sistemaDeTareas.controlador;
import com.sdt.sistemaDeTareas.modelo.Tarea;
import com.sdt.sistemaDeTareas.servicio.TareaServicio;
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
    }

    private void AgregarTarea(){

    }
}
