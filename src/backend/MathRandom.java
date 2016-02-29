package backend;

public class MathRandom implements MathInterface {
	public double executeCommand(double[] args){
		return Math.random()*args[0];
	}
}
