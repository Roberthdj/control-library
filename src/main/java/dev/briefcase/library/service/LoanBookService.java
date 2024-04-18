package dev.briefcase.library.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import dev.briefcase.library.entity.LoanBook;

public interface LoanBookService {

	public List<LoanBook> findAll(Pageable page);

	public List<LoanBook> findByIdUser(Long user, Pageable page);

	public List<LoanBook> findByIdentificationUser(String idf, Pageable page);

	public List<LoanBook> findByIdBook(Long book, Pageable page);

	public List<LoanBook> findByIsbnBook(String isbn, Pageable page);

	public List<LoanBook> findByIdUserAndIdBook(Long user, Long book, Pageable page);

	public List<LoanBook> findByIdfAndIsbn(String idf, String isbn, Pageable page);

	public LoanBook findById(Long id);

	public LoanBook save(LoanBook loanBook);

	public LoanBook update(LoanBook loanBook);
	
	public LoanBook logicalDelete(Long id, Boolean state);

	public void delete(Long id);

}
