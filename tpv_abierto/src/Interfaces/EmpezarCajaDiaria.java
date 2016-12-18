package Interfaces;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;

public class EmpezarCajaDiaria extends JDialog implements ActionListener {

	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldDineroEmpezarCaja;
	JDialogCajaDiaria caja = new JDialogCajaDiaria();

	/**
	 * Create the dialog.
	 */

	public EmpezarCajaDiaria() {
		setBounds(100, 100, 450, 400);
		getContentPane().setLayout(new BorderLayout());

		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.SOUTH);

		JLabel lblNewLabel_2 = new JLabel("DINERO : ");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel.add(lblNewLabel_2);

		textFieldDineroEmpezarCaja = new JTextField();
		textFieldDineroEmpezarCaja.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panel.add(textFieldDineroEmpezarCaja);
		textFieldDineroEmpezarCaja.setColumns(10);

		JButton btnNewButton = new JButton("ACEPTAR");
		btnNewButton.addActionListener(this);
		panel.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("CANCELAR");
		btnNewButton_1.addActionListener(this);

		panel.add(btnNewButton_1);

		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);

		JLabel lblNewLabel = new JLabel("INTRODUCE LA CANTIDAD DE DINERO");
		contentPanel.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 23));

		JLabel lblNewLabel_1 = new JLabel(" CON LA QUE EMPIEZA LA CAJA");
		contentPanel.add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 23));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() == "ACEPTAR") {
			String dinero = textFieldDineroEmpezarCaja.getText();
			if (!dinero.equals(null)) {
				caja.setDineroCaja(Integer.parseInt(dinero));
				dispose();
			}
		}
		if (e.getActionCommand() == "CANCELAR") {
			dispose();
		}
	}
}
