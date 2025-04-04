package techzone.models;

import jakarta.persistence.*;

import java.util.List;
import java.util.Set;

@Entity
@Table(name="detalles_pedidos")
public class DetallesPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDetallesPedido;
    @ManyToMany
    @JoinTable(name = "detalles_pedido_producto",
            joinColumns = @JoinColumn(name = "detalles_pedido_id"),
            inverseJoinColumns = @JoinColumn(name = "producto_id")
    )
    private List<Producto> productos;


    @ManyToOne
    @JoinColumn(name = "id_pedido")
    private Pedido pedido;
    @Column(nullable = false)
    private Double cantidad;
    @Column(nullable = false)
    private Double subtotal;
    @Column(nullable = false)
    private Double iva;
    @Column(nullable = false,name="precio_unitario")
    private Double precionUnitario;
    @Column(nullable = false)
    private Double total;
    private Boolean devuelto;
    @Column(name="cantidad_devuelta")
    private int cantidadDevuelta;


}
