/**
 * MaxHeap class. A class the provides the max-heap data structure, specifically
 * designed for processes, defined by the Process class.
 * 
 * @author Peyton Lundquist
 * @date 10/13/2021
 */
public class MaxHeap {
	protected int heapSize;
	protected final int ARRAYSIZE = 11;
	protected Process[] A;
	
	public MaxHeap() {
		A = new Process[ARRAYSIZE];
		heapSize = 0;
	}
	
	/**
	 * Maintain the max-heap property.
	 * @param i The index in the heap, which to call maxHeapify on
	 */
	public void maxHeapify(int i) {
		int l = left(i);
		int r = right(i);
		int largest;
		if((l < heapSize) && (A[l].compareTo(A[i]) > 0)) {       // If left node is greater than parent
			largest = l;
		}else {
			largest = i;
		}
		if((r < heapSize) && (A[r].compareTo(A[largest]) > 0)) { // If right node is greater than largest
			largest = r;
		}
		if(largest != i) { 					 // If the largest node isn't the parent
			Process tempProcess = A[i];
			A[i] = A[largest];
			A[largest] = tempProcess;
			maxHeapify(largest);
		}
	}
	
	/**
	 * Build a max-heap by copying an argument Process[], then calling maxHeapify
	 * on each process, from the bottom up, left to right.
	 * @param A Process[] to build a max-heap with
	 */
	public void buildMaxHeap(Process[] A) {
		this.A = new Process[A.length];
		for(int i = 1; i < A.length; i++) { // Copy array
			this.A[i] = A[i];
		}
		heapSize = this.A.length - 1;
		for(int i = Math.floorDiv(this.A.length, 2); i > 0; i--) {
			maxHeapify(i);
		}
	}
	
	/**
	 * Insertion of a Process into a max-heap, while mainting max-heap property.
	 * @param p Process to insert
	 * @throws Exception
	 */
	public void maxHeapInsert(Process p) throws Exception {
		int key = p.getPriority();
		p.setPriority(Integer.MIN_VALUE); // Insures the node starts at the bottom of the heap
		doubleHeapSpace();
		A[heapSize] = p;
		heapSize++;
		heapIncreaseKey(heapSize - 1, p, key);
	}
	
	/**
	 * Doubles the current heap space.
	 */
	private void doubleHeapSpace() {
		if(heapSize >= A.length - 1) {
			int newSize = A.length * 2;
			Process[] temp = new Process[newSize];
			System.arraycopy(A, 0, temp, 0, A.length-1); // Copies array over
			A = temp;
		}
	}
	
	/**
	 * Extracts the Max Process from the heap.
	 * @return Process with maximum priority key
	 * @throws HeapUnderFlowException
	 */
	public Process heapExtractMax() throws HeapUnderFlowException {
		if(heapSize < 1) {
			throw new HeapUnderFlowException("Heap size is less than one.");
		}
		Process max = A[0];
		A[0] = A[heapSize-1];
		heapSize = heapSize - 1;
		maxHeapify(0);		
		return max;
	}
	/**
	 * Increases the priority key of the given Process.
	 * @param i The index of the process
	 * @param p Process to increase priority key
	 * @param key Priority key value to update Process to
	 * @throws Exception
	 */
	public void heapIncreaseKey(int i, Process p, int key) throws Exception {
		p.setPriority(key);
		if(p.getPriority() < A[i].getPriority()) { // If the key isn't being increased
			throw new Exception();
		}
		A[i] = p;
		while((i > 0) && (A[i].compareTo(A[parent(i)]) > 0)) {
			Process tempProcess = A[i];
			A[i] = A[parent(i)];
			A[parent(i)] = tempProcess;
			i = parent(i);
		}
	}
	
	/**
	 * Returns the heap's maximum Process.
	 * @return The Process with the highest priority key
	 */
	public Process heapMaximum() {
		return A[1];
	}
	
	/**
	 * Parent node function.
	 * @param i Index of child
	 * @return The parent index of child node
	 */
	private int parent(int i) {
		return i >> 1;
	}
	
	/**
	 * Left node function.
	 * @param i Index of parent
	 * @return The left child node index of parent node
	 */
	private int left(int i) {
		return i << 1;
	}
	
	/**
	 * Right node function.
	 * @param i Index of parent
	 * @return The right child node index of parent node
	 */
	private int right(int i) {
		return (i << 1) + 1;
	}
	
	/**
	 * Returns heapSize.
	 * @return Number of Processes in the heap
	 */
	public int getHeapSize() {
		return heapSize;
	}
	
	/**
	 * Prints the heap iteratively.
	 * @return The heap in a Process[]
	 */
	public Process[] printHeap() {
		if(heapSize < 1) {
			return null;
		}
		System.out.print("\nMaxHeap built List: [");
		for(int i = 1; i < A.length; i++) {
			System.out.print(A[i].getPriority() + " ");
		}
		System.out.print("]\n");
		return A;	
	}
	
	/**
	 * Getter for the heap.
	 * @return The heap in a Process[]
	 */
	public Process[] getHeap() {
		return A;	
	}
	
	/**
	 * Prints a 3-level binary tree for visualization of the
	 * heap.
	 */
	public void printHeapTree() {
		for(int i = 1; i < A.length; i++) {
			System.out.print(A[i].getPriority() + " ");
		}
		System.out.print("\n\n			" + A[1].getPriority());
		System.out.print("\n                       / \\");
		System.out.print("\n                      /   \\");
		System.out.print("\n	             " + A[2].getPriority());
		System.out.print("     " + A[3].getPriority());
		System.out.print("\n                    / |    | \\");
		System.out.print("\n                   /  |    |  \\" );
		System.out.print("\n                  " + A[4].getPriority() + "   " + A[5].getPriority());
		System.out.print("    " + A[6].getPriority() + "   " + A[7].getPriority());
	}
}
