package Threads;

import javax.swing.ImageIcon;

import Interfaces.FramePrincipal;
import Interfaces.PanelMedio;
import Interfaces.PanelSuperior;


public class CrearPanelSuperior extends Thread{
	
	PanelMedio pm;
	PanelSuperior ps;
	
	FramePrincipal frame;
	ImageIcon fotoUsuario;
	boolean admin;
	
	public CrearPanelSuperior(PanelMedio pm, FramePrincipal frame,ImageIcon fotoUsuario,boolean admin){
		this.pm = pm;
		this.frame = frame;
		this.fotoUsuario = fotoUsuario;
		this.admin = admin;
	}
	
	public void run(){

		ps = new PanelSuperior(frame,pm,fotoUsuario,admin);
	}
	
	public PanelSuperior getPanelSuperior(){
		return ps;
	}

}
