/*
 * @author Dave Studin
 * Stones.java
 * 
 * Version:
 * $Id: Piles.java,v 1.1 2015/04/29 00:26:33 das2416 Exp $
 * 
 * Comments:
 * $Log: Piles.java,v $
 * Revision 1.1  2015/04/29 00:26:33  das2416
 * Got a working player1 and player2 and isFInished() method is working properly
 *
 * Revision 1.2  2015/04/27 06:07:19  das2416
 * final commit
 *
 * Revision 1.1  2015/04/18 15:55:19  das2416
 * first commit
 *
 * Revision 1.3  2015/04/06 03:18:16  das2416
 * fixed all exclamation points
 *
 * Revision 1.2  2015/04/06 02:50:41  das2416
 * final commit
 *
 * Revision 1.1  2015/04/02 18:54:33  das2416
 * test
 *
 */
import java.util.ArrayList;

public class Piles {
	
	private int numOfPiles;
	private ArrayList<Integer> piles = new ArrayList<Integer>(numOfPiles);
	
	
	public Piles(ArrayList<Integer> list){
		numOfPiles = list.size();
		piles = list;
	}
	
	public Piles(Integer num){
		numOfPiles = num;
		
	}
	
	public Integer getPileSize(){
		return piles.size();
	}
	
	public ArrayList<Integer> getPileArray(){	
		ArrayList<Integer> newList = new ArrayList<Integer>();
		for(Integer i: piles){
			newList.add(i);
		}
		return newList;
	}
	
	public Integer getPile(Integer index){
		return piles.get(index);
	}
	
	public Piles updatePiles(Piles pile, Integer pileNum, Integer rockNum){
		ArrayList<Integer> newPile = new ArrayList<Integer>(pile.getPileSize());
		newPile = pile.getPileArray();
		newPile.remove((int)pileNum);
		newPile.add((int)pileNum, (int)rockNum);
		Piles anotherNewPile = new Piles(newPile);
		return anotherNewPile;
	}
	
	public void changePiles(Integer pileNum, Integer rockNum){
		
		int current = piles.remove(pileNum-1);
		piles.add(pileNum-1, current - rockNum);
	}
	
	public Piles subtractPiles(Piles pile1, Piles pile2){
		
		ArrayList<Integer> list = new ArrayList<Integer>();
		
		for(int i = 0; i < pile1.getPileSize(); i++){
			int pile = Math.abs(pile2.getPile(i)-pile1.getPile(i));
			list.add(pile);			
		}
		Piles pileMove = new Piles(list);
		return pileMove;
	}
	
	public boolean isFinished(){
		int sum = 0;
		for(Integer i : piles){
			sum += i;
		}
		if(sum ==0){
			return true;
		}
		return false;
	}
	
	public String toString(){
		String str = "";
		for(Integer i: piles){
			str += i + " ";
		}
		return str;
	}
}