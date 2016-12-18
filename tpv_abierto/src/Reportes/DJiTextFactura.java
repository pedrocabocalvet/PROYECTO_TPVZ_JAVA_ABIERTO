package Reportes;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import Interfaces.Utilidades;
import Objetos.Mesa;

public class DJiTextFactura {

	Document document;
	FileOutputStream fileOutputStream;
	
	String rutaLogo;
	String rutaGuardarTicketPrevio;
	String nombreRestaurante;

	Image image;
	
	Font fontContenido;
	Font fontTitulos;
	
	PdfPTable table;
	PdfPCell cell;
	
	Utilidades utilidad;
	
	boolean imprimir;
	boolean mostrar;
	
	String textoFinal;

	
	public DJiTextFactura(Mesa mesa, boolean muestro, boolean imprimo, String nombre, String apellidos, String calle
			, String cp , String nif, String poblacion, String provincia, String telefono){
		
		
		textoFinal="ESTA FACTURA SIRVE COMO JUSTIFICANTE DE PAGO, MUCHAS GRACIAS POR SU VISITA Y VUELVA PRONTO.";
		mostrar = muestro;		// si esta true lo mostrara en pantalla false no
		imprimir = imprimo;		// si esto esta a true imprimira el documento si esta a false no lo imprimira
		
		utilidad = new Utilidades();
		nombreRestaurante = "RESTAURANTE DEBBUGERS";	// nombre del restaurante
		
		
		rutaLogo="images/logo1.png";
		rutaGuardarTicketPrevio="./ReportesFactura/";
		Calendar fecha = new GregorianCalendar();
		
		rutaGuardarTicketPrevio = rutaGuardarTicketPrevio + fecha.get(Calendar.YEAR) + fecha.get(Calendar.MONTH)
		+ fecha.get(Calendar.DAY_OF_MONTH) + fecha.get(Calendar.HOUR_OF_DAY) + fecha.get(Calendar.MINUTE)
		+ fecha.get(Calendar.SECOND);
		
		rutaGuardarTicketPrevio = rutaGuardarTicketPrevio + ".pdf";
		
		
		
		   // Creacion del documento con los margenes
		   document = new Document(PageSize.A4, 35, 30, 50, 50);

		   // El archivo pdf que vamos a generar
		   try {
			fileOutputStream = new FileOutputStream(rutaGuardarTicketPrevio);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		   
		   // Obtener la instancia del PdfWriter
		   try {
			PdfWriter.getInstance(document, fileOutputStream);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		   // Abrir el documento
		   document.open();
		   
		   // Obtenemos el logo de datojava
		   try {
			image = Image.getInstance(rutaLogo);
		} catch (BadElementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		   image.scaleAbsolute(80f, 80f);

		   // Crear las fuentes para el contenido y los titulos
		   Font fontContenido = FontFactory.getFont(
		     FontFactory.TIMES_ROMAN.toString(), 11, Font.NORMAL,
		     BaseColor.DARK_GRAY);
		   Font fontTitulos = FontFactory.getFont(
		     FontFactory.TIMES_BOLDITALIC, 14,  Font.BOLD,
		     BaseColor.BLACK);
		   
		   Font fontPrincipal = FontFactory.getFont(
				     FontFactory.TIMES_BOLDITALIC, 20,  Font.UNDERLINE,
				     BaseColor.BLUE);
		   
		// Creacion de una tabla
		   PdfPTable table = new PdfPTable(1);

		   // Agregar la imagen anterior a una celda de la tabla
		   PdfPCell cell = new PdfPCell(image);

		   // Propiedades de la celda
		   cell.setColspan(5);
		   cell.setBorderColor(BaseColor.WHITE);
		   cell.setHorizontalAlignment(Element.ALIGN_RIGHT);

		   // Agregar la celda a la tabla
		   table.addCell(cell);
		   
		// Agregar la tabla al documento
		   try {
			document.add(table);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		   
		// Creacion del parrafo
		   Paragraph paragraph = new Paragraph();
		   paragraph.add(new Phrase(nombreRestaurante,fontPrincipal));
			// Agregar saltos de linea
		   paragraph.add(new Phrase(Chunk.NEWLINE));
		   paragraph.add(new Phrase(Chunk.NEWLINE));
		   paragraph.add(new Phrase(Chunk.NEWLINE));
		   paragraph.add(new Phrase(Chunk.NEWLINE));

		   // Agregar un titulo con su respectiva fuente
		   paragraph.add(new Phrase("FACTURA A NOMBRE DE "+nombre+" "+apellidos, fontTitulos)); 
		   paragraph.add(new Phrase(Chunk.NEWLINE));
		   paragraph.add(new Phrase(calle+", "+poblacion+", ("+provincia+")")); 
		   paragraph.add(new Phrase(Chunk.NEWLINE));
		   paragraph.add(new Phrase(cp+". TELEFONO: "+telefono));
		   paragraph.add(new Phrase(Chunk.NEWLINE));
		   paragraph.add(new Phrase(nif));
		   
		// Agregar saltos de linea
		   paragraph.add(new Phrase(Chunk.NEWLINE));
		   paragraph.add(new Phrase(Chunk.NEWLINE));
		   
		   // aqui añadimos el nombre de la mesa
		   String nombreMesa = mesa.getNombreMesa();
		   paragraph.add(new Phrase(nombreMesa+" :"));
		   
		   paragraph.add(new Phrase(Chunk.NEWLINE));
		   
		   
		   paragraph.add(new Phrase("CANTIDAD"));
		   paragraph.add(Chunk.TABBING);
		   paragraph.add(new Phrase("ARTICULO"));
		   paragraph.add(Chunk.TABBING);
		   paragraph.add(Chunk.TABBING);
		   paragraph.add(new Phrase("PRECIO"));
		   paragraph.add(Chunk.TABBING);
		   paragraph.add(new Phrase("TOTAL\n"));
		   
		   paragraph.add(new Phrase("----------------------------------------------------------------------\n"));
		   
		   for(int i = 0; i < mesa.getDtm().getRowCount(); i++){
			   int cantidad = (int) mesa.getDtm().getValueAt(i, 0);
			   String articulo = (String) mesa.getDtm().getValueAt(i, 1);
			   double precio = (double) mesa.getDtm().getValueAt(i, 2);
			   
			   double precioUnidad = utilidad.round(precio/cantidad, 2);
 
			   paragraph.add(new Phrase(""+cantidad));
			   paragraph.add(Chunk.TABBING);
			   paragraph.add(Chunk.TABBING);
			   paragraph.add(new Phrase(articulo));
			   paragraph.add(Chunk.TABBING);
			   paragraph.add(Chunk.TABBING);
			   paragraph.add(new Phrase(""+precioUnidad));
			   paragraph.add(Chunk.TABBING);
			   paragraph.add(Chunk.TABBING);
			   paragraph.add(new Phrase(""+utilidad.round(precio,2)+"\n"));
			   
			   
		   }
		   paragraph.add(new Phrase("----------------------------------------------------------------------\n"));
		   paragraph.add(new Phrase(Chunk.NEWLINE));
		   paragraph.add(new Phrase(Chunk.NEWLINE));
		   double iva = mesa.getTotal() * 0.07;
		   double base = mesa.getTotal() * 0.93;
		   paragraph.add(new Phrase("    BASE: "));
		   paragraph.add(Chunk.TABBING);
		   paragraph.add(new Phrase(""+utilidad.round(base, 2)));
		   paragraph.add(new Phrase(Chunk.NEWLINE));
		   paragraph.add(new Phrase("I.V.A 7%: "));
		   paragraph.add(Chunk.TABBING);
		   paragraph.add(new Phrase(""+utilidad.round(iva, 2)));
		   paragraph.add(new Phrase(Chunk.NEWLINE));
		   paragraph.add(new Phrase("   TOTAL: "));
		   paragraph.add(Chunk.TABBING);
		   paragraph.add(new Phrase(""+utilidad.round(mesa.getTotal(), 2)));
		   paragraph.add(new Phrase(Chunk.NEWLINE));
		   paragraph.add(new Phrase(Chunk.NEWLINE));
		   paragraph.add(new Phrase(Chunk.NEWLINE));
		   paragraph.add(new Phrase(textoFinal,fontContenido));
		   
		   
		   
		   // Agregar el parrafo al documento
		   try {
			document.add(paragraph);
		} catch (DocumentException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}  
 
		   // Cerrar el documento
		   document.close();

		   // Abrir el archivo
		   File file = new File(rutaGuardarTicketPrevio);
		try {
			Desktop.getDesktop().open(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		   
		   
		 if(imprimir){
			 utilidad.imprimirFile(file);
		 }
	
	}

}

