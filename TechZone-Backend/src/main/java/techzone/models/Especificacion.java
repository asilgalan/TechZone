package techzone.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Table(name = "especificaciones")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Especificacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long idEspecificacion;
    @NotBlank
    @Column(nullable = false)
    private String nombre;
    @NotBlank
    @Column(nullable = false)
    private String valor;
    @NotNull
    @Column(nullable = false)
    private Long orden;
    private Boolean importante;
    private Boolean filtrable;
}
