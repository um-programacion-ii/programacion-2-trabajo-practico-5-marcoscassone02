package com.miempresa.mi_app.service;

import com.miempresa.mi_app.model.Empleado;
import com.miempresa.mi_app.repository.EmpleadoRepository;
import com.miempresa.mi_app.service.impl.EmpleadoServiceImpl;
import org.junit.jupiter.api.*;
import org.mockito.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class EmpleadoServiceTest {

    @Mock
    private EmpleadoRepository empleadoRepository;

    @InjectMocks
    private EmpleadoServiceImpl empleadoService;

    private Empleado empleado;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        empleado = new Empleado();
        empleado.setId(1L);
        empleado.setNombre("Juan");
        empleado.setApellido("Pérez");
        empleado.setEmail("juan@example.com");
        empleado.setSalario(new BigDecimal("1200.00"));
        empleado.setFechaContratacion(LocalDate.now());
    }

    @Test
    void crearEmpleado() {
        when(empleadoRepository.save(empleado)).thenReturn(empleado);

        Empleado resultado = empleadoService.crear(empleado);

        assertThat(resultado.getEmail()).isEqualTo("juan@example.com");
        verify(empleadoRepository, times(1)).save(empleado);
    }
}

