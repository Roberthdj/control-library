package dev.briefcase.library.validation;

import org.springframework.stereotype.Component;

import dev.briefcase.library.config.exception.ValidateFieldsException;
import dev.briefcase.library.entity.LibraryUser;

@Component
public class LibraryUserValidator {

	public void validateSaveData(LibraryUser libraryUser) {

		if (libraryUser.getFirstName() == null || libraryUser.getFirstName().isBlank()) {
			throw new ValidateFieldsException("First name is required.");
		}

		if (libraryUser.getFirstName().length() > 50) {
			throw new ValidateFieldsException("First name is too long.");
		}

		if (libraryUser.getLastName() == null || libraryUser.getLastName().isBlank()) {
			throw new ValidateFieldsException("Last name is required.");
		}

		if (libraryUser.getLastName().length() > 50) {
			throw new ValidateFieldsException("Last name is too long.");
		}

		if (libraryUser.getIdentification() == null || libraryUser.getIdentification().isBlank()) {
			throw new ValidateFieldsException("Identification is required.");
		}

		if (libraryUser.getIdentification().length() > 10) {
			throw new ValidateFieldsException("Identification is too long.");
		}

		if (libraryUser.getEmail() == null || libraryUser.getEmail().isBlank()) {
			throw new ValidateFieldsException("Email is required.");
		}

		if (libraryUser.getEmail().length() > 100) {
			throw new ValidateFieldsException("Email is too long.");
		}

		if (libraryUser.getPhoneNumber() == null || libraryUser.getPhoneNumber().isBlank()) {
			throw new ValidateFieldsException("Phone number is required.");
		}

		if (libraryUser.getPhoneNumber().length() > 10) {
			throw new ValidateFieldsException("Phone number is too long.");
		}

		if (libraryUser.getAddress() == null || libraryUser.getAddress().isBlank()) {
			throw new ValidateFieldsException("Address is required.");
		}

		if (libraryUser.getAddress().length() > 50) {
			throw new ValidateFieldsException("Address is too long.");
		}

	}

	public void validateUpdateData(LibraryUser libraryUser) {

		if (libraryUser.getFirstName() != null && libraryUser.getFirstName().length() > 50) {
			throw new ValidateFieldsException("First name is too long.");
		}

		if (libraryUser.getLastName() != null && libraryUser.getLastName().length() > 50) {
			throw new ValidateFieldsException("Last name is too long.");
		}

		if (libraryUser.getIdentification() != null && libraryUser.getIdentification().length() > 10) {
			throw new ValidateFieldsException("Identification is too long.");
		}

		if (libraryUser.getEmail() != null && libraryUser.getEmail().length() > 100) {
			throw new ValidateFieldsException("Email is too long.");
		}

		if (libraryUser.getPhoneNumber() != null && libraryUser.getPhoneNumber().length() > 10) {
			throw new ValidateFieldsException("Phone number is too long.");
		}

		if (libraryUser.getAddress() != null && libraryUser.getAddress().length() > 50) {
			throw new ValidateFieldsException("Address is too long.");
		}

	}

	public void validateIdNotNull(LibraryUser libraryUser) {

		if (libraryUser.getIdUser() == null) {
			throw new ValidateFieldsException("User ID is required.");
		}
	}

}
