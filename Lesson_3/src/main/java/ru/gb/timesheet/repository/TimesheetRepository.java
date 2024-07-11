package ru.gb.timesheet.repository;

import org.springframework.stereotype.Repository;
import ru.gb.timesheet.model.Timesheet;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class TimesheetRepository {

    private static Long sequence = 1L;
    private final List<Timesheet> allTimesheet = new ArrayList<>();

    public Optional<Timesheet> getById(Long id) {
        return allTimesheet.stream()
                .filter(it -> Objects.equals(it.getId(), id))
                .findFirst();
    }

    public List<Timesheet> getAll() {
        return List.copyOf(allTimesheet);
    }

    public Timesheet create(Timesheet timesheet) {
        timesheet.setId(sequence++);
        allTimesheet.add(timesheet);
        return timesheet;
    }

    public void delete(Long id) {
        allTimesheet.stream()
                .filter(it -> Objects.equals(it.getId(), id))
                .findFirst()
                .ifPresent(allTimesheet::remove);
    }

    public List<Timesheet> getAllTimesheetByProjectId(Long projectId) {
        return allTimesheet.stream().filter(t -> t.getProjectId().equals(projectId)).toList();
    }

    public List<Timesheet> getAllTimesheetByParamCreateAfter(LocalDate date) {
        return allTimesheet.stream().filter(t -> t.getCreatedAt().isAfter(date)).toList();
    }

    public List<Timesheet> getAllTimesheetByParamCreateBefore(LocalDate date) {
        return allTimesheet.stream().filter(t -> t.getCreatedAt().isBefore(date)).toList();
    }

}
