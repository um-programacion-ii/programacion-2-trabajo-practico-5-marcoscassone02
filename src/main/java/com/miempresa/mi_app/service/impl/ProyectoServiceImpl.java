
package com.miempresa.mi_app.service.impl;

import com.miempresa.mi_app.model.Proyecto;
import com.miempresa.mi_app.repository.ProyectoRepository;
import com.miempresa.mi_app.service.ProyectoService;
import jakarta.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProyectoServiceImpl implements ProyectoService {

    private final ProyectoRepository proyectoRepository;

    public ProyectoServiceImpl(ProyectoRepository proyectoRepository) {
        this.proyectoRepository = proyectoRepository;
    }

    @Override @Transactional
    public Proyecto crear(Proyecto p) {
        return proyectoRepository.save(p);
    }

    @Override @Transactional
    public Proyecto actualizar(Long id, Proyecto p) {
        Proyecto existente = proyectoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Proyecto no encontrado: " + id));
        existente.setNombre(p.getNombre());
        existente.setDescripcion(p.getDescripcion());
        existente.setFechaInicio(p.getFechaInicio());
        existente.setFechaFin(p.getFechaFin());
        existente.setEmpleados(p.getEmpleados());
        return proyectoRepository.save(existente);
    }

    @Override @Transactional
    public void eliminar(Long id) {
        if (!proyectoRepository.existsById(id)) {
            throw new EntityNotFoundException("Proyecto no encontrado: " + id);
        }
        proyectoRepository.deleteById(id);
    }

    @Override @Transactional(readOnly = true)
    public Optional<Proyecto> obtenerPorId(Long id) {
        return proyectoRepository.findById(id);
    }

    @Override @Transactional(readOnly = true)
    public List<Proyecto> listar() {
        return proyectoRepository.findAll();
    }

    @Override @Transactional(readOnly = true)
    public List<Proyecto> listarActivos() {
        return proyectoRepository.findActivos(LocalDate.now());
    }
}

