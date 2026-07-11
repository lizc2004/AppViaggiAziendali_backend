package noemicoppotelli.appviaggiaziendali_backend.controller;

import noemicoppotelli.appviaggiaziendali_backend.entities.Prenotazioni;
import noemicoppotelli.appviaggiaziendali_backend.service.PrenotazioniService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/prenotazioni")
public class PrenotazioniController {

    private final PrenotazioniService prenotazioniService;

    public PrenotazioniController(PrenotazioniService prenotazioniService) {
        this.prenotazioniService = prenotazioniService;
    }

    @GetMapping
    public List<Prenotazioni> getAll() {
        return prenotazioniService.findAll();
    }

    @GetMapping("/{id}")
    public Prenotazioni getById(@PathVariable Long id) {
        return prenotazioniService.findById(id);
    }

    @PostMapping
    public Prenotazioni create(@RequestBody Map<String, Object> body) {
        Long dipendenteId = Long.valueOf(body.get("dipendenteId").toString());
        Long viaggioId = Long.valueOf(body.get("viaggioId").toString());

        Prenotazioni prenotazione = new Prenotazioni();
        prenotazione.setNote((String) body.get("note"));
        prenotazione.setPreferenze((String) body.get("preferenze"));

        return prenotazioniService.save(dipendenteId, viaggioId, prenotazione);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        prenotazioniService.delete(id);
    }
}

