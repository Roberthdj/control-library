package dev.briefcase.library.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import dev.briefcase.library.entity.LoanBook;
import java.util.List;


@Repository
public interface LoanBookRepository extends JpaRepository<LoanBook, Long> {
	
	@Query("SELECT L FROM LoanBook L WHERE L.user.idUser=:user")
	List<LoanBook> findByIdUser(Long user, Pageable page);
	
	@Query("SELECT L FROM LoanBook L WHERE L.user.identification=:idf")
	List<LoanBook> findByIdentificationUser(String idf, Pageable page);

	@Query("SELECT L FROM LoanBook L WHERE L.book.idBook=:book")
	List<LoanBook> findByIdBook(Long book, Pageable page);
	
	@Query("SELECT L FROM LoanBook L WHERE L.book.isbn=:isbn")
	List<LoanBook> findByIsbnBook(String isbn, Pageable page);
	
	@Query("SELECT L FROM LoanBook L  WHERE L.user.idUser=:user AND L.book.idBook=:book")
	List<LoanBook> findByIdUserAndIdBook(Long user, Long book, Pageable page);
	
	@Query("SELECT L FROM LoanBook L  WHERE L.user.identification=:idf AND L.book.isbn=:isbn")
	List<LoanBook> findByIdfAndIsbn(String idf, String isbn, Pageable page);

}
