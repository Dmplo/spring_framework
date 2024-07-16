package ru.gb.timesheet.page;

import lombok.Data;
import ru.gb.timesheet.model.Timesheet;

import java.util.List;

@Data
public class ProjectPageDto {

  private String id;
  private String name;
  private List<Timesheet> timesheets;

}
