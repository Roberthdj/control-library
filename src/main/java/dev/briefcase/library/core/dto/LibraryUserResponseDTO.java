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
public class LibraryUserResponseDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long idUser;
	private String firstName;
	private String lastName;
	private String email;

}
