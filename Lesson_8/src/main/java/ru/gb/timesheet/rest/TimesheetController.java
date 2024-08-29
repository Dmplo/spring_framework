package ru.gb.timesheet.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.timesheet.model.Timesheet;
import ru.gb.timesheet.service.TimesheetService;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("api/timesheets")
public class TimesheetController {
  private final TimesheetService service;

  public TimesheetController(TimesheetService service) {
    this.service = service;
  }

  @GetMapping("/{id}")
  public ResponseEntity<Timesheet> get(@PathVariable Long id) {
    return service.findById(id)
      .map(ResponseEntity::ok)
      .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @GetMapping
  public ResponseEntity<List<Timesheet>> getAll(
    @RequestParam(required = false) LocalDate createdAtBefore,
    @RequestParam(required = false) LocalDate createdAtAfter
  ) {
    return ResponseEntity.ok(service.findAll(createdAtBefore, createdAtAfter));
  }

  @PostMapping
  public ResponseEntity<Timesheet> create(@RequestBody Timesheet timesheet) {
    final Timesheet created = service.create(timesheet);

    return ResponseEntity.status(HttpStatus.CREATED).body(created);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    service.delete(id);

    return ResponseEntity.noContent().build();
  }

  @ExceptionHandler(NoSuchElementException.class)
  public ResponseEntity<?> handleNoSuchElementException(NoSuchElementException e) {
    return ResponseEntity.notFound().build();
  }

}
