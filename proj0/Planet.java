public class Planet{
        public double xxPos;
        public double yyPos;
        public double xxVel;
        public double yyVel;
        public double mass;
        public String imgFileName;
	public static final double G = 6.67e-11; 

        public Planet(double xP, double yP, double xV, double yV, double m, String img){
                xxPos = xP;
                yyPos = yP;
                xxVel = xV;
                yyVel = yV;
		mass = m;
                imgFileName = img;
        }
        public Planet(Planet b){
                xxPos = b.xxPos;
                yyPos = b.yyPos;
                xxVel = b.xxVel;
                yyVel = b.yyVel;
                mass = b.mass;
                imgFileName = b.imgFileName;
        }

	public double calcDistance(Planet a){
		return Math.sqrt(Math.pow(this.xxPos - a.xxPos, 2) + Math.pow(this.yyPos - a.yyPos, 2));
	}
	public double calcForceExertedBy(Planet a){
		return G * this.mass * a.mass / Math.pow(calcDistance(a), 2);
	}
	public double calcForceExertedByX(Planet a){
		double dx = a.xxPos - this.xxPos;
		return calcForceExertedBy(a) * dx / calcDistance(a);
	}
	public double calcForceExertedByY(Planet a){
		double dy = a.yyPos - this.yyPos;
		return calcForceExertedBy(a) * dy / calcDistance(a);
	}
	public double calcNetForceExertedByX(Planet[] b){
		int i = 0;
		double NetForceX = 0;
		while(i < b.length){
			if(this.equals(b[i])){
				i++;
				continue;
			}
			NetForceX += calcForceExertedByX(b[i]);
			i++;
		}
		return NetForceX;
	}
	public double calcNetForceExertedByY(Planet[] b){
		int i = 0;
		double NetForceY = 0;
		while(i < b.length){
			if(this.equals(b[i])){
				i++;
				continue;
			}
			NetForceY += calcForceExertedByY(b[i]);
			i++;
		}
		return NetForceY;
	}
	public void update(double dt, double Fx, double Fy){
		double ax = Fx / this.mass;
		double ay = Fy / this.mass;
		this.xxVel += dt * ax;
		this.yyVel += dt * ay;
		this.xxPos += dt * xxVel;
		this.yyPos += dt * yyVel;
		
	}
	public void Draw(){
		StdDraw.picture(xxPos, yyPos, "images/" + imgFileName);
		StdDraw.show();
	}
}

