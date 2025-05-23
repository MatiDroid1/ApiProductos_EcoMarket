package com.productos.duoc.cl.productos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.productos.duoc.cl.productos.model.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long>{

    
}