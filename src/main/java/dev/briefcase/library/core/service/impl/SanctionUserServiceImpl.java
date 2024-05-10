package dev.briefcase.library.core.service.impl;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.briefcase.library.core.entity.LibraryUser;
import dev.briefcase.library.core.entity.SanctionUser;
import dev.briefcase.library.core.repository.LibraryUserRepository;
import dev.briefcase.library.core.repository.SanctionUserRepository;
import dev.briefcase.library.core.service.SanctionUserService;
import dev.briefcase.library.error.exception.GeneralServiceException;
import dev.briefcase.library.error.exception.NotFoundException;
import dev.briefcase.library.error.exception.ValidateFieldsException;
import dev.briefcase.library.validation.SanctionUserValidator;
import lombok.extern.slf4j.Slf4j;

@Slf4j
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
	
	@Override
	@Transactional(readOnly = true)
	public List<SanctionUser> findAll(Pageable page) {
		try {
			return repositorySanction.findAll(page).toList();
		} catch (NotFoundException | ValidateFieldsException e) {
			log.info(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GeneralServiceException(e.getMessage(), e);
		}		
	}

	@Override
	@Transactional(readOnly = true)
	public List<SanctionUser> findByIdUser(Long user, Pageable page) {
		try {
			return repositorySanction.findByIdUser(user, page);
		} catch (NotFoundException | ValidateFieldsException e) {
			log.info(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GeneralServiceException(e.getMessage(), e);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<SanctionUser> findByIdentificationUser(String idf, Pageable page) {
		try {
			return repositorySanction.findByIdentificationUser(idf, page);
		} catch (NotFoundException | ValidateFieldsException e) {
			log.info(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GeneralServiceException(e.getMessage(), e);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public SanctionUser findById(Long id) {
		try {
			return repositorySanction.findById(id)
					.orElseThrow(() -> new NotFoundException("Sanction with ID " + id + " does not exist."));
		} catch (NotFoundException | ValidateFieldsException e) {
			log.info(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GeneralServiceException(e.getMessage(), e);
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
			log.info(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GeneralServiceException(e.getMessage(), e);
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
			log.info(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GeneralServiceException(e.getMessage(), e);
		}
	}

	@Override
	public SanctionUser logicalDelete(Long id, Boolean state) {
		try {
			SanctionUser register = repositorySanction.findById(id)
					.orElseThrow(() -> new NotFoundException("Sanction ID " + id + " does not exist."));

			if (state == false) {
				System.out.println("AQUI FALSE");
				register.getUser().deactivateSanction();
				register.deactivateSanction();
			} else if (state == true) {
				System.out.println("AQUI TRUE");
				register.getUser().activateSanction();
				register.ActivateSanction();
			}
			
			repositorySanction.save(register);			
			return register;
			
		} catch (NotFoundException | ValidateFieldsException e) {
			log.info(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GeneralServiceException(e.getMessage(), e);
		}
	}

	@Override
	@Transactional
	public void delete(Long id) {
		try {
			SanctionUser register = repositorySanction.findById(id)
					.orElseThrow(() -> new NotFoundException("Sanction with ID " + id + " does not exist."));

			processSanction(register.getUser().getIdUser(), false, false);
			register.getUser().deactivateSanction();
			repositorySanction.delete(register);
		} catch (NotFoundException | ValidateFieldsException e) {
			log.info(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GeneralServiceException(e.getMessage(),e);
		}
	}
	
	/*
	 * Methods to use within the class
	 */
	
	private void processSanction(Long user, Boolean sanctionStatus, Boolean generateSanction) {
		LibraryUser registerUser = repositoryUser.findById(user)
				.orElseThrow(() -> new NotFoundException("User ID " + user + " does not exist."));
		if (registerUser.getSanctioned().equals(true) && generateSanction.equals(true)) {
			throw new GeneralServiceException(
					"The user with ID " + registerUser.getIdUser() + " has already been sanctioned.");
		}
		registerUser.setSanctioned(sanctionStatus);
	}
}
