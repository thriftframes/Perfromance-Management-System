package com.pms.controller;

import com.pms.entity.User;
import com.pms.exception.CustomException;
import com.pms.exception.ResourceNotFoundException;
import com.pms.repository.UserRepository;
import com.pms.service.UserServiceImpl;
import lombok.Data;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.pms.exception.CustomException.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) throws InvalidDataException, UserAlreadyExistsException {
        userService.registerUser(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @GetMapping("/getAllUsers")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<User> getUserByEmployeeId(@PathVariable Integer employeeId) {
        User user = userRepository.findByEmployeeId(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("User does not exist with employeeId: " + employeeId));
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/delete/{EmployeeId}")
    public ResponseEntity<ResponseEntity<?>> deleteUser(@PathVariable("EmployeeId") int employeeId) {
        try {

                return ResponseEntity.ok().body(userService.deleteUser(employeeId));

        } catch (UserNotFoundException e) {
            return null;
        }
    }

    @PutMapping("/modify")
    public ResponseEntity<User> modifyUser(@RequestBody User user) throws CustomException.UserNotFoundException {
         {
             userService.ModifyUser(user);
            // Fetch the existing user by employee ID
//            return new ResponseEntity<>()ResponseEntity.ok(userService.ModifyUser(user));
            return new ResponseEntity<>(user, HttpStatus.FOUND);


//        } catch (CustomException.InvalidDataException | CustomException.UserAlreadyExistsException e) {
//            return ResponseEntity.badRequest().body(e.getMessage());
//        }
    }}


    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest) {
        try {
            User user = userService.login(loginRequest.getEmail(), loginRequest.getPassword());
            return ResponseEntity.ok(user);
        } catch (InvalidLoginException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        } catch (UserBlockedException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestParam String email) {
        try {
            userService.forgotPassword(email);
            return ResponseEntity.ok("Forgot password request sent successfully.");
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestParam String email, @RequestParam String newPassword, @RequestParam String token) {
        try {
            userService.resetPassword(email, newPassword, token);
            return ResponseEntity.ok("Password reset successfully.");
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (InvalidTokenException | InvalidDataException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Setter
    @Data
    public static class LoginRequest {
        private String email;
        private String password;

    }
}
