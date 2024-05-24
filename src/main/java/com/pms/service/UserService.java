package com.pms.service;

import com.pms.entity.User;
import com.pms.exception.CustomException;
import com.pms.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.function.EntityResponse;

import java.util.List;
import java.util.Optional;
@Service
public abstract class UserService {

    private final UserRepository userRepository;
    private ResponseEntity ResponseEntity;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public abstract User login(String email, String password) throws CustomException.UserNotFoundException, CustomException.InvalidLoginException, CustomException.UserBlockedException;

    public abstract void forgotPassword(String email) throws CustomException.UserNotFoundException;

    public abstract void resetPassword(String email, String newPassword, String token) throws CustomException.UserNotFoundException, CustomException.InvalidTokenException, CustomException.InvalidDataException;

    public abstract void addUser(User user) throws CustomException.UserAlreadyExistsException, CustomException.InvalidDataException;

    public abstract void deleteUser(User user) throws CustomException.UserNotFoundException, CustomException.InvalidLoginException;

    public abstract User getUserByUsername(String username) throws CustomException.UserAlreadyExistsException, CustomException.InvalidDataException, CustomException.UserNotFoundException;

    public abstract void saveUser(User existingUser) throws CustomException.UserAlreadyExistsException, CustomException.InvalidDataException;

    public abstract User findUserByEmail(String email) throws CustomException.UserNotFoundException;

    public abstract User findUserByEmployeeId(Integer employeeId) throws CustomException.UserNotFoundException;

    public abstract User updateUser(Integer employeeId);

    public abstract Optional<List<User>> getAllUsers();

    public abstract void registerUser(User user) throws CustomException.UserAlreadyExistsException, CustomException.InvalidDataException;

    public abstract Optional<User> getUser(Integer userId);

    public abstract Optional<User> getUserByEmployeeId(Integer employeeId);

    public ResponseEntity<User> ModifyUser(User user){

        Optional<User> user2 = userRepository.findByEmployeeId(user.getEmployeeId());

        return ResponseEntity.ok().body(userRepository.save(user));
    }
    public ResponseEntity<?> deleteUser(int employeeId) throws CustomException.UserNotFoundException {
        Optional<User> userOptional = userRepository.findByEmployeeId(employeeId);
        User existingUser = userOptional.get();
        userRepository.delete(existingUser);
        return ResponseEntity.ok().build();

    }
}
