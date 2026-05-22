package org.ed06.model;

import java.time.LocalDate;

public class Reserva {
    public static final double DESCUENTO_VIP = 0.9;
    public static final double DESCUENTO_POR7_DIAS = 0.95;
    private int id;
    private Habitacion habitacion;
    private Cliente cliente;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private double precioTotal;

    public Reserva(int id, Habitacion habitacion, Cliente cliente, LocalDate fechaInicio, LocalDate fechaFin) {
        this.id = id;
        this.habitacion = habitacion;
        this.cliente = cliente;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.precioTotal = calcularPrecioFinal();
    }

    public int getId() {
        return id;
    }

    public Habitacion getHabitacion() {
        return habitacion;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public double getPrecioTotal() {
        return precioTotal;
    }

    // Calcula el precio total de la reserva. Para calcular el precio total, se debe calcular el precio base de la habitación por el número de noches de la reserva. En el caso de que el cliente sea vip, se aplicará un descuento del 10%. Además, si el intervalo de fechas es mayor a 7 días, se aplicará un descuento adicional del 5%.
    // Devuelve precio total de la reserva
    public double calcularPrecioFinal() {
        //calculamos los días de la reserva
        long numeroNoches = java.time.temporal.ChronoUnit.DAYS.between(fechaInicio, fechaFin);        // Calculamos el precio base de la habitación por el número de noches de la reserva
        double precioBaseTotal = habitacion.getPrecioBase() * numeroNoches;
        // Declaramos la variable para almacenar el precio final
        double precioFinal = precioBaseTotal;

        // Si el cliente es VIP, aplicamos un descuento del 10%
        if (cliente.getEsVip()) {
            precioFinal *= DESCUENTO_VIP;
        }

        // Si el intervalo de fechas es mayor a 7 días, aplicamos un descuento adicional del 5%
        if (numeroNoches > 7) {
            precioFinal *= DESCUENTO_POR7_DIAS;
        }

        // Devolvemos el precio final
        return precioFinal;
    }

    @Override
    public String toString() {
        return "Reserva{" +
                "id=" + id +
                ", habitacion=" + habitacion +
                ", cliente=" + cliente +
                ", fechaInicio=" + fechaInicio +
                ", fechaFin=" + fechaFin +
                ", precioTotal=" + precioTotal +
                '}';
    }
}
