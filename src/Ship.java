
public class Ship {
	private static final double g = 9.81;
	/*
	private double L;
	private double B;
	private double T;
	private double Tf;
	private double delta;
	private double I;
	private double Speed;
	private int stype;
	private int sclass;
	private double h;
	**/
	private double Mv;
	private double kp;
	private double My;
	private double Mdw;
	
	// метод задания значений
	void set(double L, double B, double T, double Tf, double delta, double I, double Speed, double h, int stype, int sclass){
		/*
		this.L = L;
		this.B = B;
		this.T = T;
		this.Tf = Tf;
		this.delta = delta;
		this.I = I;
		this.Speed = Speed;
		this.h = h;
		this.stype = stype;
		this.sclass = sclass;
		**/
		
		
		if ((sclass == 1) || (sclass ==2) || (sclass ==3) || (sclass == 4)) {
			
			double D = g*delta*L*B*T;
			double kd = Math.exp(-1.6*(1-delta));
			double eps = 0.0;
			double eta = 0.0;
			double omsr = 0.0;
			double v1 = 0.0;
			
			switch (sclass) {					// table 2.2.10 of RRRR
			case 1:   //class M
				eta = 1.000;
				if (h < 2.0) {
					eps = 1.000;
					omsr = 1.36;
					v1 = 4.43;
				}
				if ((2.0 <= h)&&(h < 2.5)) {
					eps = 1.000+(h-2.0)*(0.970-1.000)/0.5;
					omsr = 1.36+(h-2.0)*(1.22-1.36)/0.5;
					v1 = 4.43+(h-2.0)*(4.95-4.43)/0.5;
				}
				if ((2.5 <= h)&&(h < 3.0)) {
					eps = 0.970+(h-2.5)*(0.920-0.970)/0.5;
					omsr = 1.22+(h-2.5)*(1.11-1.22)/0.5;
					v1 = 4.95+(h-2.5)*(5.42-4.95)/0.5;
				}
				if (3.0 <= h) {
					eps = 0.920;
					omsr = 1.11;
					v1 = 5.42;
				}
				break;
			case 2:   //class O
				eta = 0.874;
				if (h < 1.5) {
					eps = 0.857;
					omsr = 1.69;
					v1 = 3.57;
				}
				if ((1.5 <= h)&&(h < 2.0)) {
					eps = 0.857+(h-1.5)*(0.805-0.857)/0.5;
					omsr = 1.69+(h-1.5)*(1.46-1.69)/0.5;
					v1 = 3.57+(h-1.5)*(4.14-3.57)/0.5;
				}
				if (2.0 < h) {
					eps = 0.805;
					omsr = 1.46;
					v1 = 4.14;
				}
				break;
			case 3:   //class P
				eta = 0.874;
				eps = 0.848;
				omsr = 1.88;
				v1 = 3.21;
				break;
			case 4:   //class L
				eta = 0.874;
				eps = 0.874;
				omsr = 2.68;
				v1 = 2.26;
				break;
			}
			double kt = Math.exp(-1.14*(T/(eta*h))*(delta/(2.0*delta+1.0)));
			double kb = (1.0- Math.exp(-0.19*delta*B/(eta*h)))*(eta*h/(0.19*delta*B));
			Mv = 0.255*eps*kd*kt*kb*B*L*L*h;
			
			double ks = 0.0;
			switch (stype) {
			case 1: ks = 123e4; break;   //cargo
			case 2: ks = 117e4; break;   //passenger
			case 3: ks = 104e4; break;   //tower
			}
			double sigma =ks*Math.sqrt(I/((1.2+B/(3*T))*D*L*L*L));
			double kmu = 0.0612*(1-0.047*sigma-0.0077*sigma*sigma);
			if (kmu < 0) kmu = 0;
			double kv =0.0;
			double k = 10*eta*h/L;
			if (k <= 0.3) {
				kv = 1+1.18*k-28*k*k+61.7*k*k*k;
			} else {
				kv = 0.5-0.8*(k-0.3)+(k-0.3)*(k-0.3);
			}
			double omk = omsr+1.92*kv*Speed/L;
			k = omk/sigma;
			kp = 1+k*k/Math.sqrt((1-k*k)*(1-k*k)+(2*kmu*k)*(2*kmu*k));
			
			double Tp = eta*h*(0.68+0.21*kv*Speed/Math.sqrt(L));
			double b0 = 4.32*Math.sqrt(delta*(B/L)*(T/L));
			double fi0 = 1-1.03*b0+b0*b0-0.417*b0*b0*b0;
			double fi1 = 0;
			if (Tf <= Tp) fi1 = 1;
			if ((Tp < Tf)&&(Tf < 1.5*Tp)) fi1 = 3-2*Tf/Tp;
			if (1.5*Tp < Tf) fi1 = 0;
			double v0 = (0.336+0.104*kv*Speed/Math.sqrt(L))*v1+0.024*kv*Speed;
			double ky = 5.3e-4*fi0*sigma*v0;
			My = ky*fi1*D*L;
			
			Mdw = kp*Mv+My;
			
			} else {
			
			Mv = 0.0;
			kp = 0.0;
			My = 0.0;
			double k0 = Math.min(1.24-1.7*B/L, 1.0);
			double k1 = 0;
			double k2 = Math.max(2.0-20.0*Tf/L, 1.0);
			double k3 = 0;
			double k3_1 = 1.0;
			double k3_2 = 0;
			switch (sclass) {
			case 5:   //class М-СП
				if (L <= 100.0)					k1 = 0.0147;			//table 2.1.4-1 RRRR
				if ((L > 100.0)&&(L <= 140.0))	k1 = 0.0147+(0.0137-0.0147)*(L-100.0)/40.0;
				if (L > 140.0)					k1 = 0.0137;
				if (L <= 60.0)					k3_2 = 0.914;
				if ((L > 60.0)&&(L <= 100.0)) 	k3_2 = 0.914+(0.870-0.914)*(L-60.0)/40.0;
				if ((L > 100.0)&&(L <= 140.0))	k3_2 = 0.870+(0.843-0.870)*(L-100.0)/40.0;
				if (L > 140.0)					k3_2 = 0.843;
				if (h < 3.0)					k3 = k3_2;
				if (h > 3.5)					k3 = k3_1;
				if ((h >= 3.0)&&(h <= 3.5))		k3 = k3_2+(k3_1-k3_2)*(h-3.0)/0.5;
				break;
			case 6:   //class М-ПР
				if (L <= 60.0) 					k1 = 0.0130;			//table 2.2.3-1 RRRR
				if ((L > 60.0)&&(L <= 100.0))	k1 = 0.0130+(0.0117-0.0130)*(L-60.0)/40.0;
				if ((L > 100.0)&&(L <= 140.0))	k1 = 0.0117+(0.0102-0.0117)*(L-100.0)/40.0;
				if (L > 140.0) 					k1 = 0.0102;
				if (L <= 25.0)					k3_2 = 0.915;
				if ((L > 25.0)&&(L <= 60.0)) 	k3_2 = 0.915+(0.887-0.915)*(L-25.0)/35.0;
				if ((L > 60.0)&&(L <= 100.0))	k3_2 = 0.887+(0.871-0.887)*(L-60.0)/40.0;
				if ((L > 100.0)&&(L <= 140.0))	k3_2 = 0.871+(0.839-0.871)*(L-100.0)/40.0;
				if (L > 140.0)					k3_2 = 0.839;
				if (h < 2.0)					k3 = k3_2;
				if (h > 2.5)					k3 = k3_1;
				if ((h >= 2.0)&&(h <= 2.5))		k3 = k3_2+(k3_1-k3_2)*(h-2.0)/0.5;
				break;
			case 7:   //class О-ПР
				if (L <= 60.0) 					k1 = 0.0154;			//table 2.3.2-1 RRRR
				if ((L > 60.0)&&(L <= 100.0))	k1 = 0.0154+(0.0114-0.0154)*(L-60.0)/40.0;
				if ((L > 100.0)&&(L <= 140.0))	k1 = 0.0114+(0.0089-0.0114)*(L-100.0)/40.0;
				if (L > 140.0) 					k1 = 0.0089;
				if (L <= 60.0)					k3_2 = 0.866;
				if ((L > 60.0)&&(L <= 100.0))	k3_2 = 0.866+(0.911-0.866)*(L-60.0)/40.0;
				if ((L > 100.0)&&(L <= 140.0))	k3_2 = 0.911+(0.841-0.911)*(L-100.0)/40.0;
				if (L > 140.0)					k3_2 = 0.841;
				if (h < 1.5)					k3 = k3_2;
				if (h > 2.0)					k3 = k3_1;
				if ((h >= 1.5)&&(h <= 2.0))		k3 = k3_2+(k3_1-k3_2)*(h-1.5)/0.5;
				break;	
			}
			Mdw = g*k0*k1*k2*k3*delta*B*L*L*h;
			}
		
	}
	
	
	// Возврат результатов расчёта
	double getMdw(){
		return Mdw;
	}
	
	double getMv(){
		return Mv;
	}
	
	double getkp(){
		return kp;
	}
	
	double getMy(){
		return My;
	}
	
}

