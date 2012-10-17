package main;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

import messages.Messages;

public class PrintResults {

	void printResultsToHtml() {
		try {
			PrintWriter pw = new PrintWriter("jmdw_results.html");
			
			pw.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 Transitional//RU\">");
			pw.println("<HTML><BODY>");
			pw.println("<TABLE border=1 cellSpacing=0 cellPadding=0 width=100%><TBODY>");
			pw.println("<TR><TD align=\"center\"><B>Input data</B></TD></TR>");
			pw.println("<TR><TD>" + Messages.getString("jmdw_main.grpShipsClass.text") + "</TD><TD>");
			int sclass = jmdw_main.ship.getsclass();
			switch (sclass){
			case 1: pw.println(Messages.getString("jmdw_main.button_M.text")); break;
			case 2: pw.println(Messages.getString("jmdw_main.button_O.text")); break;
			case 3: pw.println(Messages.getString("jmdw_main.button_R.text")); break;
			case 4: pw.println(Messages.getString("jmdw_main.button_L.text")); break;
			case 5: pw.println(Messages.getString("jmdw_main.button_MSP.text")); break;
			case 6: pw.println(Messages.getString("jmdw_main.button_MPR.text")); break;
			case 7: pw.println(Messages.getString("jmdw_main.button_OPR.text")); break;
			}
			pw.println("</TD></TR>");
			if ((sclass == 1)|(sclass == 2)|(sclass == 3)|(sclass == 4)) { // river ship
				pw.println("<TR><TD>" + Messages.getString("jmdw_main.grpShipsType.text") + "</TD><TD>");
				int stype = jmdw_main.ship.getstype();
				switch (stype) {
				case 1: pw.println(Messages.getString("jmdw_main.btnCargoShip.text")); break;
				case 2: pw.println(Messages.getString("jmdw_main.btnPassangerShip.text")); break;
				case 3: pw.println(Messages.getString("jmdw_main.btnTowingShip.text")); break;
				}
				pw.println("</TD></TR>");
			}
			pw.println("<TR><TD>" + Messages.getString("jmdw_main.lblLM.text")				+ "</TD><TD>" + Double.toString(jmdw_main.ship.getL())     + "</TD></TR>");
			pw.println("<TR><TD>" + Messages.getString("jmdw_main.lblBM.text")				+ "</TD><TD>" + Double.toString(jmdw_main.ship.getB())     + "</TD></TR>");
			pw.println("<TR><TD>" + Messages.getString("jmdw_main.lblTM.text")				+ "</TD><TD>" + Double.toString(jmdw_main.ship.getT())     + "</TD></TR>");
			pw.println("<TR><TD>" + Messages.getString("jmdw_main.lblTfM.text")				+ "</TD><TD>" + Double.toString(jmdw_main.ship.getTf())    + "</TD></TR>");
			pw.println("<TR><TD>" + Messages.getString("jmdw_main.label.text")				+ "</TD><TD>" + Double.toString(jmdw_main.ship.getdelta()) + "</TD></TR>");
			pw.println("<TR><TD>" + Messages.getString("jmdw_main.lblShipsSpeedKmh.text")	+ "</TD><TD>" + Double.toString(jmdw_main.ship.getSpeed()) + "</TD></TR>");
			pw.println("<TR><TD>" + Messages.getString("jmdw_main.lblMomentOfInertia.text")	+ "</TD><TD>" + Double.toString(jmdw_main.ship.getI())     + "</TD></TR>");
			pw.println("<TR><TD>" + Messages.getString("jmdw_main.lblHeightOfWaves.text")	+ "</TD><TD>" + Double.toString(jmdw_main.ship.geth())     + "</TD></TR>");
			pw.println("<TR><TD align=\"center\"><B>Results</B></TD></TR>");
			if ((sclass == 1)|(sclass == 2)|(sclass == 3)|(sclass == 4)) { // river ship
				pw.println("<TR><TD>" + Messages.getString("jmdw_main.lblMv.text")	+ "</TD><TD>" + Double.toString(jmdw_main.ship.getMv())	+ "</TD></TR>");
				pw.println("<TR><TD>" + Messages.getString("jmdw_main.lblKp.text")	+ "</TD><TD>" + Double.toString(jmdw_main.ship.getkp())	+ "</TD></TR>");
				pw.println("<TR><TD>" + Messages.getString("jmdw_main.lblMy.text")	+ "</TD><TD>" + Double.toString(jmdw_main.ship.getMy())	+ "</TD></TR>");
			} else{ // 5, 6 or 7 - coastal
				pw.println("<TR><TD>k0</TD><TD>" + Double.toString(jmdw_main.ship.getk0())	+ "</TD></TR>");
				pw.println("<TR><TD>k1</TD><TD>" + Double.toString(jmdw_main.ship.getk1())	+ "</TD></TR>");
				pw.println("<TR><TD>k2</TD><TD>" + Double.toString(jmdw_main.ship.getk2())	+ "</TD></TR>");
				pw.println("<TR><TD>k3</TD><TD>" + Double.toString(jmdw_main.ship.getk3())	+ "</TD></TR>");
			}
			pw.println("<TR><TD><B>" + Messages.getString("jmdw_main.lblMdw.text")	+ "</B></TD><TD><B>" + Double.toString(jmdw_main.ship.getMdw()) + "</B></TD></TR>");
			
			pw.println("</TBODY></TABLE>");
			pw.println("</BODY></HTML>");
			
			pw.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
