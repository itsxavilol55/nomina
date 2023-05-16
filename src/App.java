import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class App extends JFrame implements ActionListener, ComponentListener {
    private JLabel labelID, labelNombre, labelAntiguedad, labelTasa, labelSalarioBase, labelTipoContrato, labelPuesto,
            labelHoras;
    private JTextField textID, textNombre, textAntiguedad, textTasa, textSalarioBase, textTipoContrato, textPuesto,
            textHoras;
    private JButton botonCalcularSalario, botonMostrarEmpleados, botonIngresarEmpleados;

    public static void main(String[] args) {
        new App();
    }

    public App() {
        super("Nomina de empleados");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 600);
        setLocationRelativeTo(null);
        setLayout(null);
        setMinimumSize(new Dimension(600, 500));
        addComponentListener(this);
        labelID = new JLabel("Ingresa el Id");
        textID = new JTextField();
        labelNombre = new JLabel("Nombre:");
        textNombre = new JTextField();
        labelAntiguedad = new JLabel("Antigüedad:");
        textAntiguedad = new JTextField(10);
        labelSalarioBase = new JLabel("Salario base:");
        textSalarioBase = new JTextField(10);
        labelTipoContrato = new JLabel("Tipo de contrato:");
        textTipoContrato = new JTextField(20);
        labelPuesto = new JLabel("Puesto:");
        textPuesto = new JTextField(20);
        labelTasa = new JLabel("Tasa salarial:");
        textTasa = new JTextField(30);
        labelHoras = new JLabel("Horas Trabajadas:");
        textHoras = new JTextField(20);

        // botones
        botonCalcularSalario = new JButton("Calcular salario");
        add(botonCalcularSalario);
        botonCalcularSalario.addActionListener(this);

        botonMostrarEmpleados = new JButton("Mostrar empleados");
        add(botonMostrarEmpleados);
        botonMostrarEmpleados.addActionListener(this);

        botonIngresarEmpleados = new JButton("Añadir empleados");
        add(botonIngresarEmpleados);
        botonIngresarEmpleados.addActionListener(this);

        textNombre.setEnabled(false);
        textAntiguedad.setEnabled(false);
        textSalarioBase.setEnabled(false);
        textTipoContrato.setEnabled(false);
        textPuesto.setEnabled(false);
        textTasa.setEnabled(false);
        add(labelID);
        add(textID);
        add(labelNombre);
        add(textNombre);
        add(labelAntiguedad);
        add(textAntiguedad);
        add(labelTasa);
        add(textTasa);
        add(labelSalarioBase);
        add(textSalarioBase);
        add(labelTipoContrato);
        add(textTipoContrato);
        add(labelPuesto);
        add(textPuesto);
        add(labelHoras);
        add(textHoras);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        // if (e.getSource() == botonGuardarEmpleado) {
        // // Aquí se guardaría la información del empleado en la base de datos
        // // utilizando los valores en los campos de texto
        // } else if (e.getSource() == botonGuardarHoras) {
        // // Aquí se guardaría el registro de horas trabajadas del empleado en la
        // // base de datos utilizando los valores en los campos de texto
        // } else if (e.getSource() == botonCalcularSalario) {
        // // Aquí se calcularía el salario bruto del empleado utilizando los valores
        // // en los campos de texto y se mostraría en un cuadro de diálogo
        // int salarioBase = Integer.parseInt(textSalarioBase.getText());
        // int tasaSalario = Integer.parseInt(textPuesto.getText());
        // int horasTrabajadas = Integer.parseInt(textHoras.getText());
        // int salarioBruto = salarioBase + (tasaSalario * horasTrabajadas);
        // JOptionPane.showMessageDialog(this, "El salario bruto del empleado es: " +
        // salarioBruto);
        // } else if (e.getSource() == botonMostrarEmpleados) {
        // // Aquí se mostrarían los empleados guardados en la base de datos en el área
        // // de texto para que el usuario los pueda ver
        // // Podría ser algo así:
        // // areaTextoEmpleados.setText(""); // Para limpiar el área de texto antes de
        // // mostrar los empleados
        // // String empleados = ""; // Aquí se concatenarían los datos de los empleados
        // // while (/* Recorrer la lista de empleados guardados en la base de datos */)
        // {
        // // // Concatenar los datos del empleado actual a la variable "empleados"
        // // }
        // // areaTextoEmpleados.setText(empleados);
        // }
    }

    @Override
    public void componentResized(ComponentEvent e) {
        int cont = 0;
        labelID.setBounds(getAncho(0.05), getAltoo(0.05), getAncho(0.1) + 20, 30);
        textID.setBounds(getAncho(0.15) + 20, getAltoo(0.05), getAncho(0.25) - 20, 30);
        labelNombre.setBounds(getAncho(0.05), getAltoo(0.05) + (cont += 35), getAncho(0.1) + 20, 30);
        textNombre.setBounds(getAncho(0.15) + 20, getAltoo(0.05) + cont, getAncho(0.45) - 20, 30);
        labelAntiguedad.setBounds(getAncho(0.05), getAltoo(0.05) + (cont += 35), getAncho(0.1) + 20, 30);
        textAntiguedad.setBounds(getAncho(0.15) + 20, getAltoo(0.05) + cont, getAncho(0.45) - 20, 30);
        labelSalarioBase.setBounds(getAncho(0.05), getAltoo(0.05) + (cont += 35), getAncho(0.1) + 20, 30);
        textSalarioBase.setBounds(getAncho(0.15) + 20, getAltoo(0.05) + cont, getAncho(0.45) - 20, 30);
        labelTipoContrato.setBounds(getAncho(0.05), getAltoo(0.05) + (cont += 35), getAncho(0.1) + 20, 30);
        textTipoContrato.setBounds(getAncho(0.15) + 20, getAltoo(0.05) + cont, getAncho(0.45) - 20, 30);
        labelPuesto.setBounds(getAncho(0.05), getAltoo(0.05) + (cont += 35), getAncho(0.1) + 20, 30);
        textPuesto.setBounds(getAncho(0.15) + 20, getAltoo(0.05) + cont, getAncho(0.45) - 20, 30);
        labelTasa.setBounds(getAncho(0.05), getAltoo(0.05) + (cont += 35), getAncho(0.1) + 20, 30);
        textTasa.setBounds(getAncho(0.15) + 20, getAltoo(0.05) + cont, getAncho(0.45) - 20, 30);
        labelHoras.setBounds(getAncho(0.05), getAltoo(0.05) + (cont += 35), getAncho(0.1) + 20, 30);
        textHoras.setBounds(getAncho(0.15) + 20, getAltoo(0.05) + cont, getAncho(0.45) - 20, 30);
        botonCalcularSalario.setBounds(getAncho(0.15) + 20, getAltoo(0.15) + cont, getAncho(0.20), 30);
        botonMostrarEmpleados.setBounds(getAncho(0.40) + 20, getAltoo(0.15) + cont, getAncho(0.20), 30);
        botonIngresarEmpleados.setBounds(getAncho(0.65) + 20, getAltoo(0.15) + cont, getAncho(0.20), 30);
        revalidate();
    }

    private int getAncho(double d) {
        return (int) (getWidth() * d);
    }

    private int getAltoo(double d) {
        return (int) (getWidth() * d);
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
}