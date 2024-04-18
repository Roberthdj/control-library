package dev.briefcase.library.dto;

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
public class LoanBookRegisterDTO {

	private Long idLoan;
	private Long user;
	private Long book;
	private String departureDate;
	private String returnDate;
	private String observation;
}
