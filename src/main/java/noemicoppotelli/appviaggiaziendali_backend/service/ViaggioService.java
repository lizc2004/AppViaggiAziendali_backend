package noemicoppotelli.appviaggiaziendali_backend.service;

import noemicoppotelli.appviaggiaziendali_backend.entities.Viaggio;
import noemicoppotelli.appviaggiaziendali_backend.enums.StatoViaggio;
import noemicoppotelli.appviaggiaziendali_backend.repositories.ViaggioRepository;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import noemicoppotelli.appviaggiaziendali_backend.exceptions.NotFoundException;


import java.util.List;

@Service
public class ViaggioService {

    private final ViaggioRepository viaggioRepository;

    public ViaggioService(ViaggioRepository viaggioRepository) {
        this.viaggioRepository = viaggioRepository;
    }

    public Page<Viaggio> findAll(Pageable pageable) {
        return viaggioRepository.findAll(pageable);
    }

    public Viaggio findById(Long id) {
        return viaggioRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Viaggio non trovato"));
    }

    public Viaggio save(Viaggio viaggio) {
        return viaggioRepository.save(viaggio);
    }

    public Viaggio update(Long id, Viaggio updatedViaggio) {
        Viaggio viaggio = findById(id);

        viaggio.setDestinazione(updatedViaggio.getDestinazione());
        viaggio.setDataViaggio(updatedViaggio.getDataViaggio());
        viaggio.setStato(updatedViaggio.getStato());

        return viaggioRepository.save(viaggio);
    }

    public void delete(Long id) {
        Viaggio viaggio = findById(id);
        viaggioRepository.delete(viaggio);
    }

    public Viaggio updateStato(Long id, StatoViaggio stato) {
        Viaggio viaggio = findById(id);
        viaggio.setStato(stato);
        return viaggioRepository.save(viaggio);
    }
}
