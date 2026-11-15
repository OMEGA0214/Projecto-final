package Vista;

import Modelo.Fintech;
import Modelo.Usuario;

import javax.swing.*;
import java.awt.*;

public class PantallaInicio extends JFrame {

    private Fintech fintech;

    public PantallaInicio(Fintech fintech) {

        this.fintech = fintech;

        setTitle("Fintech");
        setSize(700, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel principal =
                new JPanel(
                        new GridLayout(
                                1,
                                2,
                                20,
                                20));

        //---------------- REGISTRO ----------------

        JPanel registro = new JPanel();
        registro.setLayout(
                new BoxLayout(
                        registro,
                        BoxLayout.Y_AXIS));

        JLabel tituloRegistro =
                new JLabel("Registro");

        tituloRegistro.setAlignmentX(
                Component.CENTER_ALIGNMENT);

        JTextField txtId =
                new JTextField();

        JTextField txtNombre =
                new JTextField();

        JTextField txtCorreo =
                new JTextField();

        txtId.setMaximumSize(
                new Dimension(220, 25));

        txtNombre.setMaximumSize(
                new Dimension(220, 25));

        txtCorreo.setMaximumSize(
                new Dimension(220, 25));

        JButton btnRegistrar =
                new JButton("Registrar");

        btnRegistrar.setAlignmentX(
                Component.CENTER_ALIGNMENT);

        registro.add(Box.createVerticalStrut(20));

        registro.add(tituloRegistro);

        registro.add(Box.createVerticalStrut(15));

        registro.add(new JLabel("ID"));
        registro.add(txtId);

        registro.add(Box.createVerticalStrut(10));

        registro.add(new JLabel("Nombre"));
        registro.add(txtNombre);

        registro.add(Box.createVerticalStrut(10));

        registro.add(new JLabel("Correo"));
        registro.add(txtCorreo);

        registro.add(Box.createVerticalStrut(15));

        registro.add(btnRegistrar);

        //---------------- LOGIN ----------------

        JPanel login = new JPanel();

        login.setLayout(
                new BoxLayout(
                        login,
                        BoxLayout.Y_AXIS));

        JLabel tituloLogin =
                new JLabel("Ingresar");

        tituloLogin.setAlignmentX(
                Component.CENTER_ALIGNMENT);

        JTextField txtLogin =
                new JTextField();

        txtLogin.setMaximumSize(
                new Dimension(220, 25));

        JButton btnIngresar =
                new JButton("Ingresar");

        btnIngresar.setAlignmentX(
                Component.CENTER_ALIGNMENT);

        login.add(Box.createVerticalStrut(20));

        login.add(tituloLogin);

        login.add(Box.createVerticalStrut(15));

        login.add(new JLabel("ID"));
        login.add(txtLogin);

        login.add(Box.createVerticalStrut(15));

        login.add(btnIngresar);

        principal.add(registro);
        principal.add(login);

        add(principal);

        //---------------- EVENTOS ----------------

        btnRegistrar.addActionListener(e -> {

            if (fintech.buscarUsuario(
                    txtId.getText()) != null) {

                JOptionPane.showMessageDialog(
                        this,
                        "Ya existe un usuario con ese ID");

                return;
            }

            Usuario usuario =
                    new Usuario(
                            txtId.getText(),
                            txtNombre.getText(),
                            txtCorreo.getText());

            fintech.registrarUsuario(usuario);

            JOptionPane.showMessageDialog(
                    this,
                    "Usuario registrado correctamente");
        });

        btnIngresar.addActionListener(e -> {

            Usuario usuario =
                    fintech.buscarUsuario(
                            txtLogin.getText());

            if (usuario == null) {

                JOptionPane.showMessageDialog(
                        this,
                        "Usuario no encontrado");

                return;
            }

            new PanelUsuarios(
                    fintech,
                    usuario);

            dispose();
        });

        setVisible(true);
    }
}