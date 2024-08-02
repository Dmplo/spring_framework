package ru.gb.timesheet.DTO.project.resultTransformer;

import org.hibernate.transform.ResultTransformer;
import ru.gb.timesheet.DTO.timesheet.TimesheetTransformerDTO;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.aspectj.runtime.internal.Conversions.longValue;


public class ProjectDTOResultTransformer implements ResultTransformer {

    private Map<Long, ProjectDTO> projectDTOMap = new LinkedHashMap<>();
    @Override
    public Object transformTuple(
            Object[] tuple,
            String[] aliases) {

        Map<String, Integer> aliasToIndexMap = aliasToIndexMap(aliases);

        Long projectId = longValue(tuple[aliasToIndexMap.get(ProjectDTO.ID_ALIAS)]);

        ProjectDTO projectDTO = projectDTOMap.computeIfAbsent(
                projectId,
                id -> new ProjectDTO(tuple, aliasToIndexMap)
        );

        projectDTO.getTimesheets().add(
                new TimesheetTransformerDTO(tuple, aliasToIndexMap)
        );

        return projectDTO;
    }


    public  Map<String, Integer> aliasToIndexMap(
            String[] aliases) {

        Map<String, Integer> aliasToIndexMap = new LinkedHashMap<>();

        for (int i = 0; i < aliases.length; i++) {
            aliasToIndexMap.put(aliases[i], i);
        }

        return aliasToIndexMap;
    }

    @Override
    public List transformList(List resultList) {
        return new ArrayList<>(projectDTOMap.values());    }
}
