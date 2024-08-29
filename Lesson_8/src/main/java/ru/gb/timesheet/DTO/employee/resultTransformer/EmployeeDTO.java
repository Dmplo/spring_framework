package ru.gb.timesheet.DTO.employee.resultTransformer;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.gb.timesheet.DTO.timesheet.TimesheetTransformerDTO;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.aspectj.runtime.internal.Conversions.intValue;
import static org.aspectj.runtime.internal.Conversions.longValue;
@Setter
@Getter
@NoArgsConstructor
public class EmployeeDTO {

    public static final String ID_ALIAS = "e_id";
    public static final String FIRSTNAME_ALIAS = "e_firstname";
    public static final String LASTNAME_ALIAS = "e_lastname";
    public static final String AGE_ALIAS = "e_age";
    public static final String SALARY_ALIAS = "e_salary";

    private Long id;
    private String firstname;
    private String lastname;
    private int age;
    private int salary;

    private Set<EmployeeProjectDTO> projects = new HashSet<>();
    private Set<TimesheetTransformerDTO> timesheets = new HashSet<>();

    public EmployeeDTO(
            Object[] tuples,
            Map<String, Integer> aliasToIndexMap) {

        this.id = longValue(tuples[aliasToIndexMap.get(ID_ALIAS)]);
        this.firstname = String.valueOf(tuples[aliasToIndexMap.get(FIRSTNAME_ALIAS)]);
        this.lastname = String.valueOf(tuples[aliasToIndexMap.get(LASTNAME_ALIAS)]);
        this.age = intValue(tuples[aliasToIndexMap.get(AGE_ALIAS)]);
        this.salary = intValue(tuples[aliasToIndexMap.get(SALARY_ALIAS)]);
    }

}