package com.productos.duoc.cl.productos.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Column;

@Entity
@Table(name = "productos")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Producto {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long productoId;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "descripcion", nullable = false)
    private String descripcion;

    @Column(name = "precio", nullable = false)
    private Long precio;

    @Column(name = "categoria_id", nullable = false)
    private Long categoria_id;

    @Column(name = "proveedor_id", nullable = false)
    private Long proveedor_id;

    @Column(name = "ecoscore", nullable = false)
    private String ecoscore;

    @Column(name = "activo", columnDefinition = "NUMBER(1) DEFAULT 1")
    private Boolean activo = true;
}
