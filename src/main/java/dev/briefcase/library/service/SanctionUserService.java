package dev.briefcase.library.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import dev.briefcase.library.entity.SanctionUser;

public interface SanctionUserService {

	public List<SanctionUser> findAll(Pageable page);

	public List<SanctionUser> findByIdUser(Long user, Pageable page);

	public List<SanctionUser> findByIdentificationUser(String idf, Pageable page);

	public SanctionUser findById(Long id);

	public SanctionUser save(SanctionUser sanctionUser);

	public SanctionUser update(SanctionUser sanctionUser);

	public SanctionUser logicalDelete(Long id, Boolean state);

	public void delete(Long id);

}
