package com.eva2.Eva2EcoSPA.model;

import java.util.List;

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
@Table(name = "Usuario")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 100)
    private String nombre;
    @Column(length = 100)
    private String email;
    @Column(length = 100)
    private String password;
    @Column(unique = true, length = 15)
    private String run;
    private String rol;
    
    @OneToMany(mappedBy = "usuario")
    private List<Lista> listas;
    
    @OneToMany(mappedBy = "usuario")
    private List<Pedido> pedidos;
}