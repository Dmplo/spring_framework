package ru.gb.timesheet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.gb.timesheet.DTO.employee.EmployeeModelDTO;
import ru.gb.timesheet.model.Employee;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Query(value = "SELECT DISTINCT new ru.gb.timesheet.DTO.employee.EmployeeModelDTO(e.id, e.firstname, e.lastname, e.age, e.salary, size(p))"
                   + " from Employee e LEFT JOIN e.projects p")
    List<EmployeeModelDTO> findEmployeesWithProjects();

}

