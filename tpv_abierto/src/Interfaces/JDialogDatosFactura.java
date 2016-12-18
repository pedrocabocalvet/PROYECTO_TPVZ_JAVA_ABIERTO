package Interfaces;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Objetos.Mesa;
import Reportes.DJiTextFactura;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JDialogDatosFactura extends JDialog {

	private final JPanel contentPanel = new JPanel();
	Utilidades util;
	private JTextField textFieldNombre;
	private JTextField textFieldApellidos;
	private JTextField textFieldCalle;
	private JTextField textFieldPoblacion;
	private JTextField textFieldCodigoPostal;
	private JTextField textFieldProvincia;
	private JTextField textFieldTelefono;
	private JTextField textFieldNif;
	Mesa mesa;
	
	boolean salir;	// semaforo para comprobar que esten todos los campos puestos


	public JDialogDatosFactura(Mesa mesa) {
		this.mesa = mesa;
		util = new Utilidades();
		
		setBounds(util.redimensionarSegunPantallaAncho(30), util.redimensionarSegunPantallaAlto(20)
				, util.redimensionarSegunPantallaAncho(28), util.redimensionarSegunPantallaAlto(39));
		
		setModal(true);		// hace que no se puede acceder a ninguna ventana hasta que no se cierre esta.
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblNombre = new JLabel("NOMBRE:");
			GridBagConstraints gbc_lblNombre = new GridBagConstraints();
			gbc_lblNombre.insets = new Insets(0, 0, 5, 5);
			gbc_lblNombre.anchor = GridBagConstraints.EAST;
			gbc_lblNombre.gridx = 0;
			gbc_lblNombre.gridy = 0;
			contentPanel.add(lblNombre, gbc_lblNombre);
		}
		{
			textFieldNombre = new JTextField();
			GridBagConstraints gbc_textFieldNombre = new GridBagConstraints();
			gbc_textFieldNombre.insets = new Insets(0, 0, 5, 0);
			gbc_textFieldNombre.fill = GridBagConstraints.HORIZONTAL;
			gbc_textFieldNombre.gridx = 1;
			gbc_textFieldNombre.gridy = 0;
			contentPanel.add(textFieldNombre, gbc_textFieldNombre);
			textFieldNombre.setColumns(10);
		}
		{
			JLabel lblApellidos = new JLabel("APELLIDOS:");
			GridBagConstraints gbc_lblApellidos = new GridBagConstraints();
			gbc_lblApellidos.anchor = GridBagConstraints.EAST;
			gbc_lblApellidos.insets = new Insets(0, 0, 5, 5);
			gbc_lblApellidos.gridx = 0;
			gbc_lblApellidos.gridy = 1;
			contentPanel.add(lblApellidos, gbc_lblApellidos);
		}
		{
			textFieldApellidos = new JTextField();
			GridBagConstraints gbc_textFieldApellidos = new GridBagConstraints();
			gbc_textFieldApellidos.insets = new Insets(0, 0, 5, 0);
			gbc_textFieldApellidos.fill = GridBagConstraints.HORIZONTAL;
			gbc_textFieldApellidos.gridx = 1;
			gbc_textFieldApellidos.gridy = 1;
			contentPanel.add(textFieldApellidos, gbc_textFieldApellidos);
			textFieldApellidos.setColumns(10);
		}
		{
			JLabel lblCalle = new JLabel("CALLE:");
			GridBagConstraints gbc_lblCalle = new GridBagConstraints();
			gbc_lblCalle.anchor = GridBagConstraints.EAST;
			gbc_lblCalle.insets = new Insets(0, 0, 5, 5);
			gbc_lblCalle.gridx = 0;
			gbc_lblCalle.gridy = 2;
			contentPanel.add(lblCalle, gbc_lblCalle);
		}
		{
			textFieldCalle = new JTextField();
			GridBagConstraints gbc_textFieldCalle = new GridBagConstraints();
			gbc_textFieldCalle.insets = new Insets(0, 0, 5, 0);
			gbc_textFieldCalle.fill = GridBagConstraints.HORIZONTAL;
			gbc_textFieldCalle.gridx = 1;
			gbc_textFieldCalle.gridy = 2;
			contentPanel.add(textFieldCalle, gbc_textFieldCalle);
			textFieldCalle.setColumns(10);
		}
		{
			JLabel lblPoblacin = new JLabel("POBLACI\u00D3N:");
			GridBagConstraints gbc_lblPoblacin = new GridBagConstraints();
			gbc_lblPoblacin.anchor = GridBagConstraints.EAST;
			gbc_lblPoblacin.insets = new Insets(0, 0, 5, 5);
			gbc_lblPoblacin.gridx = 0;
			gbc_lblPoblacin.gridy = 3;
			contentPanel.add(lblPoblacin, gbc_lblPoblacin);
		}
		{
			textFieldPoblacion = new JTextField();
			GridBagConstraints gbc_textFieldPoblacion = new GridBagConstraints();
			gbc_textFieldPoblacion.insets = new Insets(0, 0, 5, 0);
			gbc_textFieldPoblacion.fill = GridBagConstraints.HORIZONTAL;
			gbc_textFieldPoblacion.gridx = 1;
			gbc_textFieldPoblacion.gridy = 3;
			contentPanel.add(textFieldPoblacion, gbc_textFieldPoblacion);
			textFieldPoblacion.setColumns(10);
		}
		{
			JLabel lblCp = new JLabel("C.P. :");
			GridBagConstraints gbc_lblCp = new GridBagConstraints();
			gbc_lblCp.anchor = GridBagConstraints.EAST;
			gbc_lblCp.insets = new Insets(0, 0, 5, 5);
			gbc_lblCp.gridx = 0;
			gbc_lblCp.gridy = 4;
			contentPanel.add(lblCp, gbc_lblCp);
		}
		{
			textFieldCodigoPostal = new JTextField();
			GridBagConstraints gbc_textFieldCodigoPostal = new GridBagConstraints();
			gbc_textFieldCodigoPostal.insets = new Insets(0, 0, 5, 0);
			gbc_textFieldCodigoPostal.fill = GridBagConstraints.HORIZONTAL;
			gbc_textFieldCodigoPostal.gridx = 1;
			gbc_textFieldCodigoPostal.gridy = 4;
			contentPanel.add(textFieldCodigoPostal, gbc_textFieldCodigoPostal);
			textFieldCodigoPostal.setColumns(10);
		}
		{
			JLabel lblProvincia = new JLabel("PROVINCIA:");
			GridBagConstraints gbc_lblProvincia = new GridBagConstraints();
			gbc_lblProvincia.anchor = GridBagConstraints.EAST;
			gbc_lblProvincia.insets = new Insets(0, 0, 5, 5);
			gbc_lblProvincia.gridx = 0;
			gbc_lblProvincia.gridy = 5;
			contentPanel.add(lblProvincia, gbc_lblProvincia);
		}
		{
			textFieldProvincia = new JTextField();
			GridBagConstraints gbc_textFieldProvincia = new GridBagConstraints();
			gbc_textFieldProvincia.insets = new Insets(0, 0, 5, 0);
			gbc_textFieldProvincia.fill = GridBagConstraints.HORIZONTAL;
			gbc_textFieldProvincia.gridx = 1;
			gbc_textFieldProvincia.gridy = 5;
			contentPanel.add(textFieldProvincia, gbc_textFieldProvincia);
			textFieldProvincia.setColumns(10);
		}
		{
			JLabel lblTelefono = new JLabel("TELEFONO:");
			GridBagConstraints gbc_lblTelefono = new GridBagConstraints();
			gbc_lblTelefono.anchor = GridBagConstraints.EAST;
			gbc_lblTelefono.insets = new Insets(0, 0, 5, 5);
			gbc_lblTelefono.gridx = 0;
			gbc_lblTelefono.gridy = 6;
			contentPanel.add(lblTelefono, gbc_lblTelefono);
		}
		{
			textFieldTelefono = new JTextField();
			GridBagConstraints gbc_textFieldTelefono = new GridBagConstraints();
			gbc_textFieldTelefono.insets = new Insets(0, 0, 5, 0);
			gbc_textFieldTelefono.fill = GridBagConstraints.HORIZONTAL;
			gbc_textFieldTelefono.gridx = 1;
			gbc_textFieldTelefono.gridy = 6;
			contentPanel.add(textFieldTelefono, gbc_textFieldTelefono);
			textFieldTelefono.setColumns(10);
		}
		{
			JLabel lblNif = new JLabel("NIF:");
			GridBagConstraints gbc_lblNif = new GridBagConstraints();
			gbc_lblNif.anchor = GridBagConstraints.EAST;
			gbc_lblNif.insets = new Insets(0, 0, 0, 5);
			gbc_lblNif.gridx = 0;
			gbc_lblNif.gridy = 7;
			contentPanel.add(lblNif, gbc_lblNif);
		}
		{
			textFieldNif = new JTextField();
			GridBagConstraints gbc_textFieldNif = new GridBagConstraints();
			gbc_textFieldNif.fill = GridBagConstraints.HORIZONTAL;
			gbc_textFieldNif.gridx = 1;
			gbc_textFieldNif.gridy = 7;
			contentPanel.add(textFieldNif, gbc_textFieldNif);
			textFieldNif.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
					if(!textFieldNombre.getText().isEmpty() && !textFieldApellidos.getText().isEmpty() && !textFieldCalle.getText().isEmpty()
						&& !textFieldCodigoPostal.getText().isEmpty() && !textFieldNif.getText().isEmpty()
						&& !textFieldPoblacion.getText().isEmpty() && !textFieldProvincia.getText().isEmpty()
						&& !textFieldTelefono.getText().isEmpty()){	
						
						
						DJiTextFactura dJiTextFactura;
						dJiTextFactura = new DJiTextFactura(mesa,true,true,textFieldNombre.getText(),textFieldApellidos.getText(),
								textFieldCalle.getText(),textFieldCodigoPostal.getText(),textFieldNif.getText(),
								textFieldPoblacion.getText(), textFieldProvincia.getText(), textFieldTelefono.getText());
						dispose();
					}
					else
						JOptionPane.showMessageDialog(
								   null,
								   "FALTAN DATOS POR RELLENAR");
					}
					
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
