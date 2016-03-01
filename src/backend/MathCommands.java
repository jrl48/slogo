package backend;
import java.util.HashMap;
import java.util.Map;

import methodInterfaces.BooleanAnd;
import methodInterfaces.BooleanEqual;
import methodInterfaces.BooleanGreater;
import methodInterfaces.BooleanLess;
import methodInterfaces.BooleanNot;
import methodInterfaces.BooleanNotequal;
import methodInterfaces.BooleanOr;
import methodInterfaces.MathAtan;
import methodInterfaces.MathCos;
import methodInterfaces.MathDifference;
import methodInterfaces.MathInterface;
import methodInterfaces.MathLog;
import methodInterfaces.MathMinus;
import methodInterfaces.MathPi;
import methodInterfaces.MathPow;
import methodInterfaces.MathProduct;
import methodInterfaces.MathQuotient;
import methodInterfaces.MathRandom;
import methodInterfaces.MathRemainder;
import methodInterfaces.MathSin;
import methodInterfaces.MathSum;
import methodInterfaces.MathTan;

public class MathCommands{
	private Map<String, MathInterface> instructions = new HashMap<String, MathInterface>();
	
	public MathCommands(){
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
		
		
		
		//instructions.put(, value)
	}
	
	public double callCommand(String s, double[] args){
		MathInterface command = instructions.get(s);
		return command.executeCommand(args);
	}
	
}