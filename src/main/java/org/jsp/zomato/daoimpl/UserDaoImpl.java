package org.jsp.zomato.daoimpl;

import java.util.List;
import java.util.Optional;

import org.jsp.zomato.Dao.UserDao;
import org.jsp.zomato.entity.User;
import org.jsp.zomato.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
@Repository
public class UserDaoImpl implements UserDao{
	@Autowired
	private UserRepository repo;

	@Override
	public User saveUser(User user) {
		return repo.save(user);
	}

	@Override
	public Optional<User> findUserByEmail(String email) {
		return repo.findByEmail(email);
	}

	@Override
	public List<User> findAllUsers() {
		return repo.findAll();
	}

	@Override
	public Optional<User> findUserById(int id) {
		return repo.findById(id);
	}

	@Override
	public void delete(int id) {
		repo.deleteById(id);
	}

	@Override
	public List<User> findUserByName(String name) {
		return repo.findByName(name);
	}

	@Override
	public Optional<User> findUserByPhone(long phone) {
		return repo.findByPhone(phone);
	}

	@Override
	public Optional<User> findUserByEmailAndPassword(String email, String password) {
		return repo.findByEmailAndPassword(email,password);
	}

	@Override
	public Optional<User> findUserByPhoneAndPassword(long phone, String password) {
		return repo.findByPhoneAndPassword(phone,password);
	}

	@Override
	public User updateUser(User user) {
		return repo.save(user);
	}

	@Override
	public User updateLastLogin(User user) {
		// TODO Auto-generated method stub
		return repo.save(user);
	}

	

}
