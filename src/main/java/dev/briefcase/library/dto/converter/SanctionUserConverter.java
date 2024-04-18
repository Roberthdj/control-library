package dev.briefcase.library.dto.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import dev.briefcase.library.dto.LibraryUserDTO;
import dev.briefcase.library.dto.SanctionUserRegisterDTO;
import dev.briefcase.library.dto.SanctionUserResponseDTO;
import dev.briefcase.library.entity.LibraryUser;
import dev.briefcase.library.entity.SanctionUser;
import dev.briefcase.library.service.LibraryUserService;
import dev.briefcase.library.tool.Tool;

@Component
public class SanctionUserConverter extends AbstractConverter<SanctionUser, SanctionUserRegisterDTO> {

	private LibraryUserService serviceUser;
	private LibraryUserConverter converterUser;

	public SanctionUserConverter(LibraryUserService serviceUser, LibraryUserConverter converterUser) {
		this.serviceUser = serviceUser;
		this.converterUser = converterUser;
	}
	
	private LibraryUserDTO getUser(Long user) {
		LibraryUser register = serviceUser.findById(user);
		LibraryUserDTO registerDTO = converterUser.fromEntity(register);
		return registerDTO;
	}

	@Override
	public SanctionUserRegisterDTO fromEntity(SanctionUser entity) {
		if (entity == null) 
			return null;
		
		return SanctionUserRegisterDTO.builder()
				.idSanction(entity.getIdSanction())
				.user(entity.getUser().getIdUser())
				.startDate(Tool.dateToShow(entity.getStartDate()))
				.completionDate(Tool.dateToShow(entity.getCompletionDate()))
				.observation(Tool.removeWhiteSpace(entity.getObservation()))
				.build();
	}

	@Override
	public SanctionUser fromDTO(SanctionUserRegisterDTO dto) {		
		if (dto == null) 
			return null;
		
		return SanctionUser.builder()
				.idSanction(dto.getIdSanction())
				.user(new LibraryUser (dto.getUser()))
				.startDate(Tool.dateToSave(dto.getStartDate()))
				.completionDate(Tool.dateToSave(dto.getCompletionDate()))
				.observation(Tool.removeWhiteSpace(dto.getObservation()))
				.build();
	}
	
	public SanctionUserResponseDTO createResponse(SanctionUser entity) {
		if (entity == null) 
			return null;

		return new SanctionUserResponseDTO(
				entity.getIdSanction(), 
				getUser(entity.getUser().getIdUser()),
				Tool.dateToShow(entity.getStartDate()),
				Tool.dateToShow(entity.getCompletionDate()), 
				Tool.removeWhiteSpace(entity.getObservation()),
				entity.getActive());
				
	}

	public List<SanctionUserResponseDTO> listResponse(List<SanctionUser> entities) {
		if (entities == null) 
			return null;

		return entities.stream()
				.map(entity -> createResponse(entity))
				.collect(Collectors.toList());
	}

}
