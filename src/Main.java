import Estructuras.Lista;
import Modelo.*;
import Vista.PantallaInicio;

import javax.swing.*;
import java.time.LocalDateTime;


public class Main {
    public static void main(String[] args) {
        Fintech fintech = new Fintech();

        Usuario u1 = new Usuario(
                "101",
                "David",
                "david@gmail.com");

        Usuario u2 = new Usuario(
                "102",
                "Ana",
                "ana@gmail.com");

        fintech.registrarUsuario(u1);
        fintech.registrarUsuario(u2);

        // Billeteras

        Billetera b1 = new Billetera(
                "B001",
                TipoBilletera.AHORRO,
                u1);

        Billetera b2 = new Billetera(
                "B002",
                TipoBilletera.COMPRAS,
                u2);

        fintech.registrarBilletera(b1);
        fintech.registrarBilletera(b2);

        u1.getBilleteras().agregarFinal(b1);
        u2.getBilleteras().agregarFinal(b2);

        SwingUtilities.invokeLater(() ->
                new PantallaInicio(fintech));

//        // Recarga
//
//        fintech.recargar("B1", 100000);
//
//        System.out.println(
//                "Saldo David tras recarga: "
//                        + b1.getSaldo());
//
//        // Transferencia
//
//        fintech.transferir(
//                "B1",
//                "B2",
//                30000);
//
//        System.out.println(
//                "Saldo David tras transferencia: "
//                        + b1.getSaldo());
//
//        System.out.println(
//                "Saldo Ana tras transferencia: "
//                        + b2.getSaldo());
//
//        // Historial
//
//        System.out.println(
//                "\nHistorial David:");
//
//        Lista<Transaccion> historial =
//                fintech.obtenerHistorialBilletera("B1");
//
//        for (int i = 0; i < historial.getSize(); i++) {
//
//            Transaccion t = historial.obtener(i);
//
//            System.out.println(
//                    t.getId() + " - "
//                            + t.getTipoTransaccion()
//                            + " - "
//                            + t.getMonto()
//                            + " - "
//                            + t.getEstado());
//        }
//
//        // Reversión
//
//        String idTransaccion =
//                historial.obtener(1).getId();
//
//        fintech.revertirTransferencia(
//                "101",
//                idTransaccion);
//
//        System.out.println(
//                "\nDespués de revertir:");
//
//        System.out.println(
//                "Saldo David: "
//                        + b1.getSaldo());
//
//        System.out.println(
//                "Saldo Ana: "
//                        + b2.getSaldo());
//
//        // Notificaciones
//
//        System.out.println(
//                "\nNotificaciones David:");
//
//        Lista<Notificacion> notificaciones =
//                fintech.obtenerNotificaciones("101");
//
//        for (int i = 0; i < notificaciones.getSize(); i++) {
//            System.out.println(
//                    notificaciones.obtener(i).getMensaje());
//        }
//
//        // Operación programada
//
//        fintech.programarOperacion(
//                TipoTransaccion.RECARGA,
//                50000,
//                null,
//                b1,
//                LocalDateTime.now().minusMinutes(1));
//
//        fintech.ejecutarOperacionesProgramadas();
//
//        System.out.println(
//                "\nSaldo David tras operación programada: "
//                        + b1.getSaldo());

    }
}