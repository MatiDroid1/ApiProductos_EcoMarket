package com.productos.duoc.cl.productos.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.github.javafaker.Faker;
import com.productos.duoc.cl.productos.model.Producto;
import com.productos.duoc.cl.productos.repository.ProductoRepository;

@Service
public class ProductoFakerService {

    private final Faker faker = new Faker();
    private final ProductoRepository productoRepository;

    public ProductoFakerService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public List<Producto> generarYGuardarProductos(int cantidad) {
        List<Producto> productos = new ArrayList<>();
        for (int i = 0; i < cantidad; i++) {
            Producto producto = new Producto();
            producto.setNombre(faker.commerce().productName());
            producto.setDescripcion(faker.lorem().sentence());
            producto.setPrecio((long) faker.number().numberBetween(1000, 100000));
            producto.setCategoria_id(1L); // FK fija para pruebas
            producto.setProveedor_id(7L); // FK fija para pruebas
            producto.setEcoscore(faker.options().option("A", "B", "C", "D", "E"));
            producto.setActivo(true);
            productos.add(producto);
        }
        return productoRepository.saveAll(productos);
    }

    public Producto guardarProd(Producto producto) {
        return productoRepository.save(producto);
    }
}