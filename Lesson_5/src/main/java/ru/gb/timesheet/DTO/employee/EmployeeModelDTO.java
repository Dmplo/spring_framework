package ru.gb.timesheet.DTO.employee;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeModelDTO {
    private Long id;
    private String firstname;
    private String lastname;
    private int age;
    private int salary;
    private int employeeProjectQty;
}