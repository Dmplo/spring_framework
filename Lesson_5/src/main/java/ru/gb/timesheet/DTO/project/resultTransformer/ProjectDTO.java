package ru.gb.timesheet.DTO.project.resultTransformer;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.gb.timesheet.DTO.timesheet.TimesheetTransformerDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.aspectj.runtime.internal.Conversions.longValue;
@Setter
@Getter
@NoArgsConstructor
public class ProjectDTO {

    public static final String ID_ALIAS = "p_id";
    public static final String NAME_ALIAS = "p_name";

    private Long id;
    private String title;

    private List<TimesheetTransformerDTO> timesheets = new ArrayList<>();

    public ProjectDTO(
            Object[] tuples,
            Map<String, Integer> aliasToIndexMap) {

        this.id = longValue(tuples[aliasToIndexMap.get(ID_ALIAS)]);
        this.title = String.valueOf(tuples[aliasToIndexMap.get(NAME_ALIAS)]);
    }

}