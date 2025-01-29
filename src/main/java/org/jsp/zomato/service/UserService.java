package org.jsp.zomato.service;

import org.jsp.zomato.dto.Login;
import org.jsp.zomato.dto.UserLogin;
import org.jsp.zomato.entity.User;
import org.springframework.http.ResponseEntity;

public interface UserService {

	ResponseEntity<?> saveUser(User user);

	ResponseEntity<?> findAllUsers();

	ResponseEntity<?> findUserById(int id);

	ResponseEntity<?> delete(int id);

	ResponseEntity<?> findUserByName(String name);

	ResponseEntity<?> findUserByPhone(long phone);

	ResponseEntity<?> findUserByEmail(String email);

	ResponseEntity<?> login(Login login);

	ResponseEntity<?> updateUser(User user);

	ResponseEntity<?> updateLastLogin(int id);

	ResponseEntity<?> login(UserLogin login);

}
