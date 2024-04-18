package dev.briefcase.library.tool;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DataWrapper<T> {
	
	private Boolean done;
	private String message;
	private T body;
	
	public ResponseEntity<DataWrapper<T>> createResponse(){
		return new ResponseEntity<>(this, HttpStatus.OK);		
	}
	
	public ResponseEntity<DataWrapper<T>> createResponse(HttpStatus status){
		return new ResponseEntity<>(this, status);		
	}

}
