package Interfaces;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

import BaseDeDatos.Consultas;

public class pintarTabla extends DefaultTableCellRenderer {
	//
	String cantS = null;
	String cantminS = null;
	int linea = 0;
	int primerValor = 0;
	int segundoValor = 0;

	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		setEnabled(table == null || table.isEnabled());
		this.setHorizontalAlignment(SwingConstants.CENTER);
		this.setOpaque(true);

		if (Integer.valueOf(table.getValueAt(row, 4).toString()) <= Integer.valueOf(table.getValueAt(row, 5).toString())) {

			this.setForeground(Color.white);
			this.setBackground(Color.red);

		} else {
			this.setForeground(Color.black);
			this.setBackground(Color.white);
		}

		super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		return this;
	}
}