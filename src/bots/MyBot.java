package bots;

import Game.*;
import AI_libarary.Regressions;;

public class MyBot implements SkillzBot {

	@Override
	public void doTurn(Game game) {
		// TODO Auto-generated method stub
		Pirate myPirate = game.getAllMyLivingPirates()[0];
		Location target = new Location(500, 800);
		double iniSlope = ((double)(target.row-myPirate.getLocation().row))/((double)(target.col- myPirate.getLocation().col));
		double b = myPirate.getLocation().row - iniSlope*myPirate.getLocation().col;
		
		System.out.println("INIT slpoe : "+iniSlope);
		System.out.println("B : "+ b);
		
		double[] x = new double[game.getAllEnemyLivingPirates().length];
		double[] y = new double[game.getAllEnemyLivingPirates().length];
		
		for (int i = 0; i < game.getAllEnemyLivingPirates().length; i++) {
			x[i] = game.getAllEnemyLivingPirates()[i].getLocation().col;
			y[i] = game.getAllEnemyLivingPirates()[i].getLocation().row;
		}
		
		
		double optimizeSlope = Regressions.getOptimizeSlopeAvoidingMovment(x, y, 0.01, 1000, iniSlope, b)[0];
		System.out.println("Optimize slope : "+ optimizeSlope);
		
		
		myPirate.go(target);
		
		
		/*for(int i = 1;i<game.getAllMyLivingPirates().length;i++) {
			game.getAllMyLivingPirates()[i].go(game.getAllMyLivingPirates()[i-1]);
		}*/
		//game.getMyself().score += 10;
	}
	
	public static double getY(int x, double m, double b) {
		return x*m+b;
	}

}
