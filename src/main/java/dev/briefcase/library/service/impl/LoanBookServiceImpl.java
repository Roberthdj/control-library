package dev.briefcase.library.service.impl;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.briefcase.library.config.exception.GeneralServiceException;
import dev.briefcase.library.config.exception.NotFoundException;
import dev.briefcase.library.config.exception.ValidateFieldsException;
import dev.briefcase.library.entity.LibraryBook;
import dev.briefcase.library.entity.LibraryUser;
import dev.briefcase.library.entity.LoanBook;
import dev.briefcase.library.repository.LibraryBookRepository;
import dev.briefcase.library.repository.LibraryUserRepository;
import dev.briefcase.library.repository.LoanBookRepository;
import dev.briefcase.library.service.LoanBookService;
import dev.briefcase.library.validation.LoanBookValidator;

@Service
public class LoanBookServiceImpl implements LoanBookService {

	private LoanBookRepository repositoryLoan;
	private LibraryUserRepository repositoryUser;
	private LibraryBookRepository repositoryBook;
	private LoanBookValidator validator;

	public LoanBookServiceImpl(LoanBookRepository repository, LibraryUserRepository repositoryUser,
			LibraryBookRepository repositoryBook, LoanBookValidator validator) {
		this.repositoryLoan = repository;
		this.repositoryUser = repositoryUser;
		this.repositoryBook = repositoryBook;
		this.validator = validator;
	}

	private void verifyUser(Long user) {
		LibraryUser registerUser = repositoryUser.findById(user)
				.orElseThrow(() -> new NotFoundException("User ID " + user + " does not exist."));
		if (registerUser.getSanctioned().equals(true) || registerUser.getActive().equals(false)) {
			throw new GeneralServiceException(
					"The user with ID " + registerUser.getIdUser() + " has been sanctioned or is not active.");
		}
	}

	private void verifyBookAndprocessLoan(Long book, Boolean loanStatus, Boolean generateLoan) {
		LibraryBook registerBook = repositoryBook.findById(book)
				.orElseThrow(() -> new NotFoundException("Book ID " + book + " does not exist."));

		if (registerBook.getActive().equals(false)) {
			throw new GeneralServiceException("The book with ID " + registerBook.getIdBook() + " is not active.");
		}

		if (registerBook.getLent().equals(true) && generateLoan.equals(true)) {
			throw new GeneralServiceException(
					"The book with ID " + registerBook.getIdBook() + " has already been borrowed.");
		}
		registerBook.setLent(loanStatus);
	}

	@Override
	@Transactional(readOnly = true)
	public List<LoanBook> findAll(Pageable page) {
		try {
			return repositoryLoan.findAll(page).toList();
		} catch (NotFoundException | ValidateFieldsException e) {
			throw e;
		} catch (Exception e) {
			throw new GeneralServiceException(e.getMessage());
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<LoanBook> findByIdUser(Long user, Pageable page) {
		try {
			return repositoryLoan.findByIdUser(user, page);
		} catch (NotFoundException | ValidateFieldsException e) {
			throw e;
		} catch (Exception e) {
			throw new GeneralServiceException(e.getMessage());
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<LoanBook> findByIdBook(Long book, Pageable page) {
		try {
			return repositoryLoan.findByIdBook(book, page);
		} catch (NotFoundException | ValidateFieldsException e) {
			throw e;
		} catch (Exception e) {
			throw new GeneralServiceException(e.getMessage());
		}
	}

	@Override
	public List<LoanBook> findByIdentificationUser(String idf, Pageable page) {
		try {
			return repositoryLoan.findByIdentificationUser(idf, page);
		} catch (NotFoundException | ValidateFieldsException e) {
			throw e;
		} catch (Exception e) {
			throw new GeneralServiceException(e.getMessage());
		}
	}

	@Override
	public List<LoanBook> findByIsbnBook(String isbn, Pageable page) {
		try {
			return repositoryLoan.findByIsbnBook(isbn, page);
		} catch (NotFoundException | ValidateFieldsException e) {
			throw e;
		} catch (Exception e) {
			throw new GeneralServiceException(e.getMessage());
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<LoanBook> findByIdUserAndIdBook(Long user, Long book, Pageable page) {
		try {
			return repositoryLoan.findByIdUserAndIdBook(user, book, page);
		} catch (NotFoundException | ValidateFieldsException e) {
			throw e;
		} catch (Exception e) {
			throw new GeneralServiceException(e.getMessage());
		}
	}
	

	@Override
	public List<LoanBook> findByIdfAndIsbn(String idf, String isbn, Pageable page) {
		try {
			return repositoryLoan.findByIdfAndIsbn(idf, isbn, page);
		} catch (NotFoundException | ValidateFieldsException e) {
			throw e;
		} catch (Exception e) {
			throw new GeneralServiceException(e.getMessage());
		}
	}

	@Override
	@Transactional(readOnly = true)
	public LoanBook findById(Long id) {
		try {
			return repositoryLoan.findById(id)
					.orElseThrow(() -> new NotFoundException("Loan with ID " + id + " does not exist."));
		} catch (NotFoundException | ValidateFieldsException e) {
			throw e;
		} catch (Exception e) {
			throw new GeneralServiceException(e.getMessage());
		}
	}

	@Override
	@Transactional
	public LoanBook save(LoanBook loanBook) {
		try {

			validator.validateSaveData(loanBook);

			verifyUser(loanBook.getUser().getIdUser());
			verifyBookAndprocessLoan(loanBook.getBook().getIdBook(), true, true);
			
			loanBook.activateLoan();
			LoanBook register = repositoryLoan.save(loanBook);
			return register;
		} catch (NotFoundException | ValidateFieldsException e) {
			throw e;
		} catch (Exception e) {
			throw new GeneralServiceException(e.getMessage());
		}
	}

	@Override
	@Transactional
	public LoanBook update(LoanBook loanBook) {
		try {
			validator.validateIdNotNull(loanBook);
			validator.validateUpdateData(loanBook);

			LoanBook register = repositoryLoan.findById(loanBook.getIdLoan()).orElseThrow(
					() -> new NotFoundException("Loan with ID " + loanBook.getIdLoan() + " does not exist."));

			if (loanBook.getUser().getIdUser() != null) {
				register.setUser(loanBook.getUser());
			}

			if (loanBook.getBook().getIdBook() != null) {
				register.setBook(loanBook.getBook());
			}

			if (loanBook.getDepartureDate() != null) {
				register.setDepartureDate(loanBook.getDepartureDate());
			}

			if (loanBook.getReturnDate() != null) {
				register.setReturnDate(loanBook.getReturnDate());
			}

			if (loanBook.getObservation() != null && !loanBook.getObservation().isEmpty()) {
				register.setObservation(loanBook.getObservation());
			}

			repositoryLoan.save(register);
			return register;

		} catch (NotFoundException | ValidateFieldsException e) {
			throw e;
		} catch (Exception e) {
			throw new GeneralServiceException(e.getMessage());
		}
	}
	
	@Override
	@Transactional
	public LoanBook logicalDelete(Long id, Boolean state) {
		try {			
			LoanBook register = repositoryLoan.findById(id)
					.orElseThrow(() -> new NotFoundException("Loan ID " + id + " does not exist."));

			if (state == false) {
				register.deactivateLoan();
			} else if (state == true) {
				register.activateLoan();
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
			LoanBook register = repositoryLoan.findById(id)
					.orElseThrow(() -> new NotFoundException("Loan with ID " + id + " does not exist."));
			
			verifyBookAndprocessLoan(register.getBook().getIdBook(), false, false);
			repositoryLoan.delete(register);
		} catch (NotFoundException | ValidateFieldsException e) {
			throw e;
		} catch (Exception e) {
			throw new GeneralServiceException(e.getMessage());
		}

	}
}
