package dev.briefcase.library.validation;

import org.springframework.stereotype.Component;

import dev.briefcase.library.config.exception.ValidateFieldsException;
import dev.briefcase.library.entity.LibraryBook;

@Component
public class LibraryBookValidator {

	public void validateSaveData(LibraryBook libraryBook) {

		if (libraryBook.getTitle() == null || libraryBook.getTitle().isBlank()) {
			throw new ValidateFieldsException("Title is required.");
		}

		if (libraryBook.getTitle().length() > 50) {
			throw new ValidateFieldsException("Title is too long.");
		}

		if (libraryBook.getAuthor() == null || libraryBook.getAuthor().isBlank()) {
			throw new ValidateFieldsException("Author is required.");
		}

		if (libraryBook.getAuthor().length() > 50) {
			throw new ValidateFieldsException("Author is too long.");
		}

		if (libraryBook.getEditorial() == null || libraryBook.getEditorial().isBlank()) {
			throw new ValidateFieldsException("Editorial is required.");
		}

		if (libraryBook.getEditorial().length() > 50) {
			throw new ValidateFieldsException("Editorial is too long.");
		}

		if (libraryBook.getIsbn() == null || libraryBook.getIsbn().isBlank()) {
			throw new ValidateFieldsException("ISBN is required.");
		}

		if (libraryBook.getIsbn().length() > 50) {
			throw new ValidateFieldsException("ISBN is too long.");
		}
	}

	public void validateUpdateData(LibraryBook libraryBook) {

		if (libraryBook.getTitle() != null && libraryBook.getTitle().length() > 50) {
			throw new ValidateFieldsException("Title is too long.");
		}

		if (libraryBook.getAuthor() != null && libraryBook.getAuthor().length() > 50) {
			throw new ValidateFieldsException("Author is too long.");
		}

		if (libraryBook.getEditorial() != null && libraryBook.getEditorial().length() > 50) {
			throw new ValidateFieldsException("Editorial is too long.");
		}

		if (libraryBook.getIsbn() != null && libraryBook.getIsbn().length() > 50) {
			throw new ValidateFieldsException("ISBN is too long.");
		}

	}

	public void validateIdNotNull(LibraryBook libraryBook) {

		if (libraryBook.getIdBook() == null) {
			throw new ValidateFieldsException("Book ID is required.");
		}
	}

}
