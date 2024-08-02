package ru.gb.timesheet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.timesheet.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
