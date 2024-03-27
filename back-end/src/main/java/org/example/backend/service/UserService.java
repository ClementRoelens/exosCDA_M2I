package org.example.backend.service;

import org.example.backend.config.jwt.JwtProvider;
import org.example.backend.dto.UserInDTO;
import org.example.backend.entity.Role;
import org.example.backend.entity.User;
import org.example.backend.repository.RoleRepository;
import org.example.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatusCode;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Lazy
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Lazy
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        return user;
    }

    public boolean createUser(UserInDTO userInDTO){
        userInDTO.setPassword(passwordEncoder.encode(userInDTO.getPassword()));
        Role role = roleRepository.findByName(userInDTO.getRole());
        User user = userInDTO.toUser();
        user.setRole(role);
        userRepository.save(user);
        return true;
    }

    public boolean verifyUser(User user, String passwordToCheck){
        return passwordEncoder.matches(passwordToCheck, user.getPassword());
    }

    public boolean compareUserWithToken(User user, String token){
        return jwtProvider.getUsernameFromToken(token).equals(user.getEmail());
    }

    public String generateToken(String email, String password) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return jwtProvider.generateToken(authentication);
    }
}
