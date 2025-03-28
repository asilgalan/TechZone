package techzone.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="Direcciones")
public class Direccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank()
    @Column(nullable = false)
    private String calle;

    @NotBlank()
    @Size(min = 2, max = 6)
    @Column(nullable = false)
    private String cp;

    @NotBlank()
    @Column(nullable = false)
    private String ciudad;

    @NotBlank()
    @Column(nullable = false)
    private String pais;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
}
