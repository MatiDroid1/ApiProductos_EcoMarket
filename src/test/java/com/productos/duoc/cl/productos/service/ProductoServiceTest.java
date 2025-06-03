package com.productos.duoc.cl.productos.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.productos.duoc.cl.productos.model.Producto;
import com.productos.duoc.cl.productos.repository.ProductoRepository;
import com.productos.duoc.cl.productos.services.ProductoService;

public class ProductoServiceTest {
    @Mock
    private ProductoRepository pRepository;

    @InjectMocks
    private ProductoService pService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGuardarProd() {
        // Arrange (preparo los datos)
        Producto prod = new Producto();
        prod.setNombre("palo bambu");
        prod.setDescripcion("es algo");
        prod.setPrecio(3000L);
        prod.setCategoria_id(1L);
        prod.setProveedor_id(7L);
        prod.setEcoscore("A");
        prod.setActivo(true);

        when(pRepository.save(any())).thenReturn(prod);

        // Act (ejecuto el método)
        Producto prodGuardado = pService.guardarProd(prod);

        // Assert (verifico los resultados)
        assertNotNull(prodGuardado);
        assertEquals("palo bambu", prodGuardado.getNombre());
        assertEquals("es algo", prodGuardado.getDescripcion());
        assertEquals(3000L, prodGuardado.getPrecio());
        assertEquals("A", prodGuardado.getEcoscore());
        assertTrue(prodGuardado.getActivo());

        // Verifico que el save se haya llamado una vez
        verify(pRepository, times(1)).save(any());
    }

        

}
