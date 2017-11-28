import java.util.Random;

public class Philosopher implements Runnable{
	//How long it takes to eat a piece of sushi
	private static final int EAT_DURATION = 5;
	//How long philosophers philosophize when full
	private static final int PHILOSOPHIZE_DURATION = 50;
	//The random number generator that allows for philosophers to wait different amounts of time when they don't get chop sticks
	private final Random rand = new Random();
	//The table that the philosophers are sitting at
	private final Table table;
	//The sterilized cup that the chop sticks are held in
	private final Cup cup;
	//The philosopher's id
	private final int id; 
	//How much Sushi a philosopher has eaten
	private int sushiEaten = 0;
	
	/**
	 * Creates a new philosopher
	 * @param tab the table he/she sits ate
	 * @param cuppy the cup he/she gets his/her chop sticks from
	 * @param iD his/her id
	 */
	public Philosopher(Table tab, Cup cuppy, int iD) {
		table = tab;
		id = iD;
		cup = cuppy;
		new Thread(this, "Philosopher[" + id + "]").start();
	}

	/**
	 * Has the philosopher eat and philosophize until chow time is over 
	 * then has him/her depart from the table reporting how much sushi he/she ate.
	 */
	public void run() {
        
		while(table.isChowTime()){
			eatSushi();
		}
		
        // Print how much sushi was eaten by this philosopher
        System.out.println("Philiosopher " + id + " ate " + sushiEaten + " sushi roles.");
        // record how much sushi was eaten by this philosopher and have him leave the table
      	table.departPhilosopher(this);
	}
	
	/**
	 * Has the philosopher try to fetch chop sticks from the sterilized cup
	 * 
	 * if he/she succeeds then has him/her eat, 
	 * return the chop sticks and the philosophize in a content manner
	 * 
	 * if the he/she fails to get chop sticks then he/she will philosophize 
	 * for a shorter amount of time while waiting for chop sticks.
	 */
	private void eatSushi() {
		if(cup.fetchChopSticks(id)) {
			try {
				Thread.sleep(EAT_DURATION);
			} catch (InterruptedException ignored) {}
			System.out.println(id + " has eaten " + ++sushiEaten + " sushi rolls");
			if(cup.returnChopSticks(id)) {
			
			contentPhilosophizing();
			}else {
				System.out.println("Illegal chop stick duplication has occured.");
			}
		}else {
			hungryPhilosophizing();
		}
		
	}

	/**
	 * Philosophizes until he/she get hungry again
	 */
	private void contentPhilosophizing() {
		try {
			Thread.sleep(PHILOSOPHIZE_DURATION);
		} catch (InterruptedException ignored) {}
	}
	
	/**
	 * Philosophizes for a bit while waiting for chop sticks
	 */
	private void hungryPhilosophizing() {
		try {
			Thread.sleep(rand.nextInt(PHILOSOPHIZE_DURATION));
		} catch (InterruptedException ignored) {}
	}
	
	/**
	 * @return the sushiEaten
	 */
	public int getSushiEaten() {
		return sushiEaten;
	}
	
}
