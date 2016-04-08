package kerstein.deadlock;

//philosophers must ask a waiter before picking up the forks
public class Waiter {

	public synchronized boolean tryToEat(Fork a, Fork b){
		if(!a.isInUse() && !b.isInUse()){
			a.setInUse(true);
			b.setInUse(true);
			return true; //if successfully picked up forks
		}
		
		return false;
	}
}
