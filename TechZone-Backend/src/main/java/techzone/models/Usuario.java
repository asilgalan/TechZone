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

    @Size(min = 2, max = 100)
    @Column(nullable = false)
    private String apellidos;
    @NotBlank()
    @Email()
    @Column(unique = true,nullable = false)
    private String email;
    @NotBlank()
    @Column()
    private String usuario;

    private String telefono;
    @NotBlank()
    @Size(min = 6)
    @Column(nullable = false)
    private String password;

    @Column(name = "ip_registro")
    private String ipRegistro;

    @Column(name = "fecha_nacimiento")
    private Date fechaNacimiento;


    @Column(name="fecha_registro")
    private Date fechaRegistro;

    @Column(name="ultima_actualizacion")
    private Date ultimaActualizacion;
    @Column(name="terminos_condiciones")
     private boolean terminosyCondiciones=false;
    @Column(name="acepta_marketing")
    private boolean aceptaMarketing=false;


    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "usuario_roles",
            joinColumns = @JoinColumn(name = "usuario_id", referencedColumnName = "id_usuario")
    )
    @Column(name = "rol")
    private Set<Roles> roles;



    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Direccion> direcciones;

    @PrePersist
    public void prePersist() {
        setFechaRegistro(new Date());
        setUsuario(getNombre()+" "+getApellidos().split(" ")[0]);
        setUltimaActualizacion(new Date());}

    @PreUpdate
    public void preUpdate() {setUltimaActualizacion(new Date());}
}
