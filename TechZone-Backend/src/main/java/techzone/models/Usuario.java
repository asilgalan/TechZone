package techzone.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name="usuarios")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Direccion> direcciones;

}
