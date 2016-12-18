package Interfaces;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import com.itextpdf.text.log.SysoCounter;

import BaseDeDatos.ConexionBaseDatos;
import BaseDeDatos.Consultas;

@SuppressWarnings("serial")
public class JDialogAlmacen extends JDialog implements ActionListener {
	private JTable table;
	private DefaultTableModel dtm;
	FramePrincipal frame;
	Utilidades utilidad;
	int numeroArticulos = 0;
	Consultas con;
	static public String columnNames[] = { "COD", "Nombre", "P.Compra", "P.V.P", "Disponibles", "Mínimo", "Categoria" };
	String datos[][];

	Mensaje mens;

	PanelMedio pm;

	/**
	 * Create the dialog.
	 */

	public JDialogAlmacen(PanelMedio pm) {

		this.pm = pm;
		con = new Consultas();
		// this.setUndecorated(true);
		this.setBounds(GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds());
		getContentPane().setLayout(new BorderLayout());

		JPanel buttonPane = new JPanel();
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JButton addArticle = new JButton("AÑADIR PRODUCTO");
		addArticle.setFont(new Font("Tahoma", Font.PLAIN, 23));
		addArticle.addActionListener(this);
		buttonPane.add(addArticle);

		JButton btnAadirCategoria = new JButton("AÑADIR CATEGORIA");
		btnAadirCategoria.setFont(new Font("Tahoma", Font.PLAIN, 23));
		btnAadirCategoria.addActionListener(this);
		buttonPane.add(btnAadirCategoria);

		JButton modify = new JButton("MODIFICAR");
		modify.setFont(new Font("Tahoma", Font.PLAIN, 23));
		modify.addActionListener(this);
		buttonPane.add(modify);

		JButton btnEliminar = new JButton("ELIMINAR PRODUCTO");
		btnEliminar.setFont(new Font("Tahoma", Font.PLAIN, 23));
		btnEliminar.addActionListener(this);
		buttonPane.add(btnEliminar);

		JButton backToTerminal = new JButton("VOLVER");
		backToTerminal.setFont(new Font("Tahoma", Font.PLAIN, 23));
		backToTerminal.addActionListener(this);
		backToTerminal.setActionCommand("VOLVER");
		buttonPane.add(backToTerminal);

		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 0, 0 };
		gbl_panel.rowHeights = new int[] { 0, 0, 0 };
		gbl_panel.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		JLabel lblAlmacn = new JLabel("ALMAC\u00C9N");
		lblAlmacn.setFont(new Font("Tahoma", Font.PLAIN, 27));
		GridBagConstraints gbc_lblAlmacn = new GridBagConstraints();
		gbc_lblAlmacn.insets = new Insets(0, 0, 5, 0);
		gbc_lblAlmacn.gridx = 0;
		gbc_lblAlmacn.gridy = 0;
		panel.add(lblAlmacn, gbc_lblAlmacn);

		String dataValues[][] = articulosAlmacen();
		
		dtm = new DefaultTableModel(){
			public boolean isCellEditable(int rowIndex,int columnIndex){return false;}
		};
		dtm.setDataVector(dataValues, columnNames);
		

		table = new JTable(dtm);


		// pinto la tabla
		table.setDefaultRenderer(Object.class, new pintarTabla());
		table.validate();
	

		table.setFont(new Font("Tahoma", Font.PLAIN, 19));

		JScrollPane sc = new JScrollPane(table);

		GridBagConstraints gbc_table = new GridBagConstraints();
		gbc_table.fill = GridBagConstraints.BOTH;
		gbc_table.gridx = 0;
		gbc_table.gridy = 1;
		panel.add(sc, gbc_table);
		
		

		setModal(true);
	}

	public void actionPerformed(ActionEvent a) {
		int fila = -1;
		if (a.getActionCommand() == "VOLVER") {

			dispose();

		}
		if (a.getActionCommand() == "AÑADIR PRODUCTO") {

			JDialogModificarAlmacen dialog = new JDialogModificarAlmacen(pm);
			dialog.setLocationRelativeTo(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
			dispose();

		}

		if (a.getActionCommand() == "AÑADIR CATEGORIA") {

			anyadirCategoriaNueva dialog = new anyadirCategoriaNueva(pm);
			dialog.setLocationRelativeTo(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
			dispose();

		}

		if (a.getActionCommand() == "MODIFICAR") {
			fila = table.getSelectedRow();
			if(fila==-1){
				mens = new Mensaje("No has seleccionado ningún artículo");
				mens.setLocationRelativeTo(null);
				mens.setVisible(true);
			}else{
				String cod = dtm.getValueAt(fila, 0).toString();
				JDialogAnyadirAlmacen dialog = new JDialogAnyadirAlmacen(pm, articuloAmodificar(cod));
				dialog.setLocationRelativeTo(null);
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
				dispose();
			}
			
		}
		
		if (a.getActionCommand() == "ELIMINAR PRODUCTO") {
			
			fila = table.getSelectedRow();
			
			if(fila==-1){
				mens = new Mensaje("No has seleccionado ningún artículo");
				mens.setLocationRelativeTo(null);
				mens.setVisible(true);
			}else{
				
				String cod = dtm.getValueAt(fila, 0).toString();
				con.borrarArticuloAlmacen(cod);
				///////////////////////////////////
				pm.pi.borrarPanel();
				pm.pi.actualizarPanelArticulos();
				this.dispose();
				///////////////////////////////////
				JDialogAlmacen recargarAlmacen = new JDialogAlmacen(pm);
				recargarAlmacen.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				recargarAlmacen.setVisible(true);

				
			//	dispose();
			}
			
		}

	}
	// Método que recoge y devuelve artículo a modificar
	public String[] articuloAmodificar(String cod){
	
		String articulo[] = new String[columnNames.length];
		ResultSet rs = con.consultaArticulo(cod);
		//int filasMatriz = 0;
		int codigo;
		String nombre;
		double pCompra;
		double pVenta;
		int stock;
		int minimo;
		String categoria;
		
		try {
			while(rs.next()){
			
			codigo = rs.getInt("a.cod");
			nombre = rs.getString("a.nombre");
			pCompra = rs.getDouble("a.precio_compra");
			pVenta = rs.getDouble("a.pvp");
			stock = rs.getInt("a.disponibles");
			minimo = rs.getInt("a.cant_minima");
			categoria = rs.getString("c.nombre");
			
			String _codigo = String.valueOf(codigo);
			String _pc = String.valueOf(pCompra);
			String _pvp = String.valueOf(pVenta);
			String _disp = String.valueOf(stock);
			String _min = String.valueOf(minimo);
			
			articulo[0] = _codigo;
			articulo[1] = nombre;
			articulo[2] = _pc;
			articulo[3] = _pvp;
			articulo[4] = _disp;
			articulo[5] = _min;
			articulo[6] = categoria;
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return articulo;
		
	}

	// Método que recoge y devuelve en una matriz los artículos de la BBDD
	public String[][] articulosAlmacen() {

		numeroArticulos = con.consultaNumeroArticulos2();
		String articulos[][] = new String[numeroArticulos][columnNames.length];
		ResultSet rs = con.consultaArticulosAlmacen();
		int filasMatriz = 0;
		int codigo;
		String nombre;
		double pCompra;
		double pVenta;
		int stock;
		int minimo;
		String categoria;

		try {
			while (rs.next()) {

				codigo = rs.getInt("articulo.cod");
				nombre = rs.getString("articulo.nombre");
				pCompra = rs.getDouble("articulo.precio_compra");
				pVenta = rs.getDouble("articulo.pvp");
				stock = rs.getInt("articulo.disponibles");
				minimo = rs.getInt("articulo.cant_minima");
				categoria = rs.getString("categoria.nombre");

				String _codigo = String.valueOf(codigo);
				String _pc = String.valueOf(pCompra);
				String _pvp = String.valueOf(pVenta);
				String _disp = String.valueOf(stock);
				String _min = String.valueOf(minimo);

				articulos[filasMatriz][0] = _codigo;
				articulos[filasMatriz][1] = nombre;
				articulos[filasMatriz][2] = _pc;
				articulos[filasMatriz][3] = _pvp;
				articulos[filasMatriz][4] = _disp;
				articulos[filasMatriz][5] = _min;
				articulos[filasMatriz][6] = categoria;

				filasMatriz++;

			}

			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return articulos;

	}

	static public int tamanyoColumnNames() {
		int size = columnNames.length;
		return size;
	}

}
