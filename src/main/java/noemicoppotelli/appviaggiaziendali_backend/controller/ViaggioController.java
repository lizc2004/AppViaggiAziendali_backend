package noemicoppotelli.appviaggiaziendali_backend.controller;

import jakarta.validation.Valid;
import noemicoppotelli.appviaggiaziendali_backend.entities.Viaggio;
import noemicoppotelli.appviaggiaziendali_backend.enums.StatoViaggio;
import noemicoppotelli.appviaggiaziendali_backend.service.ViaggioService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/viaggi")
public class ViaggioController {

    private final ViaggioService viaggioService;

    public ViaggioController(ViaggioService viaggioService) {
        this.viaggioService = viaggioService;
    }

    @GetMapping
    public List<Viaggio> getAll() {
        return viaggioService.findAll();
    }

    @GetMapping("/{id}")
    public Viaggio getById(@PathVariable Long id) {
        return viaggioService.findById(id);
    }

    @PostMapping
    public Viaggio create(@RequestBody Viaggio viaggio) {
        return viaggioService.save(viaggio);
    }

    @PutMapping("/{id}")
    public Viaggio update(@PathVariable Long id, @RequestBody Viaggio viaggio) {
        return viaggioService.update(id, viaggio);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        viaggioService.delete(id);
    }

    @PatchMapping("/{id}/stato")
    public Viaggio updateStato(@PathVariable Long id, @RequestBody Map<String, String> body) {
        StatoViaggio stato = StatoViaggio.valueOf(body.get("stato"));
        return viaggioService.updateStato(id, stato);
    }



}
