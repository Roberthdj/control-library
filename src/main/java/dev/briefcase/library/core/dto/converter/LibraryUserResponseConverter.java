package dev.briefcase.library.core.dto.converter;

import org.springframework.stereotype.Component;

import dev.briefcase.library.core.dto.LibraryUserResponseDTO;
import dev.briefcase.library.core.entity.LibraryUser;
import dev.briefcase.library.utils.Utils;

@Component
public class LibraryUserResponseConverter extends AbstractConverter<LibraryUser, LibraryUserResponseDTO> {

	@Override
	public LibraryUserResponseDTO fromEntity(LibraryUser entity) {
		if (entity == null) 
			return null;

		return LibraryUserResponseDTO.builder()
				.idUser(entity.getIdUser())
				.firstName(Utils.removeWhiteSpace(entity.getFirstName()))
				.lastName(Utils.removeWhiteSpace(entity.getLastName()))
				.email(Utils.removeWhiteSpace(entity.getEmail()))
				.build();
	}

	@Override
	public LibraryUser fromDTO(LibraryUserResponseDTO dto) {
		if (dto == null) 
			return null;		

		return LibraryUser.builder()
				.idUser(dto.getIdUser())
				.firstName(Utils.removeWhiteSpace(dto.getFirstName()))
				.lastName(Utils.removeWhiteSpace(dto.getLastName()))
				.email(Utils.removeWhiteSpace(dto.getEmail()))
				.build();
	}

}
