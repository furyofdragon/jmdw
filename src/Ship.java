
public class Ship {
	private double L;
	private double B;
	private double T;
	private double Tf;
	private double delta;
	private double I;
	private double Speed;
	private String stype;
	private String sclass;
	private double hwaves;
	
	// метод задания значений
	void set(double L, double B, double T, double Tf, double delta, double I, double Speed, double hwaves, String stype, String sclass){
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
	
	double getLBT(){
		double LBT = L*B*T;
		return LBT;
	}
	
	double g = 9.81;
	double D = g*delta*L*B*T;
	double kd = Math.exp(-1.6*(1-delta));
	double eps = 0.0;
	double eta = 0.0;
	double omsr = 0.0;
	double v1 = 0.0;
	

	
}
