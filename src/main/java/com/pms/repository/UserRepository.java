package com.pms.repository;



import com.pms.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
//    Optional<User> findByEmail(String email);
    Optional<User> findByEmailAddress(String email);
    Optional<User> findByEmployeeId(Integer employeeId);

}

