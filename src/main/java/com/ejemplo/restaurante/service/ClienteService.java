package com.ejemplo.restaurante.service;

import com.ejemplo.restaurante.model.Cliente;
import com.ejemplo.restaurante.dto.MejorClienteDTO;
import com.ejemplo.restaurante.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final PedidoService pedidoService;

    public ClienteService(ClienteRepository clienteRepository, PedidoService pedidoService) {
        this.clienteRepository = clienteRepository;
        this.pedidoService = pedidoService;
    }

    public Cliente guardar(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public List<Cliente> listarTodos() {
        return clienteRepository.findAll();
    }

    public MejorClienteDTO obtenerMejorCliente() {
        Cliente mejor = clienteRepository.findAll().stream()
                .max(Comparator.comparingDouble(c -> pedidoService.promedioMensualPorCliente(c.getNombre())))
                .orElseThrow(() -> new IllegalStateException("No hay clientes registrados"));

        double promedio = pedidoService.promedioMensualPorCliente(mejor.getNombre());
        return new MejorClienteDTO(mejor.getNombre(), promedio);
    }
}
