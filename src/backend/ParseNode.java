package backend;

import java.util.*;

public class ParseNode {
	    
	    private String myName;
	    private double myValue;
	    private List<ParseNode> myChildren;


	    public ParseNode(String name) {
	        myValue = 0;
	        myName = name;
	        myChildren = new ArrayList<ParseNode>();
	    }
	    
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
