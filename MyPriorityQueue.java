
public class MyPriorityQueue extends MaxHeap implements PriorityQueueInterface {
	
	public MyPriorityQueue() {
		super();
	}
	
	@Override
	public void enqueue(Process p) {
		try {
			maxHeapInsert(p);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Process dequeue() {
		try {
			Process max = heapExtractMax();
			return max;
		} catch (HeapUnderFlowException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean isEmpty() {
		if(getHeapSize() > 0) {
			return false;
		}
		return true;
	}

	@Override
	public void update(Process next, int timeToIncrementPriority, int maxPriority) {
		next.resetWaitingTime();
		for(int i = 0; i < heapSize; i++) {
			A[i].incrementWaitingTime();
			if(A[i].getWaitingTime() >= timeToIncrementPriority) {
				A[i].resetWaitingTime();
				if((A[i].getPriority()) < maxPriority) {
					A[i].setPriority(A[i].getPriority() + 1);
					try {
						heapIncreaseKey(i, A[i], A[i].getPriority());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}

	}
}
