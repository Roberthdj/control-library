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
@Table(name = "books")
public class LibraryBook {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_book")
	private Long idBook;

	@Column(nullable = false, length = 50)
	private String title;

	@Column(nullable = false, length = 50)
	private String author;

	@Column(nullable = false, length = 50)
	private String editorial;

	@Column(unique = true, nullable = false, length = 25)
	private String isbn;

	@Column(nullable = false)
	private Boolean lent;

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

	public LibraryBook(Long idBook) {
		this.idBook = idBook;
	}

	/* Logical delete methods */

	public void logicalActivate() {
		this.setActive(true);
	}

	public void logicalDelete() {
		this.setActive(false);
	}

	/* Loan methods */

	public void activateLoan() {
		this.setLent(true);
	}

	public void deactivateLoan() {
		this.setLent(false);
	}
}
