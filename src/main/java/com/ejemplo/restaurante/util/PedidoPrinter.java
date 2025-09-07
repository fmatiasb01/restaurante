package com.ejemplo.restaurante.util;

import com.ejemplo.restaurante.model.Pedido;

public class PedidoPrinter {
    private final PedidoFormatter formatter;

    public PedidoPrinter(PedidoFormatter formatter) {
        this.formatter = formatter;
    }

    public void imprimirRecibo(Pedido pedido) {
        formatter.imprimir(pedido);
    }
}
