package dev.briefcase.library.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class SanctionUserResponseDTO {

	private Long idSanction;
	private LibraryUserDTO user;
	private String startDate;
	private String completionDate;
	private String observation;
	private Boolean active;

}
