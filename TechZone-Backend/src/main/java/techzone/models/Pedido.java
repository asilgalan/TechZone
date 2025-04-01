package techzone.models;


import jakarta.persistence.*;

@Entity
@Table
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false,name="id_pedido")
    private Long idPedido;
}
