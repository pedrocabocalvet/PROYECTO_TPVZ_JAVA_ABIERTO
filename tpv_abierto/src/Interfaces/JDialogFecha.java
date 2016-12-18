package Interfaces;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.JFormattedTextField;

public class JDialogFecha extends JDialog implements ActionListener{

	private final JPanel contentPanel = new JPanel();
	JDialogHistoricoGraficos jdgrafico;//Declaramos el JDialog para poder gastarlo
	JFormattedTextField formattedTextFechaInicial;//Aqui introduciremos la fecha
	JFormattedTextField formattedTextFechaFinal;//Aqui introduciremos la fecha
	String fechaIn,fechaFin; //Aqui guardaremos las fechas introducidas
	boolean pulsado=false;
	JPanel elPanellGrafic;


	/**
	 * Create the dialog.
	 * @throws ParseException 
	 */
	public JDialogFecha(JPanel epg) throws ParseException {
		elPanellGrafic = epg;
		jdgrafico = new JDialogHistoricoGraficos();
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 470, 234);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
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
			gbc_lblFechaFinal.insets = new Insets(0, 0, 0, 5);
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
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand()== "OK"){
			//Cogemos el texto introducido y lo guardamos en las variables
			fechaIn=formattedTextFechaInicial.getText();
			fechaFin=formattedTextFechaFinal.getText();
			//Llamamos al metodo que crear el JTable
			jdgrafico.crearGraficoBeneficios(elPanellGrafic,fechaIn,fechaFin);
			dispose();
		}
		if(e.getActionCommand()=="Cancel"){
			dispose();
		}
	}


	public boolean isPulsado() {
		return pulsado;
	}

	public String getFechaIn() {
		return fechaIn;
	}

	public String getFechaFin() {
		return fechaFin;
	}


	


}
