package dev.briefcase.library.core.dto.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import dev.briefcase.library.core.dto.LibraryBookResponseDTO;
import dev.briefcase.library.core.dto.LibraryUserResponseDTO;
import dev.briefcase.library.core.dto.LoanBookRegisterDTO;
import dev.briefcase.library.core.dto.LoanBookResponseDTO;
import dev.briefcase.library.core.entity.LibraryBook;
import dev.briefcase.library.core.entity.LibraryUser;
import dev.briefcase.library.core.entity.LoanBook;
import dev.briefcase.library.core.service.LibraryBookService;
import dev.briefcase.library.core.service.LibraryUserService;
import dev.briefcase.library.utils.Utils;

@Component
public class LoanBookConverter extends AbstractConverter<LoanBook, LoanBookRegisterDTO> {

	private LibraryUserService serviceUser;
	private LibraryUserResponseConverter converterResponseUser;

	private LibraryBookService serviceBook;
	private LibraryBookResponseConverter converterResponseBook;

	public LoanBookConverter(LibraryUserService serviceUser, LibraryUserResponseConverter converterResponseUser,
			LibraryBookService serviceBook, LibraryBookResponseConverter converterResponseBook) {
		this.serviceUser = serviceUser;
		this.converterResponseUser = converterResponseUser;
		this.serviceBook = serviceBook;
		this.converterResponseBook = converterResponseBook;
	}

	private LibraryUserResponseDTO getSelectedUser(Long user) {
		LibraryUser register = serviceUser.findById(user);
		LibraryUserResponseDTO registerDTO = converterResponseUser.fromEntity(register);
		return registerDTO;
	}

	private LibraryBookResponseDTO getSelectedBook(Long book) {
		LibraryBook register = serviceBook.findById(book);
		LibraryBookResponseDTO registerDTO = converterResponseBook.fromEntity(register);
		return registerDTO;
	}

	@Override
	public LoanBookRegisterDTO fromEntity(LoanBook entity) {
		if (entity == null) 
			return null;

		return LoanBookRegisterDTO.builder()
				.idLoan(entity.getIdLoan())
				.book(entity.getBook().getIdBook())
				.user(entity.getUser().getIdUser())
				.departureDate(Utils.dateToShow(entity.getDepartureDate()))
				.returnDate(Utils.dateToShow(entity.getReturnDate()))
				.observation(entity.getObservation())
				.build();
	}

	@Override
	public LoanBook fromDTO(LoanBookRegisterDTO dto) {
		if (dto == null) 
			return null;

		return LoanBook.builder()
				.idLoan(dto.getIdLoan())
				.user(new LibraryUser(dto.getUser()))
				.book(new LibraryBook(dto.getBook()))
				.departureDate(Utils.dateToSave(dto.getDepartureDate()))
				.returnDate(Utils.dateToSave(dto.getReturnDate()))
				.observation(Utils.removeWhiteSpace(dto.getObservation()))
				.build();
	}

	public LoanBookResponseDTO createResponse(LoanBook entity) {
		if (entity == null) 
			return null;

		return new LoanBookResponseDTO(
				entity.getIdLoan(), 
				getSelectedUser(entity.getUser().getIdUser()),
				getSelectedBook(entity.getBook().getIdBook()), 
				Utils.dateToShow(entity.getDepartureDate()),
				Utils.dateToShow(entity.getReturnDate()), 
				Utils.removeWhiteSpace(entity.getObservation()), 
				entity.getActive());
	}

	public List<LoanBookResponseDTO> listResponse(List<LoanBook> entities) {
		if (entities == null) 
			return null;

		return entities.stream()
				.map(entity -> createResponse(entity))
				.collect(Collectors.toList());
	}

}
