package noemicoppotelli.appviaggiaziendali_backend.repositories;

import noemicoppotelli.appviaggiaziendali_backend.entities.Viaggio;
import noemicoppotelli.appviaggiaziendali_backend.enums.StatoViaggio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface ViaggioRepository extends JpaRepository<Viaggio, Long> {
    boolean existsByDestinazione(String destinazione);
    boolean existsByDataViaggio(LocalDate dataViaggio);
    boolean existsByStato(StatoViaggio stato);
}