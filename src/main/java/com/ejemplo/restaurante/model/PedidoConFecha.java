package com.ejemplo.restaurante.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@DiscriminatorValue("con_fecha")
public class PedidoConFecha extends Pedido {

    @CreationTimestamp
    private LocalDateTime fechaCreacion;

    public PedidoConFecha() {
        super(); // Llama al constructor vacío de Pedido
    }

    public PedidoConFecha(Long id, Cliente cliente, String descripcion, Double total, boolean confirmado, LocalDateTime fechaCreacion) {
        super(id, cliente, descripcion, total, confirmado); // Llama al constructor completo de Pedido
        this.fechaCreacion = fechaCreacion;
    }


    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
}