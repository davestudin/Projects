/*
 * @author Dave Studin
 * IllegalCoordException.java
 * 
 * Version:
 * $Id: IllegalCoordException.java,v 1.2 2015/03/07 23:51:20 das2416 Exp $
 * 
 * Comments:
 * $Log: IllegalCoordException.java,v $
 * Revision 1.2  2015/03/07 23:51:20  das2416
 * changes:
 * * added comments
 *
 */

public class IllegalCoordException extends Exception{
	
	/**
	 * Constructor that creates an instance of this exception
	 */
	public IllegalCoordException(){
		super("Illegal Coordinates.");
	}
}
