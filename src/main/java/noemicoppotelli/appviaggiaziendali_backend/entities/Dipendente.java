package noemicoppotelli.appviaggiaziendali_backend.entities;
import lombok.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Email;

@Entity
@Table(name = "dipendenti")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Dipendente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Lo username è obbligatorio")
    @Column(nullable = false, unique = true)
    private String username;

    @NotBlank(message = "Il nome è obbligatorio")
    @Column(nullable = false)
    private String nome;

    @NotBlank(message = "Il cognome è obbligatorio")
    @Column(nullable = false)
    private String cognome;

    @NotBlank(message = "L'email è obbligatoria")
    @Email(message = "Email non valida")
    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "profile_image_url")
    private String profileImageUrl;
}
