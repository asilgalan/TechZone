package techzone.models.dto;

import lombok.*;
import techzone.models.ItemCarrito;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemCarritoResponseDto {

        private Long id;
        private ProductoDto producto;
        private Integer cantidad;


        public ItemCarritoResponseDto(ItemCarrito item) {
            this.id = item.getId();
            this.producto = new ProductoDto(item.getProducto());
            this.cantidad = item.getCantidad();


        }

}
