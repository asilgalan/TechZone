package techzone.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "productos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_producto")
    private Long idProducto;
    @NotBlank
    @Column(nullable = false)
    private String nombre;
    @NotBlank
    @Column(nullable = false,unique = true)
    private String slug;
    @NotBlank
    @Column(name="descripcion_corta")
    private String descripcionCorta;
    @NotBlank
    @Column(name="descripcion_larga")
    private String descripcionLarga;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false,name = "id_marca")
    private Marca marca;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false,name="id_categoria")
    private Categoria categoria;

    @NotBlank
    @Column(nullable = false,unique = true)
    private String sku;

    @NotBlank
    private String modelo;

    @NotNull
    private Double precio;
    @NotNull
    private Double coste;
    private  Double iva;
    private Double peso;

    @Column(name="fecha_lanzamiento")
    private Date fechaLanzamiento;
    @NotBlank()
    @Column(nullable = false,name="fecha_creacion")
    private LocalDateTime fechaCreacion;

    @NotBlank()
    @Column(nullable = false,name="ultima_actualizacion")
    private LocalDateTime ultimaActualizacion;

    private Integer garantia;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Valoracion> valoracion;

    private Boolean nuevo;
    private Boolean oferta;

    @PrePersist
    public void prePersist() {
        setFechaCreacion(LocalDateTime.now());
        setUltimaActualizacion(LocalDateTime.now());

    }
    @PreUpdate
    public void preUpdate() {
        setUltimaActualizacion(LocalDateTime.now());
    }

}
