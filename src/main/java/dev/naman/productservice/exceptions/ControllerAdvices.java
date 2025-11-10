package dev.naman.productservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import dev.naman.productservice.dtos.ExceptionDto;

@ControllerAdvice
public class ControllerAdvices {
//	@ExceptionHandler(NotFoundException.class)
//	private ResponseEntity<ExceptionDto> handleNotFoundException(NotFoundException notFoundException) {
//		return new ResponseEntity(new ExceptionDto(HttpStatus.NOT_FOUND, notFoundException.getMessage()), HttpStatus.NOT_FOUND);
//	}
}

//when a class annotated with controlleradvice then when controller class done it's work then
//whatever the controller return goes by controlleradvice like here if NotFoundException is thrown
//then return this. there can be multiple ControllerAdvices