package ru.gb.timesheet.DTO.employee.resultTransformer;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Map;

import static org.aspectj.runtime.internal.Conversions.longValue;

@Data
@NoArgsConstructor
public class EmployeeProjectDTO {

    public static final String ID_ALIAS = "p_id";
    public static final String NAME_ALIAS = "p_name";

    @EqualsAndHashCode.Include
    private Long id;
    private String name;

    public EmployeeProjectDTO(
            Object[] tuples,
            Map<String, Integer> aliasToIndexMap) {
        this.id = longValue(tuples[aliasToIndexMap.get(ID_ALIAS)]);
        this.name = String.valueOf(tuples[aliasToIndexMap.get(NAME_ALIAS)]);
    }





}