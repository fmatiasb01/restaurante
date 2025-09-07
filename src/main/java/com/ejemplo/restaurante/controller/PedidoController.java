package com.ejemplo.restaurante.controller;

import com.ejemplo.restaurante.model.Pedido;
import com.ejemplo.restaurante.service.PedidoBusquedaStrategy;
import com.ejemplo.restaurante.repository.PedidoRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoBusquedaStrategy pedidoBusquedaStrategy;
    private final PedidoRepository pedidoRepository;

    public PedidoController(PedidoBusquedaStrategy pedidoBusquedaStrategy,
                            PedidoRepository pedidoRepository) {
        this.pedidoBusquedaStrategy = pedidoBusquedaStrategy;
        this.pedidoRepository = pedidoRepository;
    }

    @GetMapping("/historial/{cliente}")
    public List<Pedido> historialCliente(@PathVariable String cliente) {
        return pedidoBusquedaStrategy.buscar(cliente);
    }

    @PostMapping
    public Pedido crearPedido(@RequestBody Pedido pedido) {
        return pedidoRepository.save(pedido);
    }
}
