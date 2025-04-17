package techzone.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import techzone.models.Carrito;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarritoResponse {

    private Long idCarrito;
    private UsuarioDto usuario;
    private List<ItemCarritoResponseDto> items;

    public CarritoResponse(Carrito carrito) {
        this.idCarrito = carrito.getIdCarrito();
        this.usuario = new UsuarioDto(carrito.getUsuario());
        this.items = carrito.getItems().stream()
                .map(ItemCarritoResponseDto::new)
                .collect(Collectors.toList());
    }
}
