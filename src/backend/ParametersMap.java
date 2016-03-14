package backend;

import java.util.HashMap;
import java.util.Map;


public class ParametersMap {
	private Map<String, Integer> myParamMap;
	public ParametersMap(){
		myParamMap = new HashMap<String, Integer>();
		makeMap();
	}
	
	public void makeMap(){
		myParamMap.put("Forward", 1);
		myParamMap.put("Backward", 1);
		myParamMap.put("Left", 1);
		myParamMap.put("Right", 1);
		myParamMap.put("SetHeading", 1);
		myParamMap.put("SetTowards", 2);
		myParamMap.put("SetPosition", 2);
		myParamMap.put("PenDown", 0);
		myParamMap.put("PenUp", 0);
		myParamMap.put("ShowTurtle", 0);
		myParamMap.put("HideTurtle", 0);
		myParamMap.put("Home", 0);
		myParamMap.put("ClearScreen", 0);
		myParamMap.put("Xcoordinate", 0);
		myParamMap.put("Ycoordinate", 0);
		myParamMap.put("Heading", 0);
		myParamMap.put("IsPenDown", 0);
		myParamMap.put("IsShowing", 0);
		
		myParamMap.put("Sum", 2);
		myParamMap.put("Difference", 2);
		myParamMap.put("Product", 2);
		myParamMap.put("Quotient", 2);
		myParamMap.put("Remainder", 2);
		myParamMap.put("Minus", 1);
		myParamMap.put("Random", 1);
		myParamMap.put("Sine", 1);
		myParamMap.put("Cosine", 1);
		myParamMap.put("Tangent", 1);
		myParamMap.put("ArcTangent", 1);
		myParamMap.put("NaturalLog", 1);
		myParamMap.put("Power", 2);
		myParamMap.put("Pi", 0);
		
		myParamMap.put("LessThan", 2);
		myParamMap.put("GreaterThan", 2);
		myParamMap.put("Equal", 2);
		myParamMap.put("NotEqual", 2);
		myParamMap.put("And", 2);
		myParamMap.put("Or", 2);
		myParamMap.put("Not", 1);	
		
		myParamMap.put("ID", 0);
		myParamMap.put("Turtles", 0);
		myParamMap.put("SetBackground", 1);
		myParamMap.put("SetPenColor", 1);
		myParamMap.put("SetPenSize", 1);
		myParamMap.put("SetShape", 1);
		myParamMap.put("SetPalette", 4);
		myParamMap.put("PenColor", 0);
		myParamMap.put("GetShape", 0);
		myParamMap.put("Stamp", 0);
		myParamMap.put("ClearStamps", 0);
		
		
	}
	
	public int getNumParams(String s){
		if(myParamMap.containsKey(s)){
			return myParamMap.get(s);
		}
		return -1;
	}
	
	
}
