package Vista;

import Modelo.Fintech;
import Modelo.Usuario;

import javax.swing.*;
import java.awt.*;

public class PantallaInicio extends JFrame {

    private Fintech fintech;

    public PantallaInicio(Fintech fintech) {


        setTitle("Fintech");
        setSize(700,400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel principal = new JPanel(new GridLayout(1,2));

        //---------------- REGISTRO ----------------

        JPanel registro = new JPanel();
        registro.setLayout(new BoxLayout(registro,
                BoxLayout.Y_AXIS));

        JLabel tituloRegistro =
                new JLabel("Registro");

        JTextField txtId =
                new JTextField(15);

        JTextField txtNombre =
                new JTextField(15);

        JTextField txtCorreo =
                new JTextField(15);

        JButton btnRegistrar =
                new JButton("Registrar");

        registro.add(tituloRegistro);
        registro.add(new JLabel("ID"));
        registro.add(txtId);
        registro.add(new JLabel("Nombre"));
        registro.add(txtNombre);
        registro.add(new JLabel("Correo"));
        registro.add(txtCorreo);
        registro.add(btnRegistrar);

        //---------------- LOGIN ----------------

        JPanel login = new JPanel();
        login.setLayout(new BoxLayout(login,
                BoxLayout.Y_AXIS));

        JLabel tituloLogin =
                new JLabel("Ingresar");

        JTextField txtLogin =
                new JTextField(15);

        JButton btnIngresar =
                new JButton("Ingresar");

        login.add(tituloLogin);
        login.add(new JLabel("ID"));
        login.add(txtLogin);
        login.add(btnIngresar);

        principal.add(registro);
        principal.add(login);

        add(principal);

        //---------------- EVENTOS ----------------

        btnRegistrar.addActionListener(e -> {

            if(fintech.buscarUsuario(
                    txtId.getText()) != null){

                JOptionPane.showMessageDialog(
                        this,
                        "Usuario ya existe");

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
                    "Usuario registrado");
        });

        btnIngresar.addActionListener(e -> {

            Usuario usuario =
                    fintech.buscarUsuario(
                            txtLogin.getText());

            if(usuario == null){

                JOptionPane.showMessageDialog(
                        this,
                        "Usuario no encontrado");

                return;
            }

            new PanelUsuarios(
                    fintech, usuario);

            dispose();
        });

        setVisible(true);
    }
}
