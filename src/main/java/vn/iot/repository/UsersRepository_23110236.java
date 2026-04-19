package vn.iot.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.iot.model.Users_23110236;

import java.util.Optional;

@Repository
public interface UsersRepository_23110236 extends JpaRepository<Users_23110236, Integer> {
    
    Optional<Users_23110236> findByUsername(String username);
    
    Optional<Users_23110236> findByEmail(String email);
    
    @Query("SELECT u FROM Users_23110236 u WHERE u.username = :username AND u.password = :password")
    Optional<Users_23110236> findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);
    
    @Query("SELECT u FROM Users_23110236 u WHERE u.email = :email AND u.password = :password")
    Optional<Users_23110236> findByEmailAndPassword(@Param("email") String email, @Param("password") String password);
    
    Page<Users_23110236> findAll(Pageable pageable);
}
