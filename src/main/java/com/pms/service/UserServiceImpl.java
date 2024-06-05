package com.pms.service;

import com.pms.entity.User;
import com.pms.exception.CustomException;
import com.pms.repository.UserRepository;
import com.pms.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Component
public class UserServiceImpl extends UserService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmailAddress(email);
        return new org.springframework.security.core.userdetails.User(user.getEmailAddress(),user.getPassword(), user.getAuthorities());
    }
    public UserServiceImpl(UserRepository userRepository) {
        super(userRepository);
    }



    @Override
    public void registerUser(User user) throws CustomException.UserAlreadyExistsException, CustomException.InvalidDataException {
        // Validate user data
        if (userRepository.findByEmailAddress(user.getEmailAddress()) != null) {
            throw new CustomException.UserAlreadyExistsException("User with this email already exists");
        }


        userRepository.save(user);
    }

    @Override
    public User login(String email) throws CustomException.UserNotFoundException, CustomException.InvalidLoginException, CustomException.UserBlockedException {
        User userOptional = userRepository.findByEmailAddress(email);
//        if (userOptional != null) {
//            throw new CustomException.UserNotFoundException("User not found");
//        }
//        if (userOptional.isBlocked()) {
//            throw new CustomException.UserBlockedException("User is blocked");
//        }
//
        // If using password encoder, uncomment the next line
        // if (!passwordEncoder.matches(password, user.getPassword())) {
//        if (!password.equals(userOptional.getPassword())) {
//            throw new CustomException.InvalidLoginException("Invalid email or password");
//        }

        return userOptional;
    }

    @Override
    public void forgotPassword(String email) throws CustomException.UserNotFoundException {
        // Implementation for forgot password
    }

    @Override
    public void resetPassword(String email, String newPassword, String token) throws CustomException.UserNotFoundException, CustomException.InvalidTokenException, CustomException.InvalidDataException {
        // Implementation for reset password
    }

    @Override
    public void addUser(User user) throws CustomException.UserAlreadyExistsException, CustomException.InvalidDataException {
        if (userRepository.findByEmailAddress(user.getEmailAddress()) != null) {
            throw new CustomException.UserAlreadyExistsException("User with this email already exists");
        }
        userRepository.save(user);
    }

    @Override
    public void deleteUser(User user) throws CustomException.UserNotFoundException {
        User existingUser = userRepository.findById(user.getEmployeeId())
                .orElseThrow(() -> new CustomException.UserNotFoundException("User not found"));
        userRepository.delete(existingUser);
    }

    @Override
    public User getUserByUsername(String username) throws CustomException.UserNotFoundException {
       User user = userRepository.findByEmailAddress(username);
                if(user == null){
                    throw new RuntimeException("User not found");
                }
                return user;
    }

    @Override
    public void saveUser(User user) throws CustomException.UserAlreadyExistsException, CustomException.InvalidDataException {
        if (userRepository.existsById(user.getEmployeeId())) {
            throw new CustomException.UserAlreadyExistsException("User with this employee ID already exists");
        }
        userRepository.save(user);
    }

    /**
     * @param email
     * @return
     * @throws CustomException.UserNotFoundException
     */
    @Override
    public User findUserByEmailAddress(String email) throws CustomException.UserNotFoundException {
        return null;
    }


    public User findUserByEmail(String email) throws CustomException.UserNotFoundException {

        User user = userRepository.findByEmailAddress(email);
        if(user == null){
            throw new RuntimeException("User not found");
        }
        return user;
    }

    @Override
    public User findUserByEmployeeId(Integer employeeId) throws CustomException.UserNotFoundException {
        return userRepository.findByEmployeeId(employeeId)
                .orElseThrow(() -> new CustomException.UserNotFoundException("User not found"));
    }

    @Override
    public User updateUser(Integer employeeId) {
        // Implementation for update user
        return null;
    }

    @Override
    public Optional<List<User>> getAllUsers() {
        return Optional.of(userRepository.findAll());
    }

    @Override
    public Optional<User> getUser(Integer userId) {
        return userRepository.findById(userId);
    }

    @Override
    public Optional<User> getUserByEmployeeId(Integer employeeId) {
        return userRepository.findByEmployeeId(employeeId);
    }


    public User getUserByEmployeeId() {
        return null;
    }


}