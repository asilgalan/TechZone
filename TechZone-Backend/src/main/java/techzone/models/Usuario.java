package techzone.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import techzone.models.Enums.Roles;

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
    @NotNull
    @Column(name = "fecha_nacimiento")
    private Date fechaNacimiento;

    @NotNull
    @Column(name="fecha_registro")
    private Date fechaRegistro;
    @NotNull
    @Column(name="ultima_actualizacion")
    private Date ultimaActualizacion;

    @Column(name="acepta_marketing")
    private Boolean aceptaMarketing=false;

    @NotNull()
    @Column(name="cuenta_verificada")
    private Boolean cuentaVerificada=false;
    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "usuario_roles",
            joinColumns = @JoinColumn(name = "usuario_id", referencedColumnName = "id_usuario")
    )
    @Column(name = "rol")
    private Set<Roles> roles;


    @Override
    public String toString() {
        return super.toString();

    }

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Direccion> direcciones;

    @PrePersist
    public void prePersist() {setFechaRegistro(new Date());
        setUltimaActualizacion(new Date());}

    @PreUpdate
    public void preUpdate() {setUltimaActualizacion(new Date());}
}
