package Modelo;

import Estructuras.*;

import java.time.LocalDateTime;
import java.util.HashMap;

public class Fintech {
    private HashMap<String, Usuario> usuarios;

    private HashMap<String, Billetera> billeteras;

    private ColaPrioridad operacionesProgramadas;

    private Cola notificaciones;

    private ArbolUsuarios usuariosPorPuntos;

    private Grafo grafoTransferencias;

    public Fintech() {

        usuarios = new HashMap<>();

        billeteras = new HashMap<>();

        operacionesProgramadas =
                new ColaPrioridad();

        notificaciones =
                new Cola();

        usuariosPorPuntos =
                new ArbolUsuarios();

        grafoTransferencias =
                new Grafo();
    }

    public void registrarUsuario(Usuario usuario) {
        if(usuarios.containsKey(usuario.getId())) {

            System.out.println(
                    "Ya existe un usuario con ese ID");

            return;
        }

        usuarios.put(usuario.getId(), usuario);

        usuariosPorPuntos.insertar(usuario);
    }

    public Usuario buscarUsuario(String id) {
        return usuarios.get(id);
    }

    public void eliminarUsuario(String id) {
        usuarios.remove(id);
    }

    public void registrarBilletera(Billetera billetera) {
        billeteras.put(billetera.getCodigo(), billetera);
        billetera.getUsuario().getBilleteras().agregarFinal(billetera);
    }

    public Billetera buscarBilletera(String codigo) {
        return billeteras.get(codigo);
    }

    public void recargar(String codigoBilletera, double monto) {
        Billetera billetera = buscarBilletera(codigoBilletera);
        if (billetera != null) {
            billetera.setSaldo(billetera.getSaldo() + monto);
            Transaccion transaccion = new Transaccion(TipoTransaccion.RECARGA, monto, null, billetera);
            usuariosPorPuntos.eliminar(billetera.getUsuario());
            billetera.agregarTransaccion(transaccion);
            usuariosPorPuntos.insertar(billetera.getUsuario());
            billetera.getUsuario().getTransacciones().agregarFinal(transaccion);
        }
    }

    public void retirar(String codigoBilletera, double monto) {
        Billetera billetera = buscarBilletera(codigoBilletera);

        if (billetera == null) {throw new IllegalArgumentException("La billetera no existe.");
        }

        Transaccion transaccion = new Transaccion(TipoTransaccion.RETIRO, monto, billetera, null);

        if (billetera.getSaldo() >= monto) {
            billetera.setSaldo(billetera.getSaldo() - monto);
            transaccion.setEstado(EstadoTransaccion.EJECUTADA);
        } else {
            transaccion.setEstado(EstadoTransaccion.RECHAZADA);
            billetera.agregarTransaccion(transaccion);
            billetera.getUsuario().getTransacciones().agregarFinal(transaccion);
            throw new IllegalArgumentException("Saldo insuficiente para realizar el retiro.");
        }
        usuariosPorPuntos.eliminar(billetera.getUsuario());
        billetera.agregarTransaccion(transaccion);
        usuariosPorPuntos.insertar(billetera.getUsuario());
        billetera.getUsuario().getTransacciones().agregarFinal(transaccion);
    }

    public void transferir(String origen, String destino, double monto) {

        Billetera billeteraOrigen = buscarBilletera(origen);

        if (origen.equals(destino)) {
            if (billeteraOrigen != null) {
                Transaccion transaccion =
                        new Transaccion(TipoTransaccion.TRANSFERENCIA, monto, billeteraOrigen, billeteraOrigen);
                transaccion.setEstado(EstadoTransaccion.RECHAZADA);
                billeteraOrigen.agregarTransaccion(transaccion);
                billeteraOrigen.getUsuario().getTransacciones().agregarFinal(transaccion);
            }
            throw new IllegalArgumentException("No se puede transferir a la misma billetera.");
        }

        Billetera billeteraDestino = buscarBilletera(destino);

        if (billeteraDestino == null) {
            if (billeteraOrigen != null) {
                Transaccion transaccion = new Transaccion(TipoTransaccion.TRANSFERENCIA, monto, billeteraOrigen, null);
                transaccion.setEstado(EstadoTransaccion.RECHAZADA);
                billeteraOrigen.agregarTransaccion(transaccion);
                billeteraOrigen.getUsuario().getTransacciones().agregarFinal(transaccion);
            }
            throw new IllegalArgumentException("La billetera de destino no existe.");
        }
        Transaccion transaccion = new Transaccion(TipoTransaccion.TRANSFERENCIA, monto,billeteraOrigen, billeteraDestino);
        if (billeteraOrigen.getSaldo() < monto) {
            transaccion.setEstado(EstadoTransaccion.RECHAZADA);
            billeteraOrigen.agregarTransaccion(transaccion);
            billeteraOrigen.getUsuario().getTransacciones().agregarFinal(transaccion);
            billeteraDestino.agregarTransaccion(transaccion);
            billeteraDestino.getUsuario().getTransacciones().agregarFinal(transaccion);
            throw new IllegalArgumentException("Saldo insuficiente para realizar la transferencia.");
        }
        billeteraOrigen.setSaldo(billeteraOrigen.getSaldo() - monto);
        billeteraDestino.setSaldo(billeteraDestino.getSaldo() + monto);
        transaccion.setEstado(EstadoTransaccion.EJECUTADA);
        grafoTransferencias.agregarArista(billeteraOrigen.getUsuario(), billeteraDestino.getUsuario());

        usuariosPorPuntos.eliminar(billeteraOrigen.getUsuario());
        billeteraOrigen.agregarTransaccion(transaccion);
        usuariosPorPuntos.insertar(billeteraOrigen.getUsuario());
        billeteraDestino.agregarTransaccion(transaccion);

        billeteraOrigen.getUsuario().getTransacciones().agregarFinal(transaccion);
        billeteraDestino.getUsuario().getTransacciones().agregarFinal(transaccion);
    }

    public void revertirTransferencia(String idUsuario, String idTransaccion) {
        Usuario usuario = buscarUsuario(idUsuario);
        if (usuario == null) {
            return;
        }
        Pila<Transaccion> aux = new Pila<>();
        while (!usuario.getReversibles().isEmpty()) {
            Transaccion t = usuario.getReversibles().pop();
            if (t.getId().equals(idTransaccion)) {
                if (t.getEstado() == EstadoTransaccion.EJECUTADA) {
                    long dias = java.time.Duration.between(t.getFecha(), java.time.LocalDateTime.now()).toDays();
                    if (dias <= 7) {
                        Billetera billeteraOrigen = t.getBilleOrigen();
                        Billetera billeteraDestino = t.getBilleDestino();
                        double monto = t.getMonto();
                        billeteraOrigen.setSaldo(billeteraOrigen.getSaldo() + monto);
                        billeteraDestino.setSaldo(billeteraDestino.getSaldo() - monto);
                        billeteraOrigen.getUsuario().quitarPuntos(t.getPuntosGanados());
                        t.setEstado(EstadoTransaccion.REVERTIDA);
                        break;
                    } else {
                        System.out.println("No se puede revertir la transacción, han pasado más de 7 días.");
                        break;
                    }
                }
                else{
                    System.out.println("La transacción no se puede revertir ya que no fue valida");
                }
            }
            aux.push(t);
        }
        while (!aux.isEmpty()) {
            usuario.getReversibles().push(aux.pop());
        }
    }

    public void programarOperacion(TipoTransaccion transaccion,double valor, Billetera origen, Billetera destino, LocalDateTime fecha) {
        OperacionProgramada op = new OperacionProgramada(transaccion, valor, origen, destino, fecha);
        operacionesProgramadas.encolar(op);
    }

    public void ejecutarOperacionesProgramadas() {
        LocalDateTime ahora = LocalDateTime.now();
        while (!operacionesProgramadas.estaVacia()) {
            OperacionProgramada op = operacionesProgramadas.desencolar();
            if (op.getFechaEjecucion().isBefore(ahora) || op.getFechaEjecucion().isEqual(ahora)) {
                switch (op.getTipo()) {
                    case RECARGA:
                        recargar(op.getDestino().getCodigo(), op.getValor());
                        break;
                    case RETIRO:
                        retirar(op.getOrigen().getCodigo(), op.getValor());
                        break;
                    case TRANSFERENCIA:
                        transferir(op.getOrigen().getCodigo(), op.getDestino().getCodigo(), op.getValor());
                        break;
                }
            } else {
                operacionesProgramadas.encolar(op);
                break;
            }
            op.setEjecutada(true);
        }
    }

    public void eliminarBilletera(String codigo) {
        Billetera billetera = buscarBilletera(codigo);
        billeteras.remove(codigo);
        if (billetera != null) {
            billetera.getUsuario().getBilleteras().eliminar(billetera);
        }

    }

    public Lista<Transaccion> obtenerHistorialBilletera(String codigo){
        Billetera billetera = buscarBilletera(codigo);
        if (billetera != null) {
            return billetera.getHistorial();
        }
        return new Lista<>();
    }

    public Lista<Notificacion> obtenerNotificaciones(String idUsuario){
        Usuario usuario = buscarUsuario(idUsuario);
        if (usuario != null) {
            return usuario.getNotificaciones();
        }
        return new Lista<>();
    }

    public void mostrarRankingUsuarios(){
        usuariosPorPuntos.inOrden();
    }

    public void crearBilletera(String nombre, TipoBilletera tipo, String idUsuario){
        Usuario usuario = buscarUsuario(idUsuario);
        if (usuario != null) {
            Billetera billetera = new Billetera(nombre, tipo, usuario);
            registrarBilletera(billetera);
        }
    }

    public HashMap<String, Usuario> getUsuarios() {
        return usuarios;
    }

    public HashMap<String, Billetera> getBilleteras() {
        return billeteras;
    }

    public ColaPrioridad getOperacionesProgramadas() {
        return operacionesProgramadas;
    }

    public Cola getNotificaciones() {
        return notificaciones;
    }

    public ArbolUsuarios getUsuariosPorPuntos() {
        return usuariosPorPuntos;
    }

    public Grafo getGrafoTransferencias() {
        return grafoTransferencias;
    }
}