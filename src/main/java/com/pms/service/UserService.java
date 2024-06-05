//package com.pms.service;
//
//import com.pms.entity.User;
//import com.pms.exception.CustomException;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//@Service
//public abstract class UserService {
//    public abstract User login(String email, String password) throws CustomException.UserNotFoundException, CustomException.InvalidLoginException, CustomException.UserBlockedException;
//
//    public abstract void forgotPassword(String email) throws CustomException.UserNotFoundException;
//
//    public abstract void resetPassword(String email, String newPassword, String token) throws CustomException.UserNotFoundException, CustomException.InvalidTokenException, CustomException.InvalidDataException;
//
//    public abstract void addUser(User user) throws CustomException.UserAlreadyExistsException, CustomException.InvalidDataException;
//
//    public abstract void deleteUser(User user) throws CustomException.UserNotFoundException, CustomException.InvalidLoginException;
//
//    public abstract User getUserByUsername(String username) throws CustomException.UserAlreadyExistsException, CustomException.InvalidDataException, CustomException.UserNotFoundException;
//
//    public abstract void saveUser(User existingUser) throws CustomException.UserAlreadyExistsException, CustomException.InvalidDataException;
//
//    public abstract User findUserByEmail(String email) throws CustomException.UserNotFoundException;
//
//    public abstract User findUserByEmployeeId(Integer employeeId) throws CustomException.UserNotFoundException;
//
//    public abstract User updateUser(Integer employeeId);
//
//    public abstract Optional<List<User>> getAllUsers();
//
//    public abstract void registerUser(User user) throws CustomException.UserAlreadyExistsException, CustomException.InvalidDataException;
//
//    public abstract Optional<User> getUser(Integer userId);
//
//    public abstract User getUserByEmployeeId(Integer employeeId);
//}
