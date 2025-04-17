package techzone.controllers;
import jakarta.validation.constraints.Min;
import techzone.models.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import techzone.models.dto.CarritoResponse;
import techzone.models.dto.ItemCarritoResponseDto;
import techzone.services.carrito.CarritoService;




@RestController
@RequestMapping("/api/carrito")
public class CarritoController {

    private final CarritoService carritoService;

    public CarritoController(CarritoService carritoService) {
        this.carritoService = carritoService;
    }

    @GetMapping(value = "/usuario-carrito/{usuarioId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CarritoResponse> obtenerCarritoPorUsuario(@PathVariable Long usuarioId) {
        Carrito carrito = carritoService.obtenerCarritoPorUsuario(usuarioId);
        CarritoResponse carritoResponse = new CarritoResponse(carrito);
        return ResponseEntity.ok(carritoResponse);
    }

    @PostMapping("/agregar")
    public ResponseEntity<ItemCarritoResponseDto> agregarAlCarrito(
            @RequestParam @Min(1) Long usuarioId,
            @RequestParam @Min(1) Long productoId,
            @RequestParam(defaultValue = "1") @Min(1) Integer cantidad) {

        ItemCarrito item = carritoService.agregarProducto(usuarioId, productoId, cantidad);
        ItemCarritoResponseDto response = new ItemCarritoResponseDto(item);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/eliminar")
    public ResponseEntity<Void> eliminarDelCarrito(
            @RequestParam Long usuarioId,
            @RequestParam Long productoId) {
        carritoService.eliminarProducto(usuarioId, productoId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/actualizar-cantidad")
    public ResponseEntity<ItemCarrito> actualizarCantidad(
            @RequestParam Long usuarioId,
            @RequestParam Long productoId,
            @RequestParam Integer nuevaCantidad) {
        carritoService.actualizarCantidad(usuarioId, productoId, nuevaCantidad);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/vaciar/{usuarioId}")
    public ResponseEntity<Void> vaciarCarrito(@PathVariable Long usuarioId) {
        carritoService.vaciarCarrito(usuarioId);
        return ResponseEntity.noContent().build();
    }
}