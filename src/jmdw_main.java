import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;

public class jmdw_main {
	private static Text HeightOfWaves;
	private static Text text_L;
	private static Text text_B;
	private static Text text_T;
	private static Text text_Tf;
	private static Text text_delta;
	private static Text text_I;
	private static Text text_Speed;
	private static Text txtMomentOfInertia;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Display display = Display.getDefault();
		Shell shlJavaMdw = new Shell();
		shlJavaMdw.setSize(450, 400);
		shlJavaMdw.setText("Java MDW");

		Group grpShipsClass = new Group(shlJavaMdw, SWT.NONE);
		grpShipsClass.setText("Ship's class");
		grpShipsClass.setBounds(10, 10, 144, 138);

		Button button_0 = new Button(grpShipsClass, SWT.RADIO);
		button_0.setBounds(10, 23, 44, 16);
		button_0.setText("\u041C");

		Button button_1 = new Button(grpShipsClass, SWT.RADIO);
		button_1.setBounds(10, 45, 44, 16);
		button_1.setText("\u041E");

		Button button_2 = new Button(grpShipsClass, SWT.RADIO);
		button_2.setBounds(10, 67, 44, 16);
		button_2.setText("\u0420");

		Button button_3 = new Button(grpShipsClass, SWT.RADIO);
		button_3.setBounds(10, 89, 44, 16);
		button_3.setText("\u041B");

		Button button_4 = new Button(grpShipsClass, SWT.RADIO);
		button_4.setBounds(60, 23, 64, 16);
		button_4.setText("\u041C-\u0421\u041F");

		Button button_5 = new Button(grpShipsClass, SWT.RADIO);
		button_5.setBounds(60, 45, 64, 16);
		button_5.setText("\u041C-\u041F\u0420");

		Button button_6 = new Button(grpShipsClass, SWT.RADIO);
		button_6.setBounds(60, 67, 64, 16);
		button_6.setText("\u041E-\u041F\u0420");
		
		Label lblHeightOfWaves = new Label(shlJavaMdw, SWT.NONE);
		lblHeightOfWaves.setBounds(10, 176, 144, 15);
		lblHeightOfWaves.setText("Height of waves in meters");
		
		HeightOfWaves = new Text(shlJavaMdw, SWT.BORDER | SWT.RIGHT);
		HeightOfWaves.setBounds(10, 197, 144, 21);
		
		Group grpShipsType = new Group(shlJavaMdw, SWT.NONE);
		grpShipsType.setText("Ship's type");
		grpShipsType.setBounds(160, 10, 118, 138);
		
		Button btnCargoShip = new Button(grpShipsType, SWT.RADIO);
		btnCargoShip.setBounds(10, 23, 105, 16);
		btnCargoShip.setText("cargo ship");
		
		Button btnPassangerShip = new Button(grpShipsType, SWT.RADIO);
		btnPassangerShip.setBounds(10, 45, 105, 16);
		btnPassangerShip.setText("passanger ship");
		
		Button btnTowingShip = new Button(grpShipsType, SWT.RADIO);
		btnTowingShip.setBounds(10, 67, 105, 16);
		btnTowingShip.setText("towing ship");
		
		Group grpShipssCharacteristics = new Group(shlJavaMdw, SWT.NONE);
		grpShipssCharacteristics.setText("Ships's characteristics");
		grpShipssCharacteristics.setBounds(284, 10, 140, 138);
		
		Label lblLM = new Label(grpShipssCharacteristics, SWT.NONE);
		lblLM.setBounds(10, 24, 40, 15);
		lblLM.setText("L, m");
		
		Label lblBM = new Label(grpShipssCharacteristics, SWT.NONE);
		lblBM.setBounds(10, 45, 40, 15);
		lblBM.setText("B, m");
		
		Label lblTM = new Label(grpShipssCharacteristics, SWT.NONE);
		lblTM.setBounds(10, 66, 40, 15);
		lblTM.setText("T, m");
		
		Label lblTfM = new Label(grpShipssCharacteristics, SWT.NONE);
		lblTfM.setBounds(10, 87, 40, 15);
		lblTfM.setText("Tf, m");
		
		Label label = new Label(grpShipssCharacteristics, SWT.NONE);
		label.setBounds(10, 108, 40, 15);
		label.setText("\u03B4");
		
		text_L = new Text(grpShipssCharacteristics, SWT.BORDER | SWT.RIGHT);
		text_L.setBounds(56, 24, 76, 21);
		
		text_B = new Text(grpShipssCharacteristics, SWT.BORDER | SWT.RIGHT);
		text_B.setBounds(56, 45, 76, 21);
		
		text_T = new Text(grpShipssCharacteristics, SWT.BORDER | SWT.RIGHT);
		text_T.setBounds(56, 66, 76, 21);
		
		text_Tf = new Text(grpShipssCharacteristics, SWT.BORDER | SWT.RIGHT);
		text_Tf.setBounds(56, 87, 76, 21);
		
		text_delta = new Text(grpShipssCharacteristics, SWT.BORDER | SWT.RIGHT);
		text_delta.setBounds(56, 108, 76, 21);
		
		text_I = new Text(shlJavaMdw, SWT.BORDER | SWT.RIGHT);
		text_I.setBounds(284, 197, 140, 21);
		
		Label lblShipsSpeedKmh = new Label(shlJavaMdw, SWT.NONE);
		lblShipsSpeedKmh.setBounds(160, 176, 118, 15);
		lblShipsSpeedKmh.setText("Ship's speed, km/h");
		
		text_Speed = new Text(shlJavaMdw, SWT.BORDER | SWT.RIGHT);
		text_Speed.setBounds(160, 197, 118, 21);
		
		Group grpResults = new Group(shlJavaMdw, SWT.NONE);
		grpResults.setText("Results");
		grpResults.setBounds(10, 224, 414, 99);
		
		Label lblMv = new Label(grpResults, SWT.NONE);
		lblMv.setBounds(10, 20, 55, 15);
		lblMv.setText("Mv");
		
		Label lblKp = new Label(grpResults, SWT.NONE);
		lblKp.setBounds(10, 41, 55, 15);
		lblKp.setText("kp");
		
		Label lblMy = new Label(grpResults, SWT.NONE);
		lblMy.setBounds(10, 62, 55, 15);
		lblMy.setText("My");
		
		Label lblNewLabel = new Label(grpResults, SWT.BORDER | SWT.RIGHT);
		lblNewLabel.setBounds(71, 20, 127, 15);
		
		Label lblNewLabel_1 = new Label(grpResults, SWT.BORDER | SWT.RIGHT);
		lblNewLabel_1.setBounds(71, 41, 127, 15);
		
		Label lblNewLabel_2 = new Label(grpResults, SWT.BORDER | SWT.RIGHT);
		lblNewLabel_2.setBounds(71, 62, 127, 15);
		
		Label lblMdw = new Label(grpResults, SWT.NONE);
		lblMdw.setBounds(214, 41, 55, 15);
		lblMdw.setText("Mdw");
		
		final Label lblNewLabel_3 = new Label(grpResults, SWT.BORDER | SWT.RIGHT);
		lblNewLabel_3.setBounds(275, 41, 129, 15);
		
		Button btnSolve = new Button(shlJavaMdw, SWT.NONE);
		btnSolve.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				//String s = new String();
				//s = text_L.getText();
				double L = Double.parseDouble(text_L.getText());
				double B = Double.parseDouble(text_B.getText());
				double T = Double.parseDouble(text_T.getText());
				double Tf = Double.parseDouble(text_Tf.getText());
				double delta = Double.parseDouble(text_delta.getText());
				double I = Double.parseDouble(text_I.getText());
				double Speed = Double.parseDouble(text_Speed.getText());
				double hwaves = Double.parseDouble(HeightOfWaves.getText());
				char[] stype = (text_L.getText()).toCharArray();
				char[] sclass = (text_B.getText()).toCharArray();
				Ship ship = new Ship();
				ship.set(L, B, T, Tf, delta, I, Speed, hwaves, stype, sclass);
				double LBT = L*B*T;
				String s = Double.toString(LBT);
				lblNewLabel_3.setText(s);
				
			}
		});
		btnSolve.setBounds(10, 329, 200, 25);
		btnSolve.setText("Solve");
		
		Button btnSaveResults = new Button(shlJavaMdw, SWT.NONE);
		btnSaveResults.setBounds(224, 329, 200, 25);
		btnSaveResults.setText("Save results");
		
		txtMomentOfInertia = new Text(shlJavaMdw, SWT.READ_ONLY | SWT.MULTI);
		txtMomentOfInertia.setText("Moment of inertia\r\nof cross section, m^4");
		txtMomentOfInertia.setBounds(284, 161, 140, 30);

		shlJavaMdw.open();
		shlJavaMdw.layout();
		while (!shlJavaMdw.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
}
