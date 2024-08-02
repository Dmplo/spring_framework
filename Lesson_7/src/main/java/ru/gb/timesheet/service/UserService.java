package ru.gb.timesheet.service;


import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.gb.timesheet.model.Role;
import ru.gb.timesheet.model.User;
import ru.gb.timesheet.model.UserRole;
import ru.gb.timesheet.repository.RoleRepository;
import ru.gb.timesheet.repository.UserRepository;
import ru.gb.timesheet.repository.UserRoleRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;

    public User saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    public void addRoleToUser(String username, String roleName) {
        User user = userRepository.findByUsername(username);
        Role role = roleRepository.findByName(roleName);
        UserRole userRole = new UserRole(null, user, role);
        userRoleRepository.save(userRole);
    }

    public User getUser(String username) {
        return userRepository.findByUsername(username);
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found in the database");
        }
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach( roleList -> {
            authorities.add(new SimpleGrantedAuthority(roleList.getRole().getName()));
        });
        return  new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }
}
