/*
 * @author Dave Studin
 * Water.java
 * 
 * Version:
 * $Id: Water.java,v 1.3 2015/03/07 23:59:59 das2416 Exp $
 * 
 * Comments:
 * $Log: Water.java,v $
 * Revision 1.3  2015/03/07 23:59:59  das2416
 * changes:
 * * added comments
 *
 */
import java.util.ArrayList;

public class Water extends Cell{

	//Coordinate of this cell
	private ArrayList<Integer> coord = new ArrayList<Integer>();
	//Whether or not this cell has been hit by a missile
	private boolean isHit = false;
	//Whether or not this cell is hidden
	private boolean hidden = false;
	
	/**
	 * Creates an instance of a Water cell object
	 * @param aCoord
	 */
	public Water(ArrayList<Integer> aCoord){
		super(aCoord);
	}
	
	/**
	 * Hits this cell with missile and changes its state
	 */
	public void hit(){
		isHit = true;
	}
	
	/**
	 * Returns true if this cell has been hit by a missile
	 */
	public boolean isHit(){
		return isHit;
	}
	
	/**
	 * Hides this cell and changes its visual state
	 */
	public void hide(){
		hidden = true;
	}
	
	/**
	 * Returns an int value representing the state of this cell
	 */
	public int boardVisual(){
		if(isHit){
			return 79;
		}
		else if(hidden){
			return 45;
		}
		return 45;
	}
}
