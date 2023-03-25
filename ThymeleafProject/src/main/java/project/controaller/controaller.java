package project.controaller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import project.services.Services;
import project.*;
import project.model.Cartitems;
import project.model.Product;
import project.model.User;

@Controller
public class controaller {

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	Services service;

	@GetMapping("/test")
	String usertest() {
		return "test";
	}

	@GetMapping("/snt")
	String MainHome() {
		return "Homepage";
	}

	@GetMapping({ "/adminindex" })
	public String adminindex(Model model) {
		List<User> users = service.getUsers();
		model.addAttribute("users", users);

		List<Product> products = service.getProducts();
		model.addAttribute("products", products);

		return "adminindex";
	}

	@GetMapping("/productregistration")
	public String productregistration(Model model) {
		Product product = new Product();
		model.addAttribute("product", product);
		return "productregistration";
	}

	@PostMapping("/saveproduct")
	public String saveproduct(Model model, @ModelAttribute("product") Product product) {
		service.saveProduct(product);
		return "redirect:/adminindex";
	}

	@GetMapping("/deleteproduct/{id}")
	public String deleteProduct(@PathVariable int id) {
		service.deleteProduct(id);
		return "redirect:/adminindex";
	}

	@GetMapping({ "", "/", "/index" })
	public String index(Model model) {

		List<User> users = service.getUsers();
		model.addAttribute("users", users);

		List<Product> products = service.getProducts();
		model.addAttribute("products", products);

		return "index";
	}

	@GetMapping("/registration")
	public String registration(Model model) {
		User user = new User();
		user.setRole("ROLE_USER");
		model.addAttribute("user", user);
		return "registration";
	}

	@PostMapping("/save")
	public String save(Model model, @ModelAttribute("user") User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		service.saveUser(user);
		return "redirect:/index";
	}

	@GetMapping("/delete/{id}")
	public String deleteUser(@PathVariable int id) {
		service.deleteUser(id);
		return "redirect:/index";
	}

	@GetMapping("/update/{id}")
	public String updateUser(Model model, @PathVariable int id) {
		User user = service.getUserById(id).get();
		model.addAttribute("user", user);
		return "update";
	}

	@PostMapping("/updateuser/{id}")
	public String update(Model model, @PathVariable int id, @ModelAttribute("user") User user) {
		user.setId(id);
		service.updateuser(user);
		return "redirect:/index";
	}

	@PostMapping("/addtocart/{pid}/{uname}")
	public String addToCart(@PathVariable Long pid,@PathVariable String uname,Cartitems cartitems) {
		service.addToCart(pid,uname,cartitems);
		return "redirect:/cart/{uname}";
	}

	@PostMapping("/cart/{uname}")
	public String myCart(@PathVariable String uname ,Model model) {

		List<Cartitems> cartItems = service.myCart(uname);
		model.addAttribute("cartitems", cartItems);
		return "cart";
	}

}
