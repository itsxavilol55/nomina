import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.time.temporal.WeekFields;
import java.util.ArrayList;

public class App extends JFrame implements ActionListener, ComponentListener, FocusListener {
    private JLabel labelID, labelNombre, labelAntiguedad, labelTasa, labelSalarioBase, labelTipoContrato, labelPuesto,
            labelHoras, labelFecha, labelPeriodo, labelFestivos;
    private JTextField textID, textNombre, textAntiguedad, textTasa, textSalarioBase, textTipoContrato, textPuesto,
            textHoras, textFestivos;
    private JButton botonCalcularSalario, botonMostrarEmpleados, botonIngresarEmpleados;
    private JFrame panelMostrar;
    private InsertaEmpleados panelIngresar;
    private Statement stmt;
    private JTable tabla;
    private DefaultTableModel modelo;
    private JPanel paneltabla;
    private String vacaciones, antiguedadEmp;
    private LocalDate fechaInicial, fechaFinal;
    private ArrayList<LocalDate> dates = new ArrayList<>();
    private int year;

    public static void main(String[] args) {
        new App();
    }

    public App() {
        super("Nomina de empleados");
        year = LocalDate.now().getYear();
        dates.add(LocalDate.of(year, 3, 15));
        dates.add(LocalDate.of(year, 6, 15));
        dates.add(LocalDate.of(year, 9, 15));
        dates.add(LocalDate.of(year, 12, 15));
        interfaz();
        eventos();
    }

    private void interfaz() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setLayout(null);
        setMinimumSize(new Dimension(1000, 500));
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
        labelFestivos = new JLabel("Festivos trabajados:");
        textFestivos = new JTextField(20);
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
        panelIngresar.setStmt(stmt);
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
        textFestivos.setText("0");
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
        labelFestivos.setFont(font);
        textID.setFont(font);
        textNombre.setFont(font);
        textAntiguedad.setFont(font);
        textTasa.setFont(font);
        textSalarioBase.setFont(font);
        textTipoContrato.setFont(font);
        textPuesto.setFont(font);
        textHoras.setFont(font);
        textFestivos.setFont(font);
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
        add(textFestivos);
        add(labelFestivos);
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
        LocalDate fechaActual = LocalDate.now();
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
            try {
                calcularSalario();
            } catch (Exception err) {
                JOptionPane.showMessageDialog(null, "Hay algun dato incorrecto");
                System.err.println(err);
            }
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

    private void calcularSalario() {
        float salarioBase = Float.parseFloat(textSalarioBase.getText()) / 2;
        // Se divide el salario base entre 2 para que sea por quincena
        float tasaSalario = Float.parseFloat(textTasa.getText());
        int horasTrabajadas = Integer.parseInt(textHoras.getText());
        int festivosTrabajados = Integer.parseInt(textFestivos.getText());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        int antiguedad = year - LocalDate.parse(antiguedadEmp, formatter).getYear();
        // Se calcula la antigüedad del empleado
        double sueldoTotal = salarioBase + (tasaSalario * horasTrabajadas);
        // Sueldo base más la tasa de salario por horas trabajadas
        String[] semanas = vacaciones.split(","); // Se obtienen las semanas de vacaciones del empleado
        for (String semana : semanas) {
            LocalDate fecha = LocalDate.now()
                    .with(WeekFields.ISO.weekOfYear(), Integer.parseInt(semana))
                    .with(WeekFields.ISO.weekBasedYear(), year);
            if (fecha.isAfter(fechaInicial) && fecha.isBefore(fechaFinal))
                sueldoTotal += antiguedad * 0.1 * salarioBase; // Se calcula el bono de vacaciones si corresponde
        }
        sueldoTotal = sueldoTotal + (festivosTrabajados * (salarioBase / 14) * 3);
        // Se agrega el pago por festivos trabajados
        if (dates.contains(fechaFinal))// se paga el bonus trimestral
            sueldoTotal += salarioBase * 0.1;
        if (fechaFinal.equals(LocalDate.of(year, 12, 15)))// se paga el bonus anual
            sueldoTotal += salarioBase * 3;
        // impuestos
        sueldoTotal = sueldoTotal - (sueldoTotal * 0.12);// Impuesto Sobre la Renta 12%
        sueldoTotal = sueldoTotal - (sueldoTotal * 0.067);// pago la seguridad social 6.7%
        JOptionPane.showMessageDialog(null, "El sueldo total es: " + sueldoTotal);
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
    public void focusLost(FocusEvent e) {
        if (e.getSource() == textID && textID.getText().length() > 0) {
            try {
                ResultSet resultSet = stmt.executeQuery(
                        "select e.id,e.Nombre, FechaContratacion, p.Nombre, TipoContrato, " +
                                "SalarioBase,TasaSalario, semanasVacaciones " +
                                "from empleado e inner join puesto p on e.idPuesto = p.id WHERE e.id="
                                + textID.getText());
                resultSet.next();
                textNombre.setText(resultSet.getString(2));
                textAntiguedad.setText(resultSet.getString(3));
                antiguedadEmp = resultSet.getString(3);
                textSalarioBase.setText("" + resultSet.getInt(6));
                textTipoContrato.setText(resultSet.getString(5));
                textPuesto.setText(resultSet.getString(4));
                textTasa.setText("" + resultSet.getInt(7));
                vacaciones = resultSet.getString(8);
                revalidate();
            } catch (SQLException err) {
                JOptionPane.showMessageDialog(null, "Error. No se Encontro el empleado con este ID");
                System.err.println(err);
            }
            return;
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
        labelFestivos.setBounds(getAncho(0.05), getAltoo(0.05) + (cont += 35), getAncho(0.1) + 20, 30);
        textFestivos.setBounds(getAncho(0.15) + 20, getAltoo(0.05) + cont, getAncho(0.45) - 20, 30);
        labelFecha.setBounds(getAncho(0.05), getAltoo(0.05) + (cont += 35), getAncho(0.1) + 20, 30);
        labelPeriodo.setBounds(getAncho(0.15) + 20, getAltoo(0.05) + cont, getAncho(0.45) - 20, 30);
        botonCalcularSalario.setBounds(getAncho(0.15) + 20, getAltoo(0.15) + cont, getAncho(0.20), 30);
        botonMostrarEmpleados.setBounds(getAncho(0.40) + 20, getAltoo(0.15) + cont, getAncho(0.20), 30);
        botonIngresarEmpleados.setBounds(getAncho(0.65) + 20, getAltoo(0.15) + cont, getAncho(0.20), 30);
        revalidate();
        validate();
        repaint();
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
}