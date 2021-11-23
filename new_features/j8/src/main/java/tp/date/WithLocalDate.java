package tp.date;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class WithLocalDate {

	public static void main(String[] args) {
		// Manipulations basiques de dates (via des nouveautees java 8):
		LocalDate nowDate = LocalDate.now();	 
		System.out.println("basic/default display : today (nowDate local) is " + nowDate);
		
		LocalDateTime now = LocalDateTime.now();
        System.out.println("basic/default display of LocalDateTime.now() :" + now);
        
        Instant instantT =  now.atZone(ZoneId.systemDefault()).toInstant();
        long nbMsSinceFirstJanuary1970GMT = instantT.toEpochMilli();
        System.out.println("instantT.toEpochMilli() , timestamp , nb ms since 1970-01-01 00:00:00 GMT=" + nbMsSinceFirstJanuary1970GMT);
        
       String sdate_fr_1=nowDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy",Locale.FRENCH));
 	   System.out.println("sdate_fr_1="+sdate_fr_1); //exemple: 13/09/2020
 	   String sdate_fr_2=nowDate.format(DateTimeFormatter.ofPattern("EEEE, dd MMMM yyyy",Locale.FRENCH)); // EEEE means fullname of weekday
	   System.out.println("sdate_fr_2="+sdate_fr_2);//exemple: dimanche, 13 septembre 2020
 	   

 	   LocalDate localDatePremiersPasSurLune=LocalDate.of(1969,07,21);
	   System.out.println("premiers pas sur la lune : "+localDatePremiersPasSurLune);
        
		
		// Manipulations plus elaborees de Date (avec nouveautees java 8):
	   LocalTime nowTime = LocalTime.now();	 System.out.println("now is " + nowTime);
	}

}
