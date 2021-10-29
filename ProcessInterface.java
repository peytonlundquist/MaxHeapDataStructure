/**
 * Interface for Process
 */
public interface ProcessInterface 
{
	/**
	 * Getter for priority
	 * @return priority
	 */
	public int getPriority(); 
	
	/**
	 * Setter for priority
	 * @param priority Priority to set
	 */
	public void setPriority(int priority);

	/**
	 * Getter for timeRemaining
	 * @return timeRemaining
	 */
	public int getTimeRemaining(); 
	
	/**
	 * Decrements timeRemaining
	 */
	public void decrementTimeRemaining(); 
	
	/**
	 * Boolean for whther a Process is finished or not
	 * @return Boolean
	 */
	public boolean finished(); 

	/**
	 * Getter for arrivalTime
	 * @return arrivalTime
	 */
	public int getArrivalTime(); 

	/**
	 * Getter for waitingTime
	 * @return waitingTime
	 */
	public int getWaitingTime(); 
	
	/**
	 * Increments waitingTime
	 */
	public void incrementWaitingTime(); 
	
	/**
	 * Resets waitingTime
	 */
	public void resetWaitingTime(); 
}
