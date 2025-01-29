package org.jsp.zomato.Dao;

import java.util.List;
import java.util.Optional;

import org.jsp.zomato.entity.User;

public interface UserDao {

	User saveUser(User user);

	Optional<User> findUserByEmail(String email);

	List<User> findAllUsers();

	Optional<User> findUserById(int id);

	void delete(int id);

	List<User> findUserByName(String name);

	Optional<User> findUserByPhone(long phone);

	Optional<User> findUserByEmailAndPassword(String email, String password);

	Optional<User> findUserByPhoneAndPassword(long phone, String password);

	User updateUser(User user);

	User updateLastLogin(User user);

}
