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
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import BaseDeDatos.Consultas;

public class JDialogSeleccionTablaArticulo extends JDialog implements ActionListener {
	JDialogHistoricoTablas jdtablas;
	JComboBox comboBoxArticulo;
	Integer articulo;
	Consultas con;
	String fechaIn,fechaFin; //Aqui guardaremos las fechas introducidas
	JFormattedTextField formattedTextFechaInicial;//Aqui introduciremos la fecha
	JFormattedTextField formattedTextFechaFinal;//Aqui introduciremos la fecha
	JPanel elPanellGrafic;
	
	
	private final JPanel contentPanel = new JPanel();


	/**
	 * Create the dialog.
	 * @throws ParseException 
	 */
	public JDialogSeleccionTablaArticulo(JPanel epg) throws ParseException {
		con=new Consultas();
		elPanellGrafic = epg;
		jdtablas=new JDialogHistoricoTablas();
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
			JLabel lblNewLabel = new JLabel("Articulo");
			GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
			gbc_lblNewLabel.insets = new Insets(0, 0, 0, 5);
			gbc_lblNewLabel.gridx = 0;
			gbc_lblNewLabel.gridy = 5;
			contentPanel.add(lblNewLabel, gbc_lblNewLabel);
		}
		{
			 comboBoxArticulo = new JComboBox();
			llenarComboBox(comboBoxArticulo);
			GridBagConstraints gbc_comboBoxArticulo = new GridBagConstraints();
			gbc_comboBoxArticulo.insets = new Insets(0, 0, 0, 5);
			gbc_comboBoxArticulo.fill = GridBagConstraints.HORIZONTAL;
			gbc_comboBoxArticulo.gridx = 2;
			gbc_comboBoxArticulo.gridy = 5;
			contentPanel.add(comboBoxArticulo, gbc_comboBoxArticulo);
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
		String articulos[]= con.consultaNombreArticulos();
		for(int i=0;i<articulos.length;i++){
			c.addItem(articulos[i]);
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand()=="OK"){
			 articulo= comboBoxArticulo.getSelectedIndex()+1;
			 fechaIn=formattedTextFechaInicial.getText();
			 fechaFin=formattedTextFechaFinal.getText();
			jdtablas.crearTablaArticulos(elPanellGrafic,fechaIn,fechaFin,articulo);
			jdtablas.setFechaInicial(fechaIn);
			jdtablas.setFechaFinal(fechaFin);
			jdtablas.setOpcion(articulo);
			//Aqui
			jdtablas.setPulsadoArticulo(true);
			jdtablas.setPulsadoDia(false);
			jdtablas.setPulsadoEmpleado(false);
			dispose();
		}
		if(e.getActionCommand()=="Cancel"){
			dispose();
		}
		
	}

	public Integer getArticulo() {
		return articulo;
	}

	public String getFechaIn() {
		return fechaIn;
	}

	public String getFechaFin() {
		return fechaFin;
	}
	
	

}
