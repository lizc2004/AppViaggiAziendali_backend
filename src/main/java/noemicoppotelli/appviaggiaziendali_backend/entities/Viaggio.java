package noemicoppotelli.appviaggiaziendali_backend.entities;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombork.AllConstructor;
import lombork.Data;
import NoArgsCostructor;
import noemi.coppotelli.appViaggiAziendali_backend.emunus
import noemicoppotelli.appviaggiaziendali_backend.enums.StatoViaggio;

import java.time.LocalDate;

@Entity
@Table(name = "viaggi")
@NoArgsConstructor
@AllArgsConstructor

public class Viaggio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String destinazione;

    @Column(name = "data_viaggio", nullable = false)
    private LocalDate dataViaggio;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatoViaggio stato;

}
