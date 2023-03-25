package project.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import project.model.Cartitems;
import project.model.Product;
import project.model.User;
import project.repo.ProductRepo;
import project.repo.UserRepo;
import project.repo.CartitemRepo;


@Service
public class Services implements UserDetailsService {
	@Autowired
	UserRepo repo;

	@Autowired
	ProductRepo prepo;

	@Autowired
	CartitemRepo crepo;

	public List<Product> getProducts() {
		return prepo.findAll();
	}

	public List<Product> saveProduct(Product product) {
		prepo.save(product);
		return prepo.findAll();
	}

	public List<Product> deleteProduct(int id) {
		if (prepo.findById(id).isPresent()) {
			prepo.deleteById(id);
			return prepo.findAll();
		}
		return null;
	}

	public List<User> getUsers() {
		return repo.findAll();
	}

	public List<User> saveUser(User user) {
		repo.save(user);
		return repo.findAll();
	}

	public List<User> deleteUser(int id) {
		if (repo.findById(id).isPresent()) {
			repo.deleteById(id);
			return repo.findAll();
		}
		return null;
	}

	public List<User> updateuser(User user) {
		if (repo.findById(user.getId()).isPresent()) {
			repo.save(user);
			return repo.findAll();
		}
		return null;
	}

	public Optional<User> getUserById(int id) {
		return repo.findById(id);
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		return repo.findByEmail(email);
	}

	public void addToCart(Long pid,String uname,Cartitems cartitems) {
		Product product = prepo.findById(pid).orElse(null);
		cartitems.setProduct(product);
		cartitems.setUsername(uname);
		crepo.save(cartitems);
	}

	public  List<Cartitems> myCart(String userName) {

		List<Cartitems> cartitems = new ArrayList<>();
		crepo.findByUsername(userName).forEach(cartitems::add);
		return cartitems;
	}

}
