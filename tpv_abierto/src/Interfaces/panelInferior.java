package Interfaces;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.mysql.jdbc.Blob;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.BevelBorder;
import javax.swing.text.TabExpander;

import BaseDeDatos.Consultas;
import Objetos.BotonArticulo;
import Objetos.Pool;
import Threads.AñadirTabAlTabbedPane;
import Threads.CargaImagenesCategoria;

public class panelInferior extends JPanel {
	Utilidades uti = new Utilidades();
	int altSup;
	int anchoPanel;
	int tamLadoImagen;
	int tamAltoImagen;
	JTabbedPane tabbedPane;
	
	ArrayList<JPanel> Tab; 
	int numeroGrupos;
	
	Consultas con;
	// los dos siguientes arrays iran juntos, en uno guardaremos las fotos y en el otro los codigos de la categoria
	// asi en cada posicion de los dos arrays en uno tendremos la imagen y en el otro el numero de la categoria q pertenece
	ImageIcon [] imagenesGrupos;
	int codigoCategoria [];
	// aqui guardaremos todos los botones articulos lo rellenaremos una y otra vez por cada categoria
	BotonArticulo botonesArticulos[];

	PanelMedio panelMedio; // Aqui guardaremos la listacomando para poder interactuar con ella
	
	public panelInferior(PanelMedio panelMedio) {

		
		Tab = new ArrayList<JPanel>();
		
		con = new Consultas();
		altSup = uti.redimensionarSegunPantallaAlto(50);	// alto del Jpanel
		anchoPanel = uti.redimensionarSegunPantallaAncho(100);	// ancho del Jpanl
		tamAltoImagen=10;	// aqui guardamos el alto en porcentaje de las imagenes del tabbed
		this.panelMedio = panelMedio; //Recibimos el panelMedio
		// aqui mandamos al panelMedio este panel inferior para que se lo guarde y interactue con el
		this.panelMedio.setPi(this);
	
		//   puede empezar aqui
		
		actualizarPanelArticulos();
		
		this.updateUI();
		
	}	
	

	// metodo que carga las imagenes de los grupos de articulos dentro del array
	public void cargarImagenesGrupos(){
		
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP); // Creamos un jTabbedPane para poder meter las familias de productos
		tabbedPane.setPreferredSize(new Dimension(anchoPanel, altSup));		// este metodo es el q redimensiona todo
		tabbedPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		GridBagConstraints gbc_tabbedPane = new GridBagConstraints();
		gbc_tabbedPane.insets = new Insets(0, 0, 5, 0);
		gbc_tabbedPane.fill = GridBagConstraints.BOTH;
		gbc_tabbedPane.gridx = 0;
		gbc_tabbedPane.gridy = 0;
		add(tabbedPane, gbc_tabbedPane);
		
		
		imagenesGrupos = new ImageIcon[numeroGrupos];
		codigoCategoria = new int[numeroGrupos];
		ResultSet rs=con.consultaImagenesGrupos();
		Blob blob;
		int x = 0;
		
		
		try {
			Pool pool = Pool.getInstancia();
			ThreadPoolExecutor exec = pool.getThreadPoolExecutor();
			while(rs.next()){
				/*
				// cogemos los blob de la base de datos y los codigos de categoria
				codigoCategoria[x]=rs.getInt("cod");
				blob = (Blob) rs.getBlob("imagen");
				// aqui transformamos un blob a un imageIcon
				ImageIcon imageIcon = new ImageIcon( blob.getBytes( 1L, (int) blob.length() ) );
				imagenesGrupos[x]=uti.redimensionarFoto(imageIcon,
						uti.redimensionarSegunPantallaAncho(tamLadoImagen),
						uti.redimensionarSegunPantallaAlto(tamAltoImagen));
				*/
				
				// Thread veia mas util meter en un thread la carga de arrays y redimensionamiento de fotos que la query.
				// ya que la query aunque la metiese en un Thread tendria que esperar a que acabase de todos modos,
				// asi que no tenia ninguna utilidad meterla en un Thread
				
				CargaImagenesCategoria cic = new CargaImagenesCategoria(codigoCategoria,rs.getInt("cod"), imagenesGrupos, (Blob)rs.getBlob("imagen"), x, tamLadoImagen, tamAltoImagen);
				Thread t1 = new Thread(cic);
				
				
				exec.execute(t1);
				
				
			
				x++;
			}
			
			pool.eliminaInstancia();
			try {
				while (!exec.awaitTermination(24L, TimeUnit.HOURS)) {
				    System.out.println("Not yet. Still waiting for termination");
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			rs.close();
			

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	// metodo que crea un array llenos de botonesArticulo de la categoria que le metas
	public BotonArticulo[] llenarArrayBotonesArticulos(int codigoCategoria){
		
		
		BotonArticulo ba[] = new BotonArticulo[con.numeroArticulosPorCategoria(codigoCategoria)];
		ResultSet rs=con.consultaImagenPrecioNombreArticulo(codigoCategoria);
		Blob blob;
		int x = 0;
		String nombre;
		double pvp;
		
		try {
			while(rs.next()){
				
				nombre = rs.getString("nombre");
				blob = (Blob) rs.getBlob("imagen");
				// aqui transformamos un blob a un imageIcon
				ImageIcon imageIcon = new ImageIcon( blob.getBytes( 1L, (int) blob.length() ) );
				imageIcon = uti.redimensionarFoto(imageIcon, uti.redimensionarSegunPantallaAncho(tamLadoImagen), uti.redimensionarSegunPantallaAlto(tamAltoImagen));
				pvp = rs.getDouble("pvp");
				ba[x]=new BotonArticulo(nombre,pvp,imageIcon);
				ba[x].addActionListener(panelMedio);
				x++;
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return ba;
		
	}
	
public void actualizarPanelArticulos(){
		iniciacion();
		Tab.clear();
		
		numeroGrupos = con.consultaNumeroGruposArticulo();	//aqui cogemos el número de grupos de artículos que hay

		if(numeroGrupos>8)		// aqui guardaremos el ancho de las imagenes, si hay mas de 8 imagenes que se acoplen
			tamLadoImagen=(100/numeroGrupos)-2;
		else
			tamLadoImagen=10;

		for(int x = 0; x < numeroGrupos; x++){
			JPanel tab = new JPanel();
			tab.setPreferredSize(new Dimension(100, 100));
			Tab.add(tab);
			
		}

		cargarImagenesGrupos();
		
		Pool pool = Pool.getInstancia();
		ThreadPoolExecutor exec = pool.getThreadPoolExecutor();
		
		// 1 for es para añadir al tabbedPane tantos tab como grupos de articulo haya, a cada tab le pondremos la imagen
		// de su grupo de articulo redimensionada y un layaut
		for(int x = 0; x < numeroGrupos; x++){
			
			/*
			tabbedPane.addTab("", uti.redimensionarFoto(imagenesGrupos[x],
					uti.redimensionarSegunPantallaAncho(tamLadoImagen), 
					uti.redimensionarSegunPantallaAlto(tamAltoImagen)), Tab.get(x));
			// aqui cambiaremos el numero de filas que queremos que haya de articulos dentro de cada tab
			Tab.get(x).setLayout(new GridLayout(2, 1, 0, 0));
			
			
			botonesArticulos=llenarArrayBotonesArticulos(codigoCategoria[x]);
			//aqui hacemos un for desde 0 hasta el numero de articulos que hay por cada categoria, y vamos poniendo
			// cada uno de los botonesArticulos por cada categoria
			for(int i=0; i < con.numeroArticulosPorCategoria(codigoCategoria[x]);i++){
				
				botonesArticulos[i].setIcon(botonesArticulos[i].getImagen());
				botonesArticulos[i].setOpaque(false);
				Tab.get(x).add(botonesArticulos[i]);
				Tab.get(x).validate();
			}	
				
			*/
			// Thread
			
			AñadirTabAlTabbedPane atatp = new AñadirTabAlTabbedPane(tabbedPane,imagenesGrupos[x],tamLadoImagen,tamAltoImagen,Tab.get(x),uti, codigoCategoria[x],panelMedio);
			Thread t2 = new Thread(atatp);
			exec.execute(t2);
	
		}
		
		pool.eliminaInstancia();
		try {
			while (!exec.awaitTermination(24L, TimeUnit.HOURS)) {
			    System.out.println("Not yet. Still waiting for termination");
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void borrarPanel(){
		
		tabbedPane.setVisible(false);
		tabbedPane.removeAll();
		
	}
	
	public void iniciacion(){
		setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		GridBagLayout gridBagLayout = new GridBagLayout();
		//gridBagLayout.columnWidths = new int[] { numeroGrupos};
		gridBagLayout.rowHeights = new int[] {altSup};
		gridBagLayout.columnWeights = new double[] { 1.0 };
		//gridBagLayout.rowWeights = new double[] { 1.0 };
		setLayout(gridBagLayout);
	}
	


}