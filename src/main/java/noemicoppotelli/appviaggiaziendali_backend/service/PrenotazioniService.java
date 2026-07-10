package noemicoppotelli.appviaggiaziendali_backend.service;

import noemicoppotelli.appviaggiaziendali_backend.entities.Dipendente;
import noemicoppotelli.appviaggiaziendali_backend.entities.Prenotazioni;
import noemicoppotelli.appviaggiaziendali_backend.entities.Viaggio;
import noemicoppotelli.appviaggiaziendali_backend.exceptions.BadRequestExeception;
import noemicoppotelli.appviaggiaziendali_backend.exceptions.NotFoundException;
import noemicoppotelli.appviaggiaziendali_backend.repositories.DipendenteRepository;
import noemicoppotelli.appviaggiaziendali_backend.repositories.PrenotazioniRepository;
import noemicoppotelli.appviaggiaziendali_backend.repositories.ViaggioRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PrenotazioniService {

    private final PrenotazioniRepository prenotazioneRepository;
    private final DipendenteRepository dipendenteRepository;
    private final ViaggioRepository viaggioRepository;

    public PrenotazioniService(PrenotazioniRepository prenotazioneRepository,
                               DipendenteRepository dipendenteRepository,
                               ViaggioRepository viaggioRepository) {
        this.prenotazioneRepository = prenotazioneRepository;
        this.dipendenteRepository = dipendenteRepository;
        this.viaggioRepository = viaggioRepository;
    }

    public List<Prenotazioni> findAll() {
        return prenotazioneRepository.findAll();
    }

    public Prenotazioni findById(Long id) {
        return prenotazioneRepository.findById(id)
                .orElseThrow(() -> new NotFoundException ("Prenotazione non trovata"));
    }

    public Prenotazioni save(Long dipendenteId, Long viaggioId, Prenotazioni prenotazioni) {
        Dipendente dipendente = dipendenteRepository.findById(dipendenteId)
                .orElseThrow(() -> new NotFoundException("Dipendente non trovato"));

        Viaggio viaggio = viaggioRepository.findById(viaggioId)
                .orElseThrow(() -> new NotFoundException("Viaggio non trovato"));

        LocalDate giornoViaggio = viaggio.getDataViaggio();

        if (prenotazioneRepository.existsByDipendenteIdAndGiornoViaggio(dipendenteId, giornoViaggio)) {
            throw new BadRequestExeception("Il dipendente ha già una prenotazione per questo giorno");
        }

        prenotazioni.setDipendente(dipendente);
        prenotazioni.setViaggio(viaggio);
        prenotazioni.setGiornoViaggio(giornoViaggio);

        if (prenotazioni.getDataRichiesta() == null) {
            prenotazioni.setDataRichiesta(LocalDate.now());
        }

        return prenotazioneRepository.save(prenotazioni);
    }

    public void delete(Long id) {
        Prenotazioni prenotazione = findById(id);
        prenotazioneRepository.delete(prenotazione);
    }
}
