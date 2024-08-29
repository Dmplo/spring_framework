package ru.gb.timesheet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.gb.timesheet.DTO.employee.EmployeeModelDTO;
import ru.gb.timesheet.DTO.timesheet.TimesheetModelDTO;
import ru.gb.timesheet.model.Timesheet;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TimesheetRepository extends JpaRepository<Timesheet, Long> {

    default List<Timesheet> findByCreatedAtBetweenUnsafe(LocalDate min, LocalDate max) {
        if (min == null && max == null) {
            return findAll();
        } else if (min == null) {
            return findByCreatedAtGreaterThan(max);
        } else if (max == null) {
            return findByCreatedAtLessThan(min);
        }
        return findByCreatedAtBetween(min, max);
    }

     default List<TimesheetModelDTO> findAllCustomCreatedAtBetweenUnsafe(LocalDate min, LocalDate max) {
        if (min == null && max == null) {
            return findAllCustom();
        } else if (min == null) {
            return findAllCustomCreatedAtGreaterThan(max);
        } else if (max == null) {
            return findAllCustomCreatedAtLessThan(min);
        }
        return findAllCustomCreatedAtBetween(min, max);
    }

    List<Timesheet> findByCreatedAtLessThan(LocalDate date);

    List<Timesheet> findByCreatedAtGreaterThan(LocalDate date);

    List<Timesheet> findByCreatedAtBetween(LocalDate min, LocalDate max);

    @Query("select t from Timesheet t where t.projectId = :projectId order by t.createdAt desc")
    List<Timesheet> findByProjectId(Long projectId);

    @Query("select t from Timesheet t where t.employeeId = :employeeId order by t.createdAt desc")
    List<Timesheet> findByEmployeeId(Long employeeId);

    @Query(value = """
            select distinct new ru.gb.timesheet.DTO.timesheet.TimesheetModelDTO(t.id, p.id, e.id, p.name, e.firstname, e.lastname, t.minutes, t.createdAt)
            from Timesheet t
            left join Project p on p.id = t.projectId
            left join Employee e on e.id = t.employeeId
            where t.id = :id
            """)
    Optional<TimesheetModelDTO> findByIdCustom(Long id);

    @Query(value = """
            select distinct new ru.gb.timesheet.DTO.timesheet.TimesheetModelDTO(t.id, p.id, e.id, p.name, e.firstname, e.lastname, t.minutes, t.createdAt)
            from Timesheet t
            left join Project p on p.id = t.projectId
            left join Employee e on e.id = t.employeeId
            """)
    List<TimesheetModelDTO> findAllCustom();

    @Query(value = """
            select distinct new ru.gb.timesheet.DTO.timesheet.TimesheetModelDTO(t.id, p.id, e.id, p.name, e.firstname, e.lastname, t.minutes, t.createdAt)
            from Timesheet t
            left join Project p on p.id = t.projectId
            left join Employee e on e.id = t.employeeId
            where t.createdAt < :date
            """)
    List<TimesheetModelDTO> findAllCustomCreatedAtLessThan(LocalDate date);

    @Query(value = """
            select distinct new ru.gb.timesheet.DTO.timesheet.TimesheetModelDTO(t.id, p.id, e.id, p.name, e.firstname, e.lastname, t.minutes, t.createdAt)
            from Timesheet t
            left join Project p on p.id = t.projectId
            left join Employee e on e.id = t.employeeId
            where t.createdAt > :date
            """)
    List<TimesheetModelDTO> findAllCustomCreatedAtGreaterThan(LocalDate date);

    @Query(value = """
            select distinct new ru.gb.timesheet.DTO.timesheet.TimesheetModelDTO(t.id, p.id, e.id, p.name, e.firstname, e.lastname, t.minutes, t.createdAt)
            from Timesheet t
            left join Project p on p.id = t.projectId
            left join Employee e on e.id = t.employeeId
            where t.createdAt between :min and :max
            """)
    List<TimesheetModelDTO> findAllCustomCreatedAtBetween(LocalDate min, LocalDate max);

}
