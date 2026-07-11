package noemicoppotelli.appviaggiaziendali_backend.controller;

import jakarta.validation.Valid;
import noemicoppotelli.appviaggiaziendali_backend.entities.Dipendente;
import noemicoppotelli.appviaggiaziendali_backend.service.DipendenteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/dipendenti")
public class DipendenteController {

    private final DipendenteService dipendenteService;

    public DipendenteController(DipendenteService dipendenteService) {
        this.dipendenteService = dipendenteService;
    }

    @GetMapping
    public List<Dipendente> getAll() {
        return dipendenteService.findAll();
    }

    @GetMapping("/{id}")
    public Dipendente getById(@PathVariable Long id) {
        return dipendenteService.findById(id);
    }

    @PostMapping
    public Dipendente create(@RequestBody Dipendente dipendente) {
        return dipendenteService.save(dipendente);
    }

    @PutMapping("/{id}")
    public Dipendente update(@PathVariable Long id, @RequestBody Dipendente dipendente) {
        return dipendenteService.update(id, dipendente);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        dipendenteService.delete(id);
    }

    @PatchMapping("/{id}/image")
    public Dipendente updateImage(@PathVariable Long id, @RequestBody Map<String, String> body) {
        return dipendenteService.updateProfileImage(id, body.get("profileImageUrl"));
    }

}

