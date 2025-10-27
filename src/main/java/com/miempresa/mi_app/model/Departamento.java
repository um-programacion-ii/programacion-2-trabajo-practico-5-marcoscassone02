package com.miempresa.miapp.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity @Table(name = "departamentos")
public class Departamento {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String nombre;

  @Column(length = 1000)
  private String descripcion;

  // OneToMany: lado inverso (no dueño). mappedBy apunta al campo en Empleado
  @OneToMany(mappedBy = "departamento",
             cascade = CascadeType.ALL,
             orphanRemoval = true)
  private List<Empleado> empleados = new ArrayList<>();

  // Helpers
  public void addEmpleado(Empleado e) { empleados.add(e); e.setDepartamento(this); }
  public void removeEmpleado(Empleado e) { empleados.remove(e); e.setDepartamento(null); }

}


