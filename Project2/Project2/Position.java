/*
 * @author Dave Studin
 * Position.java
 * 
 * Version:
 * $Id: Position.java,v 1.5 2015/04/06 03:31:00 das2416 Exp $
 * 
 * Comments:
 * $Log: Position.java,v $
 * Revision 1.5  2015/04/06 03:31:00  das2416
 * final commit
 *
 * Revision 1.4  2015/04/06 03:18:16  das2416
 * fixed all exclamation points
 *
 * Revision 1.3  2015/04/06 02:50:43  das2416
 * final commit
 *
 * Revision 1.2  2015/04/02 18:54:34  das2416
 * test
 *
 * Revision 1.1  2015/04/01 18:53:41  das2416
 * Got program to run successfully, still need to work on design
 *
 */
import java.util.ArrayList;

public abstract class Position <E> {

	private E start;
	private E finish;
	
	private int score;
	
	
	public E getStart(){
		return start;
	}
	
	public E getFinish(){
		return finish;
	}
	
	public int getScore(){
		return score;
	}
	
	public void setScore(int num){
		score = num;
	}
	
	public abstract boolean isFinished();
	
	public abstract ArrayList<E> getNeighbors();
	
	public abstract E computerTurn();
		
	public abstract E playerTurn();
}

