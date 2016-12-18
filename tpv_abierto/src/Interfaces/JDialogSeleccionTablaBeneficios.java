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

public class JDialogSeleccionTablaBeneficios extends JDialog implements ActionListener {
	JDialogHistoricoTablas jdtablas;
	Consultas con;
	String fechan;
	JFormattedTextField formattedTextFecha;
	JPanel elPanellGrafic;
	
	private final JPanel contentPanel = new JPanel();


	/**
	 * Create the dialog.
	 * @throws ParseException 
	 */
	public JDialogSeleccionTablaBeneficios(JPanel epg) throws ParseException {
		con=new Consultas();
		elPanellGrafic=epg;
		jdtablas= new JDialogHistoricoTablas();
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 504, 175);
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
			gbc_lblFecha.gridy = 3;
			contentPanel.add(lblFecha, gbc_lblFecha);
		}
		{
			MaskFormatter fecha= new MaskFormatter("##-##-####");
			formattedTextFecha = new JFormattedTextField(fecha);
			GridBagConstraints gbc_formattedTextFecha = new GridBagConstraints();
			gbc_formattedTextFecha.gridwidth = 5;
			gbc_formattedTextFecha.insets = new Insets(0, 0, 5, 5);
			gbc_formattedTextFecha.fill = GridBagConstraints.HORIZONTAL;
			gbc_formattedTextFecha.gridx = 2;
			gbc_formattedTextFecha.gridy = 3;
			contentPanel.add(formattedTextFecha, gbc_formattedTextFecha);
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
		if(e.getActionCommand()=="OK"){
			fechan=formattedTextFecha.getText();
			jdtablas.crearTablaDia(elPanellGrafic,fechan);
			jdtablas.setFechaInicial(fechan);
			jdtablas.setPulsadoArticulo(false);
			jdtablas.setPulsadoDia(true);
			jdtablas.setPulsadoEmpleado(false);
			dispose();
		}
		
		if(e.getActionCommand()=="Cancel"){
			dispose();
		}
		
	}



	public String getFechan() {
		return fechan;
	}
	
	
}