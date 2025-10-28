package com.miempresa.mi_app.service.impl;

import com.miempresa.mi_app.model.Departamento;
import com.miempresa.mi_app.repository.DepartamentoRepository;
import com.miempresa.mi_app.service.DepartamentoService;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DepartamentoServiceImpl implements DepartamentoService {

    private final DepartamentoRepository departamentoRepository;

    public DepartamentoServiceImpl(DepartamentoRepository departamentoRepository) {
        this.departamentoRepository = departamentoRepository;
    }

    @Override @Transactional
    public Departamento crear(Departamento d) {
        return departamentoRepository.save(d);
    }

    @Override @Transactional
    public Departamento actualizar(Long id, Departamento d) {
        Departamento existente = departamentoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Departamento no encontrado: " + id));
        existente.setNombre(d.getNombre());
        existente.setDescripcion(d.getDescripcion());
        return departamentoRepository.save(existente);
    }

    @Override @Transactional
    public void eliminar(Long id) {
        if (!departamentoRepository.existsById(id)) {
            throw new EntityNotFoundException("Departamento no encontrado: " + id);
        }
        departamentoRepository.deleteById(id);
    }

    @Override @Transactional(readOnly = true)
    public Optional<Departamento> obtenerPorId(Long id) {
        return departamentoRepository.findById(id);
    }

    @Override @Transactional(readOnly = true)
    public Optional<Departamento> obtenerPorNombre(String nombre) {
        return departamentoRepository.findByNombre(nombre);
    }

    @Override @Transactional(readOnly = true)
    public List<Departamento> listar() {
        return departamentoRepository.findAll();
    }
}

