package dev.briefcase.library.dto;

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
public class LibraryUserDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long idUser;
	private String firstName;
	private String lastName;
	private String identification;
	private String email;
	private String phoneNumber;
	private String address;
	private Boolean sanctioned;
	private Boolean active;

}
