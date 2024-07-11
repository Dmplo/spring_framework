package ru.gb.timesheet.repository;

import org.springframework.stereotype.Repository;
import ru.gb.timesheet.model.Project;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProjectRepository {

    private final List<Project> projects;
    private static Long id = 1L;

    public ProjectRepository() {
        this.projects = new ArrayList<>();
    }

    public List<Project> getAllProjects() {
        return projects;
    }

    public Project getProject(Long id) {
            return projects.stream().filter(p -> p.getId().equals(id)).findFirst().orElse(null);
    }

    public Project createProject(Project project) {
        project.setId(id++);
        projects.add(project);
        return project;
    }

    public Project updateProject(Long id, Project project) {
        Project existingProject = getProject(id);
        if (existingProject != null) {
            existingProject.setName(project.getName());
        }
        return existingProject;
    }

    public void deleteProject(Long id) {
        projects.removeIf(p -> p.getId().equals(id));
    }

}
