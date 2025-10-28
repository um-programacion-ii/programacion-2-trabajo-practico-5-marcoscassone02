package test.java.com.miempresa.mi_app.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.miempresa.mi_app.model.Empleado;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.math.BigDecimal;
import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class EmpleadoControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Test
    void crearEmpleado() throws Exception {
        Empleado empleado = new Empleado();
        empleado.setNombre("Lucía");
        empleado.setApellido("Gómez");
        empleado.setEmail("lucia@example.com");
        empleado.setFechaContratacion(LocalDate.now());
        empleado.setSalario(new BigDecimal("1500"));

        mockMvc.perform(post("/api/empleados")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(empleado)))
                .andExpect(status().isCreated());
    }

    @Test
    void listarEmpleados() throws Exception {
        mockMvc.perform(get("/api/empleados"))
                .andExpect(status().isOk());
    }
}

