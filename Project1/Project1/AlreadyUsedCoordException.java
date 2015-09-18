/*
 * @author Dave Studin
 * AlreadyUsedCoordException.java
 * 
 * Version:
 * $Id: AlreadyUsedCoordException.java,v 1.2 2015/03/08 00:07:22 das2416 Exp $
 * 
 * Comments:
 * $Log: AlreadyUsedCoordException.java,v $
 * Revision 1.2  2015/03/08 00:07:22  das2416
 * changes:
 * * added comments
 *
 */

public class AlreadyUsedCoordException extends Exception{

	/**
	 * Constructor that creates an instance of this exception
	 */
	public AlreadyUsedCoordException(){
		super("Coordinate previously fired upon.");
	}
	
	
}
