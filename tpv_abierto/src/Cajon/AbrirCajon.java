package Cajon;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Enumeration;

import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;

public class AbrirCajon {
	private OutputStream Output = null;
	SerialPort serialPort;
	private final String PORT_NAME = "COM28";
	private static final int TIME_OUT = 2000;
	private static final int DATA_RATE = 9600;
	
	public void ArduinoConnection() {
		 
		CommPortIdentifier portId = null;
		Enumeration portEnum = CommPortIdentifier.getPortIdentifiers();
		 
		while (portEnum.hasMoreElements()) {
		CommPortIdentifier currPortId = (CommPortIdentifier) portEnum.nextElement();
		 
		if (PORT_NAME.equals(currPortId.getName())) {
		portId = currPortId;
		break;
		}
		}
		 
		if (portId == null) {
		 
		//System.exit(1);
		return;
		}
		 
		try {
		 
		serialPort = (SerialPort) portId.open(this.getClass().getName(), TIME_OUT);
		 
		serialPort.setSerialPortParams(DATA_RATE,
		SerialPort.DATABITS_8,
		SerialPort.STOPBITS_1,
		SerialPort.PARITY_NONE);
		 
		Output = serialPort.getOutputStream();
		 
		} catch (Exception e) {
		 
		//System.exit(1);
		}
		 
		}
	
	public void EnviarDatos(String data) {

		try {
		Output.write(data.getBytes());

		} catch (IOException e) {

		
		}
	}
}