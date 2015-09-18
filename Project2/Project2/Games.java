/*
 * @author Dave Studin
 * Games.java
 * 
 * Version:
 * $Id: Games.java,v 1.3 2015/04/06 02:50:40 das2416 Exp $
 * 
 * Comments:
 * $Log: Games.java,v $
 * Revision 1.3  2015/04/06 02:50:40  das2416
 * final commit
 *
 * Revision 1.2  2015/04/02 18:54:32  das2416
 * test
 *
 * Revision 1.1  2015/04/01 18:52:23  das2416
 * Made it run through a game succesfully, structure and design is still shaky
 *
 */
import java.util.ArrayList;

public interface  Games <E>{

	public E getStart();
	
	public E getFinish();
	
	public boolean isFinish();
	
	public int getScore();
	
	public boolean compare(E a, E b);
	
	public ArrayList<E> getNeighbors(E parent);
	
	public ArrayList<E> getNeighbors();
	
	public void computerTurn();
	
	public void playerTurn();
	
	public Integer getPlayer1Score();
	
	public Integer getPlayer2Score();
	
	public boolean isFinished();
	
	//public ArrayList<E> getBestNeighbors(E parent);
	
}
