
package com.miempresa.mi_app.service.impl;

import com.miempresa.mi_app.model.Empleado;
import com.miempresa.mi_app.repository.EmpleadoRepository;
import com.miempresa.mi_app.service.EmpleadoService;
import jakarta.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmpleadoServiceImpl implements EmpleadoService {

    private final EmpleadoRepository empleadoRepository;

    public EmpleadoServiceImpl(EmpleadoRepository empleadoRepository) {
        this.empleadoRepository = empleadoRepository;
    }

    @Override @Transactional
    public Empleado crear(Empleado e) {
        return empleadoRepository.save(e);
    }

    @Override @Transactional
    public Empleado actualizar(Long id, Empleado e) {
        Empleado existente = empleadoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Empleado no encontrado: " + id));
        // Actualiza campos necesarios
        existente.setNombre(e.getNombre());
        existente.setApellido(e.getApellido());
        existente.setEmail(e.getEmail());
        existente.setFechaContratacion(e.getFechaContratacion());
        existente.setSalario(e.getSalario());
        existente.setDepartamento(e.getDepartamento());
        existente.setProyectos(e.getProyectos());
        return empleadoRepository.save(existente);
    }

    @Override @Transactional
    public void eliminar(Long id) {
        if (!empleadoRepository.existsById(id)) {
            throw new EntityNotFoundException("Empleado no encontrado: " + id);
        }
        empleadoRepository.deleteById(id);
    }

    @Override @Transactional(readOnly = true)
    public Optional<Empleado> obtenerPorId(Long id) {
        return empleadoRepository.findById(id);
    }

    @Override @Transactional(readOnly = true)
    public List<Empleado> listar() {
        return empleadoRepository.findAll();
    }

    @Override @Transactional(readOnly = true)
    public List<Empleado> buscarPorDepartamento(String nombreDepto) {
        return empleadoRepository.findByDepartamento_Nombre(nombreDepto);
    }

    @Override @Transactional(readOnly = true)
    public List<Empleado> buscarPorRangoSalario(BigDecimal min, BigDecimal max) {
        return empleadoRepository.findBySalarioBetween(min, max);
    }

    @Override @Transactional(readOnly = true)
    public Optional<Empleado> buscarPorEmail(String email) {
        return empleadoRepository.findByEmail(email);
    }
}

