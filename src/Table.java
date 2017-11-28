
public class Table {
	//How many philosophers are being fed
	private static final int NUMBER_OF_PHILOSOPHERS = 5;
	//How long the philosophers have to eat
	private static final long PROCESSING_TIME = 500;
	//Keeps track of if it's still time for the philosophers to eat
	private volatile boolean chowTime = true;
    //number of philosophers who have left the table
    private volatile int departed = 0;
    //When to tell the philosophers to leave
    private long endTime;
    //How much rice was eaten by philosophers
    private int totalSushiEaten = 0;
    //Sterilizing cup for storing the chop sticks
    private Cup cup;
    
    /**
     * Makes the table that call the Philosophers
     */
	public Table() {
		summonPhilosophers();
	}
	
	/**
	 * Calls the philosophers.
	 * Gives them time to eat then tells them to depart.
	 * Records and reports how much sushi the philosophers ate.
	 */
	public void summonPhilosophers() {
		cup = new Cup();
		for(int i = 0; i < NUMBER_OF_PHILOSOPHERS; i++) {
			new Philosopher(this, cup, i);
		}
		//Give the philosophers time to eat
		endTime = System.currentTimeMillis() + PROCESSING_TIME;
		while (System.currentTimeMillis() < endTime) {
        	try {
				Thread.sleep(10);
			} catch (InterruptedException ignored) {}
        }
		
		//Lets philosophers know that it's time to stop eating and depart from the table.
        chowTime=false;
        while(departed < NUMBER_OF_PHILOSOPHERS) {
        	try {
				Thread.sleep(10);
			} catch (InterruptedException ignored) {}
        }
        
        //Summarize the results
        System.out.println("Total sushi eaten by ravenous philosophers " + totalSushiEaten + " sushi rolls.");
	}
	
	/**
	 * records the amount of sushi that the philosopher ate and that he has departed
	 * @param p is the philosopher that is departing
	 */
    public synchronized void departPhilosopher(Philosopher p) {
    	totalSushiEaten += p.getSushiEaten();
    	departed++;
    }
    
    /**
     * Creates the Table
     * @param args
     */
	public static void main(String args[]){
		new Table();
    }

	/**
	 * @return true if still time to eat
	 */
	public boolean isChowTime() {
		return chowTime;
	}
}
