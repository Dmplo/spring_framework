package ru.gb.timesheet.page;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.gb.timesheet.DTO.project.resultTransformer.ProjectDTO;
import ru.gb.timesheet.service.ProjectService;

import java.util.List;
import java.util.NoSuchElementException;

@Controller
@RequestMapping("/home/projects")
@RequiredArgsConstructor
public class ProjectPageController {

  private final ProjectService service;

  @GetMapping("/{id}")
  public String getProjectPage(@PathVariable Long id, Model model) {
    List<ProjectDTO> project = service.findTimesheetsByProjectId(id);
    if (project.isEmpty()) {
      throw new NoSuchElementException();
    }
    model.addAttribute("projects", project);
    return "project-page.html";
  }

}
