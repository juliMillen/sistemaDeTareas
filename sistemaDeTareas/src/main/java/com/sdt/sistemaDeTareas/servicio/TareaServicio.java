package com.sdt.sistemaDeTareas.servicio;

import com.sdt.sistemaDeTareas.modelo.Tarea;
import com.sdt.sistemaDeTareas.repositorio.ITareaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TareaServicio {
    @Autowired
    private ITareaRepository tareaRepository;

    public void agregarTarea(Tarea tarea) {
        tareaRepository.save(tarea);
    }

    public void eliminarTarea(int id) {
        tareaRepository.deleteById(id);
    }

    public List<Tarea> listarTareas() {
        return tareaRepository.findAll();
    }

    public Tarea obtenerTarea(int id) {
        Tarea tarea = tareaRepository.findById(id).orElse(null);
        return tarea;
    }
}
