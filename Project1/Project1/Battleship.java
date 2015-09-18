/*
 * @Dave Studin
 * Board.java
 * 
 * Version:
 * $Id: Battleship.java,v 1.10 2015/03/08 00:05:46 das2416 Exp $
 * 
 * Comments:
 * $Log: Battleship.java,v $
 * Revision 1.10  2015/03/08 00:05:46  das2416
 * changes:
 * * got rid of some not-used variables
 *
 * Revision 1.9  2015/03/07 23:25:14  das2416
 * changes:
 * * added comments
 *
 * Revision 1.8  2015/03/07 00:57:54  das2416
 * this is stupid....
 *
 * Revision 1.7  2015/03/07 00:56:39  das2416
 * Another reformatting commit
 *
 * Revision 1.6  2015/03/07 00:53:34  das2416
 * New commit for reformating after try submission
 *
 * Revision 1.5  2015/03/06 21:39:56  das2416
 * Final Commit before comments
 *
 * Revision 1.4  2015/03/06 21:13:46  das2416
 * changes:
 * *added few more exception classes
 *
 * Revision 1.3  2015/03/05 16:13:04  das2416
 * changes:
 * *Got pretty much everything to work
 * **trying to use generics now
 *
 * Revision 1.2  2015/03/05 02:35:53  das2416
 * changes:
 * *added ship class
 * *added several exceptions
 *
 * Logging off for the night
 *
 * Revision 1.1  2015/03/04 20:55:13  das2416
 * First Commit
 * *made shell
 * *made Battleship and Board class
 * *got a board to print
 * *got a working buildShip() method
 *
 */
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Battleship {
	
	
	public static void main(String[]args){
		
		//Checks if there is a valid number of arguments, exits the program if an exception occurs
		if(args.length != 2){
			System.err.println("Usage: Battleship N config-file \nUsage: java Battleship N config-file");
			System.exit(1);
		}
		
		//name of file
		String fileName = args[1];
		
		//A list of ships to keep track of the ships on the board
		ArrayList<Ship> listShips = new ArrayList<Ship>();
		
		//size of the board
		int size = 0;
		
		//Creates an instance of the Board class
		Board newBoardShips = null;
		
		//attempts to read the first argument and checks to make sure its an integer, if not, it exits the program
		try{
			size = Integer.parseInt(args[0]);
			
			//throws exception
			if(size < 5){
				System.err.println("Board must be at least 5 by 5.");
				System.exit(1);
			}
			//throws exception
			else if(size > 26){
				System.err.println("Board must be at most 26 by 26.");
				System.exit(1);
			}
			//builds a ship if no exception is thrown
			else{
			newBoardShips = new Board(size);
			newBoardShips.preSetBoard();
			}
		}
		catch(Exception e){
			System.err.println("Usage: Battleship N config-file \nUsage: java Battleship N config-file");
			System.exit(1);
		}
		boolean flag = true;
		boolean flag2 = true;
		//Attempts to read the file from the second command line argument, throws an exception if the file is invalid
		try{
			BufferedReader inputStream = new BufferedReader(new FileReader(fileName));
			while(flag)
			{
				try
				{		
					//Reads the very first line and thats is
					if(inputStream.ready() && flag2)
					{
						String str = inputStream.readLine();
						inputStream.mark(str.length());	
						flag2 = false;
					}
					
					//reads the rest of the lines
					else if(inputStream.ready()){
						String str = inputStream.readLine();
						
						inputStream.mark(str.length());
						
						/* tries to create a ship given the line of input from the file, 
						 * if the ship coords are wrong, this will throw an exception
						*/
						try{
							str.toUpperCase();
							String newString = str.replaceAll("\\s+","");
							char[] shipArrayChar = newString.toCharArray();
							
							int[] shipCoord = new int[4];
							
							for(int i = 0; i < 4; i++){
								shipCoord[i] = shipArrayChar[i]-65;
								
							}
							for(int a: shipCoord){
								if(a > size){
									throw new ShipOutOfBoundsException();
								}
							}
							Ship ship = new Ship(str);
							listShips.add(ship);
							newBoardShips.buildShip(ship);
						}
						catch(Exception e){
							System.err.print(e.getMessage() + fileName);
							System.exit(1);
						}
						
					}
					
					//Closes the stream after the entire file has been read
					else
					{
						flag = false;
						inputStream.close();
					}
					
				}
				catch(IOException e)
				{
					System.err.println("Cannot open " + fileName + ".");
					System.exit(1);
				}
						
			}
		}
		catch(FileNotFoundException e){
			System.err.println("Cannot open " + fileName + ".");
			System.exit(1);
		}
		
		Board newBoard = new Board(size);
		
		newBoard.preSetBoard();
		newBoard.createBoard();
		for(Ship a: listShips){
			try{
				newBoard.buildShip(a);
			}
			catch(Exception e){
				System.err.println(e.getCause() +fileName);
				System.exit(0);
			}
		}
		
		//Opens a new input stream so the user can begin to enter commands
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		boolean flag3 = true;
		
		while(flag3){
			System.out.print("> ");
			try{
				String command = br.readLine().toLowerCase();
				
				//Command prints the boards current state
				if(command.equals("board")){
					newBoard.hideShips();
					newBoard.createBoard();
				}
				
				//Command displays the current location of ships on the board
				else if(command.equals("ships")){
					
					newBoardShips.createBoard();
				}
				
				//Command allows the user to fire at specific coords, may throw an exception
				else if(command.contains("fire")){
					String target = command.replaceFirst("fire", "");
					
					newBoard.hideShips();
					try{
						if(command.split(" ").length != 3){
							throw new IllegalCoordException();
						}
						newBoard.fireMissile(target);
						if(newBoard.isGameOver()){
							newBoard.getStats();
							flag3 =false;
							br.close();
						}
						else{
							newBoard.createBoard();	
						}	
					}
					catch(Exception e){
						System.out.println(e.getMessage());
					}
				}
				
				//Command gives list of possible commands
				else if(command.equals("help")){
					System.out.println("Possible commands: \n"+
										"board - displays the user's board \n"+
										"ships - displays the placement of the ships \n" +
										"fire r c - fires a missile at the cell at [r,c] \n"+
										"stats - prints out the game statistics \n"+
										"quit - exits the game");
				}
				
				//Command prints list of stats
				else if(command.equals("stats")){
					newBoard.getStats();
				}
				
				//Command exits the input stream
				else if(command.equals("quit")){
					flag3 =false;
					br.close();
				}
				//Any other command will give an Illegal Command prompt
				else{
					System.out.print("Illegal command.\n");
				}
			}
			catch(Exception e){
				System.out.print(e.getMessage());
			}			
		}
	}	
}
