package org.example.backend.controller;

import org.example.backend.dto.CredentialsDTO;
import org.example.backend.dto.UserInDTO;
import org.example.backend.dto.UserOutDTO;
import org.example.backend.entity.User;
import org.example.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserInDTO userInDTO){
        if (userService.createUser(userInDTO)){
            return new ResponseEntity<>("Utilisateur bien créé",HttpStatus.CREATED);
        }
        return new ResponseEntity<>("L'utilisateur n'a pas pu être créé", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody CredentialsDTO credentials){
        User user = (User) userService.loadUserByUsername(credentials.getEmail());
        if (user != null){
            if (userService.verifyUser(user, credentials.getPassword())){
                return new ResponseEntity<>(userService.generateToken(credentials.getEmail(), credentials.getPassword()), HttpStatus.ACCEPTED);
            }
            return new ResponseEntity<>("Mauvais mot de passe", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Utilisateur non-trouvé", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/getByMail/{mail}")
    public ResponseEntity<Object> getByMail(@PathVariable String mail){
        User user = (User) userService.loadUserByUsername(mail);
        if (user != null){
            return new ResponseEntity<>(user.toOutDTO(), HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
