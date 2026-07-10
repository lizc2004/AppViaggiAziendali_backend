package noemicoppotelli.appviaggiaziendali_backend.repositories;

import noemicoppotelli.appviaggiaziendali_backend.entities.Viaggio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ViaggioRepository extends JpaRepository<Viaggio, Long> {
}