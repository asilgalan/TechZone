package techzone.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import techzone.models.Favorito;
import techzone.models.Producto;
import techzone.models.Usuario;
import techzone.models.dto.FavoritoDto;
import techzone.models.dto.ProductoDto;
import techzone.models.dto.ProductoFavoritoDto;
import techzone.models.mappers.ProductoMapper;
import techzone.services.Favorito.IFavoritoService;
import techzone.services.producto.IProductoService;
import techzone.services.usuario.IUsuarioService;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    @Autowired
    private IProductoService productoService;
    @Autowired
    private ProductoMapper productoMapper;
    @Autowired
    private IFavoritoService favoritoService;
    @Autowired
    private IUsuarioService usuarioService;

    //TODOS LOS PRODUCTOS
    @GetMapping("/todos")
    public ResponseEntity<List<ProductoDto>> findAll() {

        List<Producto> productos = productoService.obtenerProductos();
        List<ProductoDto> productosDTO = productoMapper.toDtoList(productos);
        return ResponseEntity.ok(productosDTO);
    }

    //OBTENER PRODUCTO POR EL ID
    @GetMapping("/{id}")
    public ResponseEntity<Producto> findById(@PathVariable long id) {
        Optional<Producto> existeProducto=productoService.obtenerProductoPorId(id);
        if (existeProducto.isPresent()) {
            return ResponseEntity.ok(existeProducto.get());
        }
        return ResponseEntity.status(404).body(null);
    }
//TODOS LOS FAVORITOS
    @GetMapping("/todos/favoritos")
    public ResponseEntity<?> todosLosFavortitos(){
        return ResponseEntity.ok(favoritoService.favoritos());
    }
    //guardar en favorito
    @PostMapping("/guardarFavoritos")
    public ResponseEntity<?> guardarFavorito(@RequestBody FavoritoDto favorito) {

       Optional<Usuario> user=usuarioService.obtenerPorId(favorito.getIdUsuario());
       Optional<Producto> producto=productoService.obtenerProductoPorId(favorito.getIdProducto());
        boolean favoritoExistente = favoritoService.favoritos().stream()
                .anyMatch(f ->
                        f.getProducto().getIdProducto().equals(favorito.getIdProducto()) &&
                                f.getUsuario().getIdUsuario().equals(favorito.getIdUsuario())
                );
        if (favoritoExistente) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Este producto ya está en tus favoritos");
        }
       Favorito favorito1=new Favorito();
       favorito1.setProducto(producto.get());
       favorito1.setUsuario(user.get());
        return ResponseEntity.status(201).body(favoritoService.guardarFavorito(favorito1));
    }
    @GetMapping("/favoritoUsuario/{id}")
    public ResponseEntity<?> obtenerProductosFavoritosPorUsuario(@PathVariable long id) {
        try {
            List<Favorito> favoritos = favoritoService.favoritosDeUsuario(id);

            List<ProductoFavoritoDto> favoritoDtos = favoritos.stream()
                    .map(favorito -> new ProductoFavoritoDto(
                            productoMapper.toDto(favorito.getProducto()),
                            favorito.getIdFavorito()
                                        ))
                    .collect(Collectors.toList());

            return ResponseEntity.ok(favoritoDtos);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al obtener productos favoritos: " + e.getMessage());
        }
    }
    @GetMapping("/eliminarFavorito/{id}")
    public ResponseEntity<?> eliminarFavoritoPorUsuario(@PathVariable long id) {
        Optional<Favorito> favorito=favoritoService.obtenerFavorito(id);
        if (favorito.isPresent()) {
            favoritoService.eliminarFavorito(favorito.get().getIdFavorito());
            return ResponseEntity.status(204).body("Eliminado Correctamente el producto que estaba en favoritos");
        }
        return ResponseEntity.status(404).body("Error al eliminar favorito");
    }

}
