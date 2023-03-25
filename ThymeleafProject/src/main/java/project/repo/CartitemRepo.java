package project.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import project.model.Cartitems;
import project.model.Product;


public interface CartitemRepo extends JpaRepository<Cartitems, Integer> {

	Iterable<Cartitems> findByUsername(String userName);

	

}
