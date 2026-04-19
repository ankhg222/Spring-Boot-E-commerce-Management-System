package vn.iot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.iot.model.Users_23110236;
import vn.iot.repository.UsersRepository_23110236;

import java.util.List;
import java.util.Optional;

@Service
public class UserService_23110236 {
    
    @Autowired
    private UsersRepository_23110236 usersRepository;
    
    public List<Users_23110236> getAllUsers() {
        return usersRepository.findAll();
    }
    
    public Page<Users_23110236> getAllUsersPaged(Pageable pageable) {
        return usersRepository.findAll(pageable);
    }
    
    public Optional<Users_23110236> getUserById(Integer id) {
        return usersRepository.findById(id);
    }
    
    public Optional<Users_23110236> getUserByUsername(String username) {
        return usersRepository.findByUsername(username);
    }
    
    public Optional<Users_23110236> getUserByEmail(String email) {
        return usersRepository.findByEmail(email);
    }
    
    public Optional<Users_23110236> authenticateUser(String username, String password) {
        return usersRepository.findByUsernameAndPassword(username, password);
    }
    
    public Users_23110236 saveUser(Users_23110236 user) {
        return usersRepository.save(user);
    }
    
    public void deleteUser(Integer id) {
        usersRepository.deleteById(id);
    }
    
    public boolean existsByUsername(String username) {
        return usersRepository.findByUsername(username).isPresent();
    }
    
    public boolean existsByEmail(String email) {
        return usersRepository.findByEmail(email).isPresent();
    }
}
