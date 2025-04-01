package techzone.models;

import jakarta.persistence.*;


import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDate;


@Entity
@Table(name="marcas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Marca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false,name="id_marca")
    private Long idMarca;
    @NotBlank
    @Column(nullable = true)
    private String nombre;
    @NotBlank
    @Column(nullable = true,unique = true)
    private String slug;
    @NotBlank
    @Column(nullable = true)
    private String descripcion;

    @NotBlank
    @Column(nullable = true,name="logo_img")

    private String logoImg;
    @NotBlank
    @Column(nullable = true,name="enlace_web")
    private String enlaceWeb;

}
