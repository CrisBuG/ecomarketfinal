package com.eva2.Eva2EcoSPA.model;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "Inventario")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Inventario {

    @Id
    private int idInventario;
    private int idProducto;
    private int cantidadDisponible;
    private int cantidadMinima;
    private String ubicacionAlmacen;
    private boolean alertaBajoStock;

}
