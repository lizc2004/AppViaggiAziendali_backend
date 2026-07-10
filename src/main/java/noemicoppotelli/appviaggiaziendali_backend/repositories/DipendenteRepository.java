package noemicoppotelli.appviaggiaziendali_backend.repositories;

import noemicoppotelli.appviaggiaziendali_backend.entities.Dipendente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DipendenteRepository extends JpaRepository<Dipendente, Long> {
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
