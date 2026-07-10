package noemicoppotelli.appviaggiaziendali_backend.repositories;

import noemicoppotelli.appviaggiaziendali_backend.entities.Prenotazione;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface PrenotazioneRepository extends JpaRepository<Prenotazione, Long> {
    boolean existsByDipendenteIdAndGiornoViaggio(Long dipendenteId, LocalDate giornoViaggio);
}
