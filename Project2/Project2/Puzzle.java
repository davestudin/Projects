/*@author Dave Studin
 * Puzzle.java
 * 
 * Version:
 * $Id: Puzzle.java,v 1.2 2014/11/06 18:29:54 das2416 Exp $
 * 
 * Comments:
 * $Log: Puzzle.java,v $
 * Revision 1.2  2014/11/06 18:29:54  das2416
 * changes:
 * *added comments
 *
 * Revision 1.1  2014/11/03 06:13:04  das2416
 * First Commit
 *
 */
import java.util.ArrayList;
public interface Puzzle {
	
	/*
	 * Gets the starting location
	 */
	int getStart();
	
	/*
	 * Gets the ending location
	 */
	int getGoal();
	
	/*
	 * Sets the neighbors of a given hour as the a plus or minus form the originial modded 
	 * by the number of hours
	 */
	ArrayList<java.lang.Integer> getNeighbors(int config);
}
