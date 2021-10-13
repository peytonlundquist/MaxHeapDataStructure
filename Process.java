
public class Process implements ProcessInterface, Comparable<Process>{
	private int priority;
	private int timeRemaining;
	private int arrivalTime;
	private int waitingTime; 
	
	public Process(int currentTime, int processTime, int priority){
		arrivalTime = currentTime;
		this.priority = priority;
		timeRemaining = processTime;
	}
	@Override
	public int getPriority() {
		return priority;
	}

	@Override
	public void setPriority(int priority) {
		this.priority = priority;
		
	}

	@Override
	public int getTimeRemaining() {
		return timeRemaining;
	}

	@Override
	public void decrementTimeRemaining() {
		timeRemaining--;
	}

	@Override
	public boolean finished() {
		if(timeRemaining <= 0) {
			return true;
		}
		return false;
	}

	@Override
	public int getArrivalTime() {
		return arrivalTime;
	}

	@Override
	public int getWaitingTime() {
		return waitingTime;
	}

	@Override
	public void incrementWaitingTime() {
		waitingTime++;
		
	}

	@Override
	public void resetWaitingTime() {
		waitingTime = 0;
	}
	@Override
	public int compareTo(Process o) {
		if(this.getPriority() < o.getPriority()) {
			return -1; 
		}
		if (this.getPriority() > o.getPriority()) {
			return 1; 
		}else {
			if(this.getArrivalTime() < o.getArrivalTime()) {
				return 1;
			}else{
				return -1;
			}
		}
	}
}
