
public class Ship {
	private double L;
	private double B;
	private double T;
	private double Tf;
	private double delta;
	private double I;
	private double Speed;
	private char[] stype;
	private char[] sclass;
	private double hwaves;
	
	// метод задания значений
	void set(double L, double B, double T, double Tf, double delta, double I, double Speed, double hwaves, char[] stype, char[] sclass){
		this.L = L;
		this.B = B;
		this.T = T;
		this.Tf = Tf;
		this.delta = delta;
		this.I = I;
		this.Speed = Speed;
		this.hwaves = hwaves;
		this.stype = stype;
		this.sclass = sclass;
	}
}
