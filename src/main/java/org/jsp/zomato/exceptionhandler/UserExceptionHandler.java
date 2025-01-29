package org.jsp.zomato.exceptionhandler;

import org.jsp.zomato.exceptionclasses.DuplicateEmailException;
import org.jsp.zomato.exceptionclasses.InvalidUserCredentialException;
import org.jsp.zomato.exceptionclasses.InvalidUserEmailException;
import org.jsp.zomato.exceptionclasses.InvalidUserIdException;
import org.jsp.zomato.exceptionclasses.InvalidUserNameException;
import org.jsp.zomato.exceptionclasses.InvalidUserPhoneException;
import org.jsp.zomato.exceptionclasses.NoUserFoundException;
import org.jsp.zomato.responsestructure.ResponseStructure;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserExceptionHandler {
	
	@ExceptionHandler(DuplicateEmailException.class)
	public ResponseEntity<?> duplicateEmailExceptionhandler(DuplicateEmailException e){
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseStructure.builder().status(HttpStatus.NOT_FOUND.value()).message("user already exist with this email...").body(e.getMessage()).build());
	}
	
	@ExceptionHandler(NoUserFoundException.class)
	public ResponseEntity<?> noUserFoundExceptionhandler(NoUserFoundException e){
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseStructure.builder().status(HttpStatus.NOT_FOUND.value()).message("no user found in the database....").body(e.getMessage()).build());
	}
	
	@ExceptionHandler(InvalidUserIdException.class)
	public ResponseEntity<?> invalidUserIdExceptionhandler(InvalidUserIdException e){
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseStructure.builder().status(HttpStatus.NOT_FOUND.value()).message("no user found in the database with given id....").body(e.getMessage()).build());
	}
	
	@ExceptionHandler(InvalidUserPhoneException.class)
	public ResponseEntity<?> invalidUserPhoneExceptionhandler(InvalidUserPhoneException e){
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseStructure.builder().status(HttpStatus.BAD_REQUEST.value()).message("no user found in the database with given phone number....").body(e.getMessage()).build());
	}
	
	@ExceptionHandler(InvalidUserEmailException.class)
	public ResponseEntity<?> invalidUserEmailExceptionhandler(InvalidUserEmailException e){
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseStructure.builder().status(HttpStatus.BAD_REQUEST.value()).message("no user found in the database with given Email id....").body(e.getMessage()).build());
	}
	
	@ExceptionHandler(InvalidUserNameException.class)
	public ResponseEntity<?> invalidUserNameExceptionhandler(InvalidUserNameException e){
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseStructure.builder().status(HttpStatus.BAD_REQUEST.value()).message("no user found in the database with given name....").body(e.getMessage()).build());
	}
	
	@ExceptionHandler(InvalidUserCredentialException.class)
	public ResponseEntity<?> invalidUserCredentialExceptionhandler(InvalidUserCredentialException e){
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseStructure.builder().status(HttpStatus.BAD_REQUEST.value()).message("Invalid User credential unable login....").body(e.getMessage()).build());
	}

}
