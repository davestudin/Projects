import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;


/*
 * @author Dave Studin
 * Connect3.java
 * 
 * Version:
 * $Id: Connect3.java,v 1.3 2015/05/16 03:21:13 das2416 Exp $
 * 
 * Comments:
 * $Log: Connect3.java,v $
 * Revision 1.3  2015/05/16 03:21:13  das2416
 * *** empty log message ***
 *
 * Revision 1.2  2015/05/15 12:15:30  das2416
 * got it to sorta work
 *
 * Revision 1.1  2015/04/29 00:26:31  das2416
 * Got a working player1 and player2 and isFInished() method is working properly
 *
 */

public class Connect3 implements Game<Config>{

	private ArrayList<ArrayList<Integer>> config = new ArrayList<ArrayList<Integer>>();
	private Config currentConfig = null;
	private Config previousConfig = null;
	private Config start = null;
	
	private static int flippedBoard = 1;
	private HashMap<Integer,ArrayList<Game<Config>>> visited = new HashMap<Integer,ArrayList<Game<Config>>>();
	private int initialScore;
	private int evaluatedScore;
	private boolean hasBeenEvaluated = false;
	
	public Connect3(ArrayList<ArrayList<Integer>> start, ArrayList<ArrayList<Integer>> current, ArrayList<ArrayList<Integer>> previous){
		this.currentConfig = new Config(current);
		this.start = new Config(start);
		this.previousConfig = new Config(previous);
	}
	
//////////// Get Methods \\\\\\\\\\\\\\\\\\
	
	public Config getStart(){
		return start;
	}
	
	public Config getFinish(){
		return start;
	}
		
	public Config getCurrentPosition(){
		return currentConfig;
	}
	
	public Config getPreviousPosition(){
		return previousConfig;
	}
	
	public int getInitialScore(){
		return initialScore;
	}
	
	public int getCurrentScore(){
		return currentConfig.isFinished();
	}
	
	public int getEvaluatedScore(){
		return evaluatedScore;
	}
	
	public Connect3 getCurrentGame(){
		Connect3 newGame = new Connect3(start.getConfig(), currentConfig.getConfig(), previousConfig.getConfig());
		return newGame;
	}
	
	public Config getMove(Config position, Config position2){
		ArrayList<ArrayList<Integer>> list = new ArrayList<ArrayList<Integer>>();
		
		for(int i = 0; i < position.getRowSize(); i++){
			ArrayList<Integer> row = new ArrayList<Integer>();
			
			for(int k = 0; k < position.getColumnSize(); k++){
				
				if(position.getConfig().get(i).get(k) == 45 && position2.getConfig().get(i).get(k) != 45 ||
						position.getConfig().get(i).get(k) != 45 && position2.getConfig().get(i).get(k) == 45){
					if(position.getConfig().get(i).get(k) == 45){
						row.add(position2.getConfig().get(i).get(k));
					}
					else{
						row.add(position.getConfig().get(i).get(k));
					}
					
				}
				else{
					row.add(45);
				}
			}
			list.add(row);
					
		}
		Config moveConfig = new Config(Config.copyConfig(list));
		return moveConfig;
	}
	
/////////////// Void Methods \\\\\\\\\\\\\\\\\\\
	
	public void setScore(int num){	
		evaluatedScore = num;
		hasBeenEvaluated = true;	
	}
	
	public void setCurrentPosition(Config config){
		currentConfig = new Config(Config.copyConfig(config.getConfig()));
	}
	
	public void setPreviousPosition(Config config){
		previousConfig = new Config(Config.copyConfig(config.getConfig()));
	}
	
	public void flipBoard(){
		
		start = new Config(Config.compliment(this.start.getConfig()));
		currentConfig = new Config(Config.compliment(this.currentConfig.getConfig()));
		previousConfig = new Config(Config.compliment(this.previousConfig.getConfig()));		
	}
	
////////////// Flag Methods \\\\\\\\\\\\\\\\\\\\\
	
	public boolean isFinished(){
		if(currentConfig.isFinished() == 1 || currentConfig.isFinished() == 0){
			return true;
		}
		return false;
	}
	
	public boolean hasBeenEvaluated(){
		return hasBeenEvaluated;
	}
	
	public int whoseTurn(){
		return currentConfig.whoseTurn();
	}
	
	public static boolean isEqual(Game<Config> game1, Game<Config> game2){
		Config config1 = new Config(game1.getCurrentPosition().getConfig());
		Config config2 = new Config(game2.getCurrentPosition().getConfig());
		
		for(int i = 0; i < config1.getRowSize(); i++){
			for(int k = 0; k < config1.getColumnSize(); k++){
				if(config1.getConfig().get(i).get(k) != config2.getConfig().get(i).get(k)){
					return false;
				}
			}
		}
		return true;
	}
	
/////////////// Function Methods \\\\\\\\\\\\\\\\\
	
	public Game<Config> copyGame(Config start, Config curr, Config prev, int evaluatedScore){
		Game<Config> newGame = new Connect3(start.getConfig(), curr.getConfig(),prev.getConfig());
		newGame.setScore(evaluatedScore);
		return  newGame;
	}
	
	public Config updateConfig(int column, int player){
		Config newConfig = new Config(Config.copyConfig(this.config));
		
		ArrayList<ArrayList<Integer>> newList = new ArrayList<ArrayList<Integer>>();
		
		newList = config;
		
		int num =0;
		if(player == -1){
			num = 79;
		}
		else if(player == 1){
			num = 88;
		}
		
		ArrayList<Integer> columnList = new ArrayList<Integer>();
		for(Integer i: newList.get(column)){
			columnList.add(i);
		}
		for(int i = 0; i < columnList.size(); i++){
			if(columnList.get(i) == 45){
				columnList.remove(i);
				columnList.add( i, num);
				newList.remove(column);
				newList.add(column, columnList);
				newConfig = new Config(newList);
				break;
			}
		}	
		if(newConfig.isFinished() != -1){
			return newConfig;
		}
		return null;
	}
	
	public ArrayList<Game<Config>> getNeighbors(Game<Config> aGame, int player){
		
		if(aGame.isFinished()){
			return null;
		}
		else{
			
			Config currentConfig = new Config(aGame.getCurrentPosition().getConfig());	
			ArrayList<Game<Config>> neighbors = new ArrayList<Game<Config>>();			
			
			for(int i = 0; i < currentConfig.getRowSize(); i++){
				Config config1 = new Config(Config.copyConfig(currentConfig.getConfig()));			
				Config config2 = new Config(new Config(Config.copyConfig(config1.getConfig())).updateConfig(i, player).getConfig());				
				Connect3 newGame = new Connect3(start.getConfig(), config2.getConfig(), config1.getConfig());
				if(!Connect3.isEqual(newGame, this)){				
					int hashNum = newGame.getCurrentPosition().getConfigHash();
					boolean wasSeen = false;
					
					if(visited.get(hashNum) !=null && !visited.get(hashNum).isEmpty()){
						
						if(!wasSeen){
							ArrayList<Game<Config>> anotherGame = new ArrayList<Game<Config>>();
							anotherGame = visited.get(hashNum);
							anotherGame.add(newGame);
							visited.remove(hashNum).add(newGame);
							visited.put(hashNum, anotherGame);
							neighbors.add(newGame);
						}
					}
					else{
						ArrayList<Game<Config>> anotherGame = new ArrayList<Game<Config>>();
						anotherGame.add(newGame);
						visited.put(hashNum, anotherGame);
						neighbors.add(newGame);
					}
				}	
			}
			
			return neighbors;
		}
	}
	
	public Integer computerTurn(){
		
		System.out.print("Player2 : ");
		
		Connect3 prePosition = new Connect3(start.getConfig(), currentConfig.getConfig(), previousConfig.getConfig());
		Solver solve1 = new Solver();
		ArrayList<Object> possibleChoices =solve1.evaluatePosition(prePosition);
		
		
		Config bestMove  = new Config(Config.copyConfig(((Config)possibleChoices.get(1)).getConfig()));
		

		int columnNum = 0;
		
		for(int i = 0; i < bestMove.getRowSize(); i++){
			for(int k = 0; k < bestMove.getColumnSize(); k++){
				if(bestMove.getConfig().get(i).get(k) != 45){
					columnNum = i+1;
				}
			}			
		}
		
		previousConfig = new Config(Config.copyConfig(currentConfig.getConfig()));
		currentConfig = new Config(Config.copyConfig(currentConfig.playerMove(columnNum, -1).getConfig()));
		System.out.println(" " + columnNum);		
		System.out.println("Pile Configuration: \n" + currentConfig +"\n");		
		return (-1)*currentConfig.isFinished();		
	}
	
	public Integer computer1Turn(){
				
	
		System.out.print("Player1 : ");
		
		Connect3 prePosition = new Connect3(start.getConfig(), currentConfig.getConfig(), previousConfig.getConfig());
		Solver solve1 = new Solver();
		ArrayList<Object> possibleChoices =solve1.evaluatePosition(prePosition);
				
		Config bestMove  = new Config(Config.copyConfig(((Config)possibleChoices.get(1)).getConfig()));		

		int columnNum = 0;
		
		for(int i = 0; i < bestMove.getRowSize(); i++){
			for(int k = 0; k < bestMove.getColumnSize(); k++){
				if(bestMove.getConfig().get(i).get(k) != 45){
					columnNum = i+1;
				}
			}			
		}
		previousConfig = new Config(Config.copyConfig(currentConfig.getConfig()));		
		currentConfig = new Config(Config.copyConfig(currentConfig.playerMove(columnNum, 1).getConfig()));
		System.out.println(" " + columnNum);		
		System.out.println("Pile Configuration: \n" + currentConfig +"\n");	
		return currentConfig.isFinished();
		
	}
	
	public Integer playerTurn(){
		System.out.print("Player1 : ");
		
		Scanner in = new Scanner(System.in);
		boolean flag = false;
		
		int column = -1;
		while(!flag){
			try{
				column = in.nextInt();
			}
			catch(Exception e){
				
			}
			if(column == -1){
				System.out.print("Player1 : ");
			}
			else{
				flag = true;
			}
			
		}
	
		previousConfig = new Config(Config.copyConfig(currentConfig.getConfig()));		
		currentConfig = new Config(Config.copyConfig(currentConfig.playerMove(column, 1).getConfig()));
		return currentConfig.isFinished();
		
	}
	
	public Integer player2Turn(){
		System.out.print("Player2 : ");
		
		Scanner in = new Scanner(System.in);
		boolean flag = false;
		
		int column = -1;
		while(!flag){
			try{
				column = in.nextInt();
			}
			catch(Exception e){
				
			}
			if(column == -1){
				System.out.print("Player2 : ");
			}
			else{
				flag = true;
			}
			
		}
		previousConfig = new Config(currentConfig.getConfig());
		currentConfig = new Config(Config.copyConfig(currentConfig.playerMove(column, -1).getConfig()));
		
		
		return currentConfig.isFinished();
		
	}
	
//////////////// toString Method \\\\\\\\\\\\\\\\\\\\
	public String toString(){
		String str = "Current: \n" + currentConfig.toString() +
				"Current Score: " + initialScore + "\n" +
				"Evaluated Score: " + evaluatedScore;	
		return str;
	}
	/*
	public String getNeighborsString(ArrayList<Config> neighbors){
		String str = "";
		
		for(int i = 0; i < neighbors.size(); i++){
			str += "Neighbor: " + i + "\n";
			str += neighbors.get(i).toString();
		}
		return str;
	}
	*/
	
	public static void main(String[]args){
		/*
		ArrayList<ArrayList<Integer>> first = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> first1 = new ArrayList<Integer>();
		first1.add(45);
		first1.add(88);
		first1.add(88);
		first.add(first1);
		
		ArrayList<ArrayList<Integer>> second = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> second1 = new ArrayList<Integer>();
		second1.add(45);
		second1.add(45);
		second1.add(79);
		second.add(second1);
		
		Config config1 = new Config(first);
		Config config2 = new Config(second);
		Connect3 newGame = new Connect3( first, first, first);
		Config move = newGame.getMove(config1,config2);
		System.out.print(move);
		*/
		
		/*
		ArrayList<ArrayList<Integer>> aConfig = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> input = new ArrayList<Integer>();
		ArrayList<Integer> row1 = new ArrayList<Integer>();
		ArrayList<Integer> row2 = new ArrayList<Integer>();
		ArrayList<Integer> row3 = new ArrayList<Integer>();
		ArrayList<Integer> row4 = new ArrayList<Integer>();
		ArrayList<Integer> row5 = new ArrayList<Integer>();
		ArrayList<Integer> row6 = new ArrayList<Integer>();
		int gameover = -5;
		int row = 3;
		int column = 3;
		
		for(int i = 0; i < args.length; i++){
			input.add(Integer.parseInt(args[i]));
		}
		for(int i = 0; i < input.size(); i++){
			if(i < column){
				
				row1.add(input.get(i));
			}
			else if(i < column*2 && i >= column){
				
				row2.add(input.get(i));
			}
			else if(i < column*3 && i >= column*2){
				
				row3.add(input.get(i));
			}
			else if(i < column*4 && i >= column*3){
				
				row4.add(input.get(i));
			}
		}
		aConfig.add(row1);
		aConfig.add(row2);
		aConfig.add(row3);
		//aConfig.add(row4);
		
		
		Connect3 newGame = new Connect3(aConfig, aConfig, aConfig);
		System.out.print(newGame.getStart());
	
		
		
		System.out.println(newGame.getInitialScore());
		System.out.print(newGame.isFinished());
		gameover = newGame.computerTurn();
		*/
		Game newGame = null;
		
		if(args[0].equals("TakeAway")){
		
			int num =0;
			
			try{
				num = Integer.parseInt(args[1]);
				newGame = new TakeAway(num, num, num);
			}
			catch(Exception e){System.out.print("Please enter a positive number.");}
			
			System.out.println("Starting number of pennies: " + newGame.getStart().toString());
		
		
			boolean flag = true;
			int playerNumber = 0;
			
			while(flag){
						
				if(playerNumber%2 == 0){				
					
					newGame.playerTurn();
				}
				else{				
					newGame.computerTurn();			
				}		
				playerNumber++;
				
				if(newGame.isFinished()){
					flag = false;
					
					int finishedScore = (int)newGame.getCurrentScore();
					
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
		
	
		else if(args[0].equals("Stones")){
			
		
			ArrayList<Integer> input = new ArrayList<Integer>();
			boolean validGame = true;
			
			for(int i = 1; i < args.length; i++){
				try{
					int num = Integer.parseInt(args[i]);
					input.add(num);
				}
				catch(Exception e){
					validGame =false;
				}
			}
			
			if(validGame){
			
				newGame = new Stones(input);
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
						
						int finishedScore = (int)newGame.getCurrentScore();
						
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
		else if(args[0].equals("Connect3")){
			
			
			int row = 0;
			int column = 0;
			try{
				row = Integer.parseInt(args[1]);
				column = Integer.parseInt((args[1]));
			}
			catch(Exception e){
				System.err.println("Usage: Connect3 [row] [column]");
			}
			
			int gameover = -5;
			boolean flag = false;
			ArrayList<ArrayList<Integer>> aConfig = new ArrayList<ArrayList<Integer>>();
			
			for(int i = 0; i < row; i++){
				ArrayList<Integer> columns = new ArrayList<Integer>();
				for(int k = 0; k < column; k++){
					
						columns.add(45);
									
				}
				aConfig.add(columns);
			}
			newGame = new Connect3(aConfig, aConfig, aConfig);
			System.out.print(newGame.getStart());		
			int count = 0;
		
			
			while(!flag){
				if(count%2 ==0){
					gameover = newGame.playerTurn();
					System.out.print(newGame.getCurrentPosition());
					if(gameover ==1){
						System.out.print("CPU 1 Wins");
						break;
					}
					else if(gameover == 0){
						System.out.print("You Tied");
						break;
					}
					else if(gameover == -1){
						System.out.print("CPU 2 Wins");
						break;
					}
				}
				else{
					gameover = newGame.computerTurn();
					System.out.print(newGame.getCurrentPosition());
					if(gameover ==-1){
						System.out.print("CPU 2 Wins");
						break;
					}
					else if(gameover == 0){
						System.out.print("Tied");
						break;
					}
					else if(gameover == 1){
						System.out.print("CPU 1 Wins");
						break;
					}
					
				}
				count++;		
			}
		}
	}
}
