package com.ejemplo.restaurante.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("pago")
public class PedidoPago extends Pedido {
    private String metodoPago;

    public PedidoPago() {
        super(); // Llama al constructor vacío de Pedido
    }

    public PedidoPago(Long id, Cliente cliente, String descripcion, Double total, boolean confirmado, String metodoPago) {
        super(id, cliente, descripcion, total, confirmado); // Llama al constructor completo de Pedido
        this.metodoPago = metodoPago;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }
}