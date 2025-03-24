package com.godoc.service.credentials;

import com.godoc.service.credentials.entity.Credentials;
import com.godoc.service.credentials.repository.CredentialsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CredentialService implements UserDetailsService {

    @Autowired
    private CredentialsRepository credentialsRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Credentials credentials = credentialsRepository.findByUsername(username);

        if (credentials != null) {
           UserDetailsImpl userDetails = new UserDetailsImpl();

           userDetails.setUsername(credentials.getUsername());
           userDetails.setPassword(credentials.getPassword());
           userDetails.setRoles(
                   credentials.getRoles()
                           .stream()
                           .map(Role::valueOf)
                           .toList()
           );

           return userDetails;
        }

        return null;
    }
}
