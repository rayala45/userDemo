package org.jsp.zomato.exceptionclasses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NoUserFoundException extends RuntimeException{
	private String message;
	@Override
	public String getMessage() {
		return this.message;
	}

}
