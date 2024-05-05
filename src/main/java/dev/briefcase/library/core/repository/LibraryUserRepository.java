package dev.briefcase.library.core.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.briefcase.library.core.entity.LibraryUser;

@Repository
public interface LibraryUserRepository extends JpaRepository<LibraryUser, Long> {

	List<LibraryUser> findByFirstNameContaining(String firstName, Pageable page);

	List<LibraryUser> findByLastNameContaining(String lastName, Pageable page);

	List<LibraryUser> findByFirstNameContainingAndLastNameContaining(String firstName, String lastName, Pageable page);

	List<LibraryUser> findByActive(Boolean active, Pageable page);

	List<LibraryUser> findBySanctioned(Boolean sanctioned, Pageable page);

	List<LibraryUser> findByActiveAndSanctioned(Boolean actived, Boolean sanctioned, Pageable page);

	LibraryUser findByIdentification(String identificaction);

	Boolean existsByIdentification(String identification);

}
