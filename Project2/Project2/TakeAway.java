/*
 * @author Dave Studin
 * TakeAway.java
 * 
 * Version:
 * $Id: TakeAway.java,v 1.3 2015/04/06 03:18:16 das2416 Exp $
 * 
 * Comments:
 * $Log: TakeAway.java,v $
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

public class TakeAway extends Position <Integer> {

	
	private Integer start;
	private Integer finish;
	private Integer currentPosition;
	private Integer score;
	
	public TakeAway(Integer init){
		this.start = init;
		currentPosition = init;
		finish = 0;
	}
	
	public Integer getStart(){
		return start;
	}
	
	public Integer getFinish(){	
		return finish;
	}
	
	public int getScore(){
		return score;
	}
	
	public void setScore(int num){
		score = num;
	}
	
	public boolean isFinished(){
		if(currentPosition == 0){
			return true;
		}
		return false;
	}
	
	public ArrayList<Integer> getNeighbors(){
		ArrayList<Integer> neighbors = new ArrayList<Integer>();
		
		for(int i = 1; i <= 3; i++){	
			if(currentPosition - i >= 0){
				neighbors.add(currentPosition - i);
			}	
		}
		
		if(neighbors.isEmpty()){
			if(currentPosition - 1 >= 0){
				neighbors.add(currentPosition - 1);
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
						in.close();
					}
					else if(num > currentPosition){
						System.out.println("There are not that many pennies left to take.");
						System.out.print("Player1 : ");
						in.close();
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
		//TakeAway prePosition = new TakeAway(num);
		Position<Integer> prePosition = new TakeAway(num);
		Solver solve1 = new Solver();
		ArrayList<Object> possibleChoices =solve1.evaluatePosition(prePosition);
		System.out.println("Number of pennies: " + currentPosition +"\n");
		return (Integer)possibleChoices.get(2);
	}
	
	public Integer computerTurn(){
		System.out.print("Player2 : ");
		
		Position<Integer> prePosition = new TakeAway(currentPosition);
		Solver solve1 = new Solver();
		ArrayList<Object> possibleChoices =solve1.evaluatePosition(prePosition);
		
		currentPosition = (Integer)(possibleChoices.get(0));
		System.out.println(possibleChoices.get(1));
		System.out.println("Number of pennies: " + currentPosition +"\n");
		
		return (-1)*(Integer)possibleChoices.get(2);
		
	}
	
	public String toString(){
		String str = "Start: " + currentPosition +"\nScore: " + score;
		
		return str;
	}
	
	/////////////// MAIN METHOD ////////////////////////
	
	public static void main(String []args){
		
		int num = 0;
		Position<Integer> newGame = null;
		Position<ArrayList<Integer>> newGame2 = null;
		
		if(args[0].equals("TakeAway")){
			
			try{
				num = Integer.parseInt(args[1]);
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


	
}
