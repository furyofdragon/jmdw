package main;

public class Ship {
	private static final double g = 9.81;
	
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
	
	private double Mv;
	private double kp;
	private double My;
	private double Mdw;
	
	private double k0;
	private double k1;
	private double k2;
	private double k3;
	
	private double D;
	private double eps;
	private double eta;
	private double omsr;
	private double v1;
	private double kd;
	private double kt;
	private double kb;
	private double ks;
	private double sigma;
	private double kmu;
	private double kv;
	private double omk;
	private double Tp;
	private double b0;
	private double fi0;
	private double fi1;
	private double v0;
	private double ky;
	
	
	// метод задания значений
	void set(double L, double B, double T, double Tf, double delta, double I, double Speed, double h, int stype, int sclass){
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
	}
	
	void setL(double L) {this.L = L;}
	
	void setB(double B) {this.B = B;}
	
	void setT(double T) {this.T = T;}
	
	void setTf(double Tf) {this.Tf = Tf;}
	
	void setdelta(double delta) {this.delta = delta;}
	
	void setI(double I) {this.I = I;}
	
	void setSpeed(double Speed) {this.Speed = Speed;}
	
	void seth(double h) {this.h = h;}
	
	void setstype(int stype) {this.stype = stype;}
	
	void setsclass(int sclass) {this.sclass = sclass;}
	
	
	// метод расчёта
	void solve(){
		if ((sclass == 1) || (sclass ==2) || (sclass ==3) || (sclass == 4)) {
			
			D = g*delta*L*B*T;
			kd = Math.exp(-1.6*(1-delta));
			//double eps = 0.0;
			eps = 0.0;
			//double eta = 0.0;
			eta = 0.0;
			//double omsr = 0.0;
			omsr = 0.0;
			//double v1 = 0.0;
			v1 = 0.0;
			
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
			kt = Math.exp(-1.14*(T/(eta*h))*(delta/(2.0*delta+1.0)));
			kb = (1.0- Math.exp(-0.19*delta*B/(eta*h)))*(eta*h/(0.19*delta*B));
			Mv = 0.255*eps*kd*kt*kb*B*L*L*h;
			
			ks = 0.0;
			switch (stype) {
			case 1: ks = 123e4; break;   //cargo
			case 2: ks = 117e4; break;   //passenger
			case 3: ks = 104e4; break;   //tower
			}
			
			sigma = ks*Math.sqrt(I/((1.2+B/(3*T))*D*L*L*L));
			kmu = 0.0612*(1-0.047*sigma-0.0077*sigma*sigma);
			if (kmu < 0) kmu = 0;
			
			kv =0.0;
			double k = 10*eta*h/L;
			if (k <= 0.3) {
				kv = 1+1.18*k-28*k*k+61.7*k*k*k;
			} else {
				kv = 0.5-0.8*(k-0.3)+(k-0.3)*(k-0.3);
			}
			omk = omsr+1.92*kv*Speed/L;
			k = omk/sigma;
			kp = 1+k*k/Math.sqrt((1-k*k)*(1-k*k)+(2*kmu*k)*(2*kmu*k));
			
			Tp = eta*h*(0.68+0.21*kv*Speed/Math.sqrt(L));
			b0 = 4.32*Math.sqrt(delta*(B/L)*(T/L));
			fi0 = 1-1.03*b0+b0*b0-0.417*b0*b0*b0;
			fi1 = 0;
			if (Tf <= Tp) fi1 = 1;
			if ((Tp < Tf)&&(Tf < 1.5*Tp)) fi1 = 3-2*Tf/Tp;
			if (1.5*Tp < Tf) fi1 = 0;
			v0 = (0.336+0.104*kv*Speed/Math.sqrt(L))*v1+0.024*kv*Speed;
			ky = 5.3e-4*fi0*sigma*v0;
			My = ky*fi1*D*L;
			
			Mdw = kp*Mv+My;
			
			} else {
			
			Mv = 0.0;
			kp = 0.0;
			My = 0.0;
			k0 = Math.min(1.24-1.7*B/L, 1.0);
			k1 = 0;
			k2 = Math.max(2.0-20.0*Tf/L, 1.0);
			k3 = 0;
			double k3_1 = 1.0;
			double k3_2 = 0;
			switch (sclass) {
			case 5:   //class пїЅ-пїЅпїЅ
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
			case 6:   //class пїЅ-пїЅпїЅ
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
			case 7:   //class пїЅ-пїЅпїЅ
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
	double getMdw(){return Mdw;}
	
	double getMv(){return Mv;}
	
	double getkp(){return kp;}
	
	double getMy(){return My;}
	
	double getk0(){return k0;}
	
	double getk1(){return k1;}
	
	double getk2(){return k2;}
	
	double getk3(){return k3;}
	
	
	// Возврат исходных данных для печати
	double getL(){return L;}
	
	double getB(){return B;}
	
	double getT(){return T;	}
	
	double getTf(){return Tf;}
	
	double getdelta(){return delta;}
	
	double getI(){return I;}
	
	double getSpeed(){return Speed;}
	
	double geth(){return h;}
	
	int getstype(){return stype;}
	
	int getsclass(){return sclass;}
	
	
	// Возврат промежуточных данных расчёта
	double get_D()		{return D;}
	double get_eps()	{return eps;}
	double get_eta()	{return eta;}
	double get_kd()		{return kd;}
	double get_kt()		{return kt;}
	double get_kb()		{return kb;}

	double get_omsr()	{return omsr;}
	double get_kv()		{return kv;}
	double get_omk()	{return omk;}
	double get_ks()		{return ks;}
	double get_sigma()	{return sigma;}
	double get_kmu()	{return kmu;}
	
	double get_v1()		{return v1;}
	double get_b0()		{return b0;}
	double get_fi0()	{return fi0;}
	double get_v0()		{return v0;}
	double get_ky()		{return ky;}
	double get_Tp()		{return Tp;}
	double get_fi1()	{return fi1;}

}

