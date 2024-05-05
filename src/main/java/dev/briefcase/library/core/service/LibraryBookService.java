package dev.briefcase.library.core.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import dev.briefcase.library.core.entity.LibraryBook;

public interface LibraryBookService {

	public List<LibraryBook> findAll(Pageable page);

	public List<LibraryBook> findByTitle(String title, Pageable page);

	public List<LibraryBook> findByAuthor(String author, Pageable page);
	
	public List<LibraryBook> findByTitleAndAuthor(String title, String author, Pageable page);

	public List<LibraryBook> findByActive(Boolean actived, Pageable page);

	public List<LibraryBook> findByLent(Boolean lent, Pageable page);

	public LibraryBook findByIsbn(String isbn);

	public Boolean existByIsbn(String isbn);

	public LibraryBook findById(Long id);

	public LibraryBook save(LibraryBook libraryBook);

	public LibraryBook update(LibraryBook libraryBook);

	public LibraryBook logicalDelete(Long id, Boolean state);

	public void delete(Long id);

}
