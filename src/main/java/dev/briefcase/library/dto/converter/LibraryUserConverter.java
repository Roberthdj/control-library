package dev.briefcase.library.dto.converter;

import org.springframework.stereotype.Component;

import dev.briefcase.library.dto.LibraryUserDTO;
import dev.briefcase.library.entity.LibraryUser;
import dev.briefcase.library.tool.Tool;

@Component
public class LibraryUserConverter extends AbstractConverter<LibraryUser, LibraryUserDTO> {

	@Override
	public LibraryUserDTO fromEntity(LibraryUser entity) {
		if (entity == null) 
			return null;

		return LibraryUserDTO.builder()
				.idUser(entity.getIdUser())
				.firstName(Tool.removeWhiteSpace(entity.getFirstName()))
				.lastName(Tool.removeWhiteSpace(entity.getLastName()))
				.identification(Tool.removeWhiteSpace(entity.getIdentification()))
				.email(Tool.removeWhiteSpace(entity.getEmail()))
				.phoneNumber(Tool.removeWhiteSpace(entity.getPhoneNumber()))
				.address(Tool.removeWhiteSpace(entity.getAddress()))
				.sanctioned(entity.getSanctioned())
				.active(entity.getActive())
				.build();
	}

	@Override
	public LibraryUser fromDTO(LibraryUserDTO dto) {
		if (dto == null) 
			return null;		

		return LibraryUser.builder()
				.idUser(dto.getIdUser())
				.firstName(Tool.removeWhiteSpace(dto.getFirstName()))
				.lastName(Tool.removeWhiteSpace(dto.getLastName()))
				.identification(Tool.removeWhiteSpace(dto.getIdentification()))
				.email(Tool.removeWhiteSpace(dto.getEmail()))
				.phoneNumber(Tool.removeWhiteSpace(dto.getPhoneNumber()))
				.address(Tool.removeWhiteSpace(dto.getAddress()))
				.sanctioned(dto.getSanctioned())
				.active(dto.getActive())
				.build();
	}

}
