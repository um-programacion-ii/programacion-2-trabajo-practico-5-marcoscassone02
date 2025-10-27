package com.miempresa.mi_app.repository;

import com.miempresa.mi_app.model.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {
}
