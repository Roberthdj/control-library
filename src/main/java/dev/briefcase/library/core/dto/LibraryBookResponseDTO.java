package dev.briefcase.library.core.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class LibraryBookResponseDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long idBook;
	private String title;
	private String author;
	private String editorial;
	
}