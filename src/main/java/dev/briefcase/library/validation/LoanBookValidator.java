package dev.briefcase.library.validation;

import org.springframework.stereotype.Component;

import dev.briefcase.library.core.entity.LoanBook;
import dev.briefcase.library.error.exception.ValidateFieldsException;

@Component
public class LoanBookValidator {

	public void validateSaveData(LoanBook loanBook) {		

		if (loanBook.getUser().getIdUser() == null ) {
			throw new ValidateFieldsException("User ID is required.");
		}
		
		if (loanBook.getBook().getIdBook() == null) {
			throw new ValidateFieldsException("Book ID is required.");
		}
		
		if (loanBook.getDepartureDate() == null) {
			throw new ValidateFieldsException("Departure date is required.");
		}

		if (loanBook.getReturnDate() == null) {
			throw new ValidateFieldsException("Return date is required.");
		}
		
		validateDate(loanBook);

		if (loanBook.getObservation() == null || loanBook.getObservation().isBlank()) {
			throw new ValidateFieldsException("Observation is required");
		}

		if (loanBook.getObservation().length() > 100) {
			throw new ValidateFieldsException("Observation too long.");
		}		

	}

	public void validateUpdateData(LoanBook loanBook) {		
		if (loanBook.getObservation() != null && loanBook.getObservation().length() > 100) {
			throw new ValidateFieldsException("Observation too long.");
		}
	}

	public void validateIdNotNull(LoanBook loanBook) {
		if (loanBook.getIdLoan()== null) {
			throw new ValidateFieldsException("Loan ID is required.");
		}
		
		validateDate(loanBook);
	}
	
	private void validateDate(LoanBook loanBook) {
		if( loanBook.getDepartureDate().after(loanBook.getReturnDate()) || loanBook.getDepartureDate().equals(loanBook.getReturnDate())) {
			throw new ValidateFieldsException("Invalid date input!");
		}		
	}

}
