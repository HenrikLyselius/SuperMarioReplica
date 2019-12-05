package computation;

import java.util.List;

import screenObjects.ScreenObject;
import screenObjects.StationaryObject;

public class Computation {

	List <StationaryObject> stationaryList;
	
	
	public void setStationaryObjectList(List<StationaryObject> list) {
		stationaryList = list;
	}
	
	
	
	
	
	// Finds through binary search the index for an object that has a xValue equal to 
	// the player's xValue. Or the index for the object closest to the player. 
	
	public int findIndex(int xValue, List<? extends ScreenObject> list) { 	
																
		int start = 0;										 	
		int end = list.size() - 1;								 
		while(true) {
			ScreenObject so = list.get((end + start) / 2);
			if(Math.abs(xValue - so.getXValue()) <= 50 || end - start < 2) {
				return list.indexOf(so);
			}
			else if(xValue < so.getXValue()) {
				end = list.indexOf(so);
			}
			else if(xValue > so.getXValue()) {
				start = Math.max(0, list.indexOf(so) );
			}
		}
	}
	
	
	
	public int sizeOfMovementRightwards(ScreenObject so) {
		return Math.min(so.getSpeed(), freeSpaceOnRightSide(so, stationaryList));
	}
	  
	  
	public int sizeOfMovementLeftwards(ScreenObject so) {
		return Math.min(so.getSpeed(), freeSpaceOnLeftSide(so, stationaryList));
	}

	  
	  
	
	public int freeSpaceOnLeftSide(ScreenObject object1, List<? extends ScreenObject> list) {
		
		int freeSpace = 100;
		for(ScreenObject object2: list) {
			if(objectsWouldCollideInXDirection(object2, object1) &&
			Objec1IsLeftOfObject2(object2, object1) &&
			distanceBetweenObjectsXWise(object2, object1) < freeSpace && 
			object2.equals(object1) == false) {
				   		
				freeSpace = distanceBetweenObjectsXWise(object2, object1);
			}
		}
		   return freeSpace;
	}
	  
	   
	
	public int freeSpaceOnRightSide(ScreenObject object1, List<? extends ScreenObject> list) {
		
		int freeSpace = 100;
		for(ScreenObject object2: list) {
			if(objectsWouldCollideInXDirection(object1, object2) &&
			Objec1IsLeftOfObject2(object1, object2) &&
			distanceBetweenObjectsXWise(object1, object2) < freeSpace &&
			object1.equals(object2) == false) {
				   		
				freeSpace = distanceBetweenObjectsXWise(object1, object2);
			}
		}
		return freeSpace;
	}
	   
	   
	   
	
	public int freeSpaceBelow(ScreenObject object1, List<? extends ScreenObject> list) {
		
		int freeSpace = 100;
		for(ScreenObject object2: list) {
			if(objectsWouldCollideInYDirection(object1, object2) &&
			object1IsAboveObject2(object1, object2) &&
			distanceBetweenObjectsYWise(object1, object2) < freeSpace) {
				   		
				freeSpace = distanceBetweenObjectsYWise(object1, object2);
			}
		}
		return freeSpace;
	}
	   
	
	

	
	   
	   
	
	public boolean Objec1IsLeftOfObject2(ScreenObject Object1, ScreenObject Object2) {
			   
		if(Object1.getXValue() < Object2.getXValue()) {
			return true;
		}
		return false;
	}
		  
		  
	
	
	public boolean standsOnSomething(ScreenObject so) {
			
		for(ScreenObject b: stationaryList) {
			if(so.getYValue() == b.getYValue() - so.getHeight() && objectsWouldCollideInYDirection(so, b)) {
				return true;
			}
		}
		return false;
	}
		  
		  
		  
	
	public boolean isThereABlockUnderThisPoint(int x, int y) {
		
		for(ScreenObject so: stationaryList) {
			if(x >= so.getXValue() && x <= so.getXValue() + so.getWidth() && y == so.getYValue()) {
				return true;
			}
		}
		return false;
	}
	   
	   
	   
	
	public boolean object1IsAboveObject2(ScreenObject Object1, ScreenObject Object2) {
		   
		if(Object1.getYValue() < Object2.getYValue()) {
			return true;
		}
		return false;
	}
	   
	   
	
	
	public boolean objectsWouldCollideInYDirection(ScreenObject Object1, ScreenObject Object2) {
		
		if((Object1.getXValue() <= Object2.getXValue() && 
		Object1.getXValue() + Object1.getWidth() > Object2.getXValue()) ||
		
		(Object1.getXValue() >  Object2.getXValue() && 
		Object2.getXValue() + Object2.getWidth() > Object1.getXValue())){
			   		
			return true;
		}
		return false;
	}
	   
	   

	
	public int distanceBetweenObjectsYWise(ScreenObject Object1, ScreenObject Object2) {
	
		if(object1IsAboveObject2(Object1, Object2)) {
			return Math.max(Object2.getYValue() - (Object1.getYValue() + Object1.getHeight()), 0);
		}
		   return Math.max(Object1.getYValue() - (Object2.getYValue() + Object2.getHeight()), 0);
	}
	   
	   
	   
	
	public boolean objectsWouldCollideInXDirection(ScreenObject Object1, ScreenObject Object2) {
		   
		if((Object1.getYValue() < Object2.getYValue() &&
		Object2.getYValue() - Object1.getYValue() < Object1.getHeight()) ||
				
		(Object1.getYValue() >= Object2.getYValue() &&
		Object1.getYValue() - Object2.getYValue() < Object2.getHeight())){
			   		
			return true;
		}
		return false;
	}
	   
	   
	   
	
	public int distanceBetweenObjectsXWise(ScreenObject Object1, ScreenObject Object2) {
		   
		if(Objec1IsLeftOfObject2(Object1, Object2)) {
			return Math.max(Object2.getXValue() - (Object1.getXValue() + Object1.getWidth()), 0);
		}
		return Math.max(Object1.getXValue() - (Object2.getXValue() + Object2.getWidth()), 0);
	}
	
}
