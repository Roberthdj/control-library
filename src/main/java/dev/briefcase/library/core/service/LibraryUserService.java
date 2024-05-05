package dev.briefcase.library.core.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import dev.briefcase.library.core.entity.LibraryUser;

public interface LibraryUserService {

	public List<LibraryUser> findAll(Pageable page);

	public List<LibraryUser> findByFirstName(String firstName, Pageable page);

	public List<LibraryUser> findByLastName(String lastName, Pageable page);

	public List<LibraryUser> findByFirstNameContainingAndLastNameContaining(String firstName, String lastName,
			Pageable page);

	public List<LibraryUser> findByActive(Boolean actived, Pageable page);

	public List<LibraryUser> findBySanctioned(Boolean sanctioned, Pageable page);
	
	public List<LibraryUser> findByActivedAndSanctioned(Boolean actived,Boolean sanctioned, Pageable page);

	public LibraryUser findByIdentification(String identificaction);

	public Boolean existByIdentification(String identificaction);

	public LibraryUser findById(Long id);

	public LibraryUser save(LibraryUser libraryUser);

	public LibraryUser update(LibraryUser libraryUser);

	public LibraryUser logicalDelete(Long id, Boolean state);

	public void delete(Long id);

}
