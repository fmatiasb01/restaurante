package com.ejemplo.restaurante.service;

import com.ejemplo.restaurante.model.Pedido;

import java.util.List;

public interface PedidoBusquedaStrategy {
    List<Pedido> buscar(String criterio);
}
