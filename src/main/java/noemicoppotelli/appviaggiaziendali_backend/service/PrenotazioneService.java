package noemicoppotelli.appviaggiaziendali_backend.service;

import noemicoppotelli.appviaggiaziendali_backend.entities.Dipendente;
import noemicoppotelli.appviaggiaziendali_backend.entities.Prenotazione;
import noemicoppotelli.appviaggiaziendali_backend.entities.Viaggio;
import noemicoppotelli.appviaggiaziendali_backend.repositories.DipendenteRepository;
import noemicoppotelli.appviaggiaziendali_backend.repositories.PrenotazioneRepository;
import noemicoppotelli.appviaggiaziendali_backend.repositories.ViaggioRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PrenotazioneService {

    private final PrenotazioneRepository prenotazioneRepository;
    private final DipendenteRepository dipendenteRepository;
    private final ViaggioRepository viaggioRepository;

    public PrenotazioneService(PrenotazioneRepository prenotazioneRepository,
                               DipendenteRepository dipendenteRepository,
                               ViaggioRepository viaggioRepository) {
        this.prenotazioneRepository = prenotazioneRepository;
        this.dipendenteRepository = dipendenteRepository;
        this.viaggioRepository = viaggioRepository;
    }

    public List<Prenotazione> findAll() {
        return prenotazioneRepository.findAll();
    }

    public Prenotazione findById(Long id) {
        return prenotazioneRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Prenotazione non trovata"));
    }

    public Prenotazione save(Long dipendenteId, Long viaggioId, Prenotazione prenotazione) {
        Dipendente dipendente = dipendenteRepository.findById(dipendenteId)
                .orElseThrow(() -> new RuntimeException("Dipendente non trovato"));

        Viaggio viaggio = viaggioRepository.findById(viaggioId)
                .orElseThrow(() -> new RuntimeException("Viaggio non trovato"));

        LocalDate giornoViaggio = viaggio.getDataViaggio();

        if (prenotazioneRepository.existsByDipendenteIdAndGiornoViaggio(dipendenteId, giornoViaggio)) {
            throw new RuntimeException("Il dipendente ha già una prenotazione per questo giorno");
        }

        prenotazione.setDipendente(dipendente);
        prenotazione.setViaggio(viaggio);
        prenotazione.setGiornoViaggio(giornoViaggio);

        if (prenotazione.getDataRichiesta() == null) {
            prenotazione.setDataRichiesta(LocalDate.now());
        }

        return prenotazioneRepository.save(prenotazione);
    }

    public void delete(Long id) {
        Prenotazione prenotazione = findById(id);
        prenotazioneRepository.delete(prenotazione);
    }
}
