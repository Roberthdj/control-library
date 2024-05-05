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
public class LoanBookResponseDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long idLoan;
	private LibraryUserResponseDTO user;
	private LibraryBookResponseDTO book;
	private String departureDate;
	private String returnDate;
	private String observation;	
	private Boolean active;
	
}

