/*
 * @Dave Studin
 * Board.java
 * 
 * Version:
 * $Id: Board.java,v 1.8 2015/03/07 23:35:24 das2416 Exp $
 * 
 * Comments:
 * $Log: Board.java,v $
 * Revision 1.8  2015/03/07 23:35:24  das2416
 * changes:
 * * added comments
 *
 * Revision 1.7  2015/03/07 00:54:09  das2416
 * New commit for reformating after try submission
 *
 * Revision 1.6  2015/03/06 21:39:58  das2416
 * Final Commit before comments
 *
 * Revision 1.5  2015/03/06 21:13:51  das2416
 * changes:
 * *added few more exception classes
 *
 * Revision 1.4  2015/03/05 23:24:50  das2416
 * changes:
 * *converted my original program to a new one that uses inheritance
 *
 * Revision 1.3  2015/03/05 16:13:11  das2416
 * changes:
 * *Got pretty much everything to work
 * **trying to use generics now
 *
 * Revision 1.2  2015/03/05 02:35:57  das2416
 * changes:
 * *added ship class
 * *added several exceptions
 *
 * Logging off for the night
 *
 * Revision 1.1  2015/03/04 20:55:14  das2416
 * First Commit
 * *made shell
 * *made Battleship and Board class
 * *got a board to print
 * *got a working buildShip() method
 *
 */
import java.util.ArrayList;

public class Board{

	//Size of board
	private int size = 0;
	//2d array of Cell objects
	private Cell[][] boardCell = null;
	//List of ships on an instance of a board object
	private ArrayList<Ship> shipList = new ArrayList<Ship>();
	//List of coords for missles that have fired upon that location
	private ArrayList<ArrayList<Integer>> missleList = new ArrayList<ArrayList<Integer>>();
	//Boolean variable to end game
	private boolean gameOver = false;
	
	//All variables below are for stat purposes
	private int statFired = 0;
	private int statHit = 0;
	private int statMisses = 0;
	private int statSunk = 0;
	private double hitRatio = 0;
	
	/**
	 * Constructor that instantiates a Board object
	 * @param size
	 */
	public Board(int size){
		this.size = size;
		boardCell = new Cell[size][size];
		
	}
	
	// ===================== preSetBoard() =========================//
	/**
	 * Method to set board with all Water cell objects on every location
	 */
	public void preSetBoard(){
		
		for(int row = 0; row < size; row++){
			for(int column = 0; column < size; column++){
				ArrayList<Integer> coord = new ArrayList<Integer>(2);
				coord.add(row);
				coord.add(column);
				Cell newCell = new Water(coord);
				boardCell[row][column] = newCell;
			}
		}
	}
	
	
	// ===================== createBoard() =========================//
	/**
	 * Method to print the current state of the board out
	 */
	public void createBoard(){
		
		int charValue = 65;
		
		System.out.print("  ");
		
		for(int i = 0; i < size; i++){
			System.out.print((char)(charValue+i) + " ");
			
		}
		System.out.println();
		
		for(int row=0 ; row < size ; row++ ){
			System.out.print((char)(charValue + row) + " ");
            
			for(int column=0 ; column < size ; column++ ){
               
				System.out.print((char)boardCell[row][column].boardVisual() + " ");

            }
            System.out.println();
        }	
	}
	
	// ===================== buildShip() =========================//
	/**
	 * Method that takes in a Ship object and adds it to the baord
	 * @param aShip
	 * @throws OverlappingShipException
	 */
	public void buildShip(Ship aShip) throws OverlappingShipException{
		boolean overlap = true;
		shipList.add(aShip);
				
			
			
			for(ArrayList<Integer> a: aShip.getShipCoords()){
				overlap = false;
				if(boardCell[a.get(0)][a.get(1)] instanceof ShipCell){
					overlap = true;
					break;
				}
			}
			if(!overlap){
					for(ArrayList<Integer> a: aShip.getShipCoords()){				
					boardCell[a.get(0)][a.get(1)] = new ShipCell(a,aShip.getLabel());
					
				}
			}
			else{
				throw new OverlappingShipException();
			}
		
	}
	
	// ===================== hideShip() =========================//
	/**
	 * Method to hide all the ships on the board from the user
	 */
	public void hideShips(){
		
		for(int row = 0; row < size; row++){
			for(int column = 0; column < size; column++){
				ArrayList<Integer> newList = new ArrayList<Integer>();
				newList.add(row);
				newList.add(column);
				if(boardCell[row][column].intersection(newList)){
					boardCell[row][column].hide();
				}
			}
		}
	}

	
	
//===================== fireMissile() =========================//
	/**
	 * Method that allows the user to fire a missile at the specified location
	 * @param aStr
	 * @throws AlreadyUsedCoordException
	 * @throws IllegalCoordException
	 */
	public void fireMissile(String aStr) throws AlreadyUsedCoordException, IllegalCoordException{
		
		
		String str = aStr.toUpperCase();
		String newString = str.replaceAll("\\s+","");
		char[] coordChar = newString.toCharArray();
		
		ArrayList<Integer> coordInt = new ArrayList<Integer>(2);
		for(int i = 0; i < 2; i++){
			if(coordChar[i]-65 < 0 || coordChar[i]-65 > 26){
				throw new IllegalCoordException();
			}
			coordInt.add(coordChar[i]-65);
			
		}
		
		if(coordInt.get(0) > size || coordInt.get(1) > size){
			throw new IllegalCoordException();
		}
		else{
			statFired++;
		
			if(missleList.contains(coordInt)){
				throw new AlreadyUsedCoordException();
			}
			else{
					
				missleList.add(coordInt);
				boolean flag = false;
				for(Ship a: shipList){
					if(a.isHit(str)){
						statHit++;
						boardCell[coordInt.get(0)][coordInt.get(1)].hit();
						flag =true;
						System.out.println("Hit!");
						if(a.isSunk()){
							statSunk++;
							System.out.println("Sunk!");
							if(statSunk == shipList.size()){
								System.out.println("You Win!");
								gameOver = true;
							}
						}
						
					}
				}
				
				if(!flag){
					statMisses++;
					boardCell[coordInt.get(0)][coordInt.get(1)].hit();
					System.out.println("Miss!");
				}
			}	
		}	
	}
	
	/**
	 * Method that says whether the game is over or not
	 * @return boolean
	 */
	public boolean isGameOver(){
		return gameOver;
	}

	// ===================== getSize() =========================//
	/**
	 * Method that returns the size of the board
	 * @return int
	 */
	public int getSize(){
		return size;
	}
	
	// ===================== getSize() =========================//
	/**
	 * Method that modifies the stats and prints them out at the end 
	 * of the game or at the user's command
	 */
	public void getStats(){
		if(statFired != 0){
			hitRatio = 100*(double)statHit / (double)statFired;
		}
		System.out.println("Number of missiles fired: " + statFired +
				"\nNumber of hits: " + statHit + 
				"\nNumber of misses: " + statMisses +
				"\nHit ratio: " + hitRatio + "%" +
				"\nNumber of ships sunk: " + statSunk);
	}
	// ===================== toString() =========================//
	/**
	 * toString method that prints out the board size and all stats for the current state of the board
	 */
	public String toString(){
		String str ="Board size: " + size + "\n" + 
				"Number of missiles fired: " + statFired +
				"\nNumber of hits: " + statHit + 
				"\nNumber of misses: " + statMisses +
				"\nHit ratio: " + hitRatio + "%" +
				"\nNumber of ships sunk: " + statSunk;
		return str;
	}
	
}
