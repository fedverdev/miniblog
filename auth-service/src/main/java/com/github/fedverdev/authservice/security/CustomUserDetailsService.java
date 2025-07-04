package com.github.fedverdev.authservice.security;

import com.github.fedverdev.authservice.model.db.AuthUsersTable;
import com.github.fedverdev.authservice.repository.AuthUsersRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@AllArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private AuthUsersRepository authUsersRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AuthUsersTable authUser = authUsersRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
        return new CustomUserDetails(authUser.getId(), username, authUser.getPassword());
    }
}
