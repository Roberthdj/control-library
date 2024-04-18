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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.briefcase.library.config.exception.NotFoundException;
import dev.briefcase.library.dto.LibraryUserDTO;
import dev.briefcase.library.dto.converter.LibraryUserConverter;
import dev.briefcase.library.entity.LibraryUser;
import dev.briefcase.library.service.LibraryUserService;
import dev.briefcase.library.tool.DataWrapper;

@RestController
@RequestMapping("/service/user")
public class LibraryUserController {

	private LibraryUserService service;
	private LibraryUserConverter converter;

	public LibraryUserController(LibraryUserService service, LibraryUserConverter converter) {
		this.service = service;
		this.converter = converter;
	}

	@GetMapping()
	public ResponseEntity<List<LibraryUserDTO>> findAll(
			@RequestParam(value = "first_name", required = false, defaultValue = "") String first_name,
			@RequestParam(value = "last_name", required = false, defaultValue = "") String last_name,
			@RequestParam(value = "offset", required = false, defaultValue = "0") int pageNumber,
			@RequestParam(value = "limit", required = false, defaultValue = "10") int pageSize) {

		Pageable page = PageRequest.of(pageNumber, pageSize);
		List<LibraryUser> libraryUsers;

		if (first_name == null && last_name == null) {
			libraryUsers = service.findAll(page);

		} else if (first_name != null && last_name != null) {
			libraryUsers = service.findByFirstNameContainingAndLastNameContaining(first_name, last_name, page);

		} else if (first_name != null && !first_name.isBlank()) {
			libraryUsers = service.findByFirstName(first_name, page);

		} else if (last_name != null && !last_name.isBlank()) {
			libraryUsers = service.findByLastName(last_name, page);

		} else {
			libraryUsers = service.findAll(page);
		}

		if (libraryUsers.isEmpty()) {
			throw new NotFoundException("No data.");
		}

		List<LibraryUserDTO> response = converter.fromEntity(libraryUsers);
		//return ResponseEntity.ok(registerDTO);
		return new DataWrapper(true, "success", response).createResponse(HttpStatus.OK);
	}

	@GetMapping(value = "/actived")
	public ResponseEntity<List<LibraryUserDTO>> findActicedAndSanctioned(
			@RequestParam(value = "actived", required = false, defaultValue = "true") Boolean actived,
			@RequestParam(value = "sanctioned", required = false, defaultValue = "") Boolean sanctioned,
			@RequestParam(value = "offset", required = false, defaultValue = "0") int pageNumber,
			@RequestParam(value = "limit", required = false, defaultValue = "10") int pageSize) {

		Pageable  page = PageRequest.of(pageNumber, pageSize);
		List<LibraryUser> libraryUsers;

		if (actived != null && sanctioned != null) {
			libraryUsers = service.findByActivedAndSanctioned(actived, sanctioned, page);
			
		} else if (actived != null) {
			libraryUsers = service.findByActive(actived, page);

		} else if (sanctioned != null) {
			libraryUsers = service.findBySanctioned(sanctioned, page);

		} else {
			libraryUsers = service.findAll(page);
		}

		if (libraryUsers.isEmpty()) {
			throw new NotFoundException("No data.");
		}

		List<LibraryUserDTO> response = converter.fromEntity(libraryUsers);
		//return ResponseEntity.ok(registerDTO);
		return new DataWrapper(true, "success", response).createResponse(HttpStatus.OK);
	}

	@GetMapping(value = "/identification/{idf}")
	public ResponseEntity<LibraryUserDTO> findByIdentification(@PathVariable("idf") String idf) {
		LibraryUser register = service.findByIdentification(idf);
		LibraryUserDTO response = converter.fromEntity(register);
		//return ResponseEntity.ok(registerDTO);
		return new DataWrapper(true, "success", response).createResponse(HttpStatus.OK);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<LibraryUserDTO> findById(@PathVariable("id") Long id) {
		LibraryUser register = service.findById(id);
		LibraryUserDTO response = converter.fromEntity(register);
		//return ResponseEntity.ok(registerDTO);
		return new DataWrapper(true, "success", response).createResponse(HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<LibraryUserDTO> create(@RequestBody LibraryUserDTO libraryUserDTO) {
		LibraryUser register = service.save(converter.fromDTO(libraryUserDTO));
		LibraryUserDTO response = converter.fromEntity(register);
		//return ResponseEntity.status(HttpStatus.CREATED).body(registerDTO);
		return new DataWrapper(true, "success", response).createResponse(HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<LibraryUserDTO> update(@RequestBody LibraryUserDTO libraryUserDTO) {
		LibraryUser register = service.update(converter.fromDTO(libraryUserDTO));
		LibraryUserDTO response = converter.fromEntity(register);
		//return ResponseEntity.ok(registerDTO);
		return new DataWrapper(true, "success", response).createResponse(HttpStatus.OK);
	}

	@PutMapping(value = "/{id}/{state}") // state: {true:active}{false:inactive}
	public ResponseEntity<LibraryUserDTO> logicalDelete(@PathVariable("id") Long id, @PathVariable("state") Boolean state) {
		LibraryUser register = service.logicalDelete(id, state);
		LibraryUserDTO response = converter.fromEntity(register);
		//return ResponseEntity.ok(registerDTO);
		return new DataWrapper(true, "success", response).createResponse(HttpStatus.OK);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<LibraryUser> delete(@PathVariable("id") Long id) {
		service.delete(id);
		//return ResponseEntity.noContent().build();
		return new DataWrapper(true, "success", null).createResponse(HttpStatus.NO_CONTENT);
	}

}
