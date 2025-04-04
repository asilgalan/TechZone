package techzone.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name="proveedores")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Proveedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProveedor;

    @NotNull
    @Column(nullable = false)
    private String nombre;
    private String cif;
    private String telefono;
    private String email;
    private String direccion;
    private String ciudad;
    private String provincia;
    private String pais;
    @Column(name="tiempo_entrega_dia")
    private Integer tiempoEntregaDia;
    private Boolean activo=true;
    private Date fechaCreacion;

    @PrePersist
    public void prePersist() {
        setFechaCreacion(new Date());
    }
    @PreUpdate
    public void preUpdate() {
        setFechaCreacion(new Date());
    }

}
