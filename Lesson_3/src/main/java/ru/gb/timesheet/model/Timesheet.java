package ru.gb.timesheet.model;

import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@ToString
public class Timesheet {

  private Long id;
  private Long projectId;
  private int minutes;
  private LocalDate createdAt;

  public Timesheet(Long projectId, int minutes, LocalDate createdAt) {
    this.projectId = projectId;
    this.minutes = minutes;
    this.createdAt = createdAt;
  }
}
