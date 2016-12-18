package Interfaces;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CurrentDate {
  public  String currentDate() {
	  String resultado;
	  Date myDate = new Date();
	  resultado = new SimpleDateFormat("dd-MM-yyyy").format(myDate);	   
	return resultado;

  }
}