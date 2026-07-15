package noemicoppotelli.appviaggiaziendali_backend.controller;
import jakarta.validation.ValidationException;
import jakarta.validation.Valid;
import noemicoppotelli.appviaggiaziendali_backend.entities.Dipendente;
import noemicoppotelli.appviaggiaziendali_backend.payloads.LoginDTO;
import noemicoppotelli.appviaggiaziendali_backend.payloads.LoginResponseDTO;
import noemicoppotelli.appviaggiaziendali_backend.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import java.util.List;



@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody LoginDTO loginDTO) {
        return authService.login(loginDTO);
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED) // 201
    public Dipendente register(@Valid @RequestBody Dipendente dipendente, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            throw new ValidationException("Validation failed");
        }
        return authService.register(dipendente);
    }


}
