package org.jsp.zomato.exceptionclasses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InvalidUserCredentialException extends RuntimeException{
	private String message;
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return this.message;
	}

}
