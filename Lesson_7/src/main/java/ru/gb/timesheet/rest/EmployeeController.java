package ru.gb.timesheet.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.timesheet.DTO.employee.EmployeeModelDTO;
import ru.gb.timesheet.DTO.employee.resultTransformer.EmployeeDTO;
import ru.gb.timesheet.model.Employee;
import ru.gb.timesheet.model.Timesheet;
import ru.gb.timesheet.service.EmployeeService;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("api/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping("/{id}")
    public ResponseEntity<Employee> get(@PathVariable Long id) {
        return employeeService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/timesheets")
    public ResponseEntity<List<Timesheet>> getTimesheets(@PathVariable Long id) {
        return ResponseEntity.ok(employeeService.findByEmployeeId(id));
    }

    @GetMapping("/{id}/info")
    public ResponseEntity<List<EmployeeDTO>> getProjects(@PathVariable Long id) {
        return ResponseEntity.ok(employeeService.findProductsByAddressId235(id));
    }

    @GetMapping
    public ResponseEntity<List<EmployeeModelDTO>> getAll() {
        return ResponseEntity.ok(employeeService.findAll());
    }

    @PostMapping
    public ResponseEntity<Employee> create(@RequestBody Employee employee) {
        final Employee created = employeeService.save(employee);

        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        employeeService.deleteById(id);

        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<?> handleNoSuchElementException(NoSuchElementException e) {
        return ResponseEntity.notFound().build();
    }
}
