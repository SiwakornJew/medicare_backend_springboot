package com.medicare_backend.medicare_backend.service;

import com.medicare_backend.medicare_backend.entity.Authentication;
import com.medicare_backend.medicare_backend.entity.User;
import com.medicare_backend.medicare_backend.repository.UserRepository;
import com.medicare_backend.medicare_backend.service.AuthenticationService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    private AuthenticationService authservice;

    private TokenAuthenticationService tokenService;

    // Method User

    public String registerUser(User user) {
        String returnString = "";
        try {
            List<User> userIsMatch = userRepository.findByName(user.getName());
            if (userIsMatch != null && userIsMatch.isEmpty()) {
                System.out.println(userIsMatch);
                byte[] hash = authservice.getEncryptedPassword(user.getPasswordId(), "salt".getBytes());
                user.setPasswordId(authservice.bytesToHex(hash));
                userRepository.save(user);
                returnString = "Register Success";
            } else {
                returnString = "Already User";
            }
            
        } catch (Exception e) {
            System.out.println(e);
        }
        return returnString;
    }

    public String loginUser(Authentication auth) {
        String returnString = "";
        try {
            List<User> userQuery = userRepository.findByIdentificationNumber(auth.getUsername());
            if (!(userQuery != null && userQuery.isEmpty())) {
                String userPassword = userQuery.get(0).getPasswordId();
                byte[] passwordToByte = authservice.hexToByte(userPassword);
                if(authservice.authenticate(auth.getPassword(), passwordToByte, "salt".getBytes())) {
                    System.out.println(userQuery.get(0).getName());
                    String authToken = tokenService.generateJWTToken(userQuery.get(0).getName());
                    returnString = authToken;
                    return returnString;
                }
                else {
                    returnString = "Auth Failed";
                }
            }
            else {
                returnString = "User Not Found";
            }
            
            return returnString;
        } catch (Exception e) {
            System.out.println(e);
            // TODO: handle exception
        }
        return "Error";
    }

    public List<User> getListUser() {
        List<User> user = new ArrayList<User>();
        try {
            user = userRepository.findAll();
        } catch (Exception e) {
            System.out.println(e);
        }
        return user;
    }

    public Optional<User> getUserById(Long userId) {
        return userRepository.findById(userId);
    }

    public String addTaskData(String authtoken) {
        String atoken = authservice.verifyJWTToken(authtoken);
        return atoken;
    }

    // Method Function
}
