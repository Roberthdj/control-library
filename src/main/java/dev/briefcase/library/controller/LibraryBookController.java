package dev.briefcase.library.controller;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.briefcase.library.config.exception.NotFoundException;
import dev.briefcase.library.dto.LibraryBookDTO;
import dev.briefcase.library.dto.converter.LibraryBookConverter;
import dev.briefcase.library.entity.LibraryBook;
import dev.briefcase.library.service.LibraryBookService;
import dev.briefcase.library.utils.DataWrapper;

import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/service/book")
public class LibraryBookController {

	private LibraryBookService service;
	private LibraryBookConverter converter;

	public LibraryBookController(LibraryBookService service, LibraryBookConverter converter) {
		this.service = service;
		this.converter = converter;
	}

	@GetMapping()
	public ResponseEntity<List<LibraryBookDTO>> findAll(
			@RequestParam(value = "title", required = false, defaultValue = "") String title,
			@RequestParam(value = "author", required = false, defaultValue = "") String author,
			@RequestParam(value = "lent", required = false, defaultValue = "") Boolean lent,
			@RequestParam(value = "offset", required = false, defaultValue = "0") int pageNumber,
			@RequestParam(value = "limit", required = false, defaultValue = "10") int pageSize) {

		Pageable page = PageRequest.of(pageNumber, pageSize);
		List<LibraryBook> libraryBooks;

		if (title == null && author == null && lent == null) {
			libraryBooks = service.findAll(page);

		} else if (title != null && author != null && lent == null) {
			libraryBooks = service.findByTitleAndAuthor(title, author, page);

		} else if (title != null && !title.isBlank() && author == null && lent == null) {
			libraryBooks = service.findByTitle(title, page);

		} else if (author != null && !author.isBlank() && title == null && lent == null) {
			libraryBooks = service.findByAuthor(author, page);

		} else if (lent != null) {
			libraryBooks = service.findByLent(lent, page);

		} else {
			libraryBooks = service.findAll(page);
		}

		if (libraryBooks.isEmpty()) {
			throw new NotFoundException("No data.");
		}

		List<LibraryBookDTO> response = converter.fromEntity(libraryBooks);
		// return ResponseEntity.ok(registerDTO);
		return new DataWrapper(true, "success", response).createResponse(HttpStatus.OK);

	}

	@GetMapping(value = "/active")
	public ResponseEntity<List<LibraryBookDTO>> findAllActive(
			@RequestParam(value = "actived", required = false, defaultValue = "true") Boolean actived,
			@RequestParam(value = "offset", required = false, defaultValue = "0") int pageNumber,
			@RequestParam(value = "limit", required = false, defaultValue = "10") int pageSize) {

		Pageable page = PageRequest.of(pageNumber, pageSize);
		List<LibraryBook> libraryBooks;

		libraryBooks = service.findByActive(actived, page);

		if (libraryBooks.isEmpty()) {
			throw new NotFoundException("No data.");
		}

		List<LibraryBookDTO> response = converter.fromEntity(libraryBooks);
		// return ResponseEntity.ok(registerDTO);
		return new DataWrapper(true, "success", response).createResponse(HttpStatus.OK);
	}

	@GetMapping(value = "/isbn/{isbn}")
	public ResponseEntity<LibraryBookDTO> findByIsbn(@PathVariable("isbn") String isbn) {
		LibraryBook register = service.findByIsbn(isbn);
		LibraryBookDTO response = converter.fromEntity(register);
		// return ResponseEntity.ok(registerDTO);
		return new DataWrapper(true, "success", response).createResponse(HttpStatus.OK);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<LibraryBookDTO> findById(@PathVariable("id") Long id) {
		LibraryBook register = service.findById(id);
		LibraryBookDTO response = converter.fromEntity(register);
		// return ResponseEntity.ok(registerDTO);
		return new DataWrapper(true, "success", response).createResponse(HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<LibraryBookDTO> create(@RequestBody LibraryBookDTO libraryBookDTO) {
		LibraryBook register = service.save(converter.fromDTO(libraryBookDTO));
		LibraryBookDTO response = converter.fromEntity(register);
		// return ResponseEntity.status(HttpStatus.CREATED).body(registerDTO);
		return new DataWrapper(true, "success", response).createResponse(HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<LibraryBookDTO> update(@RequestBody LibraryBookDTO libraryBookDTO) {
		LibraryBook register = service.update(converter.fromDTO(libraryBookDTO));
		LibraryBookDTO response = converter.fromEntity(register);
		// return ResponseEntity.ok(registerDTO);
		return new DataWrapper(true, "success", response).createResponse(HttpStatus.OK);
	}

	@PutMapping("/{id}/{state}") // state: {true:active}{false:inactive}
	public ResponseEntity<LibraryBookDTO> logicalDelete(@PathVariable("id") Long id,
			@PathVariable("state") Boolean state) {
		LibraryBook register = service.logicalDelete(id, state);
		LibraryBookDTO response = converter.fromEntity(register);
		// return ResponseEntity.ok(registerDTO);
		return new DataWrapper(true, "success", response).createResponse(HttpStatus.OK);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<LibraryBook> delete(@PathVariable("id") Long id) {
		service.delete(id);
		// return ResponseEntity.noContent().build();
		return new DataWrapper(true, "success", null).createResponse(HttpStatus.NO_CONTENT);
	}
}
