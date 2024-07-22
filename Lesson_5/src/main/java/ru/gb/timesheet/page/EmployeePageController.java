package ru.gb.timesheet.page;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.gb.timesheet.DTO.employee.resultTransformer.EmployeeDTO;
import ru.gb.timesheet.DTO.employee.EmployeeModelDTO;
import ru.gb.timesheet.service.EmployeeService;

import java.util.List;
import java.util.NoSuchElementException;

@Controller
@RequestMapping("/home/employees")
@RequiredArgsConstructor
public class EmployeePageController {

  private final EmployeeService service;

  @GetMapping
  public String getAllEmployees(Model model) {
    List<EmployeeModelDTO> employees = service.findAll();
    model.addAttribute("employees", employees);
    return "employees-page.html";
  }

  @GetMapping("/{id}")
  public String getEmployeePage(@PathVariable Long id, Model model) {
    List<EmployeeDTO> employees = service.findProductsByAddressId235(id);
    if (employees.isEmpty()) {
      throw new NoSuchElementException();
    }
    model.addAttribute("employees", employees);
    return "employee-page.html";
  }

}
