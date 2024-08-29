package ru.gb.timesheet.DTO.employee.resultTransformer;

import org.hibernate.transform.ResultTransformer;
import ru.gb.timesheet.DTO.timesheet.TimesheetTransformerDTO;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.aspectj.runtime.internal.Conversions.longValue;


public class EmployeeDTOResultTransformer implements ResultTransformer {

    private Map<Long, EmployeeDTO> employeeDTOMap = new LinkedHashMap<>();
    @Override
    public Object transformTuple(
            Object[] tuple,
            String[] aliases) {

        Map<String, Integer> aliasToIndexMap = aliasToIndexMap(aliases);

        Long employeeId = longValue(tuple[aliasToIndexMap.get(EmployeeDTO.ID_ALIAS)]);

        EmployeeDTO employeeDTO = employeeDTOMap.computeIfAbsent(
                employeeId,
                id -> new EmployeeDTO(tuple, aliasToIndexMap)
        );

        employeeDTO.getProjects().add(
                new EmployeeProjectDTO(tuple, aliasToIndexMap)
        );
        employeeDTO.getTimesheets().add(
                new TimesheetTransformerDTO(tuple, aliasToIndexMap)
        );

        return employeeDTO;
    }


    public  Map<String, Integer> aliasToIndexMap(
            String[] aliases) {

        Map<String, Integer> aliasToIndexMap = new LinkedHashMap<>();

        for (int i = 0; i < aliases.length; i++) {
            aliasToIndexMap.put(aliases[i].toLowerCase(), i);
        }

        return aliasToIndexMap;
    }

    @Override
    public List transformList(List resultList) {
        return new ArrayList<>(employeeDTOMap.values());    }
}
