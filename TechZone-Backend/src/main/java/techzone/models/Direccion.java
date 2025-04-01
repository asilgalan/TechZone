package techzone.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name="Direcciones")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Direccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false,name="id_direccion")
    private Long idDireccion;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @Column(nullable = false)
    @NotBlank()
    private String alias;

    private String destinatario;
    @NotBlank()
    @Column(nullable = false)
    private String calle;

    @NotBlank()
    @Size(min = 2, max = 6)
    @Column(nullable = false,name="codigo_postal")
    private String codigoPostal;

    @NotBlank()
    @Column(nullable = false)
    private String ciudad;
    @NotBlank()
    @Column(nullable = false)
    private String provincia;
    @NotBlank()
    @Column(nullable = false)
    private String pais;
    @NotBlank()
    @Column(nullable = true)
    private String telefono;
    @NotBlank()
    @Column(nullable = false,name="fecha_creacion")
    private LocalDateTime fechaCreacion;

    @NotBlank()
    @Column(nullable = false,name="ultima_actualizacion")
    private LocalDateTime ultimaActualizacion;

    @PrePersist
    public void prePersist() {
        setFechaCreacion(LocalDateTime.now());
    }
    @PreUpdate
    public void preUpdate() {
        setUltimaActualizacion(LocalDateTime.now());
    }

}
