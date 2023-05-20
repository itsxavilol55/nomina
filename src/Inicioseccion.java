import java.awt.*;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.*;
import java.awt.event.*;

public class Inicioseccion extends JFrame implements ActionListener {
    private JTextField txtuser;
    private JPasswordField txtpassword;
    private JLabel lbluser, lblpw;
    private JButton btningresa;
    private JFrame vista;
    private Statement stmt;
    private String bdname;

    public Inicioseccion(String bd, JFrame vis) {
        vista = vis;
        bdname = bd;
        interfaz();
        controller();
        espera();
    }

    private void interfaz() {
        setSize(400, 300);
        setLayout(null);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        btningresa = new JButton("Ingresar");
        lbluser = new JLabel("Ingresa Usuario");
        lblpw = new JLabel("Ingresa la contraseña");
        txtuser = new JTextField();
        txtpassword = new JPasswordField();
        {
            lbluser.setBounds((int) (getWidth() * 0.1), (int) (getHeight() * 0.1), (int) (getWidth() * 0.8), 30);
            txtuser.setBounds((int) (getWidth() * 0.1), (int) (lbluser.getHeight() + 30), (int) (getWidth() * 0.8), 30);
            lblpw.setBounds((int) (getWidth() * 0.1), (int) (getHeight() * 0.4 - 30), (int) (getWidth() * 0.8), 30);
            txtpassword.setBounds((int) (getWidth() * 0.1), (int) (getHeight() * 0.4), (int) (getWidth() * 0.8), 30);
            btningresa.setBounds((int) (getWidth() * 0.35), (int) (getHeight() * 0.6), (int) (getWidth() * 0.30), 30);
        }
        add(lbluser);
        add(txtuser);
        add(lblpw);
        add(txtpassword);
        add(btningresa);
        setVisible(true);
    }

    private void controller() {
        btningresa.addActionListener(this);
    }

    // este metodo recursivo espera la respuesta del usuario y no para hasta tener
    // un usuario valido
    /**
     * If the statement is null, wait 500 milliseconds and then check again.
     */
    private void espera() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (stmt == null)
            espera();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        conexion c = new conexion(bdname, txtuser.getText(), txtpassword.getText());
        if (c.getStmt() != null) {
            stmt = c.getStmt();
            while (c.getStmt() == null) {
                // Espera activa hasta que el stmt no sea nulo
            }
            setVisible(false);
            vista.setVisible(true);
            vista.revalidate();
            vista.validate();
            vista.repaint();
            vista.update(vista.getGraphics());
        }
    }

    public Statement getStmt() {
        return stmt;
    }
}
