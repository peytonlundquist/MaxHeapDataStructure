/**
 * HeapUnderFlowException is an Exception thrown when the heap size is less
 * than one
 * 
 * @author Peyton
 * @date 10/12/2021
 */
public class HeapUnderFlowException extends Exception {
	public HeapUnderFlowException(String s) {
		super(s);
	}
}
