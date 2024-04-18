package dev.briefcase.library.config.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import dev.briefcase.library.config.exception.GeneralServiceException;
import dev.briefcase.library.config.exception.NotFoundException;
import dev.briefcase.library.config.exception.ValidateFieldsException;
import dev.briefcase.library.tool.DataWrapper;

@RestControllerAdvice
public class ErrorHandlerConfig extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> all(Exception e, WebRequest request) {
		DataWrapper<?> response = new DataWrapper<>(false, "INTERNAL SERVER ERROR", null);
		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(GeneralServiceException.class)
	public ResponseEntity<?> generalService(Exception e, WebRequest request) {
		DataWrapper<?> response = new DataWrapper<>(false, "INTERNAL SERVER ERROR -> " + e.getMessage() , null);
		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<?> noDataFound(Exception e, WebRequest request) {
		DataWrapper<?> response = new DataWrapper<>(false, e.getMessage(), null);
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(ValidateFieldsException.class)
	public ResponseEntity<?> validateFields(Exception e, WebRequest request) {
		DataWrapper<?> response = new DataWrapper<>(false, e.getMessage(), null);
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
}
