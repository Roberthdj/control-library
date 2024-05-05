package dev.briefcase.library.core.service.impl;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.briefcase.library.core.entity.LibraryBook;
import dev.briefcase.library.core.entity.LoanBook;
import dev.briefcase.library.core.repository.LibraryBookRepository;
import dev.briefcase.library.core.repository.LoanBookRepository;
import dev.briefcase.library.core.service.LibraryBookService;
import dev.briefcase.library.error.exception.GeneralServiceException;
import dev.briefcase.library.error.exception.NotFoundException;
import dev.briefcase.library.error.exception.ValidateFieldsException;
import dev.briefcase.library.validation.LibraryBookValidator;

@Service
public class LibraryBookServiceImpl implements LibraryBookService {

	private LibraryBookRepository repositoryBook;
	private LibraryBookValidator validatorBook;
	private LoanBookRepository repositoryLoan;

	public LibraryBookServiceImpl(LibraryBookRepository repositoryBook, LibraryBookValidator validatorBook,
			LoanBookRepository repositoryLoan) {
		this.repositoryBook = repositoryBook;
		this.validatorBook = validatorBook;
		this.repositoryLoan = repositoryLoan;
	}

	private void verifyActiveLoan(Long book) {
		List<LoanBook> register = repositoryLoan.findByIdBook(book, null);
		for (LoanBook reg : register) {
			if (reg.getBook().getLent().equals(true)) {
				throw new GeneralServiceException("Book with ID " + reg.getBook().getIdBook()
						+ " has been borrowed and cannot be deactivated or deleted.");
			}
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<LibraryBook> findAll(Pageable page) {
		try {
			return repositoryBook.findAll(page).toList();
		} catch (NotFoundException | ValidateFieldsException e) {
			throw e;
		} catch (Exception e) {
			throw new GeneralServiceException(e.getMessage());
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<LibraryBook> findByTitle(String title, Pageable page) {
		try {
			return repositoryBook.findByTitleContaining(title, page);
		} catch (NotFoundException | ValidateFieldsException e) {
			throw e;
		} catch (Exception e) {
			throw new GeneralServiceException(e.getMessage());
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<LibraryBook> findByAuthor(String author, Pageable page) {
		try {
			return repositoryBook.findByAuthorContaining(author, page);
		} catch (NotFoundException | ValidateFieldsException e) {
			throw e;
		} catch (Exception e) {
			throw new GeneralServiceException(e.getMessage());
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<LibraryBook> findByTitleAndAuthor(String title, String author, Pageable page) {
		try {
			return repositoryBook.findByTitleContainingAndAuthorContaining(title, author, page);
		} catch (NotFoundException | ValidateFieldsException e) {
			throw e;
		} catch (Exception e) {
			throw new GeneralServiceException(e.getMessage());
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<LibraryBook> findByActive(Boolean actived, Pageable page) {
		try {
			return repositoryBook.findByActive(actived, page);
		} catch (NotFoundException | ValidateFieldsException e) {
			throw e;
		} catch (Exception e) {
			throw new GeneralServiceException(e.getMessage());
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<LibraryBook> findByLent(Boolean lent, Pageable page) {
		try {
			return repositoryBook.findByLent(lent, page);
		} catch (NotFoundException | ValidateFieldsException e) {
			throw e;
		} catch (Exception e) {
			throw new GeneralServiceException(e.getMessage());
		}
	}

	@Override
	@Transactional(readOnly = true)
	public LibraryBook findByIsbn(String isbn) {
		try {
			if (!existByIsbn(isbn)) {
				throw new NotFoundException("ISBN " + isbn + " does not exist.");
			}
			LibraryBook register = repositoryBook.findByIsbn(isbn);
			return register;
		} catch (NotFoundException | ValidateFieldsException e) {
			throw e;
		} catch (Exception e) {
			throw new GeneralServiceException(e.getMessage());
		}
	}

	@Override
	public Boolean existByIsbn(String isbn) {
		try {
			return repositoryBook.existsByIsbn(isbn);
		} catch (NotFoundException | ValidateFieldsException e) {
			throw e;
		} catch (Exception e) {
			throw new GeneralServiceException(e.getMessage());
		}
	}

	@Override
	@Transactional(readOnly = true)
	public LibraryBook findById(Long id) {
		try {
			LibraryBook register = repositoryBook.findById(id)
					.orElseThrow(() -> new NotFoundException("Book ID " + id + " does not exist."));
			return register;
		} catch (NotFoundException | ValidateFieldsException e) {
			throw e;
		} catch (Exception e) {
			throw new GeneralServiceException(e.getMessage());
		}
	}

	@Override
	@Transactional
	public LibraryBook save(LibraryBook libraryBook) {
		try {
			validatorBook.validateSaveData(libraryBook);

			if (existByIsbn(libraryBook.getIsbn())) {
				throw new GeneralServiceException("ISBN " + libraryBook.getIsbn() + " has already been registered.");
			}

			libraryBook.setActive(true);
			libraryBook.setLent(false);
			LibraryBook register = repositoryBook.save(libraryBook);
			return register;
		} catch (NotFoundException | ValidateFieldsException e) {
			throw e;
		} catch (Exception e) {
			throw new GeneralServiceException(e.getMessage());
		}
	}

	@Override
	@Transactional
	public LibraryBook update(LibraryBook libraryBook) {
		try {
			validatorBook.validateIdNotNull(libraryBook);
			validatorBook.validateUpdateData(libraryBook);

			if (existByIsbn(libraryBook.getIsbn())) {
				throw new GeneralServiceException("ISBN " + libraryBook.getIsbn() + " has already been registered.");
			}

			LibraryBook register = repositoryBook.findById(libraryBook.getIdBook()).orElseThrow(
					() -> new NotFoundException("Book ID " + libraryBook.getIdBook() + " does not exist."));

			if (libraryBook.getTitle() != null && !libraryBook.getTitle().isBlank()) {
				register.setTitle(libraryBook.getTitle());
			}

			if (libraryBook.getAuthor() != null && !libraryBook.getAuthor().isBlank()) {
				register.setAuthor(libraryBook.getAuthor());
			}

			if (libraryBook.getEditorial() != null && !libraryBook.getEditorial().isBlank()) {
				register.setEditorial(libraryBook.getEditorial());
			}

			if (libraryBook.getIsbn() != null && !libraryBook.getIsbn().isBlank()) {
				register.setIsbn(libraryBook.getIsbn());
			}

			if (libraryBook.getLent() != null) {
				register.setLent(libraryBook.getLent());
			}

			repositoryBook.save(register);
			return register;

		} catch (NotFoundException | ValidateFieldsException e) {
			throw e;
		} catch (Exception e) {
			throw new GeneralServiceException(e.getMessage());
		}
	}

	@Override
	@Transactional
	public LibraryBook logicalDelete(Long id, Boolean state) {
		try {
			LibraryBook register = repositoryBook.findById(id)
					.orElseThrow(() -> new NotFoundException("Book ID " + id + " does not exist."));

			verifyActiveLoan(register.getIdBook());

			if (state == false) {
				register.logicalDelete();
			} else if (state == true) {
				register.logicalActivate();
			} 
			
			return register;
		} catch (NotFoundException | ValidateFieldsException e) {
			throw e;
		} catch (Exception e) {
			throw new GeneralServiceException(e.getMessage());
		}
	}

	@Override
	@Transactional
	public void delete(Long id) {
		try {
			LibraryBook register = repositoryBook.findById(id)
					.orElseThrow(() -> new NotFoundException("Book ID " + id + " does not exist."));

			verifyActiveLoan(register.getIdBook());

			repositoryBook.delete(register);
		} catch (NotFoundException | ValidateFieldsException e) {
			throw e;
		} catch (Exception e) {
			throw new GeneralServiceException(e.getMessage());
		}
	}
}
