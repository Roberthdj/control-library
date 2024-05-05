package dev.briefcase.library.security.auth;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.briefcase.library.security.auth.entity.AuthUser;

@Repository
public interface AuthUserRepository extends JpaRepository<AuthUser, Long>{
	Optional<AuthUser> findByUsername(String username);
}
