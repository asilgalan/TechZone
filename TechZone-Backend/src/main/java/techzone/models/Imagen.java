package techzone.models;

import jakarta.persistence.*;
import lombok.*;
import techzone.models.enums.tipoImagen;

@Entity
@Table(name = "imagenes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Imagen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_imagen")
    private Long idImagen;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="id_producto")
    private Producto producto;
    private  Integer orden;

    @Enumerated(EnumType.STRING)
    private tipoImagen tipoImagen;
    @Column(name="alt_text")
    private String altText;



}
