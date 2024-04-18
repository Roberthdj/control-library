package dev.briefcase.library.entity;

import java.util.Date;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
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
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "sanctions")
public class SanctionUser {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_sanction")
	private Long idSanction;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user")
	private LibraryUser user;
	
	@Column(name = "start_date")
	private Date startDate;
	
	@Column(name = "completion_date")
	private Date completionDate;
	
	@Column(length = 100)
	private String observation;
	
	@Column(nullable = false)
	private Boolean active;
	
	/* fields to audit entity */

	@CreatedDate
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "creation_date", nullable = false, updatable = false)
	private Date creationDate;

	@LastModifiedDate
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "update_date")
	private Date updateDate;
	
	public void ActivateSanction() {
		this.setActive(true);
	}

	public void deactivateSanction() {
		this.setActive(false);
	}

}
