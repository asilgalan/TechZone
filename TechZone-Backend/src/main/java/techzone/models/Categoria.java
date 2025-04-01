package techzone.models;


import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Entity
@Table(name="categorias")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false,name="id_categoria")
    private Integer idCategoria;

    @Column(nullable = false, length = 50)
    private String nombre;

    @Column(nullable = false, unique = true, length = 50)
    private String slug;

    @ManyToOne
    @JoinColumn(name = "id_padre")
    private Categoria padre;

    @Column(nullable = false)
    private String descripcion;

    private String imagenUrl;
    private String icono;

    @Column(columnDefinition = "INT DEFAULT 0")
    private Integer nivel = 0;

    @Column(columnDefinition = "INT DEFAULT 0")
    private Integer orden = 0;

    @OneToMany(mappedBy = "padre", fetch = FetchType.LAZY)
    @OrderBy("orden ASC")
    private List<Categoria> subcategorias = new ArrayList<>();
}
