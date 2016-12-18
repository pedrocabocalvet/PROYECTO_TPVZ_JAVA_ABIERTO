package Threads;

import Interfaces.PanelMedio;
import Interfaces.panelInferior;

public class CrearPanelInferior extends Thread{
	PanelMedio pm;
	panelInferior pi;
	
	public CrearPanelInferior(PanelMedio pm){
		this.pm = pm;
	}
	
	public void run(){

		pi = new panelInferior(pm);

	}
	
	public panelInferior getPanelInferior(){
		return pi;
	}

}
