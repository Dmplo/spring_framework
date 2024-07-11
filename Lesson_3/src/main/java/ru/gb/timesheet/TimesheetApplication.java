package ru.gb.timesheet;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ru.gb.timesheet.model.Project;
import ru.gb.timesheet.model.Timesheet;
import ru.gb.timesheet.repository.ProjectRepository;
import ru.gb.timesheet.repository.TimesheetRepository;

import java.time.LocalDate;

@SpringBootApplication
public class TimesheetApplication {

	public static void main(String[] args) {
		SpringApplication.run(TimesheetApplication.class, args);
	}


    @Bean
	CommandLineRunner projects(ProjectRepository projectRepository) {
        return args -> {
            projectRepository.createProject(new Project("work"));
            projectRepository.createProject(new Project("sleep"));
            projectRepository.createProject(new Project("eat"));
        };
    }

	@Bean
	CommandLineRunner timesheet(TimesheetRepository timesheetRepository) {
		return args -> {
			timesheetRepository.create(new Timesheet(1L, 3, LocalDate.of(2024, 7, 1)));
			timesheetRepository.create(new Timesheet(2L, 5, LocalDate.of(2024, 7, 5)));
			timesheetRepository.create(new Timesheet(2L, 6, LocalDate.of(2024, 7, 6)));
		};
	}

}
