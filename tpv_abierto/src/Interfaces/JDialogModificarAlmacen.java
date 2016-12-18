package Interfaces;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Graphics;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;
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
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JComboBox;
//esta clase es para añadir al almacén. Error a la hora de poner los nombres.
public class JDialogModificarAlmacen extends JDialog {

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
	public JDialogModificarAlmacen(PanelMedio pm) {
		this.pm = pm;
		
		con = new Consultas();
		setBounds(300, 300, 506, 348);
		
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[] { 0, 0, 0 };
		gbl_contentPanel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_contentPanel.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		gbl_contentPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		contentPanel.setLayout(gbl_contentPanel);
		
			JLabel lblCod = new JLabel("COD.");
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
		
			JLabel lblFoto = new JLabel("IMAGEN");
			GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
			gbc_lblNewLabel_2.insets = new Insets(0, 0, 0, 5);
			gbc_lblNewLabel_2.gridx = 0;
			gbc_lblNewLabel_2.gridy = 7;
			contentPanel.add(lblFoto, gbc_lblNewLabel_2);
			
			JButton btnCargarfoto = new JButton("Selecciona la imagen");
			btnCargarfoto.setToolTipText("Busca la imagen en tu PC");
			btnCargarfoto.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent arg0) {
					
					cargaImagen(lblFoto);
					
				}
			});
			
			GridBagConstraints gbc_btnCargarfoto = new GridBagConstraints();
			gbc_btnCargarfoto.anchor = GridBagConstraints.WEST;
			gbc_btnCargarfoto.gridx = 1;
			gbc_btnCargarfoto.gridy = 7;
			contentPanel.add(btnCargarfoto, gbc_btnCargarfoto);
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			
			JButton okButton = new JButton("OK");
			okButton.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					
					Mensaje ms1;
					//nos dice si falta algún campo de los que son Not Null en la BBDD
					if(tNombre.getText().isEmpty()){
						ms1 = new Mensaje("Campo Nombre vacio. Introduce el nombre del artículo");
						ms1.setLocationRelativeTo(null);
						ms1.setVisible(true);
					}else if(tPVP.getText().isEmpty()){
						ms1 = new Mensaje("Campo P.V.P. vacio. Introduce el precio de venta");
						ms1.setLocationRelativeTo(null);
						ms1.setVisible(true);
					}else if(icon==null){
						ms1 = new Mensaje("Añade una Imagen");
						ms1.setLocationRelativeTo(null);
						ms1.setVisible(true);	
					}else{
						
						//tomamos los valores de los campos, y si es necesario, los cambiamos de tipo para después asignarlos al metodo insertarArticulosAlmacen
						String nombre = tNombre.getText();
						
						String rutaImagen;
						
						if(icon==null){
							rutaImagen = "null";
						}else{
							rutaImagen = "load_file('"+icon.toString().replace('\\', '/')+"')";
						}
						
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
						
		////////////////////////////////////////////////////////////////////////////////////////////////
					/*	
						Image imagen=iconToImage(icono);
						Blob blob = convertirImagenABlob(imagen);
					
						
						
					*/	
		////////////////////////////////////////////////////////////////////////////////////////////////
						//con.insertarArticuloAlmacen(nombre, rutaImagen, _pCompra, _pVenta, _stock, _minimo, categoria);
						con.insertarArticuloAlmacen2prueba(nombre, fichero, _pCompra, _pVenta, _stock, _minimo, categoria);
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
	
	//este método llamamos al JFrame CargarFoto. Podemos visualizar la imagen seleccionada en este JDialog
	public void cargaImagen(JLabel lbl){
		
		JLabel lblFoto = lbl;
		int resultado;
		
		
		CargarFoto ventana = new CargarFoto();

	    FileNameExtensionFilter filtro = new FileNameExtensionFilter("JPG y PNG","jpg","png");

	    ventana.jfchCargarfoto.setFileFilter(filtro);

	    resultado= ventana.jfchCargarfoto.showOpenDialog(null);
	    
	    if (JFileChooser.APPROVE_OPTION == resultado){

	        fichero = ventana.jfchCargarfoto.getSelectedFile();
	        
	        try{

	                icon = new ImageIcon(fichero.toString());

	                icono = new ImageIcon(icon.getImage().
	                getScaledInstance(100, 100, Image.SCALE_DEFAULT));

	                lblFoto.setText(null);

	                lblFoto.setIcon( icono );

	        }catch(Exception ex){

	                JOptionPane.showMessageDialog(null, 
	                "Error abriendo la imagen "+ ex);

	        }

	    }
		
	}
	
	public static Blob convertirImagenABlob ( Image imagen ) {

	      Blob imagenBlob = null;
	      BufferedImage bi = new BufferedImage ( imagen.getWidth ( null ), imagen.getHeight ( null ), BufferedImage.TYPE_INT_ARGB );
	      Graphics bg = bi.getGraphics ();
	      bg.drawImage ( imagen, 0, 0, null );
	      bg.dispose ();

	      ByteArrayOutputStream baos = new ByteArrayOutputStream ();
	      try {
	    	  
	         ImageIO.write (bi,"png", baos );
	         		
	         baos.flush ();
	         baos.close ();
	      } catch ( IOException e ) {
	         e.printStackTrace ();
	      }

	      byte [] imagenByte = baos.toByteArray ();

	      try {
	         imagenBlob = new SerialBlob(imagenByte);
	      } catch ( SerialException se ) {
	         se.printStackTrace ();
	      } catch ( SQLException sqle ) {
	         sqle.printStackTrace ();
	      }
	      return imagenBlob;
	   }
	
	private Image iconToImage(Icon icon)
	{
	    if(icon instanceof ImageIcon)
	    {
	        return ((ImageIcon) icon).getImage();
	    }
	    else
	    {
	        BufferedImage image = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_RGB);
	        icon.paintIcon(null, image.getGraphics(), 0, 0);
	        return image;
	    }
	}
	

	
}
