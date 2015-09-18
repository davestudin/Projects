/*
 * @Dave Studin
 * Ship.java
 * 
 * Version:
 * $Id: Ship.java,v 1.5 2015/03/07 23:50:34 das2416 Exp $
 * 
 * Comments:
 * $Log: Ship.java,v $
 * Revision 1.5  2015/03/07 23:50:34  das2416
 * changes:
 * * added comments
 *
 * Revision 1.4  2015/03/07 00:53:45  das2416
 * New commit for reformating after try submission
 *
 * Revision 1.3  2015/03/06 21:13:46  das2416
 * changes:
 * *added few more exception classes
 *
 * Revision 1.2  2015/03/05 16:13:06  das2416
 * changes:
 * *Got pretty much everything to work
 * **trying to use generics now
 *
 * Revision 1.1  2015/03/05 02:35:54  das2416
 * changes:
 * *added ship class
 * *added several exceptions
 *
 * Logging off for the night
 *
 */
import java.util.ArrayList;

public class Ship{
	
	//Input form the file in int form
	private int[] shipInput = new int[4];
	//Label of the ship
	private String shipLabel;
	//Variable to count the number of ships created
	private static int numOfShips = 0;
	//List of coordinates that are contained within this ship object
	private ArrayList<ArrayList<Integer>> shipCoords =new ArrayList<ArrayList<Integer>>();
	//Counts the number of times this ship has been hit
	private int numOfHits = 0;
	//Ship size
	private int shipSize = 0;
	
	
	//==================== Constructor =========================//
	/**
	 * Creates an instance of a Ship object with the first coordinate and the last coordinate of the ship
	 * @param shipLocation
	 */
	public Ship(String shipLocation){
		
		shipLocation.toUpperCase();
		String newString = shipLocation.replaceAll("\\s+","");
		char[] shipArrayChar = newString.toCharArray();
		
		int[] ship = new int[4];
		
		for(int i = 0; i < 4; i++){
			ship[i] = shipArrayChar[i]-65;
			
		}
		shipInput = ship;
		
		
		//Horizontal Ship
		if(ship[0] == ship[2]){	
			this.shipSize = Math.abs(ship[1] - ship[3]);
			for(int column = ship[1]; column <= (shipSize + ship[1]); column++){
				ArrayList<Integer> coordPair = new ArrayList<Integer>(2);
				coordPair.add(ship[0]);
				coordPair.add(column);
				shipCoords.add(coordPair);			
			}			
		}
		
		//Vertical Ship
		if(ship[1] == ship[3]){
			this.shipSize = Math.abs(ship[0] - ship[2]);
			for(int row = ship[0]; row <= (shipSize + ship[0]); row++){
				ArrayList<Integer> coordPair = new ArrayList<Integer>(2);
				coordPair.add(row);
				coordPair.add(ship[1]);
				shipCoords.add(coordPair);
			}
		}
		
		shipLabel = (char)(numOfShips + 65) + "";
		numOfShips++;			
	}
	
	/**
	 * Same constructor as above except keeps track of the label of the ship as well as everything else
	 * @param shipLocation
	 * @param label
	 */
	public Ship(String shipLocation, String label){
		Ship ship = new Ship(shipLocation);
		this.shipInput = ship.getInputCoords();
		this.shipCoords = ship.getShipCoords();
		
		shipLabel = label;
	}
	
	//==================== isHit() =========================//
	/**
	 * Method that reports whether a ship has been hit or not by a missle
	 * @param aCoord
	 * @return boolean
	 */
	public boolean isHit(String aCoord){
		
		boolean flag = false;
		String coord = aCoord.toUpperCase();
		String newString = coord.replaceAll("\\s+","");
		char[] coordChar = newString.toCharArray();
		
		ArrayList<Integer> coordInt = new ArrayList<Integer>(2);
		for(int i = 0; i < 2; i++){
			coordInt.add(coordChar[i]-65);
		}
		
		if(shipCoords.contains(coordInt)){
			numOfHits++;
			flag = true;
		}
		
		return flag;
		
	}
	
	//==================== isSunk() =========================//
	/**
	 * Method that says if the ship has been sunk or not
	 * @return boolean
	 */
	public boolean isSunk(){
		if(numOfHits == shipSize+1){
			return true;
		}
		return false;
	}
	
	//==================== getLabel =========================//
	/**
	 * Returns the label for the ship
	 * @return String
	 */
	public String getLabel(){
		return shipLabel;
	}
	
	//==================== getShipCoords =========================//
	/**
	 * Returns the list of ship coordinates for this ship
	 * @return ArrayList<ArrayList<Integer>>
	 */
	public ArrayList<ArrayList<Integer>> getShipCoords(){
		return shipCoords;
	}
	
	//==================== getInputCoords =========================//
	/**
	 * Returns the intial input coordinates of the first coordinate of the ship and the last
	 * @return
	 */
	public int[] getInputCoords(){
		return shipInput;
	}
	
	//==================== toString() =========================//
	/**
	 * Returns the arrayList of all the ships coordinates
	 * @return String
	 */
	public String toString(){
		String str = "";
		for(ArrayList<Integer> a: shipCoords){
			str += "" + a;
		}
		return str;
	}
	
	
	
	
	
}
