package backend;

import java.util.*;

public class ParseNode {
	    
	    private String myName;
	    private double myValue;
	    private int arrayValues[];
	    private List<ParseNode> myChildren;


	    public ParseNode(String name) {
	        myValue = 0;
	        myName = name;
	        arrayValues[] = new 
	        myChildren = new ArrayList<ParseNode>();
	    }
	    
//	    public ParseNode(String value, List<String> children ) {
//	        myValue = value;
//	        myChildren = children;
//	    }
	    
	    public void setValue(double value){
	    	myValue = value;
	    }
	    
	    public void setName(String name){
	    	myName = name;
	    }
	    
	    public double getValue(){
	    	return myValue;
	    }
	    
	    public String getName(){
	    	return myName;
	    }
	    
	    public void addChild(ParseNode node){
	        myChildren.add(node);
	    }
	    
	    public List<ParseNode> getChildren(){
	    	return myChildren;
	    }
	    
	    public void removeChildren(){
	    	myChildren.clear();
	    }
	    
	    


}
