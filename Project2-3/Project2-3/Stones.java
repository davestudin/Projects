/*
 * @author Dave Studin
 * Stones.java
 * 
 * Version:
 * $Id: Stones.java,v 1.2 2015/05/16 03:21:14 das2416 Exp $
 * 
 * Comments:
 * $Log: Stones.java,v $
 * Revision 1.2  2015/05/16 03:21:14  das2416
 * *** empty log message ***
 *
 * Revision 1.1  2015/04/29 00:26:34  das2416
 * Got a working player1 and player2 and isFInished() method is working properly
 *
 * Revision 1.2  2015/04/27 06:07:19  das2416
 * final commit
 *
 * Revision 1.1  2015/04/18 15:55:16  das2416
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

public class Stones implements Game<Piles> {

	
	private Piles start =null;
	private Piles finish;
	private Piles previousPosition;
	private Piles currentPosition;
	private Integer score=-5;
	private Integer numOfPiles = 0;
	private ArrayList<Integer> listOfPiles;
	private int evaluatedScore =0;
	private static int playerTurn = 1;
	
	public Stones(ArrayList<Integer> piles){
		start = new Piles(piles);
		currentPosition = new Piles(piles);
		previousPosition = new Piles(piles);
		numOfPiles = piles.size();
		listOfPiles = piles;
		
	}
	
///////////// Get Methods \\\\\\\\\\\\\\\\\\\
	
	public Piles getStart(){	
		return start;
	}
	
	public Piles getFinish(){	
		return finish;
	}
	
	public Piles getCurrentPosition(){
		return currentPosition;
	}
	
	public Piles getPreviousPosition(){
		return previousPosition;
	}
	
	public int getEvaluatedScore(){
		return evaluatedScore;
	}
	
	public int getCurrentScore(){
		return score;
	}
	
	public int getNumOfPiles(){
		return numOfPiles;
	}
	
	public Piles getMove(Piles pile1, Piles pile2){
		
		ArrayList<Integer> list = new ArrayList<Integer>();
		
		for(int i = 0; i < pile1.getPileSize(); i++){
			int pile = Math.abs(pile2.getPile(i)-pile1.getPile(i));
			list.add(pile);			
		}
		Piles pileMove = new Piles(list);
		return pileMove;
	}
	
	
///////////// Void Methods \\\\\\\\\\\\\\\\\\\	
	
	public void setCurrentPosition(Piles num){
		currentPosition = num;
	}
	
	public void setPreviousPosition(Piles num){
		previousPosition = num;
	}
	
	public void setScore (int num){
		evaluatedScore = num;
	}
	
	public void setScore(Integer num){
		score = num;
	}
	
	public void flipBoard(){
		
	}

///////////// Flag Methods \\\\\\\\\\\\\\\\\\\
	
	public boolean isFinished(){
		if(currentPosition.isFinished()){
			return true;
		}
		return false;
	}
	
	public int whoseTurn(){
		return playerTurn;
	}
	
///////////// Function Methods \\\\\\\\\\\\\\\\\\\
	
	public ArrayList<Game<Piles>> getNeighbors(Game<Piles> piles, int num){
		Piles newConfig = piles.getCurrentPosition();
		ArrayList<Integer> pile = new ArrayList<Integer>();
		pile = newConfig.getPileArray();
		ArrayList<Game<Piles>> listPiles = new ArrayList<Game<Piles>>();
		
		for(int i = 0; i < pile.size(); i++){
			for(int k = 1; k <= pile.get(i); k++){
				Piles newPile = new Piles(piles.getCurrentPosition().getPileArray());
				int neighbor = pile.get(i)-k;
				Piles updatedPile = newPile.updatePiles(newPile, i, neighbor);
				Stones newGame = new Stones(updatedPile.getPileArray());
				listPiles.add(newGame);
			}
		}
		return listPiles;
	}
	
	
	public Game<Piles> copyGame(Piles start, Piles curr, Piles prev, int num){
		Game<Piles> newGame = new Stones(start.getPileArray());
		newGame.setCurrentPosition(curr);
		newGame.setPreviousPosition(prev);
		return  newGame;
	}
	
	
	
	public Integer playerTurn(){
		System.out.print("Player1 : ");
		
		Scanner in = new Scanner(System.in);
		boolean flag = false;
		Piles player1MovePile = null;
		ArrayList<Integer> player1Move = new ArrayList<Integer>();
		
		
		int pileNum = 0;
		int rockNum = 0;
		while(!flag){
			String input = in.nextLine();
			String[] array = input.split(" ");
			ArrayList<Integer> inputPileNum_RockNum = new ArrayList<Integer>();
		
			
			if(array.length == 2){
				for(int i = 0; i < array.length; i++){
					int num = Integer.parseInt(array[i]);
					inputPileNum_RockNum.add(num);
					
				}
				if(inputPileNum_RockNum.get(0) > start.getPileSize() || inputPileNum_RockNum.get(0) < 1){
					System.out.println("Pile does not exist.");
					System.out.print("Player1 : ");
				}
				else if(inputPileNum_RockNum.get(1) <= 0 || currentPosition.getPile(inputPileNum_RockNum.get(0) -1) < inputPileNum_RockNum.get(1)){
					System.out.println("Must choose at least 1 rock or as many rocks that are in the pile.");
					System.out.print("Player1 : ");
					
				}
				else{
					pileNum = inputPileNum_RockNum.get(0);
					rockNum = inputPileNum_RockNum.get(1);
					flag =true;
					break;
				}
			}
			
			
			else{
				System.out.println("Usage: pile1 pile2 pile3 ...");
				System.out.print("Player1 : ");
			}
		}
		
		for(int i = 0; i < listOfPiles.size(); i++){
			if(i+1 == pileNum){
				player1Move.add(i,rockNum);
			}
			else{
				player1Move.add(i,0);
			}
		}

		player1MovePile = new Piles(player1Move);
		currentPosition = player1MovePile.subtractPiles(currentPosition, player1MovePile);
		Stones prePosition = new Stones(currentPosition.getPileArray());
		
		Solver solve1 = new Solver();
		ArrayList<Object> possibleChoices =solve1.evaluatePosition(prePosition);
		System.out.println("Pile Configuration: " + currentPosition +"\n");
		if(currentPosition.isFinished()){
			in.close();
		}
		
		return -(Integer)possibleChoices.get(2);
		
			
	}
	
	public Integer computerTurn(){
		
		
		System.out.print("Player2 : ");
		
		Stones prePosition = new Stones(currentPosition.getPileArray());
		Solver solve1 = new Solver();
		ArrayList<Object> possibleChoices =solve1.evaluatePosition(prePosition);
		
		currentPosition = (Piles)possibleChoices.get(0);
		Piles bestMove  = (Piles)possibleChoices.get(1);
		
		

		int pileNum = 0;
		int rockNum = 0;
		
		for(int i = 0; i < bestMove.getPileSize(); i++){
			if(bestMove.getPile(i) != 0){
				pileNum = i+1;
				rockNum = bestMove.getPile(i);
			}
		}
		
		System.out.println(" " + pileNum + " " + rockNum);
	
		
		System.out.println("Pile Configuration: " + currentPosition +"\n");
		
		return -(Integer)possibleChoices.get(2);		
	}
	
	public String toString(){
		String str = "Current: " + currentPosition.toString();
		return str;
	}
	
	/////////////// MAIN METHOD ////////////////////////
	
	//public static void main(String []args){
		/*
		Game newGame = null;
		
		ArrayList<Integer> input = new ArrayList<Integer>();
		boolean validGame = true;
		
		for(int i = 0; i < args.length; i++){
			try{
				int num = Integer.parseInt(args[i]);
				input.add(num);
			}
			catch(Exception e){
				validGame =false;
			}
		}
		
		if(validGame){
			if(input.size() == 1){
				newGame = new TakeAway(input.get(0));
			}
			else{
				newGame = new Stones(input);
			}
			
			System.out.println("Starting configuration: " + newGame.getStart().toString());
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
		else{
			System.err.print("Invalid Game");
		}
		
	}
	*/
}
