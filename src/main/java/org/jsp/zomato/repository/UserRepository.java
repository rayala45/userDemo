package org.jsp.zomato.repository;

import java.util.List;
import java.util.Optional;

import org.jsp.zomato.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer>{

	Optional<User> findByEmail(String email);

	List<User> findByName(String name);

	Optional<User> findByPhone(long phone);

	Optional<User> findByEmailAndPassword(String email, String password);

	Optional<User> findByPhoneAndPassword(long phone, String password);


}
