package techzone.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import techzone.models.Enums.Motivo;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="historico_precios")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class HistoricoPrecio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name = "id_historico_precio")
    private Long idHistoricoPrecio;

    @OneToMany
    @JoinColumn(name="id_producto")

    private List<Producto> producto;

    @Column(name="fecha_cambio")
    private Date fechaCambio;

    @PrePersist
    public void prePersist() {
        setFechaCambio(new Date());
    }

    @Column(name = "precio_anterior")
    private Double precioAnterior;

    @NotNull
    @Column(name = "precio_nuevo", nullable = false)
    private Double precioNuevo;

    @Column(name = "motivo", nullable = false)
    private Motivo motivo;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    private String notas;





}
