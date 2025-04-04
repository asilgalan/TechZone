package techzone.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name="producto_proveedor")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductoProveedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private Proveedor proveedor;

    @OneToMany
    @JoinColumn(name="id_producto")
    private List<Producto> producto;

    @NotNull
    @Column(name="codigo_proveedor")
    private String codigoProveedor;

    @NotNull
    @Column(nullable = false,name="precio_compra")
    private Integer precioCompra;


    @Column(name="tiempo_entrega_dia")
    private Integer tiempoEntregaDia;


}
