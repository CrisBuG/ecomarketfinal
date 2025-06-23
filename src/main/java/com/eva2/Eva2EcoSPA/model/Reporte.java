package com.eva2.Eva2EcoSPA.model;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Reporte")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Reporte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String tipo;
    private Date fechaGeneracion;
    private String datos;
    private String formato;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
}
