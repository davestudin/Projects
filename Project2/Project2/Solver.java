import java.util.ArrayList;


/*@author Dave Studin
 * Solver.java
 * 
 * Versions:
 * $Id: Solver.java,v 1.11 2015/04/06 03:31:01 das2416 Exp $
 * 
 * Comments:
 * $Log: Solver.java,v $
 * Revision 1.11  2015/04/06 03:31:01  das2416
 * final commit
 *
 * Revision 1.10  2015/04/06 03:28:28  das2416
 * tried to fix incompatible types, switched to Integer instead of int
 *
 * Revision 1.9  2015/04/06 03:27:03  das2416
 * tried to fix incompatible types
 *
 * Revision 1.8  2015/04/06 03:18:16  das2416
 * fixed all exclamation points
 *
 * Revision 1.7  2015/04/06 02:50:43  das2416
 * final commit
 *
 * Revision 1.6  2015/04/02 18:54:35  das2416
 * test
 *
 * Revision 1.5  2014/11/06 18:41:07  das2416
 * changes:
 * *fixed bug in solve() method
 *
 * Revision 1.4  2014/11/06 18:30:30  das2416
 * changes:
 * *added constructor comment
 *
 * Revision 1.3  2014/11/06 18:25:39  das2416
 * changes:
 * *Added constructor and shells of methods
 *
 * Revision 1.2  2014/11/06 06:26:55  das2416
 * one more commit
 *
 * Revision 1.1  2014/11/03 06:13:08  das2416
 * First Commit
 *
 */


//import java.util.ArrayList;

/*
 * @author Dave Studin
 * Solver2.java
 * 
 * Version:
 * $Id: Solver.java,v 1.11 2015/04/06 03:31:01 das2416 Exp $
 * 
 * Comments:
 * $Log: Solver.java,v $
 * Revision 1.11  2015/04/06 03:31:01  das2416
 * final commit
 *
 * Revision 1.10  2015/04/06 03:28:28  das2416
 * tried to fix incompatible types, switched to Integer instead of int
 *
 * Revision 1.9  2015/04/06 03:27:03  das2416
 * tried to fix incompatible types
 *
 * Revision 1.8  2015/04/06 03:18:16  das2416
 * fixed all exclamation points
 *
 * Revision 1.7  2015/04/06 02:50:43  das2416
 * final commit
 *
 * Revision 1.1  2015/04/02 18:54:33  das2416
 * test
 *
 */

public class Solver{
	
	
	public <X> ArrayList<Object> evaluatePosition(Position<X> aPosition){
		
		
		ArrayList<Object> evaluated = new ArrayList<Object>(3);
		int highScore=-2;
		int bestPosition;
		int bestMove;
		
		//ArrayList of [current position, move made, score for that position]
		if(aPosition.isFinished()){
			aPosition.setScore(1);
			evaluated.add(0, aPosition.getStart());
			evaluated.add(1, aPosition.getFinish());
			evaluated.add(2, aPosition.getScore());
			highScore = aPosition.getScore();
			return evaluated;
		}
		else{
			ArrayList<X> neighbors = aPosition.getNeighbors();
			//reverse board if necessary
			for(X pos: neighbors){
				TakeAway newPosition1 = null;
				if(pos instanceof Integer){
					newPosition1 = new TakeAway((Integer)pos);
				}
								
				ArrayList<Object> posEvaluated = evaluatePosition(newPosition1);
				int newScore = (-1)*(int)posEvaluated.get(2);
							
				if(newScore > highScore){
					
					bestPosition = newPosition1.getStart();
					highScore = newScore;
					bestMove = (Integer)aPosition.getStart() - (Integer)newPosition1.getStart();
					evaluated.add(0, bestPosition);
					evaluated.add(1, bestMove);
					evaluated.add(2, highScore);				
				}
			}		
		}
		return evaluated;
	}
}
