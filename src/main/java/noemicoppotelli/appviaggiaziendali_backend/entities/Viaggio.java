package noemicoppotelli.appviaggiaziendali_backend.entities;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import noemicoppotelli.appviaggiaziendali_backend.enums.StatoViaggio;

import java.time.LocalDate;

@Entity
@Table(name = "viaggi")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Viaggio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "La destinazione è obbligatoria")
    @Column(nullable = false)
    private String destinazione;

    @NotNull(message = "La data del viaggio è obbligatoria")
    @Column(name = "data_viaggio", nullable = false)
    private LocalDate dataViaggio;

    @NotNull(message = "Lo stato è obbligatorio")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatoViaggio stato;
}