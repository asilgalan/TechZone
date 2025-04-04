package techzone.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import techzone.models.Marca;
import techzone.services.marca.IMarcaService;

import java.util.List;

@RestController

public class MarcaController {

    @Autowired
    private IMarcaService marcaService;

    @GetMapping("/marca")
    public List<Marca> todasLasMarca(){
        return marcaService.todasLasMarcas();

    }
    @PostMapping("/marca")
    public ResponseEntity<?> guardarMarca(@RequestBody Marca marca){

        return ResponseEntity.ok().body(marcaService.guardarMarca(marca));
    }
}
