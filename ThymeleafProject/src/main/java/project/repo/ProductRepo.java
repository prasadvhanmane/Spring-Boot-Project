package project.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import project.model.Product;


public interface ProductRepo extends JpaRepository<Product, Integer>{

	Optional<Product> findById(Long carId);

}
