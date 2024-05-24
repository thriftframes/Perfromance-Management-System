package com.pms.repository;



import com.pms.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    //    Optional<User> findByEmail(String email);
    User findByEmailAddress(String emailAddress);
    Optional<User> findByEmployeeId(Integer employeeId);
    Optional<User> findByUsername(String username);

}
