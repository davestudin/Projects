/*
 * @author Dave Studin
 * ShipCell.java
 * 
 * Version:
 * $Id: ShipCell.java,v 1.3 2015/03/07 23:54:54 das2416 Exp $
 * 
 * Comments:
 * $Log: ShipCell.java,v $
 * Revision 1.3  2015/03/07 23:54:54  das2416
 * changes:
 * * added comments
 *
 */
import java.util.ArrayList;

public class ShipCell extends Cell{

	//the coordinate of the cell
	private ArrayList<Integer> coord = new ArrayList<Integer>();
	//if the cell is hit or not
	private boolean isHit = false;
	//the label of the ship that this cell contains
	private String label;
	//whether this cell is hidden or not
	private boolean hidden = false;
	
	/**
	 * Creates an instance of a ShipCell object
	 * @param aCoord
	 * @param label
	 */
	public ShipCell(ArrayList<Integer> aCoord, String label){
		super(aCoord);
		this.label = label;
	}
	
	/**
	 * Hides this cell and changes its visual state
	 */
	public void hide(){
		hidden = true;
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
	 * Returns an int value representing the state of this cell
	 */
	public int boardVisual(){
		if(isHit){
			return 88;
		}
		else if(hidden){
			return 45;
		}
		else{
			return (int)label.charAt(0);
		}
		
	}
	
	
}
