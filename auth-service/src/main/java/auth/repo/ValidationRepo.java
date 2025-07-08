package auth.repo;


import auth.model.OtpValidation;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface ValidationRepo extends JpaRepository<OtpValidation,Long> {
    Optional<OtpValidation> findByToken(String token);
}
