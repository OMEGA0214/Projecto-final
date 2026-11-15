package Vista;

import Modelo.*;

import javax.swing.*;
import java.awt.*;

public class PanelOperaciones extends JPanel {

    public PanelOperaciones(
            Fintech fintech,
            Usuario usuario) {

        JButton recarga =
                new JButton("Recarga");

        JButton retiro =
                new JButton("Retiro");

        JButton transferencia =
                new JButton("Transferencia");

        setLayout(new FlowLayout(
                FlowLayout.CENTER,
                20,
                20));
        add(recarga);
        add(retiro);
        add(transferencia);


        recarga.addActionListener(e -> {

            JComboBox<Billetera> destino =
                    new JComboBox<>();

            for(int i=0;
                i<usuario.getBilleteras().getSize();
                i++){

                destino.addItem(
                        usuario.getBilleteras()
                                .obtener(i));
            }

            JTextField monto =
                    new JTextField();

            Object[] campos = {

                    "Destino",
                    destino,

                    "Monto",
                    monto
            };

            if(JOptionPane.showConfirmDialog(
                    this,
                    campos,
                    "Recarga",
                    JOptionPane.OK_CANCEL_OPTION)

                    == JOptionPane.OK_OPTION){

                fintech.recargar(

                        ((Billetera)
                                destino
                                        .getSelectedItem())
                                .getCodigo(),

                        Double.parseDouble(
                                monto.getText()));
            }
        });

        retiro.addActionListener(e -> {

            JComboBox<Billetera> origen =
                    new JComboBox<>();

            for(int i=0;
                i<usuario.getBilleteras().getSize();
                i++){

                origen.addItem(
                        usuario.getBilleteras()
                                .obtener(i));
            }

            JTextField monto =
                    new JTextField();

            Object[] campos = {

                    "Origen",
                    origen,

                    "Monto",
                    monto
            };

            if(JOptionPane.showConfirmDialog(
                    this,
                    campos,
                    "Retiro",
                    JOptionPane.OK_CANCEL_OPTION)

                    == JOptionPane.OK_OPTION){

                fintech.retirar(

                        ((Billetera)
                                origen
                                        .getSelectedItem())
                                .getCodigo(),

                        Double.parseDouble(
                                monto.getText()));
            }
        });

        transferencia.addActionListener(e -> {

            JComboBox<Billetera> origen =
                    new JComboBox<>();

            for(int i=0;
                i<usuario.getBilleteras().getSize();
                i++){

                origen.addItem(
                        usuario.getBilleteras()
                                .obtener(i));
            }

            JTextField destino =
                    new JTextField();

            JTextField monto =
                    new JTextField();

            Object[] campos = {

                    "Origen",
                    origen,

                    "Destino",
                    destino,

                    "Monto",
                    monto
            };

            if(JOptionPane.showConfirmDialog(
                    this,
                    campos,
                    "Transferencia",
                    JOptionPane.OK_CANCEL_OPTION)

                    == JOptionPane.OK_OPTION){

                fintech.transferir(

                        ((Billetera)
                                origen
                                        .getSelectedItem())
                                .getCodigo(),

                        destino.getText(),

                        Double.parseDouble(
                                monto.getText()));
            }
        });
    }
}