package main.java.com.miempresa.mi_app.controller;

import com.miempresa.mi_app.model.Empleado;
import com.miempresa.mi_app.service.EmpleadoService;
import jakarta.validation.Valid;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/empleados")
public class EmpleadoController {

    private final EmpleadoService empleadoService;

    public EmpleadoController(EmpleadoService empleadoService) {
        this.empleadoService = empleadoService;
    }

    // GET /api/empleados
    @GetMapping
    public List<Empleado> listar() {
        return empleadoService.listar();
    }

    // GET /api/empleados/{id}
    @GetMapping("/{id}")
    public Empleado obtenerPorId(@PathVariable Long id) {
        return empleadoService.obtenerPorId(id)
                .orElseThrow(() -> new RuntimeException("Empleado no encontrado: " + id));
    }

    // POST /api/empleados
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Empleado crear(@Valid @RequestBody Empleado empleado) {
        return empleadoService.crear(empleado);
    }

    // PUT /api/empleados/{id}
    @PutMapping("/{id}")
    public Empleado actualizar(@PathVariable Long id, @Valid @RequestBody Empleado empleado) {
        return empleadoService.actualizar(id, empleado);
    }

    // DELETE /api/empleados/{id}
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        empleadoService.eliminar(id);
    }

    // GET /api/empleados/departamento/{nombre}
    @GetMapping("/departamento/{nombre}")
    public List<Empleado> listarPorDepartamento(@PathVariable String nombre) {
        return empleadoService.buscarPorDepartamento(nombre);
    }

    // GET /api/empleados/salario?min=X&max=Y
    @GetMapping("/salario")
    public List<Empleado> listarPorRangoSalarial(@RequestParam BigDecimal min, @RequestParam BigDecimal max) {
        return empleadoService.buscarPorRangoSalario(min, max);
    }
}
