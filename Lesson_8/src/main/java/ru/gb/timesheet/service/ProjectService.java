package ru.gb.timesheet.service;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.timesheet.DTO.project.resultTransformer.ProjectDTO;
import ru.gb.timesheet.DTO.project.resultTransformer.ProjectDTOResultTransformer;
import ru.gb.timesheet.aspect.Recover;
import ru.gb.timesheet.model.Project;
import ru.gb.timesheet.repository.ProjectRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final EntityManager entityManager;

    public Optional<Project> findById(Long id) {
        return projectRepository.findById(id);
    }
@Recover
    public List<Project> findAll() {
        return projectRepository.findAll();
    }

    public Project create(Project project) {
        return projectRepository.save(project);
    }

    public void delete(Long id) {
        projectRepository.deleteById(id);
    }

    public List<ProjectDTO> findTimesheetsByProjectId(Long id) {
        return entityManager.createQuery("""
                     select p.id as p_id,
                            p.name as p_name,
                            t.id as t_id,
                            t.minutes as t_min,
                            t.projectId as t_project_id,
                            t.createdAt as t_created
                        from Project p
                        left join Timesheet t on t.projectId = p.id
                       where p.id = ?1
                        """).setParameter(1, id)
                .unwrap(org.hibernate.query.Query.class)
                .setResultTransformer(new ProjectDTOResultTransformer())
                .getResultList();
    }
}
