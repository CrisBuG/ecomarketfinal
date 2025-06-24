package com.eva2.Eva2EcoSPA.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Producto")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 100)
    private String nombre;
    @Column(length = 500)
    private String descripcion;
    private double precio;
    private int stock;
    private String categoria;

    @OneToMany(mappedBy = "producto")
    @JsonIgnore
    private List<Lista> listas;

    @OneToMany(mappedBy = "producto")
    @JsonManagedReference("producto-pedidos")
    private List<Pedido> pedidos;
}