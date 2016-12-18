package Interfaces;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import BaseDeDatos.Consultas;
import javax.swing.JFormattedTextField;

public class JDialogSeleccionTablaEmpleado extends JDialog implements ActionListener {
	JDialogHistoricoTablas jdtablas;
	JComboBox comboBoxEmpleado1;
	Consultas con;
	String fechan;//Aqui guardaremos la fecha
	Integer empleado1;
	JFormattedTextField formattedTextFecha;//Aqui recibiremos la fehca
	JPanel elPanellGrafic;
	
	private final JPanel contentPanel = new JPanel();


	/**
	 * Create the dialog.
	 * @throws ParseException 
	 */
	public JDialogSeleccionTablaEmpleado(JPanel epg) throws ParseException {
		con=new Consultas();
		elPanellGrafic=epg;
		jdtablas=new JDialogHistoricoTablas();
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 648, 280);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblFecha = new JLabel("Fecha");
			GridBagConstraints gbc_lblFecha = new GridBagConstraints();
			gbc_lblFecha.anchor = GridBagConstraints.WEST;
			gbc_lblFecha.insets = new Insets(0, 0, 5, 5);
			gbc_lblFecha.gridx = 0;
			gbc_lblFecha.gridy = 1;
			contentPanel.add(lblFecha, gbc_lblFecha);
		}
		{
			MaskFormatter fecha= new MaskFormatter("##-##-####");
			formattedTextFecha = new JFormattedTextField(fecha);
			GridBagConstraints gbc_formattedTextFecha = new GridBagConstraints();
			gbc_formattedTextFecha.insets = new Insets(0, 0, 5, 5);
			gbc_formattedTextFecha.fill = GridBagConstraints.HORIZONTAL;
			gbc_formattedTextFecha.gridx = 2;
			gbc_formattedTextFecha.gridy = 1;
			contentPanel.add(formattedTextFecha, gbc_formattedTextFecha);
		}
		{
			JLabel lblNewLabel = new JLabel("Empleado");
			GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
			gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel.gridx = 0;
			gbc_lblNewLabel.gridy = 3;
			contentPanel.add(lblNewLabel, gbc_lblNewLabel);
		}
		{
			 comboBoxEmpleado1 = new JComboBox();
			llenarComboBox(comboBoxEmpleado1);
			GridBagConstraints gbc_comboBoxEmpleado1 = new GridBagConstraints();
			gbc_comboBoxEmpleado1.insets = new Insets(0, 0, 5, 5);
			gbc_comboBoxEmpleado1.fill = GridBagConstraints.HORIZONTAL;
			gbc_comboBoxEmpleado1.gridx = 2;
			gbc_comboBoxEmpleado1.gridy = 3;
			contentPanel.add(comboBoxEmpleado1, gbc_comboBoxEmpleado1);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(this);
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(this);
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	//Metodo para llenar el comboBox
	public void llenarComboBox(JComboBox c){
		String empleados[]= con.consultaEmpleados();
		for(int i=0;i<empleados.length;i++){
			c.addItem(empleados[i]);
		}
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand()=="OK"){
			fechan=formattedTextFecha.getText();
			empleado1= comboBoxEmpleado1.getSelectedIndex()+1;
			jdtablas.crearTablaEmpleado(elPanellGrafic, fechan, empleado1);
			jdtablas.setFechaInicial(fechan);
			jdtablas.setOpcion(empleado1);
			jdtablas.setPulsadoArticulo(false);
			jdtablas.setPulsadoDia(false);
			jdtablas.setPulsadoEmpleado(true);
			dispose();
		}
		if(e.getActionCommand()=="Cancel"){
			dispose();
		}
		
	}

	public String getFechan() {
		return fechan;
	}

	public Integer getEmpleado1() {
		return empleado1;
	}
	
}
