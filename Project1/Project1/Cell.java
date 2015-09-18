
import java.util.ArrayList;

public abstract class Cell {

	private ArrayList<Integer> coord = new ArrayList<Integer>();
	private boolean intersection = false;
	private boolean isHit = false;
	private boolean hidden = false;
	
	/**
	 * An abstract constructor to create an abstract Cell object
	 * @param coord
	 */
	public Cell(ArrayList<Integer> coord){
		this.coord = coord;
	}
	
	/**
	 * Sets the value of this cell as "Fired upon"
	 */
	public void hit(){
		isHit = true;
	}
	
	/**
	 * Method that returns if this cell has been fired upon or not
	 * @return boolean
	 */
	public boolean isHit(){
		return isHit;
	}
	
	/**
	 * Method that sets the visual representation of this cell as water
	 */
	public void hide(){
		hidden = true;
	}
	
	/**
	 * Method that returns the int value of the char that will be represented on the board
	 * @return int
	 */
	public abstract int boardVisual();
	
	/**
	 * Method that determines if this cell is the same as another coordinate
	 * @param ArrayList<Integer> a
	 * @return boolean
	 */
	public boolean intersection(ArrayList<Integer> a){
		if(a.equals(coord)){
			intersection = true;
		}
		return intersection;
	}
}
