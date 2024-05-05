package dev.briefcase.library.core.dto.converter;

import org.springframework.stereotype.Component;

import dev.briefcase.library.core.dto.LibraryBookResponseDTO;
import dev.briefcase.library.core.entity.LibraryBook;
import dev.briefcase.library.utils.Utils;

@Component
public class LibraryBookResponseConverter extends AbstractConverter<LibraryBook, LibraryBookResponseDTO> {

	@Override
	public LibraryBookResponseDTO fromEntity(LibraryBook entity) {
		if (entity == null)
			return null;

		return LibraryBookResponseDTO.builder()
				.idBook(entity.getIdBook())
				.title(Utils.removeWhiteSpace(entity.getTitle()))
				.author(Utils.removeWhiteSpace(entity.getAuthor()))
				.editorial(Utils.removeWhiteSpace(entity.getEditorial()))
				.build();
	}

	@Override
	public LibraryBook fromDTO(LibraryBookResponseDTO dto) {
		if (dto == null)
			return null;

		return LibraryBook.builder()
				.idBook(dto.getIdBook())
				.title(Utils.removeWhiteSpace(dto.getTitle()))
				.author(Utils.removeWhiteSpace(dto.getAuthor()))
				.editorial(Utils.removeWhiteSpace(dto.getEditorial()))
				.build();
	}

}
