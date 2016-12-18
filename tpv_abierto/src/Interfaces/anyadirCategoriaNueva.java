package Interfaces;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.imageio.stream.FileImageInputStream;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import BaseDeDatos.Consultas;

public class anyadirCategoriaNueva extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField tNombre;
	
	File fichero = null;
	FileImageInputStream img = null;
	ImageIcon icon = null;
	Icon icono = null;
	Consultas con;
	Utilidades utilidad;
	int numeroCategorias = 0;
	String categorias[];

	/**
	 * Create the dialog.
	 */
	public anyadirCategoriaNueva(PanelMedio pm) {
		
		con = new Consultas();
		setBounds(300, 300, 355, 227);
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
		gbc_lblCod.gridy = 1;
		contentPanel.add(lblCod, gbc_lblCod);
		
		JLabel lblNewLabel_5 = new JLabel("NOMBRE :");
		GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
		gbc_lblNewLabel_5.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_5.gridx = 0;
		gbc_lblNewLabel_5.gridy = 2;
		contentPanel.add(lblNewLabel_5, gbc_lblNewLabel_5);
		
		tNombre = new JTextField();
		GridBagConstraints gbc_tNombre = new GridBagConstraints();
		gbc_tNombre.insets = new Insets(0, 0, 5, 0);
		gbc_tNombre.fill = GridBagConstraints.HORIZONTAL;
		gbc_tNombre.gridx = 1;
		gbc_tNombre.gridy = 2;
		contentPanel.add(tNombre, gbc_tNombre);
		tNombre.setColumns(10);
		
		JLabel lblFoto = new JLabel("IMAGEN");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 3;
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
		gbc_btnCargarfoto.gridy = 3;
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
					ms1 = new Mensaje("Campo Nombre vacio. Introduce el nombre de la categoría");
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

					//con.insertarCategoria(nombre, rutaImagen);
					con.insertarCategoria2Prueba(nombre, fichero);
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
				
				JDialogAlmacen recargarAlmacen = new JDialogAlmacen(pm);
				recargarAlmacen.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				recargarAlmacen.setVisible(true);
				dispose();
				
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

}
