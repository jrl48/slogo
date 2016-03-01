package backend;
import methodInterfaces.TurtleInterface;
import methodInterfaces.TurtleRight;
import methodInterfaces.TurtleSetHeading;
import methodInterfaces.TurtleSetPosition;
import methodInterfaces.TurtleSetTowards;
import methodInterfaces.TurtleShowTurtle;
import methodInterfaces.TurtleLeft;
import methodInterfaces.TurtlePenDown;
import methodInterfaces.TurtlePenUp;

import java.util.HashMap;
import java.util.Map;

import frontend.Display;
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
import methodInterfaces.TurtleBackward;
import methodInterfaces.TurtleClearScreen;
import methodInterfaces.TurtleForward;
import methodInterfaces.TurtleHideTurtle;
import methodInterfaces.TurtleHome;

public class Commands{
	private Map<String, TurtleInterface> turtleInstructions = new HashMap<String, TurtleInterface>();
	private Map<String, MathInterface> instructions = new HashMap<String, MathInterface>();
	
	public Commands(){
		createInstructionMap();
		createTurtleMap();

		//instructions.put(, value)
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
	
	private void createTurtleMap() {
		turtleInstructions.put("Forward", new TurtleForward());
		turtleInstructions.put("Backward", new TurtleBackward());
		turtleInstructions.put("Right", new TurtleRight());
		turtleInstructions.put("SetHeading", new TurtleSetHeading());
		turtleInstructions.put("SetTowards", new TurtleSetTowards());
		turtleInstructions.put("SetPosition", new TurtleSetPosition());
		turtleInstructions.put("PenDown", new TurtlePenUp());
		turtleInstructions.put("PenUp", new TurtlePenDown());
		turtleInstructions.put("ShowTurtle", new TurtleShowTurtle());
		turtleInstructions.put("HideTurtle", new TurtleHideTurtle());
		turtleInstructions.put("Home", new TurtleHome());
		turtleInstructions.put("ClearScreen", new TurtleClearScreen());
	}
	
	public double callCommand(String s, double[] args){
		MathInterface command = instructions.get(s);
		return command.executeCommand(args);
	}
	
}