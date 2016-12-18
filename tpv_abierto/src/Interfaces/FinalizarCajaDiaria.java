package Interfaces;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import BaseDeDatos.Consultas;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;

public class FinalizarCajaDiaria extends JDialog implements ActionListener {

	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldDineroEmpezarCaja;
	JDialogCajaDiaria caja = new JDialogCajaDiaria();
	private JTextField textFieldBeneficiosDiarios;
	static CurrentDate dia = new CurrentDate();
	static Consultas con = new Consultas();
	static int numeroVentas = con.consultaNumeroVentas();
	static double dineroEnCaja;
	static double dineroGanancias;
	String datos[][];

	/**
	 * Create the dialog.
	 */

	public FinalizarCajaDiaria() {

		setBounds(100, 100, 450, 400);
		getContentPane().setLayout(new BorderLayout());

		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.SOUTH);

		JButton btnNewButton_1 = new JButton("VOLVER");
		btnNewButton_1.addActionListener(this);

		JButton btnAceptar = new JButton("FINALIZAR CAJA");
		btnAceptar.addActionListener(this);
		panel.add(btnAceptar);

		panel.add(btnNewButton_1);

		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);

		JLabel lblNewLabel = new JLabel("LA CANTIDAD DE DINERO");
		contentPanel.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 23));

		JLabel lblNewLabel_1 = new JLabel(" CON LA QUE FINALIZALA CAJA ES DE :");
		contentPanel.add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 23));

		String dinero = String.valueOf(caja.getDineroCaja() + beneficiosCajaDiaria());

		textFieldDineroEmpezarCaja = new JTextField();
		contentPanel.add(textFieldDineroEmpezarCaja);
		textFieldDineroEmpezarCaja.setText(dinero);
		textFieldDineroEmpezarCaja.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textFieldDineroEmpezarCaja.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("\u20AC  EUROS");
		contentPanel.add(lblNewLabel_2);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 16));

		JLabel lblNewLabel_3 = new JLabel("CON UNAS GANANCIAS TOTALES DE :");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 23));
		contentPanel.add(lblNewLabel_3);

		textFieldBeneficiosDiarios = new JTextField();
		textFieldBeneficiosDiarios.setFont(new Font("Tahoma", Font.PLAIN, 18));
		String beneficios = String.valueOf(beneficiosCajaDiaria());
		textFieldBeneficiosDiarios.setText(beneficios);
		contentPanel.add(textFieldBeneficiosDiarios);
		textFieldBeneficiosDiarios.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("\u20AC  EUROS");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 16));
		contentPanel.add(lblNewLabel_4);
	}

	public static double beneficiosCajaDiaria() {
		JDialogCajaDiaria caja = new JDialogCajaDiaria();
		numeroVentas = con.consultaNumeroVentas();
		ResultSet rs = con.consultaBeneficiosDia(dia.currentDate());
		double total;
		double resultado = 0;
		try {
			while (rs.next()) {

				total = rs.getDouble("total");

				resultado = resultado + total;

			}

			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultado;

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getActionCommand() == "VOLVER") {
			dispose();
		}
		if (e.getActionCommand() == "FINALIZAR CAJA") {
			dispose();
			caja.setDineroCaja(0);
			caja.setGanancias(0);

		}
	}
}
