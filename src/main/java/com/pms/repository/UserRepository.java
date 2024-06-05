package com.pms.repository;



import com.pms.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {


    Optional<User> findByEmailAddress(String emailAddress);
    Optional<User> findByUsername(String username);
    Optional<User> findByEmployeeId(Integer employeeId);
}

