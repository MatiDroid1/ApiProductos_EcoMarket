package com.productos.duoc.cl.productos.controller;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.productos.duoc.cl.productos.model.Producto;
// import com.productos.duoc.cl.productos.services.ProductServiceFaker;
import com.productos.duoc.cl.productos.services.ProductoFakerService;
import com.productos.duoc.cl.productos.services.ProductoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Schema;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/v1/productos")
@Tag(name = "Productos", description = "Api para gestionar productos de EcoMarket")
public class ProductoController {
    @Autowired
    private ProductoService ps; 


    @GetMapping
    @Operation(summary = "Productos", description = "Operación que lista todos los productos")
    //
    @ApiResponses(value = {
    @ApiResponse(
        responseCode = "200",
        description = "Se listaron correctamente todos los productos.",
        content = @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = Producto.class)
        )
    ),
    @ApiResponse(
        responseCode = "404",
        description = "No se encontraron productos.",
        content = @Content(
            mediaType = "application/json",
            schema = @Schema(type = "string", example = "no hay datos")
        )
    ),
    @ApiResponse(
        responseCode = "500",
        description = "Error al retornar la lista de productos.",
        content = @Content(
            mediaType = "application/json",
            schema = @Schema(type = "string", example = "Error interno del servidor")
        )
    )
})

    public ResponseEntity <List<Producto>> listarProductos(){
        List<Producto> productos = ps.listarTodos();
        if(productos.isEmpty()){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.ok(productos);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id){
        if(id == 0){
            return listarProductos();
        }
            Producto prod = ps.buscarporID(id);
            if(prod == null){
                return ResponseEntity.status(404).body("Producto no encontrado con id "+id);
            }
            return ResponseEntity.ok(prod);
        
    }

    @PostMapping()
    public ResponseEntity<?> crearProducto(@RequestBody Producto producto){
        try{
            ps.guardarProd(producto);
            return ResponseEntity.status(201).body("Producto ingresado");
        }catch(Exception ex){
            return ResponseEntity.status(500).body("Producto no Ingresado, problema: "+ex.getMessage());
        }
    }

 @PutMapping("/{id}")
public ResponseEntity<?> modificarProducto(@PathVariable Long id, @RequestBody Producto producto) {
    try {
        Producto productoExistente = ps.buscarporID(id);
        if (productoExistente == null) {
            return ResponseEntity.status(404).body("Producto no encontrado");
        }

        productoExistente.setNombre(producto.getNombre());
        productoExistente.setDescripcion(producto.getDescripcion());
        productoExistente.setPrecio(producto.getPrecio());
        productoExistente.setCategoria_id(producto.getCategoria_id());
        productoExistente.setProveedor_id(producto.getProveedor_id());
        productoExistente.setEcoscore(producto.getEcoscore());
        productoExistente.setActivo(producto.getActivo());

        ps.guardarProd(productoExistente);
        return ResponseEntity.ok("Producto actualizado con éxito");
    } catch (Exception e) {
        return ResponseEntity.status(500).body("Error al actualizar producto");
    }
}

private final ProductoFakerService productoFakerService;

    public ProductoController(ProductoFakerService productoFakerService) {
        this.productoFakerService = productoFakerService;
    }

    @PostMapping("/faker")
    public ResponseEntity<List<Producto>> crearProductosFaker(@RequestParam(defaultValue = "10") int cantidad) {
        List<Producto> productos = productoFakerService.generarYGuardarProductos(cantidad);
        return ResponseEntity.status(201).body(productos);
    }

    
}
