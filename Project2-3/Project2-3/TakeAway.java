/*
 * @author Dave Studin
 * TakeAway.java
 * 
 * Version:
 * $Id: TakeAway.java,v 1.2 2015/05/16 03:21:14 das2416 Exp $
 * 
 * Comments:
 * $Log: TakeAway.java,v $
 * Revision 1.2  2015/05/16 03:21:14  das2416
 * *** empty log message ***
 *
 * Revision 1.1  2015/04/29 00:26:35  das2416
 * Got a working player1 and player2 and isFInished() method is working properly
 *
 * Revision 1.2  2015/04/27 06:07:18  das2416
 * final commit
 *
 * Revision 1.1  2015/04/18 15:55:15  das2416
 * first commit
 *
 * Revision 1.3  2015/04/06 03:18:16  das2416
 * fixed all exclamation points
 *
 * Revision 1.2  2015/04/06 02:50:41  das2416
 * final commit
 *
 * Revision 1.1  2015/04/02 18:54:33  das2416
 * test
 *
 */

import java.util.ArrayList;
import java.util.Scanner;

public class TakeAway implements Game<Integer> {

	
	private Integer start;
	private Integer finish;
	private Integer previousPosition;
	private Integer currentPosition;
	private Integer score;
	private Integer evaluatedScore;
	private int playerTurn = -1;
	
	public TakeAway(Integer current, Integer start, Integer previous){
		this.start = new Integer(start);
		this.currentPosition = new Integer(current);
		this.previousPosition= new Integer(previous);
	
		finish = new Integer(0);
	}
	
	///////////// Get Methods \\\\\\\\\\\\\\\\\\\
	
	public Integer getStart(){	
		return start;
	}
	
	public Integer getCurrentPosition(){
		return currentPosition;
	}
	
	public Integer getPreviousPosition(){
		return previousPosition;
	}
	
	public Integer getFinish(){	
		return finish;
	}
	
	public int getCurrentScore(){
		return score;
	}
	
	public int getEvaluatedScore(){
		return evaluatedScore;
	}	
	
	public Integer getMove(Integer num, Integer num2){
		return num-num2;
	}
	
///////////// Void Methods \\\\\\\\\\\\\\\\\\\
	
	public void setScore(int num){
		evaluatedScore = num;
	}
	
	public void setCurrentPosition(Integer num){
		currentPosition = new Integer(num);
	}
	
	public void setPreviousPosition(Integer num){
		previousPosition = new Integer(num);
	}	
	
	public void flipBoard(){
		
	}
	
	
///////////// Flag Methods \\\\\\\\\\\\\\\\\\\
	
	public boolean isFinished(){
		if(currentPosition == 0){
			score = -1;
			return true;
		}
		return false;
	}
	
	public int whoseTurn(){
		return playerTurn;
	}
	
	///////////// Function Methods \\\\\\\\\\\\\\\\\\\
	/*
	public Game<Integer> copyGame(Integer start, Integer curr, Integer prev, int num){
		Game<Integer> newGame = new TakeAway(curr, start, prev);
		newGame.setCurrentPosition(curr);
		newGame.setPreviousPosition(prev);
		return  newGame;
	}
	*/
	public ArrayList<Game<Integer>> getNeighbors(Game<Integer> newGame, int number){
		ArrayList<Game<Integer>> neighbors = new ArrayList<Game<Integer>>();
		int num = newGame.getCurrentPosition();
		int start = newGame.getStart();
		for(int i = 1; i <= 3; i++){	
			if(num - i >= 0){
				TakeAway aGame = new TakeAway(num - i, start, num);
				neighbors.add(aGame);
			}	
		}
		return neighbors;
	}
	
	public Integer playerTurn(){
		System.out.print("Player1 : ");
		int num =0;
		Scanner in = new Scanner(System.in);
		boolean flag = false;
		//if(in.hasNext()){
			
			while(!flag){
				
				if(in.hasNextInt()){
					num =in.nextInt();
				
				
					if(num <= 0 || num >3){
						System.out.println("Choose 1, 2, or 3 pennies to take.");
						System.out.print("Player1 : ");
						
					}
					else if(num > currentPosition){
						System.out.println("There are not that many pennies left to take.");
						System.out.print("Player1 : ");
						
					}
					else{
						
						flag = true;
						
					}
				}
				else{
					in.reset();
					System.out.println("Choose 1, 2, or 3 pennies to take.");
					System.out.print("Player1 : ");
				}
				
			}

		currentPosition -= num;
		
		System.out.println("Number of pennies: " + currentPosition +"\n");
		if(currentPosition == 0){
			in.close();
		}
		return score;
	}
	
	public Integer computerTurn(){
		System.out.print("Player2 : ");
		
		TakeAway prePosition = new TakeAway(currentPosition, start, previousPosition);
		Solver solve1 = new Solver();
		ArrayList<Object> possibleChoices =solve1.evaluatePosition(prePosition);
		
		currentPosition = (Integer)(possibleChoices.get(0));
		System.out.println(possibleChoices.get(1));
		System.out.println("Number of pennies: " + currentPosition +"\n");
		
		return -(Integer)possibleChoices.get(2);
		
	}
	
	public String toString(){
		String str = "Current: " + currentPosition + "  Score: " + evaluatedScore;
		
		return str;
	}
	
	/////////////// MAIN METHOD ////////////////////////
	/*
	public static void main(String []args){
		
		Integer num = 0;
		Game<Integer> newGame = null;
		
		
		if(args.length == 1){
			
			try{
				num = Integer.parseInt(args[0]);
				newGame = new TakeAway(num);
			}
			catch(Exception e){System.out.print("Please enter a positive number.");}
			
			System.out.println("Starting number of pennies: " + newGame.getStart().toString());
		}
		else{
			System.out.println("Not a valid game.");
		}
			
		boolean flag = true;
		int playerNumber = 0;
		
		while(flag){
					
			if(playerNumber%2 == 0){				
				
				newGame.setScore(newGame.playerTurn());
			}
			else{				
				newGame.setScore(newGame.computerTurn());			
			}		
			playerNumber++;
			
			if(newGame.isFinished()){
				flag = false;
				
				int finishedScore = (int)newGame.getScore();
				
				if(finishedScore > 0){
					System.out.print("Player1 Wins!!!");
				}
				else if(finishedScore == 0){
					System.out.print("There is a draw.");
				}
				else if(finishedScore < 0){
					System.out.print("Player1 loses...");
				}
			}
		}
			
	}
	*/	
}
