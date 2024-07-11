package ru.gb.timesheet.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.timesheet.model.Project;
import ru.gb.timesheet.model.Timesheet;
import ru.gb.timesheet.repository.ProjectRepository;
import ru.gb.timesheet.repository.TimesheetRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final TimesheetRepository timesheetRepository;

    public List<Project> getAllProjects() {
        return projectRepository.getAllProjects();
    }

    public Project getProject(Long id) {
        return projectRepository.getProject(id);
    }

    public Project createProject(Project project) {
        return projectRepository.createProject(project);
    }

    public Project updateProject(Long id, Project project) {
        return projectRepository.updateProject(id, project);
    }

    public void deleteProject(Long id) {
        projectRepository.deleteProject(id);
    }

    public List<Timesheet> getAllTimesheetByProjectId(Long projectId) {
        return timesheetRepository.getAllTimesheetByProjectId(projectId);
    }

}
