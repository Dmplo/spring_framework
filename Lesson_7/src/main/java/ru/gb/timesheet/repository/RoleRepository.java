package ru.gb.timesheet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.timesheet.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
