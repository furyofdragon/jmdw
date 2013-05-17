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
			//pw.println("<formula notation=’TeX’>E=mc^2</formula>");
			pw.println("<TABLE border=1 cellSpacing=0 cellPadding=0 width=100%><TBODY>");
			pw.println("<TR><TD align=\"center\" colspan=\"2\"><B>" + Messages.getString("jmdw_main.InputData.text") + "</B></TD></TR>");
			pw.print("<TR><TD>" + Messages.getString("jmdw_main.grpShipsClass.text") + "</TD><TD>");
			int sclass = jmdw_main.ship.getsclass();
			switch (sclass){
			case 1: pw.print(Messages.getString("jmdw_main.button_M.text")); break;
			case 2: pw.print(Messages.getString("jmdw_main.button_O.text")); break;
			case 3: pw.print(Messages.getString("jmdw_main.button_R.text")); break;
			case 4: pw.print(Messages.getString("jmdw_main.button_L.text")); break;
			case 5: pw.print(Messages.getString("jmdw_main.button_MSP.text")); break;
			case 6: pw.print(Messages.getString("jmdw_main.button_MPR.text")); break;
			case 7: pw.print(Messages.getString("jmdw_main.button_OPR.text")); break;
			}
			pw.println("</TD></TR>");
			if ((sclass == 1)|(sclass == 2)|(sclass == 3)|(sclass == 4)) { // river ship
				pw.print("<TR><TD>" + Messages.getString("jmdw_main.grpShipsType.text") + "</TD><TD>");
				int stype = jmdw_main.ship.getstype();
				switch (stype) {
				case 1: pw.print(Messages.getString("jmdw_main.btnCargoShip.text")); break;
				case 2: pw.print(Messages.getString("jmdw_main.btnPassangerShip.text")); break;
				case 3: pw.print(Messages.getString("jmdw_main.btnTowingShip.text")); break;
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
			pw.println("<TR><TD align=\"center\" colspan=\"2\"><B>" + Messages.getString("jmdw_main.Results.text") + "</B></TD></TR>");
			if ((sclass == 1)|(sclass == 2)|(sclass == 3)|(sclass == 4)) { // river ship
				pw.println("<TR><TD>" + "ε"	+ "</TD><TD>" + Double.toString(jmdw_main.ship.get_eps())	+ "</TD></TR>");
				pw.println("<TR><TD>" + "η"	+ "</TD><TD>" + Double.toString(jmdw_main.ship.get_eta())	+ "</TD></TR>");
				pw.println("<TR><TD>" + "kδ"	+ "</TD><TD>" + Double.toString(jmdw_main.ship.get_kd())	+ "</TD></TR>");
				pw.println("<TR><TD>" + "kT"	+ "</TD><TD>" + Double.toString(jmdw_main.ship.get_kt())	+ "</TD></TR>");
				pw.println("<TR><TD>" + "kB"	+ "</TD><TD>" + Double.toString(jmdw_main.ship.get_kb())	+ "</TD></TR>");
				pw.println("<TR><TD><B>" + Messages.getString("jmdw_main.lblMv.text")	+ "</B></TD><TD><B>" + Double.toString(jmdw_main.ship.getMv())	+ "</B></TD></TR>");
				pw.println("<TR><TD>" + "ωcp"	+ "</TD><TD>" + Double.toString(jmdw_main.ship.get_omsr())	+ "</TD></TR>");
				pw.println("<TR><TD>" + "kv"	+ "</TD><TD>" + Double.toString(jmdw_main.ship.get_kv())	+ "</TD></TR>");
				pw.println("<TR><TD>" + "ωk"	+ "</TD><TD>" + Double.toString(jmdw_main.ship.get_omk())	+ "</TD></TR>");
				pw.println("<TR><TD>" + "ks"	+ "</TD><TD>" + Double.toString(jmdw_main.ship.get_ks())	+ "</TD></TR>");
				pw.println("<TR><TD>" + "σ"	+ "</TD><TD>" + Double.toString(jmdw_main.ship.get_sigma())	+ "</TD></TR>");
				pw.println("<TR><TD>" + "kμ"	+ "</TD><TD>" + Double.toString(jmdw_main.ship.get_kmu())	+ "</TD></TR>");
				pw.println("<TR><TD><B>" + Messages.getString("jmdw_main.lblKp.text")	+ "</B></TD><TD><B>" + Double.toString(jmdw_main.ship.getkp())	+ "</B></TD></TR>");
				pw.println("<TR><TD>" + "b0"	+ "</TD><TD>" + Double.toString(jmdw_main.ship.get_b0())	+ "</TD></TR>");
				pw.println("<TR><TD>" + "φ0"	+ "</TD><TD>" + Double.toString(jmdw_main.ship.get_fi0())	+ "</TD></TR>");
				pw.println("<TR><TD>" + "v1"	+ "</TD><TD>" + Double.toString(jmdw_main.ship.get_v1())	+ "</TD></TR>");
				pw.println("<TR><TD>" + "v0"	+ "</TD><TD>" + Double.toString(jmdw_main.ship.get_v0())	+ "</TD></TR>");
				pw.println("<TR><TD>" + "ky"	+ "</TD><TD>" + Double.toString(jmdw_main.ship.get_ky())	+ "</TD></TR>");
				pw.println("<TR><TD>" + "Tp"	+ "</TD><TD>" + Double.toString(jmdw_main.ship.get_Tp())	+ "</TD></TR>");
				pw.println("<TR><TD>" + "φ1"	+ "</TD><TD>" + Double.toString(jmdw_main.ship.get_fi1())	+ "</TD></TR>");
				pw.println("<TR><TD><B>" + Messages.getString("jmdw_main.lblMy.text")	+ "</B></TD><TD><B>" + Double.toString(jmdw_main.ship.getMy())	+ "</B></TD></TR>");
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
