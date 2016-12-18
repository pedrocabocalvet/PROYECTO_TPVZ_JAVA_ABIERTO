package Interfaces;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.itextpdf.text.log.SysoCounter;

import BaseDeDatos.Consultas;

import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Color;
import java.awt.Component;

@SuppressWarnings("serial")
public class JDialogCajaDiaria extends JDialog implements ActionListener {
	private JTable table;
	private DefaultTableModel dtm;
	FramePrincipal frame;
	Utilidades utilidad;
	static Consultas con = new Consultas();
	static CurrentDate dia = new CurrentDate();
	static int numeroVentas = con.consultaNumeroVentas();
	static public String columnNames[] = { "Fecha", "Hora", "Nº Mesa", "Total"};
	static public String ventas[][] = articulosCajaDiaria();
	static double dineroEnCaja;
	static double dineroGanancias;
	String datos[][];
	

	/**
	 * Create the dialog.
	 */

	public JDialogCajaDiaria() {

		con = new Consultas();
		// this.setUndecorated(true);
		this.setBounds(GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds());
		getContentPane().setLayout(new BorderLayout());

		JPanel buttonPane = new JPanel();
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JButton btnFinalizarCaja = new JButton("VER DINERO CAJA");
		btnFinalizarCaja.setFont(new Font("Tahoma", Font.PLAIN, 23));
		btnFinalizarCaja.addActionListener(this);
		buttonPane.add(btnFinalizarCaja);

		JButton btnAddDinero = new JButton("AÑADIR DINERO");
		btnAddDinero.setFont(new Font("Tahoma", Font.PLAIN, 23));
		btnAddDinero.addActionListener(this);
		buttonPane.add(btnAddDinero);

		JButton backToTerminal = new JButton("VOLVER");
		backToTerminal.setFont(new Font("Tahoma", Font.PLAIN, 23));
		backToTerminal.addActionListener(this);
		backToTerminal.setActionCommand("VOLVER");
		buttonPane.add(backToTerminal);

		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 0, 0 };
		gbl_panel.rowHeights = new int[] { 0, 0, 0 };
		gbl_panel.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		JLabel lblCajaDiaria = new JLabel("CAJA DIARIA");
		lblCajaDiaria.setFont(new Font("Tahoma", Font.PLAIN, 27));
		GridBagConstraints gbc_lblCajaDiaria = new GridBagConstraints();
		gbc_lblCajaDiaria.insets = new Insets(0, 0, 5, 0);
		gbc_lblCajaDiaria.gridx = 0;
		gbc_lblCajaDiaria.gridy = 0;
		panel.add(lblCajaDiaria, gbc_lblCajaDiaria);

		String dataValues[][] = articulosCajaDiaria();
		System.out.println(dataValues);

		dtm = new DefaultTableModel(dataValues, columnNames);
		table = new JTable(dtm);
		table.setFont(new Font("Tahoma", Font.PLAIN, 19));
		JScrollPane sc = new JScrollPane(table);

		GridBagConstraints gbc_table = new GridBagConstraints();
		gbc_table.fill = GridBagConstraints.BOTH;
		gbc_table.gridx = 0;
		gbc_table.gridy = 1;

		panel.add(sc, gbc_table);
	}

	public double getDineroCaja() {
		return dineroEnCaja;
	}

	public void setDineroCaja(double i) {
		dineroEnCaja = i;
	}

	public double getGanancias(){
		return dineroGanancias;
	}
	
	public void setGanancias(double i){
		dineroGanancias = i;
	}
	
	public static String[][] articulosCajaDiaria() {

		numeroVentas = con.consultaNumeroVentas();
		ventas = new String[numeroVentas][columnNames.length];
		ResultSet rs = con.consultaBeneficiosDia(dia.currentDate());
		int filasMatriz = 0;
		Date fecha;
		Time hora;
		double total;
		int no_mesa;
		try {
			while (rs.next()) {

				fecha = rs.getDate("fecha");
				hora = rs.getTime("hora");
				no_mesa = rs.getInt("no_mesa");
				total = rs.getDouble("total");


				String _fecha = String.valueOf(fecha);
				String _hora = String.valueOf(hora);
				String _total = String.valueOf(total);
				String _no_mesa = String.valueOf(no_mesa);


				ventas[filasMatriz][0] = _fecha;
				ventas[filasMatriz][1] = _hora;
				ventas[filasMatriz][2] = _no_mesa;
				ventas[filasMatriz][3] = _total;

				filasMatriz++;

			}

			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return ventas;

	}

	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() == "EMPEZAR CAJA") {
			EmpezarCajaDiaria dialog = new EmpezarCajaDiaria();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		}
		if (e.getActionCommand() == "VER DINERO CAJA") {
			FinalizarCajaDiaria dialog = new FinalizarCajaDiaria();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		}
		if (e.getActionCommand() == "AÑADIR DINERO") {
			AddDineroCajaDiaria dialog = new AddDineroCajaDiaria();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		}
		if (e.getActionCommand() == "VOLVER") {
			dispose();
		}
	}
}
