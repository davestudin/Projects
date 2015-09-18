
/*
 * @author Dave Studin
 * Position.java
 * 
 * Version:
 * $Id: Position.java,v 1.1 2015/05/15 12:15:31 das2416 Exp $
 * 
 * Comments:
 * $Log: Position.java,v $
 * Revision 1.1  2015/05/15 12:15:31  das2416
 * got it to sorta work
 *
 * Revision 1.1  2015/04/18 15:55:17  das2416
 * first commit
 *
 * Revision 1.5  2015/04/06 03:31:00  das2416
 * final commit
 *
 * Revision 1.4  2015/04/06 03:18:16  das2416
 * fixed all exclamation points
 *
 * Revision 1.3  2015/04/06 02:50:43  das2416
 * final commit
 *
 * Revision 1.2  2015/04/02 18:54:34  das2416
 * test
 *
 * Revision 1.1  2015/04/01 18:53:41  das2416
 * Got program to run successfully, still need to work on design
 *
 */


public abstract class Position{
	
	private int isFinished;
	
	public abstract void setFinish();
	
	public int isFinished(){
		return isFinished;
	}

}
