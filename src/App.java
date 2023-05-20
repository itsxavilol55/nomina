import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.temporal.WeekFields;

public class App extends JFrame implements ActionListener, ComponentListener, FocusListener {
    private JLabel labelID, labelNombre, labelAntiguedad, labelTasa, labelSalarioBase, labelTipoContrato, labelPuesto,
            labelHoras, labelFecha, labelPeriodo;
    private JTextField textID, textNombre, textAntiguedad, textTasa, textSalarioBase, textTipoContrato, textPuesto,
            textHoras;
    private JButton botonCalcularSalario, botonMostrarEmpleados, botonIngresarEmpleados;
    private JFrame panelMostrar;
    private InsertaEmpleados panelIngresar;
    private Statement stmt;
    private JTable tabla;
    private DefaultTableModel modelo;
    private JPanel paneltabla;

    public static void main(String[] args) {
        new App();
    }

    public App() {
        super("Nomina de empleados");
        interfaz();
        eventos();
    }

    private void interfaz() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 600);
        setLocationRelativeTo(null);
        setLayout(null);
        setMinimumSize(new Dimension(850, 500));
        paneltabla = new JPanel();
        panelIngresar = new InsertaEmpleados();
        panelMostrar = new JFrame("Mostrar Empleados");
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
        labelFecha = new JLabel("Periodo de fecha:");
        labelPeriodo = new JLabel(fecha());
        // botones
        botonCalcularSalario = new JButton("Calcular salario");
        add(botonCalcularSalario);

        botonMostrarEmpleados = new JButton("Mostrar empleados");
        add(botonMostrarEmpleados);

        botonIngresarEmpleados = new JButton("Añadir empleados");
        add(botonIngresarEmpleados);

        Inicioseccion inicio1 = new Inicioseccion("empresa", this);
        stmt = inicio1.getStmt();

        modelo = new DefaultTableModel(0, 11);
        modelo.setColumnIdentifiers(
                new Object[] { "ID", "Nombre", "Fecha Contratacion", "Celular", "Domicilio",
                        "Puesto", "Tipo Contrato", "Salario Base", "Estado", "Vacaciones",
                        "Email" });
        tabla = new JTable(modelo);
        tabla.setEnabled(false);
        tabla.setRowHeight(30);
        paneltabla.add(new JScrollPane(tabla));
        paneltabla.setLayout(new BoxLayout(paneltabla, 1));

        textNombre.setEnabled(false);
        textAntiguedad.setEnabled(false);
        textSalarioBase.setEnabled(false);
        textTipoContrato.setEnabled(false);
        textPuesto.setEnabled(false);
        textTasa.setEnabled(false);
        panelIngresar.setSize(700, 700);
        panelMostrar.setSize(1100, 600);
        panelIngresar.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        panelMostrar.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        panelIngresar.setResizable(false);
        panelIngresar.setLocationRelativeTo(null);
        panelMostrar.setLocationRelativeTo(null);
        Font font = new Font("Open Sans", Font.PLAIN, 13);
        labelID.setFont(font);
        labelNombre.setFont(font);
        labelAntiguedad.setFont(font);
        labelTasa.setFont(font);
        labelSalarioBase.setFont(font);
        labelTipoContrato.setFont(font);
        labelPuesto.setFont(font);
        labelHoras.setFont(font);
        labelFecha.setFont(font);
        labelPeriodo.setFont(font);
        textID.setFont(font);
        textNombre.setFont(font);
        textAntiguedad.setFont(font);
        textTasa.setFont(font);
        textSalarioBase.setFont(font);
        textTipoContrato.setFont(font);
        textPuesto.setFont(font);
        textHoras.setFont(font);
        botonCalcularSalario.setFont(font);
        botonMostrarEmpleados.setFont(font);
        botonIngresarEmpleados.setFont(font);
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
        add(labelFecha);
        add(labelPeriodo);
        setVisible(true);
        ajustaMedidas();
    }

    private void eventos() {
        addComponentListener(this);
        botonMostrarEmpleados.addActionListener(this);
        botonIngresarEmpleados.addActionListener(this);
        botonCalcularSalario.addActionListener(this);
        textID.addFocusListener(this);
    }

    private String fecha() {
        int numeroSemana = 19; // Número de semana deseado
        int anio = 2022; // Año deseado
        // Obtener la fecha correspondiente al número de semana y año especificados
        LocalDate fecha = LocalDate.now().with(WeekFields.ISO.weekOfYear(), numeroSemana)
                .with(WeekFields.ISO.weekBasedYear(), anio);
        System.out.println("Fecha correspondiente a la semana " + numeroSemana + " del año " + anio + ": " + fecha);

        LocalDate fechaActual = LocalDate.now();
        LocalDate fechaInicial, fechaFinal;
        if (fechaActual.getDayOfMonth() < 15) {
            fechaInicial = fechaActual.withDayOfMonth(15).minusMonths(1);
            int ultimoDiaMes = fechaInicial.lengthOfMonth();
            fechaFinal = fechaInicial.withDayOfMonth(ultimoDiaMes);
        } else {
            fechaInicial = fechaActual.withDayOfMonth(1);
            fechaFinal = fechaActual.withDayOfMonth(15);
        }
        return fechaInicial + " a " + fechaFinal;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == botonCalcularSalario) {
            CalcularSalario();
            return;
        }
        if (e.getSource() == botonIngresarEmpleados) {
            IngresarEmpleados();
            return;
        }
        if (e.getSource() == botonMostrarEmpleados) {
            MostrarEmpleados();
            return;
        }
    }

    private void CalcularSalario() {

    }

    private void IngresarEmpleados() {
        panelIngresar.setVisible(true);
    }

    private void MostrarEmpleados() {
        panelMostrar.add(paneltabla);
        panelMostrar.setVisible(true);
        modelo.setNumRows(0);
        try {
            ResultSet resultSet = stmt.executeQuery(
                    "select e.id,e.Nombre, FechaContratacion, Celular, Domicilio, p.Nombre, TipoContrato, SalarioBase, Estado, SemanasVacaciones, CorreoElectronico from empleado e inner join puesto p on e.idPuesto = p.id");
            while (resultSet.next()) {
                modelo.addRow(new Object[] { resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4), resultSet.getString(5), resultSet.getString(6),
                        resultSet.getString(7), resultSet.getString(8), resultSet.getString(9),
                        resultSet.getString(10), resultSet.getString(11) });
            }
            panelMostrar.revalidate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    @Override
    public void componentResized(ComponentEvent e) {
        ajustaMedidas();
    }

    private void ajustaMedidas() {
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
        labelFecha.setBounds(getAncho(0.05), getAltoo(0.05) + (cont += 35), getAncho(0.1) + 20, 30);
        labelPeriodo.setBounds(getAncho(0.15) + 20, getAltoo(0.05) + cont, getAncho(0.45) - 20, 30);
        botonCalcularSalario.setBounds(getAncho(0.15) + 20, getAltoo(0.15) + cont, getAncho(0.20), 30);
        botonMostrarEmpleados.setBounds(getAncho(0.40) + 20, getAltoo(0.15) + cont, getAncho(0.20), 30);
        botonIngresarEmpleados.setBounds(getAncho(0.65) + 20, getAltoo(0.15) + cont, getAncho(0.20), 30);
        revalidate();
        validate();
        repaint();
        update(this.getGraphics());
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

    @Override
    public void focusGained(FocusEvent e) {

    }

    @Override
    public void focusLost(FocusEvent e) {
        if (e.getSource() == textID) {
            try {
                ResultSet resultSet = stmt.executeQuery(
                        "select e.id,e.Nombre, FechaContratacion, p.Nombre, TipoContrato, " +
                                "SalarioBase,TasaSalario " +
                                "from empleado e inner join puesto p on e.idPuesto = p.id WHERE e.id="
                                + textID.getText());
                resultSet.next();
                textNombre.setText(resultSet.getString(2));
                textAntiguedad.setText(resultSet.getString(3));
                textSalarioBase.setText(resultSet.getString(6));
                textTipoContrato.setText(resultSet.getString(5));
                textPuesto.setText(resultSet.getString(4));
                textTasa.setText(resultSet.getString(7));
                revalidate();
            } catch (SQLException err) {
                JOptionPane.showMessageDialog(null, "Error. No se Encontro el empleado con este ID");
            }
            return;
        }
    }
}