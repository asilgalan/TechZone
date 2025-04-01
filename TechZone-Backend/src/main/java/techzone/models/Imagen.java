package techzone.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "imagenes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Imagen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_imagen")
    private int idImagen;



}
