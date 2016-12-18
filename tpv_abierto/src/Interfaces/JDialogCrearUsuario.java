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

import java.awt.GridBagLayout;
import java.awt.Image;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JPasswordField;

public class JDialogCrearUsuario extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textNombre;
	private JPasswordField FielContrasena;
	private JTextField textCargo;
	File fichero = null;
	FileImageInputStream img = null;
	ImageIcon icon = null;
	Icon icono = null;
	Consultas con;
	Utilidades utilidad;
	

	/**
	 * Create the dialog.
	 */
	public JDialogCrearUsuario() {
		con = new Consultas();
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 512, 388);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		
			JLabel lblNombre = new JLabel("Nombre");
			GridBagConstraints gbc_lblNombre = new GridBagConstraints();
			gbc_lblNombre.insets = new Insets(0, 0, 5, 5);
			gbc_lblNombre.gridx = 0;
			gbc_lblNombre.gridy = 0;
			contentPanel.add(lblNombre, gbc_lblNombre);
		
		
			textNombre = new JTextField();
			GridBagConstraints gbc_textNombre = new GridBagConstraints();
			gbc_textNombre.insets = new Insets(0, 0, 5, 0);
			gbc_textNombre.fill = GridBagConstraints.HORIZONTAL;
			gbc_textNombre.gridx = 2;
			gbc_textNombre.gridy = 0;
			contentPanel.add(textNombre, gbc_textNombre);
			textNombre.setColumns(10);
		
		
			JLabel lblContrasena = new JLabel("Contrase\u00F1a");
			GridBagConstraints gbc_lblContrasena = new GridBagConstraints();
			gbc_lblContrasena.insets = new Insets(0, 0, 5, 5);
			gbc_lblContrasena.gridx = 0;
			gbc_lblContrasena.gridy = 2;
			contentPanel.add(lblContrasena, gbc_lblContrasena);
	
		
			FielContrasena = new JPasswordField();
			GridBagConstraints gbc_FielContrasena = new GridBagConstraints();
			gbc_FielContrasena.insets = new Insets(0, 0, 5, 0);
			gbc_FielContrasena.fill = GridBagConstraints.HORIZONTAL;
			gbc_FielContrasena.gridx = 2;
			gbc_FielContrasena.gridy = 2;
			contentPanel.add(FielContrasena, gbc_FielContrasena);
		
		
			JLabel lblCargo = new JLabel("Cargo");
			GridBagConstraints gbc_lblCargo = new GridBagConstraints();
			gbc_lblCargo.insets = new Insets(0, 0, 5, 5);
			gbc_lblCargo.gridx = 0;
			gbc_lblCargo.gridy = 4;
			contentPanel.add(lblCargo, gbc_lblCargo);
		
		
			textCargo = new JTextField();
			GridBagConstraints gbc_textCargo = new GridBagConstraints();
			gbc_textCargo.insets = new Insets(0, 0, 5, 0);
			gbc_textCargo.fill = GridBagConstraints.HORIZONTAL;
			gbc_textCargo.gridx = 2;
			gbc_textCargo.gridy = 4;
			contentPanel.add(textCargo, gbc_textCargo);
			textCargo.setColumns(10);
		
		
			JLabel lblFoto = new JLabel("Imagen");
			GridBagConstraints gbc_lblFoto = new GridBagConstraints();
			gbc_lblFoto.insets = new Insets(0, 0, 0, 5);
			gbc_lblFoto.gridx = 0;
			gbc_lblFoto.gridy = 6;
			contentPanel.add(lblFoto, gbc_lblFoto);
		
		
			JButton btnCargarfoto = new JButton("Seleccionar Foto");
			btnCargarfoto.setToolTipText("Busca la imagen en tu PC");
			btnCargarfoto.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent arg0) {
					
					cargaImagen(lblFoto);
					
				}
			});
			GridBagConstraints gbc_btnCargarfoto = new GridBagConstraints();
			gbc_btnCargarfoto.fill = GridBagConstraints.HORIZONTAL;
			gbc_btnCargarfoto.gridx = 2;
			gbc_btnCargarfoto.gridy = 6;
			contentPanel.add(btnCargarfoto, gbc_btnCargarfoto);
		
		
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent a) {
						if(comprobarCampos(textNombre, textCargo, FielContrasena)){
							String mensaje = "Error alguno de los campos esta vacio";
							String titulo = "Campos vacios";
							JOptionPane.showMessageDialog(null, mensaje, titulo, JOptionPane.ERROR_MESSAGE);
						}else{
							String rutaImagen;
							//Comprobamos si hay imagen o no
							if(icon==null){
								rutaImagen = "null";
							}else{
								rutaImagen = "load_file('"+icon.toString().replace('\\', '/')+"')";
							}
							//con.insertarEmpleado(textNombre.getText(), rutaImagen, FielContrasena.getText(), textCargo.getText());
							con.insertarEmpleado2Prueba(textNombre.getText(), fichero, FielContrasena.getText(), textCargo.getText());
							dispose();
						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			
			
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			
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
		
		//Metodo para comprobar si los campos estan llenos
		public boolean comprobarCampos(JTextField usuario, JTextField cargo, JPasswordField contrasena ){
			boolean vacio;
			if(usuario.getText().isEmpty() || cargo.getText().isEmpty() || contrasena.getText().isEmpty()){
				vacio=true;
			}else{
				vacio=false;
			}
			return vacio;
		}
	
}
