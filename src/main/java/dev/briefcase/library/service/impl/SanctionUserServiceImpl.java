package dev.briefcase.library.service.impl;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.briefcase.library.config.exception.GeneralServiceException;
import dev.briefcase.library.config.exception.NotFoundException;
import dev.briefcase.library.config.exception.ValidateFieldsException;
import dev.briefcase.library.entity.LibraryUser;
import dev.briefcase.library.entity.SanctionUser;
import dev.briefcase.library.repository.LibraryUserRepository;
import dev.briefcase.library.repository.SanctionUserRepository;
import dev.briefcase.library.service.SanctionUserService;
import dev.briefcase.library.validation.SanctionUserValidator;

@Service
public class SanctionUserServiceImpl implements SanctionUserService {

	private SanctionUserRepository repositorySanction;
	private LibraryUserRepository repositoryUser;
	private SanctionUserValidator validator;

	public SanctionUserServiceImpl(SanctionUserRepository repositorySanction, LibraryUserRepository repositoryUser,
			SanctionUserValidator validator) {
		this.repositorySanction = repositorySanction;
		this.repositoryUser = repositoryUser;
		this.validator = validator;
	}

	private void processSanction(Long user, Boolean sanctionStatus, Boolean generateSanction) {
		LibraryUser registerUser = repositoryUser.findById(user)
				.orElseThrow(() -> new NotFoundException("User ID " + user + " does not exist."));
		if (registerUser.getSanctioned().equals(true) && generateSanction.equals(true)) {
			throw new GeneralServiceException(
					"The user with ID " + registerUser.getIdUser() + " has already been sanctioned.");
		}
		registerUser.setSanctioned(sanctionStatus);
	}

	@Override
	@Transactional(readOnly = true)
	public List<SanctionUser> findAll(Pageable page) {
		try {
			return repositorySanction.findAll(page).toList();
		} catch (NotFoundException | ValidateFieldsException e) {
			throw e;
		} catch (Exception e) {
			throw new GeneralServiceException(e.getMessage());
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<SanctionUser> findByIdUser(Long user, Pageable page) {
		try {
			return repositorySanction.findByIdUser(user, page);
		} catch (NotFoundException | ValidateFieldsException e) {
			throw e;
		} catch (Exception e) {
			throw new GeneralServiceException(e.getMessage());
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<SanctionUser> findByIdentificationUser(String idf, Pageable page) {
		try {
			return repositorySanction.findByIdentificationUser(idf, page);
		} catch (NotFoundException | ValidateFieldsException e) {
			throw e;
		} catch (Exception e) {
			throw new GeneralServiceException(e.getMessage());
		}
	}

	@Override
	@Transactional(readOnly = true)
	public SanctionUser findById(Long id) {
		try {
			return repositorySanction.findById(id)
					.orElseThrow(() -> new NotFoundException("Sanction with ID " + id + " does not exist."));
		} catch (NotFoundException | ValidateFieldsException e) {
			throw e;
		} catch (Exception e) {
			throw new GeneralServiceException(e.getMessage());
		}
	}

	@Override
	@Transactional
	public SanctionUser save(SanctionUser sanctionUser) {
		try {

			validator.validateSaveData(sanctionUser);

			processSanction(sanctionUser.getUser().getIdUser(), true, true);

			sanctionUser.ActivateSanction();
			SanctionUser register = repositorySanction.save(sanctionUser);
			return register;
		} catch (NotFoundException | ValidateFieldsException e) {
			throw e;
		} catch (Exception e) {
			throw new GeneralServiceException(e.getMessage());
		}
	}

	@Override
	@Transactional
	public SanctionUser update(SanctionUser sanctionUser) {
		try {
			validator.validateIdNotNull(sanctionUser);
			validator.validateUpdateData(sanctionUser);

			SanctionUser register = repositorySanction.findById(sanctionUser.getIdSanction())
					.orElseThrow(() -> new NotFoundException(
							"Sanction with ID " + sanctionUser.getIdSanction() + " does not exist."));

			if (sanctionUser.getUser().getIdUser() != null) {
				// register.setUser(sanctionUser.getUser());
			}

			if (sanctionUser.getStartDate() != null) {
				register.setStartDate(sanctionUser.getStartDate());
			}

			if (sanctionUser.getCompletionDate() != null) {
				register.setCompletionDate(sanctionUser.getCompletionDate());
			}

			if (sanctionUser.getObservation() != null && !sanctionUser.getObservation().isEmpty()) {
				register.setObservation(sanctionUser.getObservation());
			}

			repositorySanction.save(register);
			return register;

		} catch (NotFoundException | ValidateFieldsException e) {
			throw e;
		} catch (Exception e) {
			throw new GeneralServiceException(e.getMessage());
		}
	}

	@Override
	public SanctionUser logicalDelete(Long id, Boolean state) {
		try {
			SanctionUser register = repositorySanction.findById(id)
					.orElseThrow(() -> new NotFoundException("Sanction ID " + id + " does not exist."));

			if (state == false) {
				register.getUser().deactivateSanction();
				register.deactivateSanction();
			} else if (state == true) {
				register.getUser().activateSanction();
				register.ActivateSanction();
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
		SanctionUser register = repositorySanction.findById(id)
				.orElseThrow(() -> new NotFoundException("Sanction with ID " + id + " does not exist."));

		processSanction(register.getUser().getIdUser(), false, false);
		register.getUser().deactivateSanction();
		repositorySanction.delete(register);
	}
}