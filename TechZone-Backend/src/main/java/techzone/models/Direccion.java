package techzone.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name="direcciones")
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
    @NotNull
    @Column(nullable = false,name="fecha_creacion")
    private Date fechaCreacion;

    @NotNull
    @Column(name="ultima_actualizacion")
    private Date ultimaActualizacion;

    @PrePersist
    public void prePersist() {
        setFechaCreacion(new Date());
        setUltimaActualizacion(new Date());
    }
    @PreUpdate
    public void preUpdate() {
        setUltimaActualizacion(new Date());
    }

}
