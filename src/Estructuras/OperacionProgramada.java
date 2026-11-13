package Estructuras;

import java.time.LocalDateTime;

public class OperacionProgramada {

    private String id;

    private LocalDateTime fechaEjecucion;

    private TipoTransaccion tipo;

    private double valor;

    private Billetera origen;

    private Billetera destino;

    private boolean ejecutada;

    public OperacionProgramada(
            TipoTransaccion tipo,
            double valor,
            Billetera origen,
            Billetera destino,
            LocalDateTime fechaEjecucion) {

        this.tipo = tipo;
        this.valor = valor;
        this.origen = origen;
        this.destino = destino;
        this.fechaEjecucion = fechaEjecucion;

        this.ejecutada = false;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getFechaEjecucion() {
        return fechaEjecucion;
    }

    public void setFechaEjecucion(LocalDateTime fechaEjecucion) {
        this.fechaEjecucion = fechaEjecucion;
    }

    public TipoTransaccion getTipo() {
        return tipo;
    }

    public void setTipo(TipoTransaccion tipo) {
        this.tipo = tipo;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Billetera getOrigen() {
        return origen;
    }

    public void setOrigen(Billetera origen) {
        this.origen = origen;
    }

    public Billetera getDestino() {
        return destino;
    }

    public void setDestino(Billetera destino) {
        this.destino = destino;
    }

    public boolean isEjecutada() {
        return ejecutada;
    }

    public void setEjecutada(boolean ejecutada) {
        this.ejecutada = ejecutada;
    }
}
