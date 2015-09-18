import java.util.ArrayList;


/*@author Dave Studin
 * Solver.java
 * 
 * Versions:
 * $Id: Solver.java,v 1.3 2015/05/16 03:21:14 das2416 Exp $
 * 
 * Comments:
 * $Log: Solver.java,v $
 * Revision 1.3  2015/05/16 03:21:14  das2416
 * *** empty log message ***
 *
 * Revision 1.2  2015/05/15 12:15:31  das2416
 * got it to sorta work
 *
 * Revision 1.1  2015/04/29 00:26:34  das2416
 * Got a working player1 and player2 and isFInished() method is working properly
 *
 * Revision 1.2  2015/04/27 06:07:19  das2416
 * final commit
 *
 * Revision 1.1  2015/04/18 15:55:18  das2416
 * first commit
 *
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
 * $Id: Solver.java,v 1.3 2015/05/16 03:21:14 das2416 Exp $
 * 
 * Comments:
 * $Log: Solver.java,v $
 * Revision 1.3  2015/05/16 03:21:14  das2416
 * *** empty log message ***
 *
 * Revision 1.2  2015/05/15 12:15:31  das2416
 * got it to sorta work
 *
 * Revision 1.1  2015/04/29 00:26:34  das2416
 * Got a working player1 and player2 and isFInished() method is working properly
 *
 * Revision 1.2  2015/04/27 06:07:19  das2416
 * final commit
 *
 * Revision 1.1  2015/04/18 15:55:18  das2416
 * first commit
 *
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
	
	ArrayList<Object> evaluated = new ArrayList<Object>(3);

	public <X> ArrayList<Object> evaluatePosition(Game<X> game){
			
		ArrayList<Object> posEvaluated = new ArrayList<Object>(3);
		int highScore = -10;
		
		
		X bestPosition;
		X bestMove;
				
		//ArrayList of [current position, move made, score for that position]
		if(game.isFinished()){
			int aScore = game.getCurrentScore();
			game.setScore(aScore);
			posEvaluated.add(0, game.getStart());
			posEvaluated.add(1, game.getCurrentPosition());
			posEvaluated.add(2, game.getEvaluatedScore());
			return posEvaluated;
		
			
		}
		
		else{
			if(!game.isFinished()){
				
			
			ArrayList<Game<X>> neighbors = game.getNeighbors(game, -1);
			
			//Checks to see if the neighbor will have the highest possible score in order to return early
			for(Game<X> pos: neighbors){
				if(pos.isFinished()){
					int newScore = -1;
					
					
					bestPosition = pos.getCurrentPosition();
					highScore = newScore;
					game.setScore(newScore);
					bestMove = pos.getMove(game.getCurrentPosition(), bestPosition);
				
					if(!evaluated.isEmpty()){
						evaluated.remove(0);
						evaluated.add(0, bestPosition);
						evaluated.remove(1);
						evaluated.add(1, bestMove);
						evaluated.remove(2);
						evaluated.add(2, highScore);
						return evaluated;
					}
					else{						
						evaluated.add(0, bestPosition);
						evaluated.add(1, bestMove);
						evaluated.add(2, highScore);
						return evaluated;
					}					
				}
			}
			for(Game<X> pos: neighbors){			
				pos.flipBoard();
				posEvaluated = evaluatePosition(pos);
			
				if(!posEvaluated.isEmpty()){
				
					int newScore = (-1)*(int)posEvaluated.get(2);
						
					if(newScore > highScore){
						bestPosition = pos.getCurrentPosition();
						highScore = newScore;
						game.setScore(newScore);
						bestMove = pos.getMove(game.getCurrentPosition(), bestPosition);									
						if(!evaluated.isEmpty()){
							evaluated.remove(0);
							evaluated.add(0, bestPosition);
							evaluated.remove(1);
							evaluated.add(1, bestMove);
							evaluated.remove(2);
							evaluated.add(2, highScore);
							return evaluated;
						}
						else{						
							evaluated.add(0, bestPosition);
							evaluated.add(1, bestMove);
							evaluated.add(2, highScore);
							return evaluated;
						}				
					}					
				}
			}
									
			return evaluated;
			}
		}		
		return null;
	}
	
	
	
	
	
	
	/*
	private int playerTurn = -1;
	private int recursionDepth = 0;
	private int recursionCount = 0;
	private int highScore = -500;
	private int newScore = 0;
	private boolean bestCase = false;
	private Set<Game<X>> visited = new HashSet<Game<X>>();
	private ArrayList<Object> backup = new ArrayList<Object>(3);
	ArrayList<Object> evaluated = new ArrayList<Object>(3);
	private boolean isDone = false;
	
	public <X> ArrayList<Object> evaluatePosition(Game<X> game){
		//ArrayList<Object> evaluated = new ArrayList<Object>(3);
		X bestPosition;
		X bestMove;
		
		if(true){
			playerTurn = game.whoseTurn();
			ArrayList<Game<X>> neighborList = game.getNeighbors(playerTurn);
			int lowScore = 10;
			int count = 0;
			int num = 0;
			
			for(Game<X> aGame: neighborList){
				if(aGame.isFinished() || aGame.getScore() > 100){
					bestPosition = aGame.getPreviousPosition();
					highScore = aGame.getScore(); 
					bestMove = aGame.getMove(aGame.getPreviousPosition(), aGame.getCurrentPosition());
					
					backup.add(0, bestPosition);
					backup.add(1, bestMove);
					backup.add(2, highScore);
					return backup;
					
				}
				else if(aGame.getScore() < lowScore){
					num = count;
				}
				count++;
			}
			
			
			Game<X> anotherGame = neighborList.get(num);
			bestPosition = anotherGame.getPreviousPosition();
			highScore = anotherGame.getScore(); 
			bestMove = anotherGame.getMove(anotherGame.getPreviousPosition(), anotherGame.getCurrentPosition());
			
			backup.add(0, bestPosition);
			backup.add(1, bestMove);
			backup.add(2, highScore);
		}
		recursionDepth++;
		
		if(true){
			
			
			playerTurn = -game.whoseTurn();
			ArrayList<Game<X>> neighborList = game.getNeighbors(playerTurn);
			for(Game<X> aGame: neighborList){
				if(aGame.isFinished() || aGame.getScore() > 100){
					bestPosition = aGame.getPreviousPosition();
					highScore = aGame.getScore(); 
					bestMove = aGame.getMove(aGame.getPreviousPosition(), aGame.getCurrentPosition());
					
					backup.add(0, bestPosition);
					backup.add(1, bestMove);
					backup.add(2, highScore);
					return backup;
					
				}
			}
		
		
			//ArrayList of [current position, move made, score for that position]
			if(game.isFinished()){
					if(evaluated.isEmpty()){
						evaluated.add(0, game.getStart());
						evaluated.add(1, game.getCurrentPosition());
						evaluated.add(2, game.getScore());
						newScore = (int)game.getScore();
						
						return evaluated;
					}
					else{
						evaluated.remove(0);
						evaluated.add(0, game.getStart());
						evaluated.remove(1);
						evaluated.add(1, game.getCurrentPosition());
						evaluated.remove(2);
						evaluated.add(2, game.getScore());
						newScore = (int)game.getScore();
						
						return evaluated;
					}
			}
			else{
				
				playerTurn = game.whoseTurn();
				//ArrayList<Game<X>> neighbors = new ArrayList<Game<X>>();
				ArrayList<Game<X>> neighbors = game.getNeighbors(playerTurn);
				
				
				for(Game<X> aGame: neighbors){
									
					Game<X> newGame = game.copyGame(game.getStart(), aGame.getCurrentPosition(), game.getCurrentPosition());
					
										
					ArrayList<Object> posEvaluated = evaluatePosition(newGame);
					if(!posEvaluated.isEmpty() || posEvaluated == null){
						newScore = (-1)*(int)posEvaluated.get(2);
						
						
							
						if(newScore > highScore){
								
								bestPosition = newGame.getPreviousPosition();
								highScore = newScore; 
								bestMove = newGame.getMove(newGame.getPreviousPosition(), newGame.getCurrentPosition());
								if(evaluated.isEmpty()){
									evaluated.add(0, bestPosition);
									evaluated.add(1, bestMove);
									evaluated.add(2, highScore);
								
									
								}
								else{
									evaluated.remove(0);
									evaluated.add(0, bestPosition);
									evaluated.remove(1);
									evaluated.add(1, bestMove);
									evaluated.remove(2);
									evaluated.add(2, highScore);
									
								}
								
								
							
							}
					}
					}	
				return evaluated;
			}
			
			
		
		
	
	}
	*/
}