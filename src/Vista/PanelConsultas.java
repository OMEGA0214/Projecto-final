package Vista;

import Modelo.*;

import javax.swing.*;
import java.awt.*;

public class PanelConsultas extends JPanel {

    public PanelConsultas(
            Fintech fintech,
            Usuario usuario) {

        setLayout(new BorderLayout());

        JTextArea area = new JTextArea();
        area.setEditable(false);

        JButton btnNotificaciones =
                new JButton(
                        "Ver Notificaciones");

        JButton btnHistorial =
                new JButton(
                        "Ver Historial");

        JPanel panelBotones =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.CENTER,
                                20,
                                10));

        panelBotones.add(btnNotificaciones);
        panelBotones.add(btnHistorial);

        btnNotificaciones.addActionListener(e -> {

            area.setText("");

            if(usuario.getNotificaciones()
                    .getSize() == 0) {

                area.setText(
                        "No hay notificaciones.");
                return;
            }

            for(int i = 0;
                i < usuario.getNotificaciones()
                        .getSize();
                i++) {

                area.append(
                        usuario.getNotificaciones()
                                .obtener(i)
                                .toString()
                                + "\n\n");
            }
        });

        btnHistorial.addActionListener(e -> {

            area.setText("");

            if(usuario.getBilleteras()
                    .getSize() == 0) {

                area.setText(
                        "No hay billeteras registradas.");
                return;
            }

            for(int i = 0;
                i < usuario.getBilleteras()
                        .getSize();
                i++) {

                Billetera billetera =
                        usuario.getBilleteras()
                                .obtener(i);

                area.append(
                        "===== "
                                + billetera.getNombre()
                                + " =====\n");

                if(billetera.getHistorial()
                        .getSize() == 0) {

                    area.append(
                            "Sin transacciones\n\n");

                    continue;
                }

                for(int j = 0;
                    j < billetera.getHistorial()
                            .getSize();
                    j++) {

                    area.append(
                            billetera.getHistorial()
                                    .obtener(j)
                                    .toString()
                                    + "\n");
                }

                area.append("\n");
            }
        });

        add(panelBotones,
                BorderLayout.NORTH);

        add(new JScrollPane(area),
                BorderLayout.CENTER);
    }
}