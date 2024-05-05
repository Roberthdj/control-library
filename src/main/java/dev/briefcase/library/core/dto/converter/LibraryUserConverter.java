package dev.briefcase.library.core.dto.converter;

import org.springframework.stereotype.Component;

import dev.briefcase.library.core.dto.LibraryUserDTO;
import dev.briefcase.library.core.entity.LibraryUser;
import dev.briefcase.library.utils.Utils;

@Component
public class LibraryUserConverter extends AbstractConverter<LibraryUser, LibraryUserDTO> {

	@Override
	public LibraryUserDTO fromEntity(LibraryUser entity) {
		if (entity == null) 
			return null;

		return LibraryUserDTO.builder()
				.idUser(entity.getIdUser())
				.firstName(Utils.removeWhiteSpace(entity.getFirstName()))
				.lastName(Utils.removeWhiteSpace(entity.getLastName()))
				.identification(Utils.removeWhiteSpace(entity.getIdentification()))
				.email(Utils.removeWhiteSpace(entity.getEmail()))
				.phoneNumber(Utils.removeWhiteSpace(entity.getPhoneNumber()))
				.address(Utils.removeWhiteSpace(entity.getAddress()))
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
				.firstName(Utils.removeWhiteSpace(dto.getFirstName()))
				.lastName(Utils.removeWhiteSpace(dto.getLastName()))
				.identification(Utils.removeWhiteSpace(dto.getIdentification()))
				.email(Utils.removeWhiteSpace(dto.getEmail()))
				.phoneNumber(Utils.removeWhiteSpace(dto.getPhoneNumber()))
				.address(Utils.removeWhiteSpace(dto.getAddress()))
				.sanctioned(dto.getSanctioned())
				.active(dto.getActive())
				.build();
	}

}
