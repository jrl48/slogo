package methodinterfaces;

public class MathLog implements MathInterface {
	public double executeCommand(double[] args){
		return Math.log(args[0]);
	}
}
