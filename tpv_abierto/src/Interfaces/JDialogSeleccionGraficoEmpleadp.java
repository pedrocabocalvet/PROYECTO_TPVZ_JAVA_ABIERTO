package Interfaces;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import BaseDeDatos.Consultas;

import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import javax.swing.JComboBox;

public class JDialogSeleccionGraficoEmpleadp extends JDialog implements ActionListener {
	JDialogHistoricoGraficos jdgrafico;
	JComboBox comboBoxEmpleado1,comboBoxEmpleado2; //ComboBox para elegir los empleados
	Consultas con;
	Integer empleado1,empleado2; //Variables en las que guardaremos el index del empleado introducido
	String fechaIn,fechaFin;
	JFormattedTextField formattedTextFechaInicial;//Aqui introduciremos la fecha
	JFormattedTextField formattedTextFechaFinal;//Aqui introduciremos la fecha
	JPanel elPanellGrafic;
	
	private final JPanel contentPanel = new JPanel();


	/**
	 * Create the dialog.
	 * @throws ParseException 
	 */
	public JDialogSeleccionGraficoEmpleadp(JPanel epg) throws ParseException {
		con=new Consultas();
		elPanellGrafic = epg;
		jdgrafico=new JDialogHistoricoGraficos();
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 682, 319);
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
			JLabel lblFechaInicial = new JLabel("Fecha Inicial");
			GridBagConstraints gbc_lblFechaInicial = new GridBagConstraints();
			gbc_lblFechaInicial.anchor = GridBagConstraints.EAST;
			gbc_lblFechaInicial.insets = new Insets(0, 0, 5, 5);
			gbc_lblFechaInicial.gridx = 0;
			gbc_lblFechaInicial.gridy = 1;
			contentPanel.add(lblFechaInicial, gbc_lblFechaInicial);
		}
		{
			MaskFormatter fecha= new MaskFormatter("##-##-####");
			 formattedTextFechaInicial = new JFormattedTextField(fecha);
			GridBagConstraints gbc_formattedTextFechaInicial = new GridBagConstraints();
			gbc_formattedTextFechaInicial.gridwidth = 5;
			gbc_formattedTextFechaInicial.insets = new Insets(0, 0, 5, 0);
			gbc_formattedTextFechaInicial.fill = GridBagConstraints.HORIZONTAL;
			gbc_formattedTextFechaInicial.gridx = 2;
			gbc_formattedTextFechaInicial.gridy = 1;
			contentPanel.add(formattedTextFechaInicial, gbc_formattedTextFechaInicial);
		}
		{
			JLabel lblFechaFinal = new JLabel("Fecha Final");
			GridBagConstraints gbc_lblFechaFinal = new GridBagConstraints();
			gbc_lblFechaFinal.insets = new Insets(0, 0, 5, 5);
			gbc_lblFechaFinal.gridx = 0;
			gbc_lblFechaFinal.gridy = 3;
			contentPanel.add(lblFechaFinal, gbc_lblFechaFinal);
		}
		{
			MaskFormatter fecha= new MaskFormatter("##-##-####");
			 formattedTextFechaFinal = new JFormattedTextField(fecha);
			GridBagConstraints gbc_formattedTextFechaFinal = new GridBagConstraints();
			gbc_formattedTextFechaFinal.gridwidth = 5;
			gbc_formattedTextFechaFinal.insets = new Insets(0, 0, 0, 5);
			gbc_formattedTextFechaFinal.fill = GridBagConstraints.HORIZONTAL;
			gbc_formattedTextFechaFinal.gridx = 2;
			gbc_formattedTextFechaFinal.gridy = 3;
			contentPanel.add(formattedTextFechaFinal, gbc_formattedTextFechaFinal);
		}
		{
			JLabel lblNewLabel = new JLabel("Empleado");
			GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
			gbc_lblNewLabel.insets = new Insets(0, 0, 0, 5);
			gbc_lblNewLabel.gridx = 0;
			gbc_lblNewLabel.gridy = 5;
			contentPanel.add(lblNewLabel, gbc_lblNewLabel);
		}
		{
			 comboBoxEmpleado1 = new JComboBox();
			llenarComboBox(comboBoxEmpleado1);
			GridBagConstraints gbc_comboBoxEmpleado1 = new GridBagConstraints();
			gbc_comboBoxEmpleado1.insets = new Insets(0, 0, 0, 5);
			gbc_comboBoxEmpleado1.fill = GridBagConstraints.HORIZONTAL;
			gbc_comboBoxEmpleado1.gridx = 2;
			gbc_comboBoxEmpleado1.gridy = 5;
			contentPanel.add(comboBoxEmpleado1, gbc_comboBoxEmpleado1);
		}
		{
			JLabel lblEmpleado = new JLabel("Empleado");
			GridBagConstraints gbc_lblEmpleado = new GridBagConstraints();
			gbc_lblEmpleado.insets = new Insets(0, 0, 0, 5);
			gbc_lblEmpleado.gridx = 4;
			gbc_lblEmpleado.gridy = 5;
			contentPanel.add(lblEmpleado, gbc_lblEmpleado);
		}
		{
			 comboBoxEmpleado2 = new JComboBox();
			llenarComboBox(comboBoxEmpleado2);
			GridBagConstraints gbc_comboBoxEmpleado2 = new GridBagConstraints();
			gbc_comboBoxEmpleado2.fill = GridBagConstraints.HORIZONTAL;
			gbc_comboBoxEmpleado2.gridx = 6;
			gbc_comboBoxEmpleado2.gridy = 5;
			contentPanel.add(comboBoxEmpleado2, gbc_comboBoxEmpleado2);
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
	
	//Metodo para llenar el comboBox con los empleados que hay
	public void llenarComboBox(JComboBox c){
		String empleados[]= con.consultaEmpleados();
		for(int i=0;i<empleados.length;i++){
			c.addItem(empleados[i]);
		}
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand()=="OK"){
			//Aqui guardamos los dos valores introducidos en el combobox
			 empleado1= comboBoxEmpleado1.getSelectedIndex()+1;
			 empleado2= comboBoxEmpleado2.getSelectedIndex()+1;
			//Cogemos el texto introducido y lo guardamos en las variables
			 fechaIn=formattedTextFechaInicial.getText();
			 fechaFin=formattedTextFechaFinal.getText();
			//Llamamos al metodo que crear el JTable
			jdgrafico.crearGraficoEmpleados(elPanellGrafic,fechaIn,fechaFin,empleado1,empleado2);
			dispose();
		}
		if(e.getActionCommand()=="Cancel"){
			dispose();
		}
		
	}

	

	public Integer getEmpleado1() {
		return empleado1;
	}

	public Integer getEmpleado2() {
		return empleado2;
	}

	public String getFechaIn() {
		return fechaIn;
	}

	public String getFechaFin() {
		return fechaFin;
	}
	
	

}
