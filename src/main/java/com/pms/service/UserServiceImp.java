//package com.pms.service;
//
//import com.pms.entity.User;
//import com.pms.exception.CustomException;
//import com.pms.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//// import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class UserServiceImpl extends UserService {
//
//    @Autowired
//    UserRepository userRepository;
//
//    // Uncomment and configure passwordEncoder if needed
//    // @Autowired
//    // PasswordEncoder passwordEncoder;
//
//    @Override
//    public void registerUser(User user) throws CustomException.UserAlreadyExistsException, CustomException.InvalidDataException {
//        // Validate user data
//        if (userRepository.findByEmail(user.getEmailAddress()).isPresent()) {
//            throw new CustomException.UserAlreadyExistsException("User with this email already exists");
//        }
//
//        // If using password encoder, uncomment the next line
//        // user.setPassword(passwordEncoder.encode(user.getPassword()));
//        userRepository.save(user);
//    }
//
//    @Override
//    public User login(String email, String password) throws CustomException.UserNotFoundException, CustomException.InvalidLoginException, CustomException.UserBlockedException {
//        Optional<User> userOptional = userRepository.findByEmail(email);
//        if (userOptional.isEmpty()) {
//            throw new CustomException.UserNotFoundException("User not found");
//        }
//
//        User user = userOptional.get();
//
//        if (user.isBlocked()) {
//            throw new CustomException.UserBlockedException("User is blocked");
//        }
//
//        // If using password encoder, uncomment the next line
//        // if (!passwordEncoder.matches(password, user.getPassword())) {
//        if (!password.equals(user.getPassword())) {
//            throw new CustomException.InvalidLoginException("Invalid email or password");
//        }
//
//        return user;
//    }
//
//    @Override
//    public void forgotPassword(String email) throws CustomException.UserNotFoundException {
//        // Implementation for forgot password
//    }
//
//    @Override
//    public void resetPassword(String email, String newPassword, String token) throws CustomException.UserNotFoundException, CustomException.InvalidTokenException, CustomException.InvalidDataException {
//        // Implementation for reset password
//    }
//
//    @Override
//    public void addUser(User user) throws CustomException.UserAlreadyExistsException, CustomException.InvalidDataException {
//        if (userRepository.findByEmail(user.getEmailAddress()).isPresent()) {
//            throw new CustomException.UserAlreadyExistsException("User with this email already exists");
//        }
//        userRepository.save(user);
//    }
//
//    @Override
//    public void deleteUser(User user) throws CustomException.UserNotFoundException {
//        User existingUser = userRepository.findById(user.getEmployeeId())
//                .orElseThrow(() -> new CustomException.UserNotFoundException("User not found"));
//        userRepository.delete(existingUser);
//    }
//
//    @Override
//    public User getUserByUsername(String username) throws CustomException.UserNotFoundException {
//        return userRepository.findByEmail(username)
//                .orElseThrow(() -> new CustomException.UserNotFoundException("User not found"));
//    }
//
//    @Override
//    public void saveUser(User user) throws CustomException.UserAlreadyExistsException, CustomException.InvalidDataException {
//        if (userRepository.existsById(user.getEmployeeId())) {
//            throw new CustomException.UserAlreadyExistsException("User with this employee ID already exists");
//        }
//        userRepository.save(user);
//    }
//
//    @Override
//    public User findUserByEmail(String email) throws CustomException.UserNotFoundException {
//        return userRepository.findByEmail(email)
//                .orElseThrow(() -> new CustomException.UserNotFoundException("User not found"));
//    }
//
//    @Override
//    public User findUserByEmployeeId(Integer employeeId) throws CustomException.UserNotFoundException {
//        return userRepository.findByEmployeeId(employeeId)
//                .orElseThrow(() -> new CustomException.UserNotFoundException("User not found"));
//    }
//
//    @Override
//    public User updateUser(Integer employeeId) {
//        // Implementation for update user
//        return null;
//    }
//
//    @Override
//    public Optional<List<User>> getAllUsers() {
//        return Optional.of(userRepository.findAll());
//    }
//
//    @Override
//    public Optional<User> getUser(Integer userId) {
//        return userRepository.findById(userId);
//    }
//
//    @Override
//    public User getUserByEmployeeId(Integer employeeId) {
//        return userRepository.findByEmployeeId(employeeId);
//    }
//
//    public Optional<User> findByEmail(String email) {
//        return userRepository.findByEmail(email);
//    }
//}
