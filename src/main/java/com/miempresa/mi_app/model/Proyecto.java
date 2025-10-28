package main.java.com.miempresa.mi_app.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity @Table(name = "proyectos")
public class Proyecto {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String nombre;

  @Column(length = 1000)
  private String descripcion;

  private LocalDate fechaInicio;
  private LocalDate fechaFin;

  // ManyToMany inverso (no dueño). mappedBy apunta al set en Empleado
  @ManyToMany(mappedBy = "proyectos")
  private Set<Empleado> empleados = new HashSet<>();

  // Helpers opcionales
  public void addEmpleado(Empleado e) { empleados.add(e); e.getProyectos().add(this); }
  public void removeEmpleado(Empleado e) { empleados.remove(e); e.getProyectos().remove(this); }


}

