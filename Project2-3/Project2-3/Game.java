/*
 * @author Dave Studin
 * Games.java
 * 
 * Version:
 * $Id: Game.java,v 1.3 2015/05/16 03:21:14 das2416 Exp $
 * 
 * Comments:
 * $Log: Game.java,v $
 * Revision 1.3  2015/05/16 03:21:14  das2416
 * *** empty log message ***
 *
 * Revision 1.2  2015/05/15 12:15:31  das2416
 * got it to sorta work
 *
 * Revision 1.1  2015/04/29 00:26:33  das2416
 * Got a working player1 and player2 and isFInished() method is working properly
 *
 * Revision 1.1  2015/04/18 15:55:20  das2416
 * first commit
 *
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
import java.util.HashMap;
import java.util.Scanner;


public interface  Game <E>{
	
	
	//Get Methods
	public E getStart();
	public E getFinish();
	public E getCurrentPosition();
	public E getPreviousPosition();
	public int getCurrentScore();
	public int getEvaluatedScore();
	public E getMove(E position, E position2);
	
	//Voided Methods
	public void setScore(int num);
	public void setCurrentPosition(E config);
	public void setPreviousPosition(E config);
	public void flipBoard();
	
	//Flag Methods
	public boolean isFinished();
	public int whoseTurn();
	
	//Function Methods
	//public Game<E> copyGame(E start, E current, E previous, int evaluatedScore);
	public ArrayList<Game<E>> getNeighbors(Game<E> parent, int playerTurn);	
	public Integer computerTurn();	
	public Integer playerTurn();
	
	//toString method
	public String toString();
	/*
	 *Current game position 
	 *Current game score
	 *Evaluated game score
	 * 
	 */

}
