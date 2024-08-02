package ru.gb.timesheet.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.timesheet.DTO.project.resultTransformer.ProjectDTO;
import ru.gb.timesheet.model.Project;
import ru.gb.timesheet.service.ProjectService;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("api/projects")
@Tag(name = "Projects", description = "API для работы с проектами")
public class ProjectController {

    private final ProjectService service;

    public ProjectController(ProjectService service) {
        this.service = service;
    }

    @Operation(
            summary = "Get Project",
            description = "Получить проект по его идентификатору",
            responses = {
                    @ApiResponse(description = "Успешный ответ", responseCode = "200", content = @Content(schema = @Schema(implementation = Project.class))),
                    @ApiResponse(description = "Проект не найден", responseCode = "404", content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
                    @ApiResponse(description = "Внутренняя ошибка", responseCode = "500", content = @Content(schema = @Schema(implementation = Void.class)))
            })
    @GetMapping("/{id}")
    public ResponseEntity<Project> get(@PathVariable @Parameter(description = "Идентификатор проекта") Long id) {
        Project project = service.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Проект не найден"));
        return ResponseEntity.ok(project);
    }

    @Operation(
            summary = "Get Timesheets by Project id",
            description = "Получить записи учета рабочего времени по его идентификатору проекта")
    @GetMapping("/{id}/timesheets")
    public ResponseEntity<List<ProjectDTO>> getTimesheetsByProjectId(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(service.findTimesheetsByProjectId(id));
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(
            summary = "Get all Projects id",
            description = "Получить все проекты")
    @GetMapping
    public ResponseEntity<List<Project>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @Operation(
            summary = "Create new project",
            description = "Создать новый проект")
    @PostMapping
    public ResponseEntity<Project> create(@RequestBody Project project) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(project));
    }

    @Operation(
            summary = "Delete project by id",
            description = "Удалить проект по идентификатору")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
