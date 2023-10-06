public class NBody{
	public static String imageToDraw = "./images/starfield.jpg";
	public static double readRadius(String file){
		In in = new In(file);
		in.readInt();
		double Radius = in.readDouble();
		return Radius;
	}
	public static Planet[] readPlanets(String file){
		In in = new In(file);
		int num = in.readInt();
		Planet[] allPlanets = new Planet[num];
		in.readDouble();
		int i = 0;
		while(i < num){
			double Px = in.readDouble();
			double Py = in.readDouble();
			double Vx = in.readDouble();
			double Vy = in.readDouble();
			double m = in.readDouble();
			String img = in.readString();
			allPlanets[i] = new Planet(Px, Py, Vx, Vy, m, img);
			i++;
		}
		return allPlanets;
	}
	public static void drawBackground(double R){
		StdDraw.setScale(-R, R);
		StdDraw.clear();
		StdDraw.picture(-100, 100, imageToDraw);
		StdDraw.show();
		StdDraw.pause(2000);
	}
	public static void main(String[] args){
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];
		double R = NBody.readRadius(filename);
		Planet[] universe = NBody.readPlanets(filename);
		
		StdDraw.enableDoubleBuffering();
		double t = 0.0;
		while(t < T){
			double[] xForces = new double[universe.length];
			double[] yForces = new double[universe.length];
			for(int i = 0; i < universe.length; i++){
				xForces[i] = universe[i].calcNetForceExertedByX(universe);
				yForces[i] = universe[i].calcNetForceExertedByY(universe);
				universe[i].update(dt, xForces[i], yForces[i]);
			}
			drawBackground(R);
			for(int i = 0; i < universe.length; i++){
				universe[i].draw();
			}
			StdDraw.show();
			StdDraw.pause(10);
			t += dt;
		}
		StdOut.printf("%d\n", universe.length);
		StdOut.printf("%.2e\n", R);
		for (int i = 0; i < universe.length; i++) {
    			StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
					universe[i].xxPos, universe[i].yyPos, universe[i].xxVel,
					universe[i].yyVel, universe[i].mass, universe[i].imgFileName);   
		}
	}
}
