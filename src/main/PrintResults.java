package main;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class PrintResults {

	void printResultsToHtml() {
		try {
			PrintWriter pw = new PrintWriter("results.txt");
			pw.println("PrintWriter is easy to use.");
			
			jmdw_main.ship.getMdw();
			pw.println("Mdw = " + Double.toString(jmdw_main.ship.getMdw()));
			
			pw.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
