package dev.briefcase.library.core.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.briefcase.library.core.entity.LibraryBook;

@Repository
public interface LibraryBookRepository extends JpaRepository<LibraryBook, Long> {

	List<LibraryBook> findByTitleContaining(String title, Pageable page);

	List<LibraryBook> findByAuthorContaining(String author, Pageable page);
	
	List<LibraryBook> findByTitleContainingAndAuthorContaining(String title, String author, Pageable page);

	List<LibraryBook> findByActive(Boolean active, Pageable page);

	List<LibraryBook> findByLent(Boolean lent, Pageable page);

	LibraryBook findByIsbn(String isbn);

	Boolean existsByIsbn(String isbn);

}
