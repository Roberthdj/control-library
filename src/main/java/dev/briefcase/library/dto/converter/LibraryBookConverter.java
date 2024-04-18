package dev.briefcase.library.dto.converter;

import org.springframework.stereotype.Component;

import dev.briefcase.library.dto.LibraryBookDTO;
import dev.briefcase.library.entity.LibraryBook;
import dev.briefcase.library.tool.Tool;

@Component
public class LibraryBookConverter extends AbstractConverter<LibraryBook, LibraryBookDTO> {

	@Override
	public LibraryBookDTO fromEntity(LibraryBook entity) {
		if (entity == null)
			return null;

		return LibraryBookDTO.builder()
				.idBook(entity.getIdBook())
				.title(Tool.removeWhiteSpace(entity.getTitle()))
				.author(Tool.removeWhiteSpace(entity.getAuthor()))
				.editorial(Tool.removeWhiteSpace(entity.getEditorial()))
				.isbn(Tool.removeWhiteSpace(entity.getIsbn()))
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
				.title(Tool.removeWhiteSpace(dto.getTitle()))
				.author(Tool.removeWhiteSpace(dto.getAuthor()))
				.editorial(Tool.removeWhiteSpace(dto.getEditorial()))
				.isbn(Tool.removeWhiteSpace(dto.getIsbn()))
				.lent(dto.getLent())
				.active(dto.getActive())
				.build();
	}

}
