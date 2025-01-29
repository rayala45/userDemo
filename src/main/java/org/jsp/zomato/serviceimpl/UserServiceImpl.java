package org.jsp.zomato.serviceimpl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.jsp.zomato.Dao.UserDao;
import org.jsp.zomato.dto.Login;
import org.jsp.zomato.dto.UserLogin;
import org.jsp.zomato.entity.User;
import org.jsp.zomato.exceptionclasses.DuplicateEmailException;
import org.jsp.zomato.exceptionclasses.InvalidUserCredentialException;
import org.jsp.zomato.exceptionclasses.InvalidUserIdException;
import org.jsp.zomato.exceptionclasses.InvalidUserNameException;
import org.jsp.zomato.exceptionclasses.InvalidUserPhoneException;
import org.jsp.zomato.exceptionclasses.NoUserFoundException;
import org.jsp.zomato.responsestructure.ResponseStructure;
import org.jsp.zomato.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserDao dao;
	
	@Autowired
	UserEmailService mail;

	@Override
	public ResponseEntity<?> saveUser(User user) {
		Optional<User> optional=dao.findUserByEmail(user.getEmail());
		if(optional.isPresent())
			throw DuplicateEmailException.builder().message("duplicate Email...").build();
		user=dao.saveUser(user);
		mail.sendEmail(user.getEmail(), user.toString()+"hello... "+user.getName(), "profile");
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value()).message("user created successfull...").body(user).build());
	}

	@Override
	public ResponseEntity<?> findAllUsers() {
		List<User> ul=dao.findAllUsers();
		if(ul.isEmpty())
			throw NoUserFoundException.builder().message("No user found in the exception...").build();
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value()).message("users found successfull...").body(ul).build());
	}

	@Override
	public ResponseEntity<?> findUserById(int id) {
		Optional<User> optional=dao.findUserById(id);
		if(optional.isEmpty())
			throw InvalidUserIdException.builder().message("Invalid User id.....").build();
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value()).message("user found successfull...").body(optional.get()).build());
	}

	@Override
	public ResponseEntity<?> delete(int id) {
		Optional<User> optional=dao.findUserById(id);
		if(optional.isEmpty())
			throw InvalidUserIdException.builder().message("Invalid User id unable to delete.....").build();
		dao.delete(id);
		mail.sendEmail(optional.get().getEmail(), "your account deleted successfully..", "Profile");
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value()).message("user found successfull...").body("User deleted Suceessfully...").build());
	
	}

	@Override
	public ResponseEntity<?> findUserByName(String name) {
		List<User> ul=dao.findUserByName(name);
		if(ul.isEmpty())
			throw InvalidUserNameException.builder().message("Invalid User name unable to find.....").build();
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value()).message("user found successfully with given name...").body(ul).build());
	}

	@Override
	public ResponseEntity<?> findUserByPhone(long phone) {
		Optional<User> optional=dao.findUserByPhone(phone);
		if(optional.isEmpty())
			throw InvalidUserPhoneException.builder().message("Invalid User phone number unable to find.....").build();
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value()).message("user found successfully with given number...").body(optional.get()).build());
	}

	@Override
	public ResponseEntity<?> findUserByEmail(String email) {
		Optional<User> optional=dao.findUserByEmail(email);
		if(optional.isEmpty())
			throw InvalidUserPhoneException.builder().message("Invalid User email id unable to find.....").build();
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value()).message("user found successfully with given email id...").body(optional.get()).build());
	}

	@Override
	public ResponseEntity<?> login(Login login) {
		if(login.getEmail()!=null) {
			Optional<User> optional=dao.findUserByEmailAndPassword(login.getEmail(),login.getPassword());
			if(optional.isEmpty())
				throw InvalidUserCredentialException.builder().message("Invalid User credential  unable to login.....").build();
			optional.get().setLastlogin(LocalDateTime.now());
			User user = dao.updateLastLogin(optional.get());
			mail.sendEmail(user.getEmail(), "your login is successful in the web.."+user.getLastlogin(), "login");
			return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value()).message("login successfull...").body(optional.get()).build());
		}
		Optional<User> optional=dao.findUserByPhoneAndPassword(login.getPhone(),login.getPassword());
		if(optional.isEmpty())
			throw InvalidUserCredentialException.builder().message("Invalid User credential  unable to login.....").build();
		optional.get().setLastlogin(LocalDateTime.now());
		User user = dao.updateLastLogin(optional.get());
		mail.sendEmail(user.getEmail(), "your login is successful in the web.."+user.getLastlogin(), "login");
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value()).message("login successfull.....").body(optional.get()).build());
	}

	@Override
	public ResponseEntity<?> updateUser(User user) {
		Optional<User> optional=dao.findUserById(user.getId());
		if(optional.isEmpty())
			throw InvalidUserIdException.builder().message("Invalid User unable to update user.....").build();
		user=dao.updateUser(user);
		mail.sendEmail(user.getEmail(), "updated profile successful..", "profile update");
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value()).message("user found successfully updated...").body(optional.get()).build());
	}

	@Override
	public ResponseEntity<?> updateLastLogin(int id) {
		Optional<User> optional=dao.findUserById(id);
		if(optional.isEmpty())
			throw InvalidUserIdException.builder().message("Invalid User unable to update user.....").build();
		User user=optional.get();
		user.setLastlogin(LocalDateTime.now());
		user=dao.updateLastLogin(user);
		mail.sendEmail(user.getEmail(), "your lastlogin updated..."+user.getLastlogin(), "Hello");
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value()).message("last login is updated successfully...").body(user).build());
	}

	@Override
	public ResponseEntity<?> login(UserLogin login) {
		String email=login.getEmail();
		char ch=email.charAt(0);
		if(email.length()==10 && (ch>'5' && ch<='9')) {
			boolean flag=true;
			for(int i=0;i<email.length();i++) {
				if(!(email.charAt(i)>='0' && email.charAt(i)<='9')) {
					flag=false;
				}
			}
			if(flag) {
				Optional<User> optional=dao.findUserByPhoneAndPassword(Long.parseLong(login.getEmail()),login.getPassword());
				if(optional.isEmpty())
					throw InvalidUserCredentialException.builder().message("Invalid User credential  unable to login.....").build();
				optional.get().setLastlogin(LocalDateTime.now());
				User user = dao.updateLastLogin(optional.get());
				mail.sendEmail(user.getEmail(), "your login is successful in the web.."+user.getLastlogin(), "login");
				return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value()).message("login successfull...").body(optional.get()).build());
			}
		}
			Optional<User> optional=dao.findUserByEmailAndPassword(login.getEmail(),login.getPassword());
			if(optional.isEmpty())
				throw InvalidUserCredentialException.builder().message("Invalid User credential  unable to login.....").build();
			optional.get().setLastlogin(LocalDateTime.now());
			User user = dao.updateLastLogin(optional.get());
			mail.sendEmail(user.getEmail(), "your login is successful in the web.."+user.getLastlogin(), "login");
			return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value()).message("login successfull...").body(optional.get()).build());
	}

}
