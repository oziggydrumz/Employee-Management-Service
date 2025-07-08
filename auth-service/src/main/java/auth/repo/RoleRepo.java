package auth.repo;

import auth.model.MyRole;
import model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepo extends JpaRepository<MyRole,Long> {
    Optional<MyRole> findByTitle(String value);
}
