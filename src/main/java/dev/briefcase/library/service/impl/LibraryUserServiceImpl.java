package dev.briefcase.library.service.impl;

import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.briefcase.library.config.exception.GeneralServiceException;
import dev.briefcase.library.config.exception.NotFoundException;
import dev.briefcase.library.config.exception.ValidateFieldsException;
import dev.briefcase.library.entity.LibraryUser;
import dev.briefcase.library.entity.LoanBook;
import dev.briefcase.library.repository.LibraryUserRepository;
import dev.briefcase.library.repository.LoanBookRepository;
import dev.briefcase.library.service.LibraryUserService;
import dev.briefcase.library.validation.LibraryUserValidator;

@Service
public class LibraryUserServiceImpl implements LibraryUserService {

	private LibraryUserRepository repositoryUser;
	private LibraryUserValidator validatorUser;
	private LoanBookRepository repositoryLoan;

	public LibraryUserServiceImpl(LibraryUserRepository repositoryUser, LibraryUserValidator validatorUser,
			LoanBookRepository repositoryLoan) {
		this.repositoryUser = repositoryUser;
		this.validatorUser = validatorUser;
		this.repositoryLoan = repositoryLoan;
	}

	private void verifyLoanOrSanction(Long user, Boolean sanctined) {
		List<LoanBook> register = repositoryLoan.findByIdUser(user, null);

		if (sanctined.equals(true))
			throw new GeneralServiceException(
					"User with ID " + user + " has active sanction and cannot be deactivated or deleted.");

		for (LoanBook reg : register) {
			if (reg.getBook().getLent().equals(true)) {
				throw new GeneralServiceException(
						"User with ID " + user + " has active loans and cannot be deactivated or deleted.");
			}
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<LibraryUser> findAll(Pageable page) {
		try {
			return repositoryUser.findAll(page).toList();
		} catch (NotFoundException | ValidateFieldsException e) {
			throw e;
		} catch (Exception e) {
			throw new GeneralServiceException(e.getMessage());
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<LibraryUser> findByFirstName(String firstName, Pageable page) {
		try {
			return repositoryUser.findByFirstNameContaining(firstName, page);
		} catch (NotFoundException | ValidateFieldsException e) {
			throw e;
		} catch (Exception e) {
			throw new GeneralServiceException(e.getMessage());
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<LibraryUser> findByLastName(String lastName, Pageable page) {
		try {
			return repositoryUser.findByLastNameContaining(lastName, page);
		} catch (NotFoundException | ValidateFieldsException e) {
			throw e;
		} catch (Exception e) {
			throw new GeneralServiceException(e.getMessage());
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<LibraryUser> findByFirstNameContainingAndLastNameContaining(String firstName, String lastName,
			Pageable page) {
		try {
			return repositoryUser.findByFirstNameContainingAndLastNameContaining(firstName, lastName, page);
		} catch (NotFoundException | ValidateFieldsException e) {
			throw e;
		} catch (Exception e) {
			throw new GeneralServiceException(e.getMessage());
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<LibraryUser> findBySanctioned(Boolean sanctioned, Pageable page) {
		try {
			return repositoryUser.findBySanctioned(sanctioned, page);
		} catch (NotFoundException | ValidateFieldsException e) {
			throw e;
		} catch (Exception e) {
			throw new GeneralServiceException(e.getMessage());
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<LibraryUser> findByActive(Boolean actived, Pageable page) {
		try {
			return repositoryUser.findByActive(actived, page);
		} catch (NotFoundException | ValidateFieldsException e) {
			throw e;
		} catch (Exception e) {
			throw new GeneralServiceException(e.getMessage());
		}
	}

	@Override
	public List<LibraryUser> findByActivedAndSanctioned(Boolean actived, Boolean sanctioned, Pageable page) {
		try {
			return repositoryUser.findByActiveAndSanctioned(actived, sanctioned, page);
		} catch (NotFoundException | ValidateFieldsException e) {
			throw e;
		} catch (Exception e) {
			throw new GeneralServiceException(e.getMessage());
		}
	}

	@Override
	@Transactional(readOnly = true)
	public LibraryUser findByIdentification(String identification) {
		try {
			if (!existByIdentification(identification)) {
				throw new NotFoundException("Identification " + identification + " does not exist.");
			}
			LibraryUser register = repositoryUser.findByIdentification(identification);
			return register;
		} catch (NotFoundException | ValidateFieldsException e) {
			throw e;
		} catch (Exception e) {
			throw new GeneralServiceException(e.getMessage());
		}
	}

	@Override
	public Boolean existByIdentification(String identification) {
		try {
			return repositoryUser.existsByIdentification(identification);
		} catch (NotFoundException | ValidateFieldsException e) {
			throw e;
		} catch (Exception e) {
			throw new GeneralServiceException(e.getMessage());
		}
	}

	@Override
	@Transactional(readOnly = true)
	public LibraryUser findById(Long id) {
		try {
			LibraryUser register = repositoryUser.findById(id)
					.orElseThrow(() -> new NotFoundException("User ID " + id + " does not exist."));
			return register;
		} catch (NotFoundException | ValidateFieldsException e) {
			throw e;
		} catch (Exception e) {
			throw new GeneralServiceException(e.getMessage());
		}
	}

	@Override
	@Transactional
	public LibraryUser save(LibraryUser libraryUser) {
		try {
			validatorUser.validateSaveData(libraryUser);

			if (existByIdentification(libraryUser.getIdentification())) {
				throw new GeneralServiceException(
						"Identification " + libraryUser.getIdentification() + " has already been registered.");
			}

			libraryUser.setActive(true);
			libraryUser.setSanctioned(false);
			LibraryUser register = repositoryUser.save(libraryUser);
			return register;
		} catch (NotFoundException | ValidateFieldsException e) {
			throw e;
		} catch (Exception e) {
			throw new GeneralServiceException(e.getMessage());
		}
	}

	@Override
	@Transactional
	public LibraryUser update(LibraryUser libraryUser) {
		try {
			validatorUser.validateIdNotNull(libraryUser);
			validatorUser.validateUpdateData(libraryUser);

			if (existByIdentification(libraryUser.getIdentification())) {
				throw new GeneralServiceException(
						"Identification " + libraryUser.getIdentification() + " has already been registered.");
			}

			LibraryUser register = repositoryUser.findById(libraryUser.getIdUser()).orElseThrow(
					() -> new NotFoundException("User ID " + libraryUser.getIdUser() + " does not exist."));

			if (libraryUser.getFirstName() != null && !libraryUser.getFirstName().isBlank()) {
				register.setFirstName(libraryUser.getFirstName());
			}

			if (libraryUser.getLastName() != null && !libraryUser.getLastName().isBlank()) {
				register.setLastName(libraryUser.getLastName());
			}

			if (libraryUser.getIdentification() != null && !libraryUser.getIdentification().isBlank()) {
				register.setIdentification(libraryUser.getIdentification());
			}

			if (libraryUser.getEmail() != null && !libraryUser.getEmail().isBlank()) {
				register.setEmail(libraryUser.getEmail());
			}

			if (libraryUser.getPhoneNumber() != null && !libraryUser.getPhoneNumber().isBlank()) {
				register.setPhoneNumber(libraryUser.getPhoneNumber());
			}

			if (libraryUser.getAddress() != null && !libraryUser.getAddress().isBlank()) {
				register.setAddress(libraryUser.getAddress());
			}

			if (libraryUser.getSanctioned() != null) {
				register.setSanctioned(libraryUser.getSanctioned());
			}

			repositoryUser.save(register);
			return register;

		} catch (NotFoundException | ValidateFieldsException e) {
			throw e;
		} catch (Exception e) {
			throw new GeneralServiceException(e.getMessage());
		}
	}

	@Override
	@Transactional
	public LibraryUser logicalDelete(Long id, Boolean state) {
		try {
			LibraryUser register = repositoryUser.findById(id)
					.orElseThrow(() -> new NotFoundException("User ID " + id + " does not exist."));

			verifyLoanOrSanction(register.getIdUser(), register.getSanctioned());

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
			LibraryUser register = repositoryUser.findById(id)
					.orElseThrow(() -> new NotFoundException("User ID " + id + " does not exist."));

			verifyLoanOrSanction(register.getIdUser(), register.getSanctioned());

			repositoryUser.delete(register);
		} catch (NotFoundException | ValidateFieldsException e) {
			throw e;
		} catch (Exception e) {
			throw new GeneralServiceException(e.getMessage());
		}
	}
}
