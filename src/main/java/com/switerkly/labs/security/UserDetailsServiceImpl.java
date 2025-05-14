package com.switerkly.labs.security;

import com.switerkly.labs.user.Role;
import com.switerkly.labs.user.User;
import com.switerkly.labs.user.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

     @PostConstruct
     void init() {
         User superadmin = User.builder()
                 .firstName("Serhii")
                 .lastName("Sh")
                 .email("superadmin@gmail.com")
                 .password(passwordEncoder.encode("password"))
                 .enabled(true)
                 .accountLocked(false)
                 .roles(List.of(Role.SUPERADMIN))
                 .build();

         User admin = User.builder()
                 .firstName("Serhii")
                 .lastName("Sh")
                 .email("admin@gmail.com")
                 .password(passwordEncoder.encode("password"))
                 .enabled(true)
                 .accountLocked(false)
                 .roles(List.of(Role.ADMIN))
                 .build();

         User user = User.builder()
                 .firstName("Serhii")
                 .lastName("Sh")
                 .email("user@gmail.com")
                 .password(passwordEncoder.encode("password"))
                 .enabled(true)
                 .accountLocked(false)
                 .roles(List.of(Role.USER))
                 .build();

         repository.deleteAll();
         repository.save(superadmin);
         repository.save(admin);
         repository.save(user);
     }

    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        return repository.findByEmail(userEmail)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
