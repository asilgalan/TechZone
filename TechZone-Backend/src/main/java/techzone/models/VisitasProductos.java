package techzone.models;

import jakarta.persistence.*;

import lombok.*;
import techzone.models.enums.OrigenVisita;

import java.time.LocalDateTime;


@Data
@Entity
@Table(name="visitas_productos")
@NoArgsConstructor
@AllArgsConstructor

public class VisitasProductos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_visita")
    private Long idVisita;

    @ManyToOne
    @JoinColumn(name = "id_producto")
    private Producto producto;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;
    @Column(name = "fecha_visita")
    private LocalDateTime fechaVisita;

    @Column(name = "ip", length = 45)
    private String ip;

    @Enumerated(EnumType.STRING)
    @Column(name = "origen")
    private OrigenVisita origen;

 @PrePersist
    protected void onCreate() {
     fechaVisita = LocalDateTime.now();
 }
}
