package noemicoppotelli.appviaggiaziendali_backend.service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import noemicoppotelli.appviaggiaziendali_backend.entities.Dipendente;
import noemicoppotelli.appviaggiaziendali_backend.repositories.DipendenteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DipendenteService {

    private final DipendenteRepository dipendenteRepository;

    public DipendenteService(DipendenteRepository dipendenteRepository) {
        this.dipendenteRepository = dipendenteRepository;
    }

    public Page<Dipendente> findAll(Pageable pageable) {
        return dipendenteRepository.findAll(pageable);
    }

    public Dipendente findById(Long id) {
        return dipendenteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Dipendente non trovato"));
    }

    public Dipendente save(Dipendente dipendente) {
        if (dipendenteRepository.existsByUsername(dipendente.getUsername())) {
            throw new RuntimeException("Username già esistente");
        }

        if (dipendenteRepository.existsByEmail(dipendente.getEmail())) {
            throw new RuntimeException("Email già esistente");
        }

        return dipendenteRepository.save(dipendente);
    }

    public Dipendente update(Long id, Dipendente updatedDipendente) {
        Dipendente dipendente = findById(id);

        dipendente.setUsername(updatedDipendente.getUsername());
        dipendente.setNome(updatedDipendente.getNome());
        dipendente.setCognome(updatedDipendente.getCognome());
        dipendente.setEmail(updatedDipendente.getEmail());
        dipendente.setProfileImageUrl(updatedDipendente.getProfileImageUrl());

        return dipendenteRepository.save(dipendente);
    }

    public void delete(Long id) {
        Dipendente dipendente = findById(id);
        dipendenteRepository.delete(dipendente);
    }

    public Dipendente updateProfileImage(Long id, String imageUrl) {
        Dipendente dipendente = findById(id);
        dipendente.setProfileImageUrl(imageUrl);
        return dipendenteRepository.save(dipendente);
    }
}