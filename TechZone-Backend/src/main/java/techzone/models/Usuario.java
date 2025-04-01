package techzone.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(name="usuarios")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_usuario")
    private Long idUsuario;

    @NotBlank()
    @Size(min = 2, max = 50)
    @Column(nullable = false)
    private String nombre;
    @NotBlank()

    @Size(min = 2, max = 100)
    @Column(nullable = false)
    private String apellidos;
    @NotBlank()
    @Email()
    @Column(unique = true,nullable = false)
    private String email;
    @NotBlank()
    @Column(unique = true,nullable = false)
    private String usuario;
    @NotBlank()
    @Column(nullable = false)
    private String telefono;
    @NotBlank()
    @Size(min = 6)
    @Column(nullable = false)
    private String password;
    @NotBlank()
    @Column(nullable = false,name = "ip_registro")
    private String ipRegistro;
    @NotBlank()
    @Column(nullable = false ,name = "fecha_nacimiento")
    private Date fechaNacimiento;

    @NotBlank()
    @Column(nullable = false,name="fecha_registro")
    private LocalDateTime fechaRegistro;
    @NotBlank()
    @Column(nullable = false,name="ultima_actualizacion")
    private LocalDateTime ultimaActualizacion;

    @Column(nullable = false,name="acepta_marketing")
    private String aceptaMarketing;

    @NotBlank()
    @Column(name="cuenta_verificada")
    private String cuentaVerificada;
    @ElementCollection
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "usuario_roles", joinColumns = @JoinColumn(name = "usuario_id"))
    @Column(name = "rol")
    private Set<Roles> roles;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Direccion> direcciones;

    @PrePersist
    public void prePersist() {

        setFechaRegistro(LocalDateTime.now());
    }

    @PreUpdate
    public void preUpdate() {
        setUltimaActualizacion(LocalDateTime.now());
    }
}
