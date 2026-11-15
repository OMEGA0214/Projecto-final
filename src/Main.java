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

        SwingUtilities.invokeLater(() ->
                new PantallaInicio(fintech));

    }
}