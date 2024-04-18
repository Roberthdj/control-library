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
public class LoanBookResponseDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long idLoan;
	private LibraryUserDTO user;
	private LibraryBookDTO book;
	private String departureDate;
	private String returnDate;
	private String observation;	
	private Boolean active;
	
}

