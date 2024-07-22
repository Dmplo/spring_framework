package ru.gb.timesheet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.gb.timesheet.model.*;
import ru.gb.timesheet.repository.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

@SpringBootApplication
public class TimesheetApplication {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(TimesheetApplication.class, args);

        ProjectRepository projectRepo = ctx.getBean(ProjectRepository.class);
        TimesheetRepository timesheetRepo = ctx.getBean(TimesheetRepository.class);
        EmployeeRepository employeeRepo = ctx.getBean(EmployeeRepository.class);
        EmployeeProjectRepository employeeProjectRepo = ctx.getBean(EmployeeProjectRepository.class);

        List<Project> projects = new ArrayList<>();
        List<Employee> employees = new ArrayList<>();
        List<String> names = List.of("Johny", "Leo", "Brad", "Arnold", "Will", "Chuck", "Jackie", "Donald");
        List<String> lastnames = List.of("Depp", "Da Vinci", "Pitt", "Schwarzenegger", "Smith", "Norris", "Chan", "Duck");

        for (int i = 1; i <= 10; i++) {
            Employee employee = new Employee();
            employee.setId((long) i);
            employee.setFirstname(getRandom(names));
            employee.setLastname(getRandom(lastnames));
            employee.setAge(ThreadLocalRandom.current().nextInt(20, 60));
            employee.setSalary(ThreadLocalRandom.current().nextInt(50000, 100000));
            employeeRepo.save(employee);
            employees.add(employee);
        }


        for (int i = 1; i <= 5; i++) {
            Project project = new Project();
            project.setId((long) i);
            project.setName("Project #" + i);
            projectRepo.save(project);
            projects.add(project);
        }


        LocalDate createdAt = LocalDate.now();
        for (int i = 1; i <= 10; i++) {
            createdAt = createdAt.plusDays(1);
            Timesheet timesheet = new Timesheet();
            timesheet.setId((long) i);
            timesheet.setProjectId(ThreadLocalRandom.current().nextLong(1, 6));
            timesheet.setCreatedAt(createdAt);
            timesheet.setMinutes(ThreadLocalRandom.current().nextInt(100, 1000));
            timesheet.setEmployeeId(getRandom(employees).getId());
            timesheetRepo.save(timesheet);
        }


        for (int i = 1; i <= 10; i++) {
            EmployeeProject employeeProject = new EmployeeProject();
            employeeProject.setId((long) i);
            Optional<Timesheet> timesheet = timesheetRepo.findById((long) i);

            if (timesheet.isPresent()) {
                System.out.println(timesheet.get());
                Project project = projects.stream().filter(p -> p.getId().equals(timesheet.get().getProjectId())).findFirst().get();
                Employee employee = employees.stream().filter(e -> e.getId().equals(timesheet.get().getEmployeeId())).findFirst().get();
                employeeProject.setProject(project);
                employeeProject.setEmployee(employee);
                employeeProjectRepo.save(employeeProject);
            }
        }
    }

    public static <T> T getRandom(List<? extends T> items) {
        int randomIndex = ThreadLocalRandom.current().nextInt(0, items.size());
        return items.get(randomIndex);
    }

}
