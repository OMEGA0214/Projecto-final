package Vista;

import Modelo.Fintech;
import Modelo.Usuario;

import javax.swing.*;
import java.awt.*;

public class PanelUsuarios extends JFrame {

    public PanelUsuarios(
            Fintech fintech,
            Usuario usuario) {

        setTitle(
                "Fintech - "
                        + usuario.getNombre());

        setSize(900,600);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JTabbedPane tabs =
                new JTabbedPane();

        tabs.add(
                "Billeteras",
                new PanelBilleteras(
                        fintech,
                        usuario));

        tabs.add(
                "Transacciones",
                new PanelOperaciones(
                        fintech,
                        usuario));

        tabs.add(
                "Consultas",
                new PanelConsultas(
                        fintech,
                        usuario));

        JButton cerrarSesion =
                new JButton(
                        "Cerrar Sesión");

        cerrarSesion.addActionListener(e -> {

            new PantallaInicio(fintech);;

            dispose();
        });

        setLayout(new BorderLayout());

        JPanel superior =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.RIGHT));

        superior.add(
                cerrarSesion);

        add(
                superior,
                BorderLayout.NORTH);

        add(
                tabs,
                BorderLayout.CENTER);

        setVisible(true);
    }
}

