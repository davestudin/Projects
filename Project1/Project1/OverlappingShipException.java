/*
 * @Dave Studin
 * Ship.java
 * 
 * Version:
 * $Id: OverlappingShipException.java,v 1.3 2015/03/07 23:50:57 das2416 Exp $
 * 
 * Comments:
 * $Log: OverlappingShipException.java,v $
 * Revision 1.3  2015/03/07 23:50:57  das2416
 * changes:
 * * added comments
 *
 * Revision 1.2  2015/03/06 21:13:45  das2416
 * changes:
 * *added few more exception classes
 *
 * Revision 1.1  2015/03/05 02:35:52  das2416
 * changes:
 * *added ship class
 * *added several exceptions
 *
 * Logging off for the night
 *
 */

public class OverlappingShipException extends Exception {
	
	/**
	 * Constructor that creates an instance of this exception
	 */
	public OverlappingShipException(){
		super("Overlapping or out-of-bounds ships in ");
	}
}
