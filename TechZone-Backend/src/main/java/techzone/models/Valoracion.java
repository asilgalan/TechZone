package techzone.models;

import com.fasterxml.jackson.databind.ser.std.DateSerializer;
import lombok.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "valoraciones")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Valoracion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_valoracion")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_usuario", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_producto", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Producto producto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pedido")
    @OnDelete(action = OnDeleteAction.SET_NULL)
    private Pedido pedido;

    @Column(nullable = false, columnDefinition = "TINYINT")
    @Min(value = 1, message = "La puntuación mínima es 1")
    @Max(value = 5, message = "La puntuación máxima es 5")
    private Integer puntuacion;

    @Size(max = 100, message = "El título no puede exceder los 100 caracteres")
    @Column(length = 100)
    private String titulo;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String comentario;


    @Column(name = "fecha", updatable = false)
    private Date fecha;


    @Column(nullable = true)
    private Boolean verificada = false;

    @Column(nullable = true)
    private String respuesta;


    @Column(name = "fecha_respuesta")
    private Date fechaRespuesta;

    public Valoracion(Usuario usuario, Producto producto, Integer puntuacion) {
        this.usuario = usuario;
        this.producto = producto;
        this.puntuacion = puntuacion;
    }


    @PrePersist
    public void prePersist() {
        setFecha(new Date());
    }
}
