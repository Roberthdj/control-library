package dev.briefcase.library.validation;

import org.springframework.stereotype.Component;

import dev.briefcase.library.config.exception.ValidateFieldsException;
import dev.briefcase.library.entity.SanctionUser;

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
	}

	public void validateIdNotNull(SanctionUser sanctionUser) {
		if (sanctionUser.getIdSanction() == null) {
			throw new ValidateFieldsException("Sanction ID is required.");
		}
	}
}
