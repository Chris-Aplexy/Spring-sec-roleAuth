package com.roleAuth.roleAuthorisation.Service;

import com.roleAuth.roleAuthorisation.Model.User;
import com.roleAuth.roleAuthorisation.Repository.RoleRepository;
import com.roleAuth.roleAuthorisation.Repository.UserRepository;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void registerUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        roleRepository.saveAll(user.getRoles());
    }

    @Secured(("USER"))
    public void getUser(long id){
        userRepository.findById(id).orElseThrow(()-> new UsernameNotFoundException("User does not exist"));
    }

    public  void deleteUser(String userName){
        userRepository.deleteByUserName(userName);
        roleRepository.deleteAll(userRepository.findByUserName(userName).get().getRoles());
    }

    @Secured("ADMIN")
    public void getAll() {
        userRepository.findAll();
    }
}
