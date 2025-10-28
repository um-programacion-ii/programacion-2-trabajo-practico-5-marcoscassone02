
package com.miempresa.mi_app.repository;

import com.miempresa.mi_app.model.Empleado;
import com.miempresa.mi_app.model.Proyecto;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {

    // Buscar empleados por nombre de departamento
    List<Empleado> findByDepartamento_Nombre(String nombre);

    // Buscar por rango salarial
    List<Empleado> findBySalarioBetween(BigDecimal min, BigDecimal max);

    // Buscar por email (único)
    Optional<Empleado> findByEmail(String email);
}

