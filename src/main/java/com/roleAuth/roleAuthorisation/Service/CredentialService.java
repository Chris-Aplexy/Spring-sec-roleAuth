package com.roleAuth.roleAuthorisation.Service;

import com.roleAuth.roleAuthorisation.Model.Role;
import com.roleAuth.roleAuthorisation.Model.User;
import com.roleAuth.roleAuthorisation.Repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CredentialService implements UserDetailsService {

    private final UserRepository userRepository;

    public CredentialService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> authUser = userRepository.findByUserName(username);
        authUser.orElseThrow(()-> new UsernameNotFoundException("user does not exist"));

        List<GrantedAuthority> authorities = authUser.get().getRoles().stream()
                .map(Role::getRoleName)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
                /*.getRoles().stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
        */

        return new org.springframework.security.core.userdetails.User(authUser.get().getUserName(),authUser.get().getPassword(),authorities);
    }
}
