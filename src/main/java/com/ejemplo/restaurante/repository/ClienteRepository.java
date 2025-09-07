package com.ejemplo.restaurante.repository;

import com.ejemplo.restaurante.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Cliente findByNombre(String nombre);
}
