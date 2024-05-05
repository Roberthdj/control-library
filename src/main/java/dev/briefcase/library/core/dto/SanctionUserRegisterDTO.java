package dev.briefcase.library.core.dto;

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
public class SanctionUserRegisterDTO {

	private Long idSanction;
	private Long user;
	private String startDate;
	private String completionDate;
	private String observation;

}
