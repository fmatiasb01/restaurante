package com.ejemplo.restaurante.controller;

import com.ejemplo.restaurante.model.Pedido;
import com.ejemplo.restaurante.service.PedidoBusquedaStrategy;
import com.ejemplo.restaurante.service.PedidoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;
    private final PedidoBusquedaStrategy pedidoBusquedaStrategy;

    public PedidoController(PedidoService pedidoService, PedidoBusquedaStrategy pedidoBusquedaStrategy) {
        this.pedidoService = pedidoService;
        this.pedidoBusquedaStrategy = pedidoBusquedaStrategy;
    }

    @PostMapping
    public ResponseEntity<Pedido> crearPedido(@RequestBody Pedido pedido) {
        return ResponseEntity.ok(pedidoService.guardar(pedido));
    }

    @GetMapping
    public ResponseEntity<List<Pedido>> listarPedidos() {
        return ResponseEntity.ok(pedidoService.listarTodos());
    }

    @GetMapping("/historial/{nombreCliente}")
    public ResponseEntity<List<Pedido>> historialPorCliente(@PathVariable String nombreCliente) {
        return ResponseEntity.ok(pedidoBusquedaStrategy.buscar(nombreCliente));
    }

    @GetMapping("/promedio/{nombreCliente}")
    public ResponseEntity<Double> promedioMensualPorCliente(@PathVariable String nombreCliente) {
        return ResponseEntity.ok(pedidoService.promedioMensualPorCliente(nombreCliente));
    }
}
