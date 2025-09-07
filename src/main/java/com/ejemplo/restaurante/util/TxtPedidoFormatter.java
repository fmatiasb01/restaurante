package com.ejemplo.restaurante.util;

import com.ejemplo.restaurante.model.Pedido;

import java.io.IOException;
import java.io.PrintWriter;

public class TxtPedidoFormatter implements PedidoFormatter {

    @Override
    public void imprimir(Pedido pedido) {
        String nombreArchivo = "recibo_pedido_" + pedido.getId() + ".txt";

        try (PrintWriter writer = new PrintWriter(nombreArchivo)) {
            writer.println("=== Recibo de Pedido ===");
            if (pedido.getCliente() != null) {
                writer.println("Cliente: " + pedido.getCliente().getNombre());
            } else {
                writer.println("Cliente: No asignado");
            }
            writer.println("Descripción: " + pedido.getDescripcion());
            writer.println("Total: $" + pedido.getTotal());
            writer.println("=========================");
        } catch (IOException e) {
            throw new RuntimeException("Error al imprimir recibo en TXT", e);
        }
    }
}
