package ru.gb.timesheet.service;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.timesheet.DTO.employee.EmployeeModelDTO;
import ru.gb.timesheet.DTO.employee.resultTransformer.EmployeeDTO;
import ru.gb.timesheet.DTO.employee.resultTransformer.EmployeeDTOResultTransformer;
import ru.gb.timesheet.model.Employee;
import ru.gb.timesheet.model.Timesheet;
import ru.gb.timesheet.repository.EmployeeProjectRepository;
import ru.gb.timesheet.repository.EmployeeRepository;
import ru.gb.timesheet.repository.TimesheetRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final TimesheetRepository timesheetRepository;
    private final EmployeeProjectRepository employeeProjectRepository;
    private final EntityManager entityManager;

    public Optional<Employee> findById(Long id) {
        return employeeRepository.findById(id);
    }

    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    public void deleteById(Long id) {
        employeeRepository.deleteById(id);
    }

    public List<EmployeeModelDTO> findAll() {
        return employeeRepository.findEmployeesWithProjects();
    }

    public List<Timesheet> findByEmployeeId(Long id) {
        return timesheetRepository.findByEmployeeId(id);
    }

    public List<EmployeeDTO> findProductsByAddressId235(Long id) {
        return entityManager.createNativeQuery("""
                            select
                            p.id as p_id,
                            p.name as p_name,
                            t.id as t_id,
                            t.minutes as t_min,
                            t.created_at as t_created,
                            t.project_id as t_project_id,
                            e.firstname as e_firstname,
                            e.lastname as e_lastname,
                            e.age as e_age,
                            e.salary as e_salary,
                            e.id as e_id
                            from employee e
                            left join employee_project ep on e.id = ep.employee_id
                            left join project p on p.id = ep.project_id
                            left join timesheet t on t.employee_id = e.id
                            where e.id = ?1
                        """).setParameter(1, id)
                .unwrap(org.hibernate.query.Query.class)
                .setResultTransformer(new EmployeeDTOResultTransformer())
                .getResultList();
    }
}
