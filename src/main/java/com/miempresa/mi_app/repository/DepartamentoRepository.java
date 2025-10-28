
package com.miempresa.mi_app.repository;

import com.miempresa.mi_app.model.Departamento;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartamentoRepository extends JpaRepository<Departamento, Long> {
    Optional<Departamento> findByNombre(String nombre);
}

