package techzone.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import techzone.models.Enums.Aplicacion;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name="descuentos")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Descuento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_descuento")
    private Long idDescuento;

    @Column(nullable = false, unique = true)
    private String codigo;

    @Column(nullable = false)
    private String nombre;
    private Double valor;

    @Column(nullable = false, name = "fecha_inicio")
    private Date fechaInicio;

    @Column(name = "fecha_fin") // Corregí el nombre que estaba repetido como fecha_inicio
    private Date fechaFin;

    private Double total;

    @Column(name = "uso_maximo")
    private Integer usoMaximo;

    @Column(name = "minimo_compra")
    private Double minimoCompra;

    @Enumerated(EnumType.STRING)
    private Aplicacion aplicacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_categoria")
    private Categoria categoria;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_producto")
    private Producto producto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    private Boolean activo;

    private LocalDateTime fechaCreacion;

    @PrePersist
    public void prePersist() {
        setFechaCreacion(LocalDateTime.now());
    }
}



