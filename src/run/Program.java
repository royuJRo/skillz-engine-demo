package run;


import AI_libarary.Regressions;
import Graphic_Motor.FrameMannger;

public class Program {
	
	public static void main(String[] args) {
		FrameMannger frameMannger = new FrameMannger();
		frameMannger.turnOn();
		
		/*double[] x = {1 ,2 ,3 ,4 ,5, 6, 7};
		double[] y = {13, 19, 25, 31, 37, 43, 49};
		
		long time1 = System.currentTimeMillis(); 
		
		double[] R_Args = Regressions.OptimizeLinearRegression(x, y, 0.01, 1000);
		
		long time2 = System.currentTimeMillis();
		
		System.out.println("t:"+ (time2-time1));
		
		System.out.println("[w: "+R_Args[0]+"; b: "+R_Args[1]+"]");*/
		
		
		/*double[] x = {2, 10, 5, 10, 10, 5, 5, 5, 5};
		double[] y = {17, 10, 10, 45, 15, 30, 30, 30, 30};
		
		double optimizeSlope[] = Regressions.getOptimizeSlopeAvoidingMovment(x, y, 0.01, 1000, 2, 10);
		
		System.out.println(optimizeSlope[0]);
		System.out.println(optimizeSlope[1]);*/
	}

}
