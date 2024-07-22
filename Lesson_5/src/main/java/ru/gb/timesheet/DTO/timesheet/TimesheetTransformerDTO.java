package ru.gb.timesheet.DTO.timesheet;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Map;

import static org.aspectj.runtime.internal.Conversions.longValue;

@Data
@NoArgsConstructor
public class TimesheetTransformerDTO {

    public static final String ID_ALIAS = "t_id";
    public static final String MIN_ALIAS = "t_min";
    public static final String CREATED_ALIAS = "t_created";
    public static final String PROJECT_ID_ALIAS = "t_project_id";

    @EqualsAndHashCode.Include
    private Long id;
    private Long projectId;
    private String minutes;
    private String createdAt;

    public TimesheetTransformerDTO(
            Object[] tuples,
            Map<String, Integer> aliasToIndexMap) {
        this.id = longValue(tuples[aliasToIndexMap.get(ID_ALIAS)]);
        this.projectId = longValue(tuples[aliasToIndexMap.get(PROJECT_ID_ALIAS)]);
        this.minutes = String.valueOf(tuples[aliasToIndexMap.get(MIN_ALIAS)]);
        this.createdAt = String.valueOf(tuples[aliasToIndexMap.get(CREATED_ALIAS)]);
    }

}