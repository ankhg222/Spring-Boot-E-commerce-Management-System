package vn.iot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.iot.model.UserRoles_23110236;
import vn.iot.repository.UserRolesRepository_23110236;

import java.util.List;
import java.util.Optional;

@Service
public class UserRolesService_23110236 {
    
    @Autowired
    private UserRolesRepository_23110236 userRolesRepository;
    
    public List<UserRoles_23110236> getAllRoles() {
        return userRolesRepository.findAll();
    }
    
    public Optional<UserRoles_23110236> getRoleById(Integer id) {
        return userRolesRepository.findById(id);
    }
    
    public UserRoles_23110236 saveRole(UserRoles_23110236 role) {
        return userRolesRepository.save(role);
    }
    
    public void deleteRole(Integer id) {
        userRolesRepository.deleteById(id);
    }
}
