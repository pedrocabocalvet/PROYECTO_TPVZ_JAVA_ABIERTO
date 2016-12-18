package Inicio;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;

import BaseDeDatos.ConexionBaseDatos;
import Interfaces.FramePrincipal;
import Interfaces.PanelSuperior;
import Interfaces.PanelMedio;
import Interfaces.panelInferior;
import Login.PantallaLogin;
import Objetos.Pool;
import Threads.CrearPanelInferior;
import Threads.CrearPanelMedio;
import Threads.CrearPanelSuperior;

public class Iniciacion {	// comentario
	
	PanelMedio pm;
	panelInferior pi;
	PanelSuperior ps;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@SuppressWarnings("unused")
			public void run() {
				
				
				
				try {
					
					PantallaLogin frame = new PantallaLogin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		});
		
	}
	
	public void iniciarAplicacion(ImageIcon fotoUsuario, int idUsuario, boolean admin){
		
		try {
			FramePrincipal frame = new FramePrincipal();
			
			frame.setVisible(true);
			//ConexionBaseDatos cbd;
			//cbd = ConexionBaseDatos.getInstancia();

			//pm = new PanelMedio(idUsuario);
			// Thread
			
			CrearPanelMedio cpm = new CrearPanelMedio(idUsuario);
			cpm.start();
			cpm.join();
			pm = cpm.getPanelMedio();
			
			frame.add(pm,BorderLayout.CENTER);

			
			//pi = new panelInferior(pm);
			//ps = new PanelSuperior(frame,pm,fotoUsuario,admin);
			// 2 Thread
			
			CrearPanelInferior cpi = new CrearPanelInferior(pm);
			CrearPanelSuperior cps = new CrearPanelSuperior(pm, frame,fotoUsuario,admin);
			cpi.start();
			cps.start();
			cpi.join();
			cps.join();
			pi = cpi.getPanelInferior();
			ps = cps.getPanelSuperior();
			frame.add(pi,BorderLayout.SOUTH);
			frame.add(ps,BorderLayout.NORTH);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}