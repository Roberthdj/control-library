package dev.briefcase.library.validation;

import org.springframework.stereotype.Component;

import dev.briefcase.library.core.entity.SanctionUser;
import dev.briefcase.library.error.exception.ValidateFieldsException;

@Component
public class SanctionUserValidator {

	public void validateSaveData(SanctionUser sanctionUser) {

		if (sanctionUser.getUser().getIdUser() == null) {
			throw new ValidateFieldsException("User ID is required.");
		}

		if (sanctionUser.getStartDate() == null) {
			throw new ValidateFieldsException("Start date is required.");
		}

		if (sanctionUser.getCompletionDate() == null) {
			throw new ValidateFieldsException("Completion date is requiered.");
		}
		
		validateDate(sanctionUser);

		if (sanctionUser.getObservation() == null || sanctionUser.getObservation().isBlank()) {
			throw new ValidateFieldsException("Observation is required");
		}

		if (sanctionUser.getObservation().length() > 100) {
			throw new ValidateFieldsException("Observation too long.");
		}		

	}

	public void validateUpdateData(SanctionUser sanctionUser) {
		if (sanctionUser.getObservation() != null && sanctionUser.getObservation().length() > 100) {
			throw new ValidateFieldsException("Observation too long.");
		}
		
		validateDate(sanctionUser);
	}

	public void validateIdNotNull(SanctionUser sanctionUser) {
		if (sanctionUser.getIdSanction() == null) {
			throw new ValidateFieldsException("Sanction ID is required.");
		}
	}
	
	private void validateDate(SanctionUser sanctionUser) {
		if( sanctionUser.getStartDate().after(sanctionUser.getCompletionDate()) || sanctionUser.getStartDate().equals(sanctionUser.getCompletionDate())) {
			throw new ValidateFieldsException("Invalid date input!");
		}		
	}
}
