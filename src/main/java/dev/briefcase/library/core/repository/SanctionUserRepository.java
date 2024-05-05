package dev.briefcase.library.core.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import dev.briefcase.library.core.entity.SanctionUser;

@Repository
public interface SanctionUserRepository extends JpaRepository<SanctionUser, Long> {

	@Query("SELECT S FROM SanctionUser S WHERE S.user.idUser=:user")
	List<SanctionUser> findByIdUser(Long user, Pageable page);

	@Query("SELECT S FROM SanctionUser S WHERE S.user.identification=:idf")
	List<SanctionUser> findByIdentificationUser(String idf, Pageable page);

}
