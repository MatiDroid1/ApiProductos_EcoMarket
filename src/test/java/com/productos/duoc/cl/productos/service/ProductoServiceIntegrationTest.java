package com.productos.duoc.cl.productos.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.productos.duoc.cl.productos.model.Producto;
import com.productos.duoc.cl.productos.services.ProductoService;

@SpringBootTest
public class ProductoServiceIntegrationTest {
    // Esto hace que levante el contexto completo, incluyendo la conexión a la BD

    @Autowired
    private ProductoService pService;

    @Test
    public void testBuscarProductoPorIdReal() {
        Long idExistente = 3L;

        Producto prod = pService.buscarporID(idExistente);
        System.out.println("Producto encontrado: " + prod.getNombre());
        assertNotNull(prod); // Verifica que encontró algo
        assertEquals(idExistente, prod.getProductoId());

    }

    @Test
    public void testGuardarProductoEnBD() {
        Producto nuevoProd = new Producto();
        nuevoProd.setNombre("Producto Test Real 2");
        nuevoProd.setDescripcion("SAS");
        nuevoProd.setPrecio(9999L);
        nuevoProd.setCategoria_id(1L);
        nuevoProd.setProveedor_id(7L);
        nuevoProd.setEcoscore("A");
        nuevoProd.setActivo(true);

        Producto guardado = pService.guardarProd(nuevoProd);

        assertNotNull(guardado.getProductoId()); // Ya tiene ID generado
        assertEquals("Producto Test Real 2", guardado.getNombre());
    }
}
