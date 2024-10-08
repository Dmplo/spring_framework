package ru.gb.timesheet.service;

import org.slf4j.event.Level;
import org.springframework.stereotype.Service;
import ru.gb.timesheet.DTO.timesheet.TimesheetModelDTO;
import ru.gb.timesheet.aspect.Recover;
import ru.gb.timesheet.aspect.Timer;
import ru.gb.timesheet.model.Timesheet;
import ru.gb.timesheet.repository.ProjectRepository;
import ru.gb.timesheet.repository.TimesheetRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

@Service
@Timer(level = Level.TRACE)
public class TimesheetService {

    private final TimesheetRepository timesheetRepository;
    private final ProjectRepository projectRepository;

    public TimesheetService(TimesheetRepository repository, ProjectRepository projectRepository) {
        this.timesheetRepository = repository;
        this.projectRepository = projectRepository;
    }

    @Recover
    public Optional<Timesheet> findById(Long id) {
        return timesheetRepository.findById(id);
    }

    public List<Timesheet> findAll(LocalDate createdAtBefore, LocalDate createdAtAfter) {
        return timesheetRepository.findByCreatedAtBetweenUnsafe(createdAtBefore, createdAtAfter);
    }

    public Optional<TimesheetModelDTO> findByIdCustom(Long id) {
        return timesheetRepository.findByIdCustom(id);
    }

    public List<TimesheetModelDTO> findAllCustom(LocalDate createdAtBefore, LocalDate createdAtAfter) {
        return timesheetRepository.findAllCustomCreatedAtBetweenUnsafe(createdAtBefore, createdAtAfter);
    }

    public Timesheet create(Timesheet timesheet) {
        if (Objects.isNull(timesheet.getProjectId())) {
            throw new IllegalArgumentException("projectId must not be null");
        }

        if (projectRepository.findById(timesheet.getProjectId()).isEmpty()) {
            throw new NoSuchElementException("Project with id " + timesheet.getProjectId() + " does not exists");
        }

        timesheet.setCreatedAt(LocalDate.now());
        return timesheetRepository.save(timesheet);
    }

    @Timer(enabled = false)
    @Recover
    public void delete(Long id) {
        timesheetRepository.deleteById(id);
    }

}