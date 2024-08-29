package ru.gb.timesheet.DTO.timesheet;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TimesheetModelDTO {

  private long id;
  private long projectId;
  private long employeeId;
  private String projectName;
  private String employeeFirstname;
  private String employeeLastname;
  private Integer minutes;
  private LocalDate createdAt;

}
