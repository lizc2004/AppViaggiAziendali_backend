package noemicoppotelli.appviaggiaziendali_backend.repositories;

import noemicoppotelli.appviaggiaziendali_backend.entities.Prenotazioni;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface PrenotazioniRepository extends JpaRepository<Prenotazioni, Long> {
    boolean existsByDipendenteIdAndGiornoViaggio(Long dipendenteId, LocalDate giornoViaggio);
    boolean existsByViaggioId(Long viaggioId);
}