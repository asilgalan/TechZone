package techzone.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="inventarios")
public class Inventario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name="id_inventario")
    private Long idInventario;

    @ManyToOne(fetch = FetchType.LAZY)

    @JoinColumn(nullable = false,name="id_producto")
    private Producto producto;

    @Column(nullable = false)
    private int stock;
    private LocalDateTime fecha_actualizacion;


    @PreUpdate
    public void preUpdate() {
        setFecha_actualizacion( LocalDateTime.now());
    }


}
