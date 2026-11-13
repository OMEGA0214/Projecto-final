package Modelo;

import Estructuras.*;

import java.util.HashMap;

public class Fintech {
    private HashMap<String, Usuario> usuarios;

    private HashMap<String, Billetera> billeteras;

    private ColaPrioridad operacionesProgramadas;

    private Cola notificaciones;

    private ArbolUsuarios usuariosPorPuntos;

    private Grafo grafoTransferencias;

    public Fintech() {
    }

    public void registrarUsuario(Usuario usuario) {
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
    }

    public Billetera buscarBilletera(String codigo) {
        return billeteras.get(codigo);
    }

    public void recargar(String codigoBilletera, double monto) {
        Billetera billetera = buscarBilletera(codigoBilletera);
        if (billetera != null) {
            billetera.setSaldo(billetera.getSaldo() + monto);
            Transaccion transaccion = new Transaccion(TipoTransaccion.RECARGA, monto, null, billetera);
            billetera.agregarTransaccion(transaccion);

        }
    }

    public void retirar(String codigoBilletera, double monto) {
        Billetera billetera = buscarBilletera(codigoBilletera);
        Transaccion transaccion = new Transaccion(TipoTransaccion.RETIRO, monto, billetera, null);

        if (billetera != null && billetera.getSaldo() >= monto) {
            billetera.setSaldo(billetera.getSaldo() - monto);
            transaccion.setEstado(EstadoTransaccion.EJECUTADA);
        } else {
            System.out.println("Saldo insuficiente para realizar el retiro.");
            transaccion.setEstado((EstadoTransaccion.RECHAZADA));

        }
        if (billetera != null) {
            billetera.agregarTransaccion(transaccion);
        }

    }

    public void transferir(String origen, String destino, double monto) {
        if (origen.equals(destino)) {
            System.out.println("No se puede transferir a la misma billetera.");
            return;
        }
        Billetera billeteraOrigen = buscarBilletera(origen);
        Billetera billeteraDestino = buscarBilletera(destino);
        Transaccion transaccion = new Transaccion(TipoTransaccion.TRANSFERENCIA, monto, billeteraOrigen, billeteraDestino);
        if (billeteraOrigen != null && billeteraDestino != null && billeteraOrigen.getSaldo() >= monto) {
            billeteraOrigen.setSaldo(billeteraOrigen.getSaldo() - monto);
            billeteraDestino.setSaldo(billeteraDestino.getSaldo() + monto);
            transaccion.setEstado(EstadoTransaccion.EJECUTADA);
            grafoTransferencias.agregarArista(billeteraOrigen.getUsuario(), billeteraDestino.getUsuario());
        } else {
            System.out.println("Saldo insuficiente para realizar la transferencia.");
            transaccion.setEstado(EstadoTransaccion.RECHAZADA);
        }
        if (billeteraOrigen != null) {
            billeteraOrigen.agregarTransaccion(transaccion);
        }
        if (billeteraDestino != null) {
            billeteraDestino.agregarTransaccion(transaccion);
        }
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

}