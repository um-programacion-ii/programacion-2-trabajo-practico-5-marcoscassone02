package main.java.com.miempresa.mi_app.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity @Table(name = "empleados")
public class Empleado {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String nombre;
  private String apellido;

  @Column(unique = true)
  private String email;

  private LocalDate fechaContratacion;
  private Double salario;

  // ManyToOne: lado dueño en Empleado con FK departamento_id
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "departamento_id")
  private Departamento departamento;

  // ManyToMany: lado dueño en Empleado con join table
  @ManyToMany
  @JoinTable(name = "empleado_proyecto",
      joinColumns = @JoinColumn(name = "empleado_id"),
      inverseJoinColumns = @JoinColumn(name = "proyecto_id"))
  private Set<Proyecto> proyectos = new HashSet<>();

  // Helpers
  public void setDepartamento(Departamento d) { this.departamento = d; }
  public void addProyecto(Proyecto p) { this.proyectos.add(p); p.getEmpleados().add(this); }
  public void removeProyecto(Proyecto p) { this.proyectos.remove(p); p.getEmpleados().remove(this); }

}

}
