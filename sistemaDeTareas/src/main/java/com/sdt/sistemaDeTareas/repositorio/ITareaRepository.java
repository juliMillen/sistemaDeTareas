package com.sdt.sistemaDeTareas.repositorio;

import com.sdt.sistemaDeTareas.modelo.Tarea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITareaRepository extends JpaRepository<Tarea,Integer> {
}
