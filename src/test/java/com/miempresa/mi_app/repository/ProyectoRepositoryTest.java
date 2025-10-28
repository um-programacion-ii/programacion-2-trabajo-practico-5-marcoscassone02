package test.java.com.miempresa.mi_app.repository;

import com.miempresa.mi_app.model.Proyecto;
import com.miempresa.mi_app.repository.ProyectoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class ProyectoRepositoryTest {

    @Autowired
    private ProyectoRepository proyectoRepository;

    @Test
    void findActivos_deberiaRetornarSoloProyectosActivos() {
        Proyecto activo = new Proyecto();
        activo.setNombre("Proyecto Activo");
        activo.setFechaInicio(LocalDate.now());
        activo.setFechaFin(LocalDate.now().plusDays(5));

        Proyecto finalizado = new Proyecto();
        finalizado.setNombre("Proyecto Finalizado");
        finalizado.setFechaInicio(LocalDate.now().minusDays(10));
        finalizado.setFechaFin(LocalDate.now().minusDays(1));

        proyectoRepository.saveAll(List.of(activo, finalizado));

        List<Proyecto> activos = proyectoRepository.findActivos(LocalDate.now());
        assertThat(activos).extracting(Proyecto::getNombre).contains("Proyecto Activo");
    }
}

