import java.util.ArrayList;

/*
 * @author Dave Studin
 * Connect3.java
 * 
 * Version:
 * $Id: Config.java,v 1.3 2015/05/16 03:21:13 das2416 Exp $
 * 
 * Comments:
 * $Log: Config.java,v $
 * Revision 1.3  2015/05/16 03:21:13  das2416
 * *** empty log message ***
 *
 * Revision 1.2  2015/05/15 12:15:30  das2416
 * got it to sorta work
 *
 * Revision 1.1  2015/04/29 00:26:32  das2416
 * Got a working player1 and player2 and isFInished() method is working properly
 *
 */

public class Config{

	private ArrayList<ArrayList<Integer>> config = new ArrayList<ArrayList<Integer>>();
	private int rowSize = 0;
	private ArrayList<ArrayList<Integer>> rowList = new ArrayList<ArrayList<Integer>>();
	private int columnSize = 0;
	private int configHash = 0;
	//private HashSet<Config> seen = new HashSet<Config>();
	
	/**
	 * Constructor that generates instance of the Connect3 game
	 * @param aConfig
	 */
	public Config(ArrayList<ArrayList<Integer>> aConfig){
		this.rowSize = aConfig.size();
		this.columnSize = aConfig.get(0).size();
		this.config = aConfig;
		
		
		ArrayList<ArrayList<Integer>> rowList = new ArrayList<ArrayList<Integer>>();
		for(int i = 0; i < columnSize; i++){
			ArrayList<Integer> row = new ArrayList<Integer>();
			for(int k = 0; k < rowSize; k++){
				row.add(aConfig.get(k).get(i));
			}
			rowList.add(row);
		}
		this.rowList = rowList;
		
		for(int i = 0; i < columnSize; i++){
			
			for(int k = 0; k < rowSize; k++){
				if(config.get(k).get(i) != 45){
					configHash++;
				}
			}
		}
	}
	
	
	/**
	 * Method to get the size of the row of the board
	 * @return rowSize
	 */
	public int getRowSize(){
		return rowSize;
	}
	
	/**
	 * Returns the ArrayList for the given row that is specified
	 * @param row
	 * @return represented row in an ArrayList
	 */
	public ArrayList<Integer> getRow(Integer row){
		return rowList.get(row-1);
	}
	
	public ArrayList<ArrayList<Integer>> getRowList(){
		return rowList;
	}
	
	/**
	 * Method to return the size of the column
	 * @return columnSize
	 */
	public int getColumnSize(){
		return columnSize;
	}
	
	public int getConfigHash(){
		return configHash;
	}
	
	
	public int whoseTurn(){
		int count = 0;
		for(int i =0; i < rowSize; i++){
			
			for(int k = 0; k < columnSize; k++){
				if(config.get(i).get(k) != 45){			
					count++;
				}
			}
		}
		if(count%2 == 0){
			return 1;
		}
		else{
			return -1;
		}
	}
	
	/**
	 * Returns the ArrayList for the given column that is specified from "bottom" of the board to the top
	 * @param row
	 * @return represented row in an ArrayList
	 */
	public ArrayList<Integer> getColumn(Integer column){
		return config.get(column);
	}
	
	/**
	 * Returns the ArrayList for the given config for this Connect3 game
	 * @param row
	 * @return represented row in an ArrayList
	 */
	public ArrayList<ArrayList<Integer>> getConfig(){
		return config;
	}
	
	/**
	 * Returns the config after a player has placed his piece on a certain config
	 * @param config
	 * @param column
	 * @param player
	 * @return
	 */
	public Config updateConfig(int column, int player){
		ArrayList<ArrayList<Integer>> newList = new ArrayList<ArrayList<Integer>>();
		newList = config;
		Config newConfig = new Config(Config.copyConfig(config));
		
		int num =0;
		if(player == -1){
			num = 79;
		}
		else if(player == 1){
			num = 88;
		}
		
		ArrayList<Integer> columnList = new ArrayList<Integer>();
		for(int i: config.get(column)){
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
		return newConfig;
	}
	
	public Config playerMove(int column, int player){
			
		Config newConfig = new Config(copyConfig(config));
		ArrayList<ArrayList<Integer>> newList = new ArrayList<ArrayList<Integer>>();
		newList = newConfig.getConfig();
		int playerNum = 0;
		if(player == -1){
			playerNum = 79;
		}
		else if(player == 1){
			playerNum = 88;
		}
		
		ArrayList<Integer> columnList = new ArrayList<Integer>();
		for(int i: config.get(column-1)){
			columnList.add(i);
		}
		
		for(int i = 0; i < columnList.size(); i++){
			if(columnList.get(i) == 45){
				columnList.remove(i);
				columnList.add( i, playerNum);
				newList.remove(column-1);
				newList.add(column-1, columnList);		
				break;
			}
		}
	
		Config updated = new Config(newList);
		//seen.add(newConfig);
		
		return updated;
	}
	
	public static ArrayList<ArrayList<Integer>> copyConfig(ArrayList<ArrayList<Integer>> config){
		ArrayList<ArrayList<Integer>> newList = new ArrayList<ArrayList<Integer>>();
		for(int i = 0; i < config.size(); i++){
			ArrayList<Integer> newColumn = new ArrayList<Integer>();
			for(int k = 0; k < config.get(i).size(); k++){
				int num = config.get(i).get(k);
				newColumn.add(num);
			}
			newList.add(newColumn);
		}
		return newList;
	}
	
	public int isFinished(){
		
		boolean isFinished = false;
		boolean isComplete = true;
		//Finds if 3-in-a-row for any row
		for(ArrayList<Integer> row: rowList){
			for(int i = 0; i < row.size(); i++){
				int first = row.get(i);
				int second = 0;
				int third = 0;
				if(i+1 < row.size() && first !=45){
					second = row.get(i+1);
					
					if(i+2 < row.size()){
						third = row.get(i+2);
					}
				}
				if(first == second && second == third){
					
					isFinished = true;
				}
				
			}
		}
		
		//Finds if 3-in-a-row for any column
		for(ArrayList<Integer> column: config){
			for(int i = 0; i < column.size(); i++){
				int first = column.get(i);
				int second = -1;
				int third = 1;
				if(i+1 < column.size() && first !=45){
					second = column.get(i+1);
					
					if(i+2 < column.size()){
						third = column.get(i+2);
					}
				}
				if(first == second && second == third){
					
					isFinished = true;
				}
				
			}
		}
		
		//Finds if 3-in-a-row for positively correlated diagonal
		for(int i =0; i < rowSize; i++){
			
			for(int k = 0; k < columnSize; k++){
				int first = -1;
				int second = 0;
				int third = 1;
				
				if(config.get(i).get(k) != 45){
					first =config.get(i).get(k);
					
					if(i+1 < rowSize && k+1 < columnSize){
						second = config.get(i+1).get(k+1);
						
						if(i+2 < rowSize && k+2 < columnSize){
							third =config.get(i+2).get(k+2);
							
						}
					}
				}
				if(first == second && second == third){
					
					isFinished = true;
				}
				
			}
		}
		
		for(int i =0; i < rowSize; i++){
			
			for(int k = 0; k < columnSize; k++){
				int first = -1;
				int second = 0;
				int third = 1;
				
				if(config.get(i).get(k) != 45){
					first =config.get(i).get(k);
					
					if(i+1 < rowSize && k-1 >= 0){
						second = config.get(i+1).get(k-1);
						
						if(i+2 < rowSize && k-2 >= 0){
							third =config.get(i+2).get(k-2);
						}
					}
				}
				if(first == second && second == third){
					
					isFinished = true;
				}
				
			}
		}
		for(int i =0; i < rowSize; i++){	
			for(int k = 0; k < columnSize; k++){
				if(config.get(i).get(k) == 45){
					isComplete = false;
				}
			}
		}
		
		if(isFinished){
			return 1;
		}
		if(isComplete && !isFinished){
			return 0;
		}
		
		else{
			return -2;
		}
		
	}
	
	public static ArrayList<ArrayList<Integer>> compliment(ArrayList<ArrayList<Integer>> current){
		ArrayList<ArrayList<Integer>> newList = new ArrayList<ArrayList<Integer>>();
		for(int i = 0; i < current.size(); i++){
			ArrayList<Integer> newColumn = new ArrayList<Integer>();
			for(int k = 0; k < current.get(i).size(); k++){
				int num = current.get(i).get(k);
				if(num == 79){
					num = 88;
				}
				else if(num == 88){
					num = 79;
				}
				newColumn.add(num);
			}
			newList.add(newColumn);
		}
		return newList;
	}
	
	public boolean isEqual(Config config1, Config config2){
		for(int i = 0; i < config1.getRowSize(); i++){
			for(int k = 0; k < config1.getColumnSize(); k++){
				if(config1.getConfig().get(i).get(k) != config2.getConfig().get(i).get(k)){
					return false;
				}
			}
		}
		return true;
	}
	
	public String toString(){
		String str = "";
		for(int i = columnSize-1; i >= 0; i--){
			for(int k = 0; k < rowSize; k++){
				if(config.get(k).get(i) == 45){
					str += "- ";
				}
				else if(config.get(k).get(i) == 79){
					str += "O ";
				}
				else if(config.get(k).get(i) == 7){
					str += "? ";
				}
				else if(config.get(k).get(i) == 88){
					str += "X ";
				}
			
			}
			str+= "\n";
		}
		return str;
	}
}
