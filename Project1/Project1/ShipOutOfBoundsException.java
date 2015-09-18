/*
 * @Dave Studin
 * Ship.java
 * 
 * Version:
 * $Id: ShipOutOfBoundsException.java,v 1.3 2015/03/07 23:55:30 das2416 Exp $
 * 
 * Comments:
 * $Log: ShipOutOfBoundsException.java,v $
 * Revision 1.3  2015/03/07 23:55:30  das2416
 * changes:
 * * added comments
 *
 * Revision 1.2  2015/03/06 21:13:50  das2416
 * changes:
 * *added few more exception classes
 *
 * Revision 1.1  2015/03/05 02:35:56  das2416
 * changes:
 * *added ship class
 * *added several exceptions
 *
 * Logging off for the night
 *
 */

public class ShipOutOfBoundsException extends Exception {
	
	/**
	 * Constructor that creates an instance of this exception
	 */
	public ShipOutOfBoundsException(){
		super("Overlapping or out-of-bounds ships in ");
	}

}
