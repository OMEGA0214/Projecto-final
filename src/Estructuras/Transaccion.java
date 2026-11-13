package Estructuras;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Transaccion {
    public static int ids =1;
    public String id;
    public LocalDateTime fecha;
    public TipoTransaccion tipoTransaccion;
    public double monto;
    public Billetera billeOrigen;
    public Billetera billeDestino;
    public EstadoTransaccion estado;
    public int puntosGanados;

    public Transaccion(
                       TipoTransaccion tipoTransaccion,
                       double monto,
                       Billetera billeOrigen,
                       Billetera billeDestino) {

        this.id = "T" + ids++;
        this.tipoTransaccion = tipoTransaccion;
        this.monto = monto;
        this.billeOrigen = billeOrigen;
        this.billeDestino = billeDestino;

        this.fecha = LocalDateTime.now();
        this.estado = EstadoTransaccion.EJECUTADA;
        switch (tipoTransaccion) {
            case RECARGA:
                this.puntosGanados = (int) (monto / 100);
                break;

            case RETIRO:
                this.puntosGanados = (int) (monto / 100) * 2;
                break;

            case TRANSFERENCIA:
                this.puntosGanados = (int) (monto / 100) * 3;
                break;
        }
    }

    public int getIds() {
        return ids;
    }

    public void setIds(int ids) {
        this.ids = ids;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public TipoTransaccion getTipoTransaccion() {
        return tipoTransaccion;
    }

    public void setTipoTransaccion(TipoTransaccion tipoTransaccion) {
        this.tipoTransaccion = tipoTransaccion;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public Billetera getBilleOrigen() {
        return billeOrigen;
    }

    public void setBilleOrigen(Billetera billeOrigen) {
        this.billeOrigen = billeOrigen;
    }

    public Billetera getBilleDestino() {
        return billeDestino;
    }

    public void setBilleDestino(Billetera billeDestino) {
        this.billeDestino = billeDestino;
    }

    public EstadoTransaccion getEstado() {
        return estado;
    }

    public void setEstado(EstadoTransaccion estado) {
        this.estado = estado;
    }

    public int getPuntosGanados() {
        return puntosGanados;
    }

    public void setPuntosGanados(int puntosGanados) {
        this.puntosGanados = puntosGanados;
    }

}
