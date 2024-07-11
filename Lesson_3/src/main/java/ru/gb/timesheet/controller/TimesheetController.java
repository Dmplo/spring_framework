package ru.gb.timesheet.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.timesheet.model.Timesheet;
import ru.gb.timesheet.service.TimesheetService;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/timesheets")
public class TimesheetController {

    private final TimesheetService service;

    public TimesheetController(TimesheetService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Timesheet> get(@PathVariable Long id) {
        Optional<Timesheet> ts = service.getById(id);

        if (ts.isPresent()) {
            return ResponseEntity.ok().body(ts.get());
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Timesheet> create(@RequestBody Timesheet timesheet) {
        timesheet = service.create(timesheet);
        if (timesheet != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(timesheet);
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping
    private ResponseEntity<List<Timesheet>> getAll(@RequestParam(required = false) Map<String, String> requestParams) {

        List<Timesheet> timesheets;

        if (requestParams.containsKey("createdAtAfter")) {
           LocalDate createdAtAfter = LocalDate.parse(requestParams.get("createdAtAfter"));
            timesheets = service.getAllTimesheetByParamCreateAfter(createdAtAfter);
        } else if (requestParams.containsKey("createdAtBefore")) {
            LocalDate createdAtBefore = LocalDate.parse(requestParams.get("createdAtBefore"));
            timesheets = service.getAllTimesheetByParamCreateBefore(createdAtBefore);
        } else {
            timesheets = service.getAll();
        }

        if (!timesheets.isEmpty()) {
            return ResponseEntity.ok().body(timesheets);
        }

        return ResponseEntity.notFound().build();
    }

}
