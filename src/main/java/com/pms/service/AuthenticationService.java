package com.pms.service;

import com.pms.Config.JWTGenerator;
import com.pms.Config.LoginRequest;
import com.pms.entity.User;
import com.pms.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AuthenticationService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    CustomUserDetailsService service;


    @Autowired
    JWTGenerator tokenService;
    // In-memory user store (replace with database in real applications)
    private static final Map<String, String> users = new HashMap<>();
    static {
        users.put("username", "password");
    }

    // In-memory token store (replace with database in real applications)
    private static final Map<String, String> tokens = new HashMap<>();



    public String authenticate(LoginRequest loginRequest) {
        String email = loginRequest.getEmailAddress();
        String pass = loginRequest.getPassword();

        User user = userRepository.findByEmailAddress(email);
        System.out.println("123344"+user);
        if (user != null) {
            //check if password matches

            if (pass.equals(user.getPassword())) {
                UserDetails userdetails = service.loadUserByUsername(email);
                System.out.println("1222" +userdetails);
                return tokenService.generateToken(userdetails);
            } else {
                throw new RuntimeException("Password does not match");
            }
        } else {
            throw new UsernameNotFoundException("User not found");
        }


    }

    public String generateToken() {
        String token = UUID.randomUUID().toString();
        tokens.put(token, getUsernameFromToken(token));
        return token;
    }

    public boolean validateToken(String token) {
        return tokens.containsKey(token);
    }

    public String getUsernameFromToken(String token) {
        return tokens.get(token);
    }
}
