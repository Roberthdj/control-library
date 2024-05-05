package dev.briefcase.library.core.dto.converter;

import org.springframework.stereotype.Component;

import dev.briefcase.library.core.dto.LibraryBookDTO;
import dev.briefcase.library.core.entity.LibraryBook;
import dev.briefcase.library.utils.Utils;

@Component
public class LibraryBookConverter extends AbstractConverter<LibraryBook, LibraryBookDTO> {

	@Override
	public LibraryBookDTO fromEntity(LibraryBook entity) {
		if (entity == null)
			return null;

		return LibraryBookDTO.builder()
				.idBook(entity.getIdBook())
				.title(Utils.removeWhiteSpace(entity.getTitle()))
				.author(Utils.removeWhiteSpace(entity.getAuthor()))
				.editorial(Utils.removeWhiteSpace(entity.getEditorial()))
				.isbn(Utils.removeWhiteSpace(entity.getIsbn()))
				.lent(entity.getLent())
				.active(entity.getActive())
				.build();
	}

	@Override
	public LibraryBook fromDTO(LibraryBookDTO dto) {
		if (dto == null)
			return null;

		return LibraryBook.builder()
				.idBook(dto.getIdBook())
				.title(Utils.removeWhiteSpace(dto.getTitle()))
				.author(Utils.removeWhiteSpace(dto.getAuthor()))
				.editorial(Utils.removeWhiteSpace(dto.getEditorial()))
				.isbn(Utils.removeWhiteSpace(dto.getIsbn()))
				.lent(dto.getLent())
				.active(dto.getActive())
				.build();
	}

}
