package backend;

import java.util.HashMap;
import java.util.Map;
import methodInterfaces.*;
import frontend.MultipleTurtles;

public class Commands{

	private Map<String, MathInterface> instructions = new HashMap<String, MathInterface>();
	
	public Commands(){
		createInstructionMap();
	}
	
	private void createInstructionMap() {
		instructions.put("Sum", new MathSum());
		instructions.put("Difference", new MathDifference());
		instructions.put("Product", new MathProduct());
		instructions.put("Quotient", new MathQuotient());
		instructions.put("Remainder",  new MathRemainder());
		instructions.put("Minus", new MathMinus());
		instructions.put("Random", new MathRandom());
		instructions.put("Sine", new MathSin());
		instructions.put("Cosine", new MathCos());
		instructions.put("Tangent", new MathTan());
		instructions.put("ArcTangent", new MathAtan());
		instructions.put("NaturalLog", new MathLog());
		instructions.put("Power", new MathPow());
		instructions.put("Pi", new MathPi());
		instructions.put("LessThan", new BooleanLess());
		instructions.put("GreaterThan", new BooleanGreater());
		instructions.put("Equal", new BooleanEqual());
		instructions.put("NotEqual", new BooleanNotequal());
		instructions.put("And", new BooleanAnd());
		instructions.put("Or", new BooleanOr());
		instructions.put("Not", new BooleanNot());
	}

	
	public double callCommand(String s, double[] args, MultipleTurtles myTurtles){
		if (instructions.containsKey(s)) {
			MathInterface command = instructions.get(s);
			return command.executeCommand(args);
		} else {
			return myTurtles.executeCommand(s, args);
		}	
	}
	
}