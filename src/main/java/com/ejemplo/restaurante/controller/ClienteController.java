package com.ejemplo.restaurante.controller;

import com.ejemplo.restaurante.model.Cliente;
import com.ejemplo.restaurante.dto.MejorClienteDTO; // <- import correcto
import com.ejemplo.restaurante.service.ClienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping
    public ResponseEntity<Cliente> crearCliente(@RequestBody Cliente cliente) {
        return ResponseEntity.ok(clienteService.guardar(cliente));
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> listarClientes() {
        return ResponseEntity.ok(clienteService.listarTodos());
    }

    @GetMapping("/mejor-cliente")
    public ResponseEntity<MejorClienteDTO> obtenerMejorCliente() {
        // Cambiamos el tipo de ResponseEntity a MejorClienteDTO
        return ResponseEntity.ok(clienteService.obtenerMejorCliente());
    }
}
