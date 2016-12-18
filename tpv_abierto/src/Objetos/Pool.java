package Objetos;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import BaseDeDatos.ConexionBaseDatos;

public class Pool {
	private static Pool INSTANCIA=null;
	private final int CARRILES=4;
	static ThreadPoolExecutor myTPE;
	
	// constructor privado para que solo se pueda llamar desde esta clase no desde fuera
	private Pool(){
		realizarPool();
	}

	private void realizarPool() {
		// TODO Auto-generated method stub
		myTPE = (ThreadPoolExecutor)Executors.newFixedThreadPool(CARRILES);
		
	}
	
	//Si no existe, crea una instancia de esta clase
	private synchronized static void creaInstancia(){
		if(INSTANCIA==null){
			INSTANCIA = new Pool();
		}
	}
	// con este metodo prodremos crear desde fuera una instancia de este objeto y cogerla para usarla
	public static Pool getInstancia(){
		if(INSTANCIA==null) creaInstancia();
		return INSTANCIA;
	}
	
	public static void eliminaInstancia(){
		if(!(INSTANCIA==null)){
			INSTANCIA= null;
			myTPE.shutdown();
		}
	}
	
	public ThreadPoolExecutor getThreadPoolExecutor(){
		return myTPE;
	}
}
