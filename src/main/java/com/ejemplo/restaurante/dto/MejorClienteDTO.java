package com.ejemplo.restaurante.dto;

public class MejorClienteDTO {

    private String nombre;
    private Double promedioMensual;

    public MejorClienteDTO(String nombre, Double promedioMensual) {
        this.nombre = nombre;
        this.promedioMensual = promedioMensual;
    }

    public String getNombre() { return nombre; }
    public Double getPromedioMensual() { return promedioMensual; }

}
