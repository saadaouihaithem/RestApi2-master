package com.smartTech.RestApi.Service;

import com.smartTech.RestApi.Model.Role;
import com.smartTech.RestApi.Model.User;

import java.util.List;

public interface UserService {

    User saveUser(User user);
    Role saveRole(Role role);
    void addRoleToUser(String username, String roleName);
    User getUser(String username);
    List<User> getUsers();


}
