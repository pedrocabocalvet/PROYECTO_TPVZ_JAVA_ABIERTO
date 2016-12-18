package Interfaces;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Component;
import java.awt.Point;
import java.awt.Insets;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import javax.swing.SwingConstants;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.ComponentOrientation;

public class JDialogConfiguracion extends JDialog implements ActionListener {

	private final JPanel contentPanel = new JPanel();
	PanelMedio pm;
	JDialogCrearUsuario jdusuario;
	// estos dos ficheros son para guardar las properties
	Properties misProps;
	FileOutputStream os;

	public JDialogConfiguracion(PanelMedio pm) {
		
		
		
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setVisible(true);
		this.pm = pm;
		setBounds(100, 100, 450, 300);
		setTitle("CONFIGURACION");
		contentPanel.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		// no reajustable el tamaño
		this.setResizable(false);
		// se localiza en el centro de la pantalla
		this.setLocationRelativeTo(null);
		
		setContentPane(contentPanel);
		contentPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel panelCentral = new JPanel();
		contentPanel.add(panelCentral, BorderLayout.CENTER);
		GridBagLayout gbl_panelCentral = new GridBagLayout();
		gbl_panelCentral.columnWidths = new int[]{0, 0};
		gbl_panelCentral.rowHeights = new int[]{0};
		gbl_panelCentral.columnWeights = new double[]{0.0, 0.0};
		gbl_panelCentral.rowWeights = new double[]{0.0};
		panelCentral.setLayout(gbl_panelCentral);
		
		JButton btnMesas = new JButton("MESAS");
		btnMesas.addActionListener(this);
		GridBagConstraints gbc_btnMesas = new GridBagConstraints();
		gbc_btnMesas.insets = new Insets(0, 0, 0, 5);
		gbc_btnMesas.gridx = 0;
		gbc_btnMesas.gridy = 0;
		panelCentral.add(btnMesas, gbc_btnMesas);
		
		JButton btnEmpleados = new JButton("EMPLEADOS");
		btnEmpleados.addActionListener(this);
		GridBagConstraints gbc_btnEmpleados = new GridBagConstraints();
		gbc_btnEmpleados.gridx = 1;
		gbc_btnEmpleados.gridy = 0;
		panelCentral.add(btnEmpleados, gbc_btnEmpleados);
		
		JPanel panelSur = new JPanel();
		contentPanel.add(panelSur, BorderLayout.SOUTH);
		panelSur.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		
		JButton btnVolver = new JButton("VOLVER");
		btnVolver.addActionListener(this);
		panelSur.add(btnVolver);
		
		
		
		
		

	}

	@Override
	public void actionPerformed(ActionEvent e) {


		if(e.getActionCommand()=="VOLVER")
			this.dispose();
		
		if(e.getActionCommand()=="MESAS"){
			
			Object seleccion = JOptionPane.showInputDialog(
					   null,
					   "NUMERO DE MESAS",
					   "ELIGA CUANTAS MESAS QUIERES",
					   JOptionPane.QUESTION_MESSAGE,
					   null,  // null para icono defecto
					   new Object[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"}, 
					   "");
			
			int numero = Integer.parseInt((String)seleccion);
			
			// desde aqui hasta pm.cargarMesas no incluido lo que hacemos es guardar la seleccion en la properties
			misProps = pm.getMisProps();
			
			misProps.setProperty("NUMMESAS", (String)seleccion);
			
			try {
				os = new FileOutputStream("config.prop".toString().replace("\\", "/"));
				misProps.store(os, null);
				os.close();
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
			
			pm.cargarMesas(numero);
			
			
		}
		if(e.getActionCommand()=="EMPLEADOS"){
			jdusuario = new JDialogCrearUsuario();
			jdusuario.setModal(true);
			jdusuario.setLocationRelativeTo(null);
			jdusuario.setVisible(true);
		}
	}

}
