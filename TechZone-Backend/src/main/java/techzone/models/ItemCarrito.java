package techzone.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "items_carrito")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemCarrito {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_carrito", nullable = false)
    private Carrito carrito;

    @ManyToOne
    @JoinColumn(name = "id_producto", nullable = false)
    private Producto producto;

    private Integer cantidad;

    @Transient
    public Double getSubtotal() {
        return producto.getPrecio() * cantidad;
    }
}
