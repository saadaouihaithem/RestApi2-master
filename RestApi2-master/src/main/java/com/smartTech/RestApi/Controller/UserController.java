package com.smartTech.RestApi.Controller;

import com.smartTech.RestApi.Model.Role;
import com.smartTech.RestApi.Model.User;
import com.smartTech.RestApi.Service.UserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;



// get all the Users in the database
     @GetMapping("/users")
    public ResponseEntity<List<User>>getUsers(){

        return ResponseEntity.ok().body(userService.getUsers());
    }

    // get all the role in the database

    @PostMapping("/user/save")
    public ResponseEntity<User>saveUser(@RequestBody User user ){
        URI uri =URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/User/save").toUriString());
        return ResponseEntity.created(uri).body(userService.saveUser(user));
    }


    //get all the role to the user in the database


    @PostMapping("/role/save")
    public ResponseEntity<Role>saveUser(@RequestBody Role role ){
        URI uri =URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/Role/save").toUriString());
        return ResponseEntity.created(uri).body(userService.saveRole(role));
    }
// add New Role to User
    @PostMapping("/role/addtouser")
    public ResponseEntity<?>addRoleToUser(@RequestBody RoleToUserForm form ){
        userService.addRoleToUser(form.getUsername(),form.getRoleName());
        return ResponseEntity.ok().build();
    }


}
@Data
class RoleToUserForm {

    private String username ;
    private String RoleName;
}
