package ru.gb.timesheet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gb.timesheet.model.UserRole;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {}
