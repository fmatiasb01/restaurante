package com.ejemplo.restaurante.service;

import com.ejemplo.restaurante.model.Cliente;
import com.ejemplo.restaurante.model.Pedido;
import com.ejemplo.restaurante.repository.ClienteRepository;
import com.ejemplo.restaurante.repository.PedidoRepository;
import org.springframework.stereotype.Service;

import java.time.YearMonth;
import java.util.DoubleSummaryStatistics;
import java.util.List;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ClienteRepository clienteRepository;

    public PedidoService(PedidoRepository pedidoRepository, ClienteRepository clienteRepository) {
        this.pedidoRepository = pedidoRepository;
        this.clienteRepository = clienteRepository;
    }

    public Pedido guardar(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    public List<Pedido> listarTodos() {
        return pedidoRepository.findAll();
    }

    public Double promedioMensualPorCliente(String nombreCliente) {
        Cliente cliente = clienteRepository.findByNombre(nombreCliente);
        if (cliente == null) {
            throw new IllegalArgumentException("Cliente no encontrado");
        }
        List<Pedido> pedidos = pedidoRepository.findByCliente(cliente);
        if (pedidos.isEmpty()) {
            return 0.0;
        }

        DoubleSummaryStatistics stats = pedidos.stream()
                .mapToDouble(Pedido::getTotal)
                .summaryStatistics();

        int meses = Math.max(1, YearMonth.now().getMonthValue());
        return stats.getSum() / meses;
    }
}
