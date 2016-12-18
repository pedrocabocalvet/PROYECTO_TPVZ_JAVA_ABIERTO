package Threads;

import Interfaces.PanelMedio;

public class CrearPanelMedio extends Thread{

	PanelMedio pm;
	int idUsuario;
	
	public CrearPanelMedio(int idUsuario){
		this.idUsuario = idUsuario;
	}
	
	public void run(){
		pm = new PanelMedio(idUsuario);

		
	}
	
	public PanelMedio getPanelMedio(){
		return pm;
	}
		
}
