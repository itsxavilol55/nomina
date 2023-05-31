import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class InsertaEmpleados extends JFrame implements ActionListener, ComponentListener {
    private JLabel labelNombre, labelFechaContratacion, labelCelular, labelDomicilio, labelIdPuesto,
            labelTipoContrato, labelSalarioBase, labelEstado, labelSemanasVacaciones, labelCorreoElectronico;
    private JTextField textNombre, textFechaContratacion, textCelular, textDomicilio, textIdPuesto,
            textTipoContrato, textSalarioBase, textEstado, textSemanasVacaciones, textCorreoElectronico;
    private JButton botonInsertar;
    private Statement stmt;

    public InsertaEmpleados() {
        setTitle("Insertar Empleado");
        setLayout(null);
        addComponentListener(this);
        labelNombre = new JLabel("Nombre:");
        textNombre = new JTextField();
        labelFechaContratacion = new JLabel("Fecha de Contratación (YYYY-MM-DD):");
        textFechaContratacion = new JTextField();
        labelCelular = new JLabel("Celular:");
        textCelular = new JTextField();
        labelDomicilio = new JLabel("Domicilio:");
        textDomicilio = new JTextField();
        labelIdPuesto = new JLabel("ID Puesto:");
        textIdPuesto = new JTextField();
        labelTipoContrato = new JLabel("Tipo de Contrato:");
        textTipoContrato = new JTextField();
        labelSalarioBase = new JLabel("Salario Base:");
        textSalarioBase = new JTextField();
        labelEstado = new JLabel("Estado:(Activo o Baja)");
        textEstado = new JTextField();
        labelSemanasVacaciones = new JLabel("Semanas de Vacaciones:");
        textSemanasVacaciones = new JTextField();
        labelCorreoElectronico = new JLabel("Correo Electrónico:");
        textCorreoElectronico = new JTextField();
        botonInsertar = new JButton("Insertar");
        botonInsertar.addActionListener(this);
        add(labelNombre);
        add(textNombre);
        add(labelFechaContratacion);
        add(textFechaContratacion);
        add(labelCelular);
        add(textCelular);
        add(labelDomicilio);
        add(textDomicilio);
        add(labelIdPuesto);
        add(textIdPuesto);
        add(labelTipoContrato);
        add(textTipoContrato);
        add(labelSalarioBase);
        add(textSalarioBase);
        add(labelEstado);
        add(textEstado);
        add(labelSemanasVacaciones);
        add(textSemanasVacaciones);
        add(labelCorreoElectronico);
        add(textCorreoElectronico);
        add(botonInsertar);
    }

    @Override
    public void componentResized(ComponentEvent e) {
        labelNombre.setBounds(20, 20, 100, 30);
        textNombre.setBounds(190, 20, 200, 30);

        labelFechaContratacion.setBounds(20, 60, 240, 30);
        textFechaContratacion.setBounds(240, 60, 200, 30);

        labelCelular.setBounds(20, 100, 100, 30);
        textCelular.setBounds(190, 100, 200, 30);

        labelDomicilio.setBounds(20, 140, 100, 30);
        textDomicilio.setBounds(190, 140, 200, 30);

        labelIdPuesto.setBounds(20, 180, 100, 30);
        textIdPuesto.setBounds(190, 180, 200, 30);

        labelTipoContrato.setBounds(20, 220, 100, 30);
        textTipoContrato.setBounds(190, 220, 200, 30);

        labelSalarioBase.setBounds(20, 260, 100, 30);
        textSalarioBase.setBounds(190, 260, 200, 30);

        labelEstado.setBounds(20, 300, 100, 30);
        textEstado.setBounds(190, 300, 200, 30);

        labelSemanasVacaciones.setBounds(20, 340, 160, 30);
        textSemanasVacaciones.setBounds(190, 340, 200, 30);

        labelCorreoElectronico.setBounds(20, 380, 120, 30);
        textCorreoElectronico.setBounds(190, 380, 200, 30);

        botonInsertar.setBounds(20, 420, 100, 30);
    }

    @Override
    public void componentMoved(ComponentEvent e) {
    }

    @Override
    public void componentShown(ComponentEvent e) {

    }

    @Override
    public void componentHidden(ComponentEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String nombre = textNombre.getText();
        String fechaContratacion = textFechaContratacion.getText();
        String celular = textCelular.getText();
        String domicilio = textDomicilio.getText();
        int idPuesto = Integer.parseInt(textIdPuesto.getText());
        String tipoContrato = textTipoContrato.getText();
        float salarioBase = Float.parseFloat(textSalarioBase.getText());
        String estado = textEstado.getText();
        String semanasVacaciones = textSemanasVacaciones.getText();
        String correoElectronico = textCorreoElectronico.getText();
        String sql = "INSERT INTO Empleado  VALUES ('"
                + nombre + "', '" + fechaContratacion + "', '" + celular + "', '" + domicilio + "', " + idPuesto + ", '"
                + tipoContrato + "', " + salarioBase + ", '" + estado + "', '" + semanasVacaciones + "', '"
                + correoElectronico + "')";
        try {
            stmt.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Empleado Insertado Correctamente");
        } catch (SQLException err) {
            JOptionPane.showMessageDialog(null, "Empleado no fue Insertado Correctamente");
        } catch (Exception err) {
            JOptionPane.showMessageDialog(null, "Empleado no fue Insertado Correctamente");
        }
    }

    public void setStmt(Statement stmt) {
        this.stmt = stmt;
    }
}