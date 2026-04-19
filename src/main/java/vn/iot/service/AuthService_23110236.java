package vn.iot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.iot.model.Users_23110236;
import vn.iot.repository.UsersRepository_23110236;

import java.util.Optional;

@Service
public class AuthService_23110236 {
    
    @Autowired
    private UsersRepository_23110236 usersRepository;
    
    public Optional<Users_23110236> authenticate(String email, String password) {
        return usersRepository.findByEmailAndPassword(email, password);
    }
    
    public boolean register(Users_23110236 user) {
        if (usersRepository.findByUsername(user.getUsername()).isPresent()) {
            return false; // Username already exists
        }
        if (usersRepository.findByEmail(user.getEmail()).isPresent()) {
            return false; // Email already exists
        }
        usersRepository.save(user);
        return true;
    }
    
    public boolean isAdmin(Users_23110236 user) {
        return user.getUserRole() != null && "ADMIN".equalsIgnoreCase(user.getUserRole().getRoleName());
    }
    
    public boolean isSeller(Users_23110236 user) {
        return user.getUserRole() != null && "SELLER".equalsIgnoreCase(user.getUserRole().getRoleName());
    }
}
