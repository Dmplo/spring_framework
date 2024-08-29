package ru.gb.timesheet.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "project")
public class Project {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  @EqualsAndHashCode.Include
  private Long id;
  private String name;

  @OneToMany(
          mappedBy = "project",
          cascade = CascadeType.ALL,
          orphanRemoval = true
  )
  private List<EmployeeProject> employees = new ArrayList<>();

}
