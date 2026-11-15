package Vista;

import Modelo.*;

import javax.swing.*;
import java.awt.*;

public class PanelBilleteras extends JPanel {

    private Fintech fintech;
    private Usuario usuario;
    private JTextArea area;

    public PanelBilleteras(Fintech fintech,
                           Usuario usuario) {

        this.fintech = fintech;
        this.usuario = usuario;

        setLayout(new BorderLayout());

        area = new JTextArea();
        area.setEditable(false);

        JButton btnActualizar =
                new JButton("Actualizar");

        btnActualizar.addActionListener(e -> {

            area.setText("");

            for(int i = 0;
                i < usuario.getBilleteras().getSize();
                i++) {

                Billetera b =
                        usuario.getBilleteras()
                                .obtener(i);

                area.append(b + "\n");
            }
        });

        JButton btnCrear =
                new JButton("Crear Billetera");

        btnCrear.addActionListener(e -> {

            JTextField nombre =
                    new JTextField();

            JComboBox<TipoBilletera> tipo =
                    new JComboBox<>(
                            TipoBilletera.values());

            Object[] campos = {
                    "Nombre", nombre,
                    "Tipo", tipo
            };

            int opcion =
                    JOptionPane.showConfirmDialog(
                            this,
                            campos,
                            "Nueva billetera",
                            JOptionPane.OK_CANCEL_OPTION);

            if(opcion ==
                    JOptionPane.OK_OPTION){

                String codigo =
                        "B" +
                                (fintech.getBilleteras()
                                        .size()+1);

                Billetera billetera =
                        new Billetera(nombre.getText(),
                                (TipoBilletera)
                                        tipo.getSelectedItem(),
                                usuario);

                fintech.registrarBilletera(
                        billetera);
            }
        });

        JPanel arriba = new JPanel();

        arriba.add(btnActualizar);
        arriba.add(btnCrear);

        add(arriba, BorderLayout.NORTH);

        add(
                new JScrollPane(area),
                BorderLayout.CENTER);
    }
}