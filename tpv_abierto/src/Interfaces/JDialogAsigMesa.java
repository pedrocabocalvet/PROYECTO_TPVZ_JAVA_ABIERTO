package Interfaces;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Objetos.Mesa;

import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Window.Type;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JDialogAsigMesa extends JDialog implements ActionListener {
	
	ImageIcon foto;
	Utilidades util;
	int lado;
	PanelMedio panelMedio;
	Utilidades utilidad;
	
	DefaultTableModel dtm;
	Mesa mesas[];


	public JDialogAsigMesa(PanelMedio pm) {
		
		mesas=pm.getMesas();		// aqui cojemos el array de mesas del panelMedio
		
		panelMedio=pm;
		setTitle("ASIGNAR MESA");
		util = new Utilidades();
		lado = 200;			// tamaño por lado del icono 
			
		setBounds(util.redimensionarSegunPantallaAncho(25), util.redimensionarSegunPantallaAlto(15)
				, util.redimensionarSegunPantallaAncho(50), util.redimensionarSegunPantallaAlto(60));
		
		getContentPane().setLayout(new BorderLayout(0, 0));
		{
			JPanel panelSuperior = new JPanel();
			getContentPane().add(panelSuperior, BorderLayout.NORTH);
			panelSuperior.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			{
				JLabel lblNewLabel = new JLabel("");
				foto = new ImageIcon("images/iconococinero.png");
				foto = util.redimensionarFoto(foto, lado, lado);
				lblNewLabel.setIcon(foto);
				
				panelSuperior.add(lblNewLabel);
			}
		}
		{
			JPanel panelInferior = new JPanel();
			getContentPane().add(panelInferior, BorderLayout.CENTER);
			GridBagLayout gbl_panelInferior = new GridBagLayout();
			gbl_panelInferior.columnWidths = new int[]{0};
			gbl_panelInferior.rowHeights = new int[]{0};
			gbl_panelInferior.columnWeights = new double[]{0.2,0.2,0.2};
			gbl_panelInferior.rowWeights = new double[]{0.2,0.2,0.2,0.2};
			panelInferior.setLayout(gbl_panelInferior);
			{
				int nuevaLinea=0;		// por la linea que va 
				int nuevaCelda=0;		// por la celda que va
				
				for(int x = 0; x < mesas.length; x++){
					if(nuevaCelda==3){
						nuevaCelda=0;					// cada vez que lleva 3 en una linea salta de linea
						nuevaLinea=nuevaLinea+1;	
					}
					
					JButton btnNewButton = new JButton(mesas[x].getNombreMesa());
					btnNewButton.addActionListener(this);
					GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
					gbc_btnNewButton.fill=GridBagConstraints.BOTH;
					gbc_btnNewButton.insets = new Insets(0, 0, 0, 0);
					gbc_btnNewButton.gridx = nuevaCelda;
					gbc_btnNewButton.gridy = nuevaLinea;
					panelInferior.add(btnNewButton, gbc_btnNewButton);
					nuevaCelda=nuevaCelda+1;
				}
			}	

		}
		setModal(true);		// hace que no se puede acceder a ninguna ventana hasta que no se cierre esta.
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		// TODO Auto-generated method stub
		
		switch (e.getActionCommand()){
	
		case "mesa 1":
			panelMedio.asignarMesa(1);
			dtm=mesas[1].getDtm();
			panelMedio.setMesaActual(mesas[1]);
			panelMedio.asignarNuevoDtm(dtm);
			
			break;
		case "mesa 2":
			panelMedio.asignarMesa(2);
			dtm=mesas[2].getDtm();
			panelMedio.setMesaActual(mesas[2]);
			panelMedio.asignarNuevoDtm(dtm);
			break;
		case "mesa 3":
			panelMedio.asignarMesa(3);
			dtm=mesas[3].getDtm();
			panelMedio.setMesaActual(mesas[3]);
			panelMedio.asignarNuevoDtm(dtm);
			break;
		case "mesa 4":
			panelMedio.asignarMesa(4);
			dtm=mesas[4].getDtm();
			panelMedio.setMesaActual(mesas[4]);
			panelMedio.asignarNuevoDtm(dtm);
			break;
		case "mesa 5":
			panelMedio.asignarMesa(5);
			dtm=mesas[5].getDtm();
			panelMedio.setMesaActual(mesas[5]);
			panelMedio.asignarNuevoDtm(dtm);
			break;
		case "mesa 6":
			panelMedio.asignarMesa(6);
			dtm=mesas[6].getDtm();
			panelMedio.setMesaActual(mesas[6]);
			panelMedio.asignarNuevoDtm(dtm);
			break;
		case "mesa 7":
			dtm=mesas[7].getDtm();
			panelMedio.setMesaActual(mesas[7]);
			panelMedio.asignarNuevoDtm(dtm);
			panelMedio.asignarMesa(7);
			break;
		case "mesa 8":
			panelMedio.asignarMesa(8);
			panelMedio.setMesaActual(mesas[8]);
			dtm=mesas[8].getDtm();
			panelMedio.asignarNuevoDtm(dtm);
			break;
		case "mesa 9":
			panelMedio.asignarMesa(9);
			panelMedio.setMesaActual(mesas[9]);
			dtm=mesas[9].getDtm();
			panelMedio.asignarNuevoDtm(dtm);
			break;
		case "Barra":
			panelMedio.asignarMesa(0);
			panelMedio.setMesaActual(mesas[0]);
			dtm=mesas[0].getDtm();
			panelMedio.asignarNuevoDtm(dtm);
			break;
		
		}
		// con esta linea cada vez q asignamos una mesa ponemos el label de total de comanda al valor total q tenga esa mesa
		panelMedio.ponerTextoEnLblFacturasTotal(panelMedio.getMesaActual().getTotal());
		dispose();
	}

}
