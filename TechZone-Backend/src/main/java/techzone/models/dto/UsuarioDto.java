package techzone.models.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import techzone.models.enums.Roles;
import techzone.models.Usuario;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDto {
    private Long idUsuario;
    private String nombre;
    private String apellidos;
    private String email;
    private Set<Roles> roles;


    public UsuarioDto(Usuario usuario) {
        this.idUsuario = usuario.getIdUsuario();
        this.nombre = usuario.getNombre();
        this.apellidos = usuario.getApellidos();
        this.email = usuario.getEmail();
        this.roles = usuario.getRoles();
    }

}
