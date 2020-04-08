/**
 * @(#)ThreadCounter.java
 *
 * @Clemens 
 * @version 1.00 2017/5/3
 */
public class ThreadCounter extends Thread {
	//Number of seconds to delay
	private int delay;
	private String name;
	
	public ThreadCounter(String n, int d) {
		this.name = n;
		this.delay = d;
	}
	
	/** Override run(), which is empty in Thread
	 *	When .start() is invoked, run() is invoked.
	 */
	public void run(){
		for (int i = 0; i < 100; i++) {
			System.out.println(this.name + ":\t" + i);
			/** PAUSE THREAD */
			try {
				//Static method, paused the Thread from which
				//	the method was invoked.
				Thread.sleep(this.delay);
			} catch (InterruptedException e) {
				//nothing
			}
		}
	}
	
    //==========================================================================

	public static void main(String[] args) {
		//Create threads
		//Delayed 1 second
		ThreadCounter t1 = new ThreadCounter("FIRST", 1000);
		//Delayed 5 seconds
		ThreadCounter t2 = new ThreadCounter("SECOND", 5000);
		
		//Start threads
		t1.start();
		t2.start();
	}
}