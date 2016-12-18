package Interfaces;
import javax.swing.JPanel;

import Objetos.LineaVenta;

import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JToggleButton;
import java.awt.Font;

public class Botonera extends JPanel{ 

	Utilidades utilidad;
	int anchoBoton;
	int altoBoton;
	PanelMedio panelMedio;
	LineaVenta lineaventa;
	JToggleButton tglbtn2cifras;		// necesito crear este boton aqui para saber desde un metodo si esta apretado o no
	int numerosSize = 33;
	
	
	public Botonera(int porcentajeAncho, int porcentajeAlto, PanelMedio pm) {
		panelMedio = pm;
		utilidad= new Utilidades();
		anchoBoton=utilidad.redimensionarSegunPantallaAncho(porcentajeAncho);
		altoBoton=utilidad.redimensionarSegunPantallaAlto(porcentajeAlto);
		System.out.println(altoBoton);
		System.out.println(anchoBoton);
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{anchoBoton,anchoBoton,anchoBoton};
		gridBagLayout.rowHeights = new int[]{altoBoton, altoBoton, altoBoton, altoBoton};

		setLayout(gridBagLayout);
		
		JButton btn1 = new JButton("1");
		btn1.setFont(new Font("Tahoma", Font.PLAIN, numerosSize));
		btn1.addActionListener(pm);
		GridBagConstraints gbc_btn1 = new GridBagConstraints();
		gbc_btn1.fill=GridBagConstraints.BOTH;
		gbc_btn1.insets = new Insets(5, 5, 5, 5);
		gbc_btn1.gridx = 0;
		gbc_btn1.gridy = 0;
		add(btn1, gbc_btn1);
		
		JButton btn2 = new JButton("2");
		btn2.setFont(new Font("Tahoma", Font.PLAIN, numerosSize));
		btn2.addActionListener(pm);
		GridBagConstraints gbc_btn2 = new GridBagConstraints();
		gbc_btn2.fill=GridBagConstraints.BOTH;
		gbc_btn2.insets = new Insets(5, 0, 5, 5);
		gbc_btn2.gridx = 1;
		gbc_btn2.gridy = 0;
		add(btn2, gbc_btn2);
		
		JButton btn3 = new JButton("3");
		btn3.setFont(new Font("Tahoma", Font.PLAIN, numerosSize));
		btn3.addActionListener(pm);
		GridBagConstraints gbc_btn3 = new GridBagConstraints();
		gbc_btn3.fill=GridBagConstraints.BOTH;
		gbc_btn3.insets = new Insets(5, 0, 5, 0);
		gbc_btn3.gridx = 2;
		gbc_btn3.gridy = 0;
		add(btn3, gbc_btn3);
		
		JButton btn4 = new JButton("4");
		btn4.setFont(new Font("Tahoma", Font.PLAIN, numerosSize));
		btn4.addActionListener(pm);
		GridBagConstraints gbc_btn4 = new GridBagConstraints();
		gbc_btn4.fill=GridBagConstraints.BOTH;
		gbc_btn4.insets = new Insets(0, 5, 5, 5);
		gbc_btn4.gridx = 0;
		gbc_btn4.gridy = 1;
		add(btn4, gbc_btn4);
		
		JButton btn5 = new JButton("5");
		btn5.setFont(new Font("Tahoma", Font.PLAIN, numerosSize));
		btn5.addActionListener(pm);
		GridBagConstraints gbc_btn5 = new GridBagConstraints();
		gbc_btn5.fill=GridBagConstraints.BOTH;
		gbc_btn5.insets = new Insets(0, 0, 5, 5);
		gbc_btn5.gridx = 1;
		gbc_btn5.gridy = 1;
		add(btn5, gbc_btn5);
		
		JButton btn6 = new JButton("6");
		btn6.setFont(new Font("Tahoma", Font.PLAIN, numerosSize));
		btn6.addActionListener(pm);
		GridBagConstraints gbc_btn6 = new GridBagConstraints();
		gbc_btn6.fill=GridBagConstraints.BOTH;
		gbc_btn6.insets = new Insets(0, 0, 5, 0);
		gbc_btn6.gridx = 2;
		gbc_btn6.gridy = 1;
		add(btn6, gbc_btn6);
		
		JButton btn7 = new JButton("7");
		btn7.setFont(new Font("Tahoma", Font.PLAIN, numerosSize));
		btn7.addActionListener(pm);
		GridBagConstraints gbc_btn7 = new GridBagConstraints();
		gbc_btn7.fill=GridBagConstraints.BOTH;
		gbc_btn7.insets = new Insets(0, 5, 5, 5);
		gbc_btn7.gridx = 0;
		gbc_btn7.gridy = 2;
		add(btn7, gbc_btn7);
		
		JButton btn8 = new JButton("8");
		btn8.setFont(new Font("Tahoma", Font.PLAIN, numerosSize));
		btn8.addActionListener(pm);
		GridBagConstraints gbc_btn8 = new GridBagConstraints();
		gbc_btn8.fill=GridBagConstraints.BOTH;
		gbc_btn8.insets = new Insets(0, 0, 5, 5);
		gbc_btn8.gridx = 1;
		gbc_btn8.gridy = 2;
		add(btn8, gbc_btn8);
		
		JButton btn9 = new JButton("9");
		btn9.setFont(new Font("Tahoma", Font.PLAIN, numerosSize));
		btn9.addActionListener(pm);
		GridBagConstraints gbc_btn9 = new GridBagConstraints();
		gbc_btn9.fill=GridBagConstraints.BOTH;
		gbc_btn9.insets = new Insets(0, 0, 5, 0);
		gbc_btn9.gridx = 2;
		gbc_btn9.gridy = 2;
		add(btn9, gbc_btn9);
		
		
		
		tglbtn2cifras = new JToggleButton("_ _");
		tglbtn2cifras.setFont(new Font("Tahoma", Font.PLAIN, numerosSize));
		tglbtn2cifras.addActionListener(pm);
		GridBagConstraints gbc_tglbtn2cifras = new GridBagConstraints();
		gbc_tglbtn2cifras.fill=GridBagConstraints.BOTH;
		gbc_tglbtn2cifras.insets = new Insets(0, 5, 0, 5);
		gbc_tglbtn2cifras.gridx = 0;
		gbc_tglbtn2cifras.gridy = 3;
		add(tglbtn2cifras, gbc_tglbtn2cifras);

		
		JButton btn0 = new JButton("0");
		btn0.setFont(new Font("Tahoma", Font.PLAIN, numerosSize));
		btn0.addActionListener(pm);
		GridBagConstraints gbc_btn0 = new GridBagConstraints();
		gbc_btn0.fill=GridBagConstraints.BOTH;
		gbc_btn0.insets = new Insets(0, 0, 0, 5);
		gbc_btn0.gridx = 1;
		gbc_btn0.gridy = 3;
		add(btn0, gbc_btn0);
		
		JButton btnC = new JButton("C");
		btnC.setFont(new Font("Tahoma", Font.PLAIN, numerosSize));
		btnC.addActionListener(pm);
		GridBagConstraints gbc_btnC = new GridBagConstraints();
		gbc_btnC.fill=GridBagConstraints.BOTH;
		gbc_btnC.gridx = 2;
		gbc_btnC.gridy = 3;
		add(btnC, gbc_btnC);
		
		
		
		

	}
	
	
	public boolean tglbtn2cifrasApretado (){
		
		return tglbtn2cifras.isSelected();
		
	}
	
	public void tglbtn2cifrasPresionar(){
		tglbtn2cifras.doClick();
		tglbtn2cifras.setSelected(false);
		
		
	}
	

	

}
