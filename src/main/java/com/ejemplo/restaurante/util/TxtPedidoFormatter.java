package com.ejemplo.restaurante.util;

import com.ejemplo.restaurante.model.Pedido;
import com.ejemplo.restaurante.model.PedidoConFecha;

import java.io.PrintWriter;
import java.io.IOException;

public class TxtPedidoFormatter implements PedidoFormatter {

    @Override
    public void imprimir(Pedido pedido) {
        // Validación y casteo tradicional
        if (!(pedido instanceof PedidoConFecha)) {
            throw new IllegalArgumentException("Pedido debe ser del tipo PedidoConFecha");
        }

        PedidoConFecha pedidoConFecha = (PedidoConFecha) pedido;

        String nombreArchivo = "recibo_pedido_" + pedidoConFecha.getId() + ".txt";

        try (PrintWriter writer = new PrintWriter(nombreArchivo)) {
            writer.println("=== Recibo de Pedido ===");
            if (pedidoConFecha.getCliente() != null) {
                writer.println("Cliente: " + pedidoConFecha.getCliente().getNombre());
            } else {
                writer.println("Cliente: No asignado");
            }
            writer.println("Descripción: " + pedidoConFecha.getDescripcion());
            writer.println("Fecha: " + (pedidoConFecha.getFechaCreacion() != null
                    ? pedidoConFecha.getFechaCreacion()
                    : "No disponible"));
            writer.println("Total: $" + pedidoConFecha.getTotal());
            writer.println("=========================");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
