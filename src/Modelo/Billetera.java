package Modelo;

import Estructuras.Lista;

public class Billetera {
    public static int ids = 1;
    public String codigo;
    public String nombre;
    public TipoBilletera tipoBilletera;
    public double saldo;
    public boolean estado;
    public Usuario usuario;
    public Lista<Transaccion> historial;

    public Billetera( String nombre, TipoBilletera tipoBilletera, Usuario usuario) {
        this.codigo = "B" + ids++;
        this.nombre = nombre;
        this.tipoBilletera = tipoBilletera;

        this.saldo = 0.0;
        this.estado = true;

        this.usuario = usuario;

        this.historial = new Lista<Transaccion>();
    }

    public void agregarTransaccion(Transaccion transaccion) {
        historial.agregarFinal(transaccion);
        if( transaccion.getEstado() == EstadoTransaccion.EJECUTADA ) {
            usuario.agregarPuntos(transaccion.getPuntosGanados());
            if(transaccion.getTipoTransaccion() == TipoTransaccion.TRANSFERENCIA) {
                usuario.getReversibles().push(transaccion);
            }
        }

    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public TipoBilletera getTipoBilletera() {
        return tipoBilletera;
    }

    public void setTipoBilletera(TipoBilletera tipoBilletera) {
        this.tipoBilletera = tipoBilletera;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Lista<Transaccion> getHistorial() {
        return historial;
    }

    public void setHistorial(Lista<Transaccion> historial) {
        this.historial = historial;
    }

    @Override
    public String toString() {
        return codigo
                + " - "
                + nombre
                + " - "
                + tipoBilletera
                + " - $"
                + saldo;
    }
}
