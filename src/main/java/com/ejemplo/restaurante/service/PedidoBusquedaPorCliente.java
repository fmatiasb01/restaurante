package com.ejemplo.restaurante.service;

import com.ejemplo.restaurante.model.Cliente;
import com.ejemplo.restaurante.model.Pedido;
import com.ejemplo.restaurante.repository.ClienteRepository;
import com.ejemplo.restaurante.repository.PedidoRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class PedidoBusquedaPorCliente implements PedidoBusquedaStrategy {

    private final PedidoRepository pedidoRepository;
    private final ClienteRepository clienteRepository;

    public PedidoBusquedaPorCliente(PedidoRepository pedidoRepository, ClienteRepository clienteRepository) {
        this.pedidoRepository = pedidoRepository;
        this.clienteRepository = clienteRepository;
    }

    @Override
    public List<Pedido> buscar(String nombreCliente) {
        Cliente cliente = clienteRepository.findByNombre(nombreCliente);
        if (cliente == null) {
            return Collections.emptyList();
        }
        return pedidoRepository.findByCliente(cliente);
    }
}
