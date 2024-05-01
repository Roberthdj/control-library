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
import dev.briefcase.library.dto.SanctionUserRegisterDTO;
import dev.briefcase.library.dto.SanctionUserResponseDTO;
import dev.briefcase.library.dto.converter.SanctionUserConverter;
import dev.briefcase.library.entity.SanctionUser;
import dev.briefcase.library.service.SanctionUserService;
import dev.briefcase.library.utils.DataWrapper;

@RestController
@RequestMapping("/service/sanction")
public class SanctionUserController {

	private SanctionUserService service;
	private SanctionUserConverter converter;

	public SanctionUserController(SanctionUserService service, SanctionUserConverter converter) {
		this.service = service;
		this.converter = converter;
	}

	@GetMapping()
	public ResponseEntity<List<SanctionUserResponseDTO>> findAll(
			@RequestParam(value = "user", required = false, defaultValue = "") Long user,
			@RequestParam(value = "idf", required = false, defaultValue = "") String idf,
			@RequestParam(value = "offset", required = false, defaultValue = "0") int pageNumber,
			@RequestParam(value = "limit", required = false, defaultValue = "10") int pageSize) {

		Pageable page = PageRequest.of(pageNumber, pageSize);
		List<SanctionUser> sanctionUsers;

		if (user == null && idf.isBlank()) {
			sanctionUsers = service.findAll(page);

		} else if (user != null && idf.isBlank()) {
			sanctionUsers = service.findByIdUser(user, page);

		} else if (user == null && !idf.isBlank()) {
			sanctionUsers = service.findByIdentificationUser(idf, page); 

		} else {
			sanctionUsers = service.findAll(page);
		}

		if (sanctionUsers.isEmpty()) {
			throw new NotFoundException("No data.");
		}

		List<SanctionUserResponseDTO> response = converter.listResponse(sanctionUsers);
		//return ResponseEntity.ok(registerDTO);
		return new DataWrapper(true, "success", response).createResponse(HttpStatus.OK);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<SanctionUserResponseDTO> findById(@PathVariable("id") Long id) {
		SanctionUser register = service.findById(id);
		SanctionUserResponseDTO response = converter.createResponse(register);
		//return ResponseEntity.ok(response);
		return new DataWrapper(true, "success", response).createResponse(HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<SanctionUserResponseDTO> create(
			@RequestBody SanctionUserRegisterDTO sanctionUserRegisterDTO) {
		SanctionUser register = service.save(converter.fromDTO(sanctionUserRegisterDTO));
		SanctionUserResponseDTO response = converter.createResponse(register);
		//return ResponseEntity.status(HttpStatus.CREATED).body(response);
		return new DataWrapper(true, "success", response).createResponse(HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<SanctionUserResponseDTO> update(
			@RequestBody SanctionUserRegisterDTO sanctionUserRegisterDTO) {
		SanctionUser register = service.update(converter.fromDTO(sanctionUserRegisterDTO));
		SanctionUserResponseDTO response = converter.createResponse(register);
		//return ResponseEntity.ok(response);
		return new DataWrapper(true, "success", response).createResponse(HttpStatus.OK);
	}
	
	@PutMapping(value = "/{id}/{state}") // state: {true:active}{false:inactive}
	public ResponseEntity<SanctionUserResponseDTO> logicalDelete(@PathVariable("id") Long id, @PathVariable("state") Boolean state) {
		SanctionUser register = service.logicalDelete(id, state);
		SanctionUserResponseDTO response = converter.createResponse(register);
		//return ResponseEntity.ok(registerDTO);
		return new DataWrapper(true, "success", response).createResponse(HttpStatus.OK);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<SanctionUserResponseDTO> delete(@PathVariable("id") Long id) {
		service.delete(id);
		//return ResponseEntity.noContent().build();
		return new DataWrapper(true, "success", null).createResponse(HttpStatus.NO_CONTENT);
	}

}
