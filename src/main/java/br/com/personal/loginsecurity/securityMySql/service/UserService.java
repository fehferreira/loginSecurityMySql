package br.com.personal.loginsecurity.securityMySql.service;

import br.com.personal.loginsecurity.securityMySql.model.Role;
import br.com.personal.loginsecurity.securityMySql.model.User;
import br.com.personal.loginsecurity.securityMySql.repository.RoleRepository;
import br.com.personal.loginsecurity.securityMySql.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;

@Service
public class UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public User findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public User findByUserName(String userName){
        return userRepository.findByUserName(userName);
    }

    public User saveUser(User user){
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(true);
        Role userRole = roleRepository.findByRole("ADMIN");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        return userRepository.save(user);
    }

}
