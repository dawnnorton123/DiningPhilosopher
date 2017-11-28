
public class Cup {
	//number of chop sticks to start with
	private static final int STARTING_STICKS = 5;
	//number of chop sticks needed to eat
	private static final int STICKS_NEEDED = 2;
	//how many chop sticks are currently in the cup
	private int chopSticks = STARTING_STICKS;
	
	/**
	 * basic constructor
	 */
	public Cup() {}

	/**
	 * Provides chop sticks if their are enough to eat with.
	 * @return true if chop sticks retrieved successfully.
	 */
	public synchronized boolean fetchChopSticks(int id) {
		if(chopSticks > STICKS_NEEDED-1) {
			chopSticks -=STICKS_NEEDED;
			System.out.println(chopSticks + " chop sticks left in cup after " + id + " retreived chop sticks");
			return true;			
		}else return false;
	}
	
	/**
	 * Records the return of the chop sticks.
	 * @return false if total chop sticks exceeds starting amount.
	 */
	public synchronized boolean returnChopSticks(int id) {
		if(chopSticks < (STARTING_STICKS - STICKS_NEEDED)+1) {
			chopSticks +=STICKS_NEEDED;
			System.out.println(chopSticks + " chop sticks in cup after " + id + " returned chop sticks");
			return true;
		}else return false;
	}
}
