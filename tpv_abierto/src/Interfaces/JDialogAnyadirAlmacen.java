package Interfaces;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.imageio.stream.FileImageInputStream;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import BaseDeDatos.Consultas;

import java.awt.Image;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JComboBox;
//esta clase es para modificar almacén. Error a la hora de poner los nombres.
public class JDialogAnyadirAlmacen extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField tCantMinima;
	private JTextField tPVP;
	private JTextField tDisponibles;
	private JTextField tNombre;
	private JTextField tPreCompra;
	File fichero = null;
	FileImageInputStream img = null;
	ImageIcon icon = null;
	Icon icono = null;
	Consultas con;
	Utilidades utilidad;
	int numeroCategorias = 0;
	String categorias[];
	
	PanelMedio pm;

	/**
	 * Create the dialog.
	 */
	@SuppressWarnings("unchecked")
	public JDialogAnyadirAlmacen(PanelMedio pm, String[] articulo) {
		this.pm = pm;
		
		con = new Consultas();
		setBounds(300, 300, 506, 275);
		
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[] { 0, 0, 0 };
		gbl_contentPanel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_contentPanel.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		gbl_contentPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		contentPanel.setLayout(gbl_contentPanel);
		
			JLabel lblCod = new JLabel(articulo[0]);
			GridBagConstraints gbc_lblCod = new GridBagConstraints();
			gbc_lblCod.insets = new Insets(0, 0, 5, 5);
			gbc_lblCod.gridx = 0;
			gbc_lblCod.gridy = 0;
			contentPanel.add(lblCod, gbc_lblCod);
		
		
			JLabel lblNewLabel_5 = new JLabel("NOMBRE :");
			GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
			gbc_lblNewLabel_5.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel_5.gridx = 0;
			gbc_lblNewLabel_5.gridy = 1;
			contentPanel.add(lblNewLabel_5, gbc_lblNewLabel_5);
		
			tNombre = new JTextField();
			GridBagConstraints gbc_tNombre = new GridBagConstraints();
			gbc_tNombre.insets = new Insets(0, 0, 5, 0);
			gbc_tNombre.fill = GridBagConstraints.HORIZONTAL;
			gbc_tNombre.gridx = 1;
			gbc_tNombre.gridy = 1;
			contentPanel.add(tNombre, gbc_tNombre);
			tNombre.setColumns(10);
			tNombre.setText(articulo[1]);
			tNombre.setEditable(false);
		
			JLabel lblNewLabel_4 = new JLabel("PRECIO COMPRA");
			GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
			gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel_4.gridx = 0;
			gbc_lblNewLabel_4.gridy = 2;
			contentPanel.add(lblNewLabel_4, gbc_lblNewLabel_4);
		
			tPreCompra = new JTextField();
			GridBagConstraints gbc_tPreCompra = new GridBagConstraints();
			gbc_tPreCompra.insets = new Insets(0, 0, 5, 0);
			gbc_tPreCompra.fill = GridBagConstraints.HORIZONTAL;
			gbc_tPreCompra.gridx = 1;
			gbc_tPreCompra.gridy = 2;
			contentPanel.add(tPreCompra, gbc_tPreCompra);
			tPreCompra.setColumns(10);
			tPreCompra.setText(articulo[2]);
	
			JLabel lblNewLabel_6 = new JLabel("P.V.P.");
			GridBagConstraints gbc_lblNewLabel_6 = new GridBagConstraints();
			gbc_lblNewLabel_6.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel_6.gridx = 0;
			gbc_lblNewLabel_6.gridy = 3;
			contentPanel.add(lblNewLabel_6, gbc_lblNewLabel_6);
		
			tPVP = new JTextField();
			GridBagConstraints gbc_tPVP = new GridBagConstraints();
			gbc_tPVP.insets = new Insets(0, 0, 5, 0);
			gbc_tPVP.fill = GridBagConstraints.HORIZONTAL;
			gbc_tPVP.gridx = 1;
			gbc_tPVP.gridy = 3;
			contentPanel.add(tPVP, gbc_tPVP);
			tPVP.setColumns(10);
			tPVP.setText(articulo[3]);
		
			JLabel lblNewLabel_3 = new JLabel("DISPONIBLES");
			GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
			gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel_3.gridx = 0;
			gbc_lblNewLabel_3.gridy = 4;
			contentPanel.add(lblNewLabel_3, gbc_lblNewLabel_3);
		
			tDisponibles = new JTextField();
			GridBagConstraints gbc_tDisponibles = new GridBagConstraints();
			gbc_tDisponibles.insets = new Insets(0, 0, 5, 0);
			gbc_tDisponibles.fill = GridBagConstraints.HORIZONTAL;
			gbc_tDisponibles.gridx = 1;
			gbc_tDisponibles.gridy = 4;
			contentPanel.add(tDisponibles, gbc_tDisponibles);
			tDisponibles.setColumns(10);
			tDisponibles.setText(articulo[4]);
		
			JLabel lblNewLabel_1 = new JLabel("CANTIDAD MINIMA");
			GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
			gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel_1.gridx = 0;
			gbc_lblNewLabel_1.gridy = 5;
			contentPanel.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
			tCantMinima = new JTextField();
			GridBagConstraints gbc_tCantMinima = new GridBagConstraints();
			gbc_tCantMinima.insets = new Insets(0, 0, 5, 0);
			gbc_tCantMinima.fill = GridBagConstraints.HORIZONTAL;
			gbc_tCantMinima.gridx = 1;
			gbc_tCantMinima.gridy = 5;
			contentPanel.add(tCantMinima, gbc_tCantMinima);
			tCantMinima.setColumns(10);
			tCantMinima.setText(articulo[5]);
		
			JLabel lblNewLabel = new JLabel("CATEGORIA");
			GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
			gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
			gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel.gridx = 0;
			gbc_lblNewLabel.gridy = 6;
			contentPanel.add(lblNewLabel, gbc_lblNewLabel);
		
			@SuppressWarnings("rawtypes")
			JComboBox comboCat = new JComboBox();
			
			//Este string de categorias debe cogerlo desde la bbdd
			String categorias[]=con.consultaCategorias();
			
			for (int i = 0; i<categorias.length;i++){
				comboCat.addItem(categorias[i]);
			}
			    
			GridBagConstraints gbc_comboBox = new GridBagConstraints();
			gbc_comboBox.insets = new Insets(0, 0, 5, 0);
			gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
			gbc_comboBox.gridx = 1;
			gbc_comboBox.gridy = 6;
			contentPanel.add(comboCat, gbc_comboBox);
		
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			
			JButton okButton = new JButton("OK");
			okButton.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					
					Mensaje ms1;
					//nos dice si falta algún campo de los que son Not Null en la BBDD
					if(tPVP.getText().isEmpty()){
						ms1 = new Mensaje("Campo P.V.P. vacio. Introduce el precio de venta");
						ms1.setLocationRelativeTo(null);
						ms1.setVisible(true);				
					}else{
						//tomamos los valores de los campos, y si es necesario, los cambiamos de tipo para después asignarlos al metodo insertarArticulosAlmacen
						
						String nombre = tNombre.getText();
				
						String pCompra;
						Double _pCompra = 0.0;
						
						if(tPreCompra.getText().isEmpty()){
							_pCompra = 0.0;
						}else{
							pCompra = tPreCompra.getText();
							_pCompra = Double.parseDouble(pCompra);
						}
						
						String pVenta = tPVP.getText();
						Double _pVenta = Double.parseDouble(pVenta);
						
						String stock;
						Integer _stock = 0;
						
						if(tDisponibles.getText().isEmpty()){
							_stock = 0;
						}else{
							stock = tDisponibles.getText();
							_stock = Integer.parseInt(stock);
						}
						
						String minimo;
						Integer _minimo = 0;
						
						if(tCantMinima.getText().isEmpty()){
							_minimo = 0;
						}else{
							minimo = tCantMinima.getText();
							_minimo = Integer.parseInt(minimo);
						}
						
						Integer categoria = comboCat.getSelectedIndex()+1;
		
						con.updateArticulo(articulo[0], nombre, _pCompra, _pVenta, _stock, _minimo, categoria);
						pm.pi.borrarPanel();
						pm.pi.actualizarPanelArticulos();
						
						
						JDialogAlmacen recargarAlmacen = new JDialogAlmacen(pm);
						recargarAlmacen.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
						recargarAlmacen.setVisible(true);
						
						dispose();
						
					}
						
				}
			});
			
			
			okButton.setToolTipText("Añadir artículo");
			okButton.setActionCommand("OK");
			buttonPane.add(okButton);
			getRootPane().setDefaultButton(okButton);
		
			JButton cancelButton = new JButton("Cancel");
			cancelButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					dispose();
					
					JDialogAlmacen recargarAlmacen = new JDialogAlmacen(pm);
					recargarAlmacen.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					recargarAlmacen.setVisible(true);
				}
			});
			cancelButton.setActionCommand("Cancel");
			buttonPane.add(cancelButton);				
			setModal(true);
	}
	
}
