package techzone.models;


import jakarta.persistence.*;
import lombok.*;
import techzone.models.Enums.EstadoEnvio;
import techzone.models.Enums.MetodoPago;

import java.time.LocalDateTime;


@Entity
@Table(name="pedidos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false,name="id_pedido")
    private Long idPedido;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="id_usuario")
    private Usuario usuario;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="id_direccion")
    private Direccion direccion;
    @Column(nullable = false,name="fecha_pedido")
    private LocalDateTime fechaPedido;

    @Column(name="fecha_actualizacion")
    private LocalDateTime fechaActualizacion;

    @Column(nullable = false,name="estado_envio")
    private EstadoEnvio estadoEnvio;
    @Column(nullable = false,name="metodo_pago")
    private MetodoPago metadoPago;

    @Column(nullable = false)
    private Double subTotal;
    private Double descuento;
    @Column(nullable = false)
     private Double envio;
    private Double total;
    @Column(nullable = false,name="iva_total")
    private Double ivaTotal;

     private Descuento descuento
    @Column(name="codigo_descuento")
    private String codigoDescuento;
    private String observacion;


    @PrePersist
    public void prePersist(){
        setFechaPedido(LocalDateTime.now());

    }
   @PreUpdate
    public void preUpdate(){
        setFechaActualizacion(LocalDateTime.now());
   }

}
