package Interfaces;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

public class FramePrincipal extends JFrame {

	private JPanel contenedorPrincipal;


	/**
	 * Create the frame.
	 */
	public FramePrincipal() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// quitar los bordes del jframe para que coja el fullscreen
		this.setUndecorated(true);
		// expandir el jframe todo lo que da la pantalla de si
		this.setExtendedState(MAXIMIZED_BOTH);
		contenedorPrincipal = new JPanel();
		contenedorPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contenedorPrincipal);
		contenedorPrincipal.setLayout(new BorderLayout(0, 0));
	}
	
	public void cerrar(){
		dispose();
	}
	
	public void visivilizar(){
		this.setVisible(true);
	}

}
