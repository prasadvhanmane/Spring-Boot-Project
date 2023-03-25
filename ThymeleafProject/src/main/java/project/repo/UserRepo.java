package project.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import project.model.User;


public interface UserRepo extends JpaRepository<User, Integer> {
	UserDetails findByEmail(String email);

}
