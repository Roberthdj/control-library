package dev.briefcase.library.core.entity;

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
@Table(name = "loans")
public class LoanBook {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_loan")
	private Long idLoan;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user")
	private LibraryUser user;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "book")
	private LibraryBook book;

	@Column(name = "departure_date")
	private Date departureDate;

	@Column(name = "return_date")
	private Date returnDate;

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

	public void activateLoan() {
		this.setActive(true);
	}

	public void deactivateLoan() {
		this.setActive(false);
	}
}
