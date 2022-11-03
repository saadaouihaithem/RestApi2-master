package com.smartTech.RestApi.Repository;

import com.smartTech.RestApi.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
 User findByUsername(String username);

}
