package org.jsp.zomato.controller;

import org.jsp.zomato.dto.Login;
import org.jsp.zomato.dto.UserLogin;
import org.jsp.zomato.entity.User;
import org.jsp.zomato.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService service;
	
	@PostMapping
	public ResponseEntity<?> saveUser(@RequestBody User user){
		return service.saveUser(user);
	}
	
	@GetMapping
	public ResponseEntity<?> findAll(){
		return service.findAllUsers();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findUserById(@PathVariable int id){
		return service.findUserById(id);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable int id){
		return service.delete(id);
	}
	
	@GetMapping("/name/{name}")
	public ResponseEntity<?> findUserByName(@PathVariable String name){
		return service.findUserByName(name);
	}
	
	@GetMapping("/phone/{phone}")
	public ResponseEntity<?> findUserById(@PathVariable long phone){
		return service.findUserByPhone(phone);
	}
	
	@GetMapping("/email/{email}")
	public ResponseEntity<?> findUserByEmail(@PathVariable String email){
		return service.findUserByEmail(email);
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody Login login){
		return service.login(login);
	}
	
	@PatchMapping("/update")
	public ResponseEntity<?> updateUser(@RequestBody User user){
		return service.updateUser(user);
	}
	
	@PatchMapping("/{id}/lastlogin")
	public ResponseEntity<?> updateLastLogin(@PathVariable int id){
		return service.updateLastLogin(id);
	}
	
	@PostMapping("/login2")
	public ResponseEntity<?> userLogin(@RequestBody UserLogin login){
		return service.login(login);
	}
}
