package Interfaces;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JFileChooser;

//ventana de carga de imágenes
public class CargarFoto extends JFrame {

	private JPanel contentPane;
	public static JFileChooser jfchCargarfoto;

	public CargarFoto() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 526, 294);
		contentPane = new JPanel();
		jfchCargarfoto = new JFileChooser();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(jfchCargarfoto);
		setContentPane(contentPane);
	}

}