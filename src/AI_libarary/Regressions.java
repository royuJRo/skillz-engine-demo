package AI_libarary;

public class Regressions {
	
	private Regressions() {
		
	}
	
	
	public static double[] OptimizeLinearRegression(double[] X, double[] Y, double learningRate, int generations) {
		return LinearRegression.OptimizeLinearRegression(X, Y, learningRate, generations);
	}
	
	public static double[] getOptimizeSlopeAvoidingMovment(double[] X, double[] Y, double learningRate, int generations, double iniSlope, double b) {
		return AvoidedeMovment.OptimizeW(X, Y, iniSlope, b, learningRate, generations);
		
	}
	
	
	
	
	
	
	private static class LinearRegression{
		/**
		 * 
		 * @param x x's array
		 * @param y y's array
		 * @return [w, b]
		 */
		
		private static double function(double x, double w, double b) {
			return x*w + b;
		}
		
		
		private static double[] getPredictions(double[] X,double w,double b) {
			double[] preds = new double[X.length];
			for (int i = 0; i < preds.length; i++) {
				preds[i] = function(X[i], w, b);
			}
			
			return preds;
		}
		
		
		private static double getCostDerivativeW(double[] X, double[] Y, double w, double b) {
			double[] preds = getPredictions(X, w, b);
			
			double djw = 0;
			for (int i = 0; i < preds.length; i++) {
				djw += (preds[i] - Y[i]) * X[i];
			}
			
			return djw/X.length;
		}
		
		private static double getCostDerivativeB(double[] X, double[] Y, double w, double b) {
			double[] preds = getPredictions(X, w, b);
			
			double djw = 0;
			for (int i = 0; i < preds.length; i++) {
				djw += (preds[i] - Y[i]);
			}
			
			return djw/X.length;
		}
		
		
		private static double[] propagate(double[] X, double[] Y, double w, double b, double learningRate) {
			double w1 = w - learningRate*getCostDerivativeW(X, Y, w, b);
			double b1 = b - learningRate*getCostDerivativeB(X, Y, w, b)*10;
			
			double[] gradients = {w1, b1};
			
			return gradients;
		}
		
		private static double cost(double[] predictions, double[] Y) {
			double j = 0;
			for(int i = 0;i<predictions.length; i++) {
				j += Math.pow((predictions[i] - Y[i]), 2)*0.5;
			}
			
			return j/predictions.length;
		}
		
		
		public static double[] OptimizeLinearRegression(double[] X, double[] Y, double learningRate, int generations) {
			double w = 0;
			double b = 0;
			
			for(int i = 0; i<generations;i++) {
				double[] gradients = propagate(X, Y, w, b, learningRate);
				w = gradients[0];
				b = gradients[1];
			}
			
			double[] args = {w, b};
			return args;
		}
		
		
	}
	
	private static class AvoidedeMovment{
		private static double function(double x, double w, double b) {
			return x*w + b;
		}
		
		
		private static double[] getPredictions(double[] X,double w,double b) {
			double[] preds = new double[X.length];
			for (int i = 0; i < preds.length; i++) {
				preds[i] = function(X[i], w, b);
			}
			
			return preds;
		}
		
		
		private static double getCostDerivativeW(double[] X, double[] Y, double w, double b) {
			double[] preds = getPredictions(X, w, b);
			
			double djw = 0;
			for (int i = 0; i < preds.length; i++) {
				djw += (getCostActivationDerivative(preds[i]-Y[i])) * X[i];
			}
			
			return djw/X.length;
		}
		
		
		
		private static double propagateW(double[] X, double[] Y, double w, double b, double learningRate) {
			double w1 = w + learningRate*getCostDerivativeW(X, Y, w, b);
			
			return w1;
		}
		
		private static double costActivation(double z) {
			return 0.001*(-0.01*Math.pow(z, 3) + 0.5*Math.pow(z, 2));
		}
		
		private static double getCostActivationDerivative(double z) {
			//(-0.03*(d**2))*0.001+d*0.001
			return -0.03*Math.pow(z, 2)*0.001 + z*0.001;
		}
		
		
		private static double cost(double[] predictions, double[] Y) {
			double j = 0;
			for(int i = 0;i<predictions.length; i++) {
				j += costActivation((predictions[i] - Y[i]));
			}
			
			return j/predictions.length;
		}
		
		
		public static double[] OptimizeW(double[] X, double[] Y, double w1, double b, double learningRate, int generations) {
			double w = w1;
			
			for(int i = 0; i<generations;i++) {
				double gradient = propagateW(X, Y, w, b, learningRate);
				w = gradient;
			}
			
			double[] args = {w, b};
			return args;
		}
		
	}
	
}
