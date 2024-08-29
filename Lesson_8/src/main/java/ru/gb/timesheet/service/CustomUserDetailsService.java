package ru.gb.timesheet.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ru.gb.timesheet.model.User;
import ru.gb.timesheet.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

  private final UserRepository userRepository;

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