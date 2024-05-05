package dev.briefcase.library.core.dto.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import dev.briefcase.library.core.dto.LibraryUserResponseDTO;
import dev.briefcase.library.core.dto.SanctionUserRegisterDTO;
import dev.briefcase.library.core.dto.SanctionUserResponseDTO;
import dev.briefcase.library.core.entity.LibraryUser;
import dev.briefcase.library.core.entity.SanctionUser;
import dev.briefcase.library.core.service.LibraryUserService;
import dev.briefcase.library.utils.Utils;

@Component
public class SanctionUserConverter extends AbstractConverter<SanctionUser, SanctionUserRegisterDTO> {

	private LibraryUserService serviceUser;
	private LibraryUserResponseConverter converterResponseUser;

	public SanctionUserConverter(LibraryUserService serviceUser, LibraryUserResponseConverter converterResponseUser) {
		this.serviceUser = serviceUser;
		this.converterResponseUser = converterResponseUser;
	}
	
	private LibraryUserResponseDTO getUser(Long user) {
		LibraryUser register = serviceUser.findById(user);
		LibraryUserResponseDTO registerDTO = converterResponseUser.fromEntity(register);
		return registerDTO;
	}

	@Override
	public SanctionUserRegisterDTO fromEntity(SanctionUser entity) {
		if (entity == null) 
			return null;
		
		return SanctionUserRegisterDTO.builder()
				.idSanction(entity.getIdSanction())
				.user(entity.getUser().getIdUser())
				.startDate(Utils.dateToShow(entity.getStartDate()))
				.completionDate(Utils.dateToShow(entity.getCompletionDate()))
				.observation(Utils.removeWhiteSpace(entity.getObservation()))
				.build();
	}

	@Override
	public SanctionUser fromDTO(SanctionUserRegisterDTO dto) {		
		if (dto == null) 
			return null;
		
		return SanctionUser.builder()
				.idSanction(dto.getIdSanction())
				.user(new LibraryUser (dto.getUser()))
				.startDate(Utils.dateToSave(dto.getStartDate()))
				.completionDate(Utils.dateToSave(dto.getCompletionDate()))
				.observation(Utils.removeWhiteSpace(dto.getObservation()))
				.build();
	}
	
	public SanctionUserResponseDTO createResponse(SanctionUser entity) {
		if (entity == null) 
			return null;

		return new SanctionUserResponseDTO(
				entity.getIdSanction(), 
				getUser(entity.getUser().getIdUser()),
				Utils.dateToShow(entity.getStartDate()),
				Utils.dateToShow(entity.getCompletionDate()), 
				Utils.removeWhiteSpace(entity.getObservation()),
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
