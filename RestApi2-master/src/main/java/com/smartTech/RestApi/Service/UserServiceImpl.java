package com.smartTech.RestApi.Service;

import com.smartTech.RestApi.Model.Role;
import com.smartTech.RestApi.Model.User;
import com.smartTech.RestApi.Repository.RoleRepository;
import com.smartTech.RestApi.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
@Service

@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {
    private final RoleRepository roleRepository;
    private  final UserRepository userRepository;




    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       User user =userRepository.findByUsername(username);
       if(user==null) {
           log.error("User not found in the database ");

           throw new UsernameNotFoundException("User not found in the database ");

       } else{
           log.error("User found uin the databse :{}",username);



       }
        Collection<SimpleGrantedAuthority>authorities= new ArrayList<>();
       user.getRoles().forEach(role -> {
           authorities.add(new SimpleGrantedAuthority(role.getName()));
       });
        return new org.springframework.security.core.userdetails.User(user.getUsername()
                ,user.getPassword(),authorities);
    }
    @Override
    public User saveUser(User user) {

        log.info("Saving user in the database", user.getName());
        return userRepository.save(user);
    }

    @Override
    public Role saveRole(Role role) {
        log.info("Saving user in the database", role.getName());
        return roleRepository.save(role);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        log.info("Adding role {} to user{} ", roleName,username);
         User user =userRepository.findByUsername(username);
         Role role=roleRepository.findByName(roleName);
         user.getRoles().add(role);
    }

    @Override
    public User getUser(String username) {
        log.info("fetching user{}", username);
        return userRepository.findByUsername(username);
    }

    @Override
    public List<User> getUsers() {
        log.info("fetching all users in the database");
        return userRepository.findAll();
    }


}
