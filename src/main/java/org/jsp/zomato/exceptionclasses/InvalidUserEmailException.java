package org.jsp.zomato.exceptionclasses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InvalidUserEmailException extends RuntimeException{

	private String message;
	@Override
	public String getMessage() {
		return this.message;
	}
}
