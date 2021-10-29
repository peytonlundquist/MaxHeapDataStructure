/**
 * A priority queue interface
 */
public interface PriorityQueueInterface 
{
	/**
	 * Adding to the back of the queue
	 * @param p Process to enqueue
	 */
    public void enqueue(Process p);

    /**
     * Removing from the front of the queue
     * @return Process dequeued
     */
    public Process dequeue(); 

    /**
     * Empty queue checker
     * @return Boolean for if the queue is empty
     */
    public boolean isEmpty();

    /**
     * Updates Processes
     * @param next Process to update
     * @param timeToIncrementPriority The time to increment the priority of the Process
     * @param maxPriority The maximum priority level for any given Process
     */
    public void update(Process next, int timeToIncrementPriority, int maxPriority); 
	
}
