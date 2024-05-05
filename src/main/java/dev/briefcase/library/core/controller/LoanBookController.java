package dev.briefcase.library.core.controller;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.briefcase.library.core.dto.LoanBookRegisterDTO;
import dev.briefcase.library.core.dto.LoanBookResponseDTO;
import dev.briefcase.library.core.dto.converter.LoanBookConverter;
import dev.briefcase.library.core.entity.LibraryUser;
import dev.briefcase.library.core.entity.LoanBook;
import dev.briefcase.library.core.service.LoanBookService;
import dev.briefcase.library.error.exception.NotFoundException;
import dev.briefcase.library.utils.DataWrapper;

@RestController
@RequestMapping("/service/loan")
public class LoanBookController {

	private LoanBookService serviceLoan;
	private LoanBookConverter converterLoan;
	
	public LoanBookController(LoanBookService serviceLoan, LoanBookConverter converterLoan)	 {
		this.serviceLoan = serviceLoan;
		this.converterLoan = converterLoan;
	}

	@GetMapping()
	public ResponseEntity<List<LoanBookResponseDTO>> findAll(
			@RequestParam(value = "idfUser", required = false, defaultValue = "") String idfUser,
			@RequestParam(value = "isbnBook", required = false, defaultValue = "") String isbnBook,
			@RequestParam(value = "offset", required = false, defaultValue = "0") int pageNumber,
			@RequestParam(value = "limit", required = false, defaultValue = "10") int pageSize) {

		Pageable page = PageRequest.of(pageNumber, pageSize);
		List<LoanBook> loanBooks;

		if (idfUser.isBlank() && isbnBook.isBlank()) {
			loanBooks = serviceLoan.findAll(page);
			
		} else if (!idfUser.isBlank() && !isbnBook.isBlank()) {
			loanBooks = serviceLoan.findByIdfAndIsbn(idfUser, isbnBook, page);

		} else if (!idfUser.isBlank() && isbnBook.isBlank()) {
			loanBooks = serviceLoan.findByIdentificationUser(idfUser, page);

		} else if (idfUser.isBlank() && !isbnBook.isBlank()) {
			loanBooks = serviceLoan.findByIsbnBook(isbnBook, page);

		} else {
			loanBooks = serviceLoan.findAll(page);
		}

		if (loanBooks.isEmpty()) {
			throw new NotFoundException("No data.");
		}

		List<LoanBookResponseDTO> response = converterLoan.listResponse(loanBooks);
		//return ResponseEntity.ok(registerDTO);
		return new DataWrapper(true, "success", response).createResponse(HttpStatus.OK);
	}
	
	@GetMapping("/id")
	public ResponseEntity<List<LoanBookResponseDTO>> findAll(
			@RequestParam(value = "user", required = false, defaultValue = "") Long user,
			@RequestParam(value = "book", required = false, defaultValue = "") Long book,
			@RequestParam(value = "offset", required = false, defaultValue = "0") int pageNumber,
			@RequestParam(value = "limit", required = false, defaultValue = "10") int pageSize) {

		Pageable page = PageRequest.of(pageNumber, pageSize);
		List<LoanBook> loanBooks;

		if (user == null && book == null) {
			loanBooks = serviceLoan.findAll(page);
			
		} else if (user != null && book != null) {
			loanBooks = serviceLoan.findByIdUserAndIdBook(user, book, page);

		} else if (user != null && book == null) {
			loanBooks = serviceLoan.findByIdUser(user, page);

		} else if (user == null && book != null) {
			loanBooks = serviceLoan.findByIdBook(book, page);

		} else {
			loanBooks = serviceLoan.findAll(page);
		}

		if (loanBooks.isEmpty()) {
			throw new NotFoundException("No data.");
		}

		List<LoanBookResponseDTO> response = converterLoan.listResponse(loanBooks);
		//return ResponseEntity.ok(registerDTO);
		return new DataWrapper(true, "success", response).createResponse(HttpStatus.OK);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<LoanBookResponseDTO> findById(@PathVariable("id") Long id) {
		LoanBook register = serviceLoan.findById(id);
		LoanBookResponseDTO response = converterLoan.createResponse(register);
		//return ResponseEntity.ok(response);
		return new DataWrapper(true, "success", response).createResponse(HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<LoanBookResponseDTO> create(@RequestBody LoanBookRegisterDTO loanBookRegisterDTO) {
		LoanBook register = serviceLoan.save(converterLoan.fromDTO(loanBookRegisterDTO));
		LoanBookResponseDTO response = converterLoan.createResponse(register);
		//return ResponseEntity.status(HttpStatus.CREATED).body(response);
		return new DataWrapper(true, "success", response).createResponse(HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<LoanBookResponseDTO> update(@RequestBody LoanBookRegisterDTO loanBookRegisterDTO) {
		LoanBook register = serviceLoan.update(converterLoan.fromDTO(loanBookRegisterDTO));
		LoanBookResponseDTO response = converterLoan.createResponse(register);
		//return ResponseEntity.ok(response);
		return new DataWrapper(true, "success", response).createResponse(HttpStatus.OK);
	}
	
	@PutMapping(value = "/{id}/{state}") // state: {true:active}{false:inactive}
	public ResponseEntity<LoanBookResponseDTO> logicalDelete(@PathVariable("id") Long id, @PathVariable("state") Boolean state) {
		LoanBook register = serviceLoan.logicalDelete(id, state);
		LoanBookResponseDTO response = converterLoan.createResponse(register);
		//return ResponseEntity.ok(registerDTO);
		return new DataWrapper(true, "success", response).createResponse(HttpStatus.OK);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<LibraryUser> delete(@PathVariable("id") Long id) {
		serviceLoan.delete(id);
		//return ResponseEntity.noContent().build();
		return new DataWrapper(true, "success", null).createResponse(HttpStatus.NO_CONTENT);
	}

}
