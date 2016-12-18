package Login;
// desde aqui
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
// hasta aqui


import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import BaseDeDatos.ConexionBaseDatos;
import BaseDeDatos.Consultas;
import Inicio.Iniciacion;
import Interfaces.Utilidades;
import Threads.DevuelveFotoUsuario;
import Threads.DevuelveIdUsuario;
import Threads.ValidarUsuarioContraseña;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Dimension;

import javax.swing.JTextField;
import javax.swing.JPasswordField;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.border.BevelBorder;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;

public class PantallaLogin extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JButton btnEnviar;
	private JTextField textUsuario;
	private JPasswordField passwordField;
	private Consultas con;
	public Iniciacion principioApp;
	public Utilidades util;
	JCheckBox chckbxNewCheckBoxAdmin;
	

	public PantallaLogin() {

		util = new Utilidades();
		con = new Consultas();
		principioApp = new Iniciacion();
		
		setMinimumSize(new Dimension(800, 600));
		setTitle("LOGIN");
		//setMaximumSize(new Dimension(751, 601));
		this.setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 600);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JLabel lblFondo = new JLabel();
		lblFondo.setIcon(new ImageIcon("images/imagenInicio.gif"));
		contentPane.add(lblFondo, BorderLayout.CENTER);
		
		JPanel panelSur = new JPanel();
		contentPane.add(panelSur, BorderLayout.SOUTH);
		panelSur.setLayout(new BorderLayout(0, 0));
		
		JPanel panelUsuario = new JPanel();
		panelSur.add(panelUsuario, BorderLayout.CENTER);
		
		JLabel lblNewLabel = new JLabel("USUARIO: ");
		panelUsuario.add(lblNewLabel);
		
		textUsuario = new JTextField();
		panelUsuario.add(textUsuario);
		textUsuario.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("CONTRASE\u00D1A:");
		panelUsuario.add(lblNewLabel_1);
		
		passwordField = new JPasswordField();
		passwordField.setColumns(10);
		panelUsuario.add(passwordField);
		
		JPanel panelBotones = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panelBotones.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		panelSur.add(panelBotones, BorderLayout.EAST);
		
		JButton btnNewButtonEnviar = new JButton("ENVIAR");
		btnNewButtonEnviar.addActionListener(this);
		panelBotones.add(btnNewButtonEnviar);
		
		JPanel panelAdmin = new JPanel();
		panelSur.add(panelAdmin, BorderLayout.WEST);
		
		chckbxNewCheckBoxAdmin = new JCheckBox("Administrador");
		panelAdmin.add(chckbxNewCheckBoxAdmin);
		

	}
	
	public boolean conexionActiveDirectory(String usuario, String contraseña){
		
		boolean respuesta = true;
		
		Hashtable<String, String> env = new Hashtable<String, String>();
		env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
		env.put(Context.SECURITY_AUTHENTICATION, "simple");
		env.put(Context.PROVIDER_URL, "ldap://10.2.72.186");
		
		//Rellenamos con el usuario/dominio y password
		env.put(Context.SECURITY_PRINCIPAL, usuario+"@sistemasserver2012.com");
		env.put(Context.SECURITY_CREDENTIALS, contraseña);

		DirContext ctx;

		try {
			// Authenticate the logon user
			ctx = new InitialDirContext(env);
			System.out.println("El usuario se ha autenticado correctamente");
			
			ctx.close();

		} catch (NamingException ex) {
			respuesta = false;
			System.out.println("Ha habido un error en la autenticación");
		}
		
		return respuesta;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getActionCommand()=="ENVIAR"){
			String usuario;
			char password[];
			String contraseña;
			boolean respuesta=false;
			boolean respuestaAD=false;
			boolean administrador = false;
			
			
			usuario = textUsuario.getText();
			password = passwordField.getPassword();
			contraseña = new String(password); 
			
			if(!usuario.isEmpty() && !contraseña.isEmpty()){
			
				if(chckbxNewCheckBoxAdmin.isSelected()){
					
					respuestaAD = conexionActiveDirectory(usuario,contraseña);
		
				}
				else{
					//respuesta=con.AutentificarUsuarioContraseña(usuario, contraseña);
					// thread
					ValidarUsuarioContraseña vuc = new ValidarUsuarioContraseña(usuario, contraseña);
					vuc.start();
					try {
						vuc.join();
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					respuesta = vuc.getValue();
				}
			}
			
			if(respuesta){
				
				ImageIcon foto = null;
				int idUsuario;
				
				//idUsuario = con.devuelveIdUsuario(usuario);
				//foto = con.FotoUsuario(usuario);
				//2 Thread
				
				DevuelveIdUsuario diu = new DevuelveIdUsuario(usuario);
				DevuelveFotoUsuario dfu = new DevuelveFotoUsuario(usuario);
				diu.start();
				dfu.start();
				try {
					diu.join();
					dfu.join();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				idUsuario = diu.getIdUsuario();
				foto = dfu.getImageIconFoto();
				
				

				foto= util.redimensionarFoto(foto, 300, 300);
				String mensaje = "Bienvenido "+usuario;
				
				this.dispose();
				JOptionPane.showMessageDialog(null, "", mensaje, JOptionPane.INFORMATION_MESSAGE,
												foto);
				// le pasamos la foto y el idUsuario para que los guarde en pSuperior para poner la foto y en pMedio para guardar IDusuario
				principioApp.iniciarAplicacion(foto, idUsuario,administrador);
			}
			else if(respuestaAD){
				administrador=true;
				ImageIcon foto = null;
				int idUsuario = 999;
				String mensaje = "Bienvenido "+usuario;
				foto=new ImageIcon("images/administrador.png");
				foto= util.redimensionarFoto(foto, 300, 300);
				
				this.dispose();
				JOptionPane.showMessageDialog(null, "", mensaje, JOptionPane.INFORMATION_MESSAGE,
						foto);
				// le pasamos la foto y el idUsuario para que los guarde en pSuperior para poner la foto y en pMedio para guardar IDusuario
				principioApp.iniciarAplicacion(foto, idUsuario,administrador);
			}
			else if(respuesta == false){
				ImageIcon foto = null;
				foto = new ImageIcon("images/peligro.png");
				foto= util.redimensionarFoto(foto, 300, 300);
				
				textUsuario.setText("");
				passwordField.setText("");
				
				JOptionPane.showMessageDialog(null, "", "Usuario o contraseña incorrectos", JOptionPane.INFORMATION_MESSAGE,
						foto);

			}
			
			
		}
		
	}
}
