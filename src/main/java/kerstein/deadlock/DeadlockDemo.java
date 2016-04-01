package kerstein.deadlock;

public class DeadlockDemo {
	
	public synchronized static void neverReturn(){
		try {
			Thread.sleep(100000000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public synchronized static void notGoingToHappen(){
		System.out.println("Never happens");
	}
	
	public static void main(String[]args){
		new Thread(){
			public void run(){
				DeadlockDemo.neverReturn();
			}

		}.start();
	}
}
