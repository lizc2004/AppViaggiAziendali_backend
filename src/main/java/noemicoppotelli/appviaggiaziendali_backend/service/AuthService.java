package noemicoppotelli.appviaggiaziendali_backend.service;

import noemicoppotelli.appviaggiaziendali_backend.entities.Dipendente;
import noemicoppotelli.appviaggiaziendali_backend.payloads.LoginDTO;
import noemicoppotelli.appviaggiaziendali_backend.payloads.LoginResponseDTO;
import noemicoppotelli.appviaggiaziendali_backend.exceptions.UnauthorizedException;
import noemicoppotelli.appviaggiaziendali_backend.security.JwtTools;
import noemicoppotelli.appviaggiaziendali_backend.service.DipendenteService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final DipendenteService dipendenteService;
    private final JwtTools jwtTools;
    private final PasswordEncoder passwordEncoder;

    public AuthService(DipendenteService dipendenteService, JwtTools jwtTools, PasswordEncoder passwordEncoder) {
        this.dipendenteService = dipendenteService;
        this.jwtTools = jwtTools;
        this.passwordEncoder = passwordEncoder;
    }

    public Dipendente register(Dipendente dipendente) {
        dipendente.setPassword(passwordEncoder.encode(dipendente.getPassword()));
        return dipendenteService.save(dipendente);
    }

    public LoginResponseDTO login(LoginDTO loginDTO) {
        Dipendente dipendente = dipendenteService.findByEmail(loginDTO.getEmail())
                .orElseThrow(() -> new UnauthorizedException("Credenziali non valide"));

        if (!passwordEncoder.matches(loginDTO.getPassword(), dipendente.getPassword())) {
            throw new UnauthorizedException("Credenziali non valide");
        }

        return new LoginResponseDTO(jwtTools.generateToken(dipendente));
    }


}
