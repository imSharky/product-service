package dev.naman.productservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//on adding response status we can remove code of controller advice
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NotFoundException extends Exception{

	public NotFoundException(String message) {
		super(message);
	}
}