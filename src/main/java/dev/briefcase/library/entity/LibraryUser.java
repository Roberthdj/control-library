package dev.briefcase.library.entity;

import java.util.Date;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "users")
public class LibraryUser {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_user")
	private Long idUser;

	@Column(name = "first_name", nullable = false, length = 50)
	private String firstName;

	@Column(name = "last_name", nullable = false, length = 50)
	private String lastName;

	@Column(unique = true, nullable = false, length = 30)
	private String identification;

	@Column(nullable = false, length = 100)
	private String email;

	@Column(nullable = false, length = 30)
	private String phoneNumber;

	@Column(length = 50)
	private String address;

	@Column(nullable = false)
	private Boolean sanctioned;

	@Column(nullable = false)
	private Boolean active;	

	public LibraryUser(Long idUser) {
		this.idUser = idUser;
	}

	/* fields to audit entity */

	@CreatedDate
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "creation_date", nullable = false, updatable = false)
	private Date creationDate;

	@LastModifiedDate
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "update_date")
	private Date updateDate;

	/* Logical delete methods */

	public void logicalActivate() {
		this.setActive(true);
	}

	public void logicalDelete() {
		this.setActive(false);
	}
	
	/* Sanction methods */

	public void activateSanction() {
		this.setSanctioned(true);
	}

	public void deactivateSanction() {
		this.setSanctioned(false);
	}

}
