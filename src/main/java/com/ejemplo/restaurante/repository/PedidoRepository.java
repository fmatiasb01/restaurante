package com.ejemplo.restaurante.repository;

import com.ejemplo.restaurante.model.Cliente;
import com.ejemplo.restaurante.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    List<Pedido> findByCliente(Cliente cliente);
}