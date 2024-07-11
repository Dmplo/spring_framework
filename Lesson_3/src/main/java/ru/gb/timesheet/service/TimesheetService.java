package ru.gb.timesheet.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.timesheet.model.Timesheet;
import ru.gb.timesheet.repository.ProjectRepository;
import ru.gb.timesheet.repository.TimesheetRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TimesheetService {

  private final TimesheetRepository timesheetRepository;
  private final ProjectRepository projectRepository;

  public Optional<Timesheet> getById(Long id) {
    return timesheetRepository.getById(id);
  }

  public List<Timesheet> getAll() {
    return timesheetRepository.getAll();
  }

  public Timesheet create(Timesheet timesheet) {
    if (projectRepository.getProject(timesheet.getProjectId()) != null) {
      timesheet.setCreatedAt(LocalDate.now());
      return timesheetRepository.create(timesheet);
    }
    return null;
  }

  public void delete(Long id) {
    timesheetRepository.delete(id);
  }

  public List<Timesheet> getAllTimesheetByParamCreateAfter(LocalDate date) {
   return timesheetRepository.getAllTimesheetByParamCreateAfter(date);
  }

  public List<Timesheet> getAllTimesheetByParamCreateBefore(LocalDate date) {
    return timesheetRepository.getAllTimesheetByParamCreateBefore(date);
  }

}
