import java.util.NoSuchElementException;

/**
 * A unit test class for MaxHeaps
 * 
 * 
 * @author mvail, mhthomas, amussell, Peyton Lundquist
 */
public class HeapTester {

	public static void main(String[] args) {
		HeapTester tester = new HeapTester(args);
		tester.runTests();
	}
	
	/** tester constructor
	 * @param args command line args
	 */
	public HeapTester(String[] args) {
		for (String arg : args) {
			if (arg.equalsIgnoreCase("-a"))
				printFailuresOnly = false;
			if (arg.equalsIgnoreCase("-s"))
				showToString = false;
			if (arg.equalsIgnoreCase("-m"))
				printSectionSummaries = false;
		}
	}
	
	private enum Result {
		IndexOutOfBounds, IllegalState, NoSuchElement, 
		ConcurrentModification, UnsupportedOperation, HeapUnderFlow,
		NoException, UnexpectedException,
		True, False, Pass, Fail, 
		MatchingValue,
		ValidString
	};
	
	/** Print test results in a consistent format
	 * @param testDesc description of the test
	 * @param result indicates if the test passed or failed
	 */
	private void printTest(String testDesc, boolean result) {
		totalRun++;
		if (result) { passes++; }
		else { failures++; }
		if (!result || !printFailuresOnly) {
			System.out.printf("%-46s\t%s\n", testDesc, (result ? "   PASS" : "***FAIL***"));
		}
	}

	/** Print a final summary */
	private void printFinalSummary() {
		String verdict = String.format("\nTotal Tests Run: %d,  Passed: %d (%.1f%%),  Failed: %d\n",
				totalRun, passes, passes*100.0/totalRun, failures);
		String line = "";
		for (int i = 0; i < verdict.length(); i++) {
			line += "-";
		}
		System.out.println(line);
		System.out.println(verdict);
	}

	/** Print a section summary */
	private void printSectionSummary() {
		secTotal = totalRun - secTotal;
		secPasses = passes - secPasses;
		secFails = failures - secFails;
		System.out.printf("\nSection Tests: %d,  Passed: %d,  Failed: %d\n", secTotal, secPasses, secFails);
		secTotal = totalRun; //reset for next section
		secPasses = passes;
		secFails = failures;		
		System.out.printf("Tests Run So Far: %d,  Passed: %d (%.1f%%),  Failed: %d\n",
				totalRun, passes, passes*100.0/totalRun, failures);
	}

	/* Named elements for use in tests:
	 * 
	 * Each process corresponds to their assigned letter
	 * in such manner as; A is to highest priority(10), 
	 * B is to middle priority(8), C is to lowest priority(6).
	 * 
	 * Each processes' arrivalTime is irrelevant due to their
	 * unique priorities.
	 * 
	 */
	private static final Process PROCESS_A = new Process(1, 0, 10);
	private static final Process PROCESS_B = new Process(1, 0, 8);
	private static final Process PROCESS_C = new Process(1, 0, 6);
	private static final Process PROCESS_D = new Process(1, 0, 4);
	//	private static final Integer ELEMENT_X = -1;//element that should appear in no lists
	//	private static final Integer ELEMENT_Z = -2;//element that should appear in no lists

	//tracking number of tests and test results
	private int passes = 0;
	private int failures = 0;
	private int totalRun = 0;

	private int secTotal = 0;
	private int secPasses = 0;
	private int secFails = 0;
	
	//control output - modified by command-line args
	private boolean printFailuresOnly = true;
	private boolean showToString = true;
	private boolean printSectionSummaries = true;
	
	private void runTests() {
		Process[] LIST_A = {PROCESS_A};
		String STRING_A = "A";
		Process[] LIST_B = {PROCESS_B};
		String STRING_B = "B";
		Process[] LIST_AB = {PROCESS_A, PROCESS_B};
		String STRING_AB = "AB";
		
		//newly constructed empty list
		testEmptyList(newList, "newList");
		//empty to 1-element list
		testSingleElementList(emptyList_maxHeapInsertA_A, "maxHeapInsertA_A", LIST_A, STRING_A);
		testTwoElementList(A_maxHeapInsertB_AB, "maxHeapInsertB_AB", LIST_AB, STRING_AB);
	}




	private MaxHeap newMaxHeap() {
		MaxHeap maxHeap = new MaxHeap();
		return maxHeap;
	}

	/*
	 * Scenarios 
	 */
	private Scenario<Process[]> newList = () -> newMaxHeap();

	/** Scenario: [ ] -> maxHeapInsert(A) -> [A] 
	 * @return [A] after maxHeapInsert(A)
	 */
	private MaxHeap emptyList_maxHeapInsertA_A() {
		MaxHeap heap = newMaxHeap(); //starting state
		try {
			heap.maxHeapInsert(PROCESS_A);//change (produces resulting list)
		} catch (Exception e) {
			return heap;
		} 
		return heap; //return the resulting list
	}
	private Scenario<Process[]> emptyList_maxHeapInsertA_A = () -> emptyList_maxHeapInsertA_A();
	
	/** Scenario: [A] -> maxHeapInsert(B) -> [A, B] 
	 * @return [A, B] after maxHeapInsert(B)
	 */
	private MaxHeap A_maxHeapInsertB_AB() {
		MaxHeap heap = emptyList_maxHeapInsertA_A(); //starting state
		try {
			heap.maxHeapInsert(PROCESS_B);//change (produces resulting list)
		} catch (Exception e) {
			return heap;
		} 
		return heap; //return the resulting list
	}
	private Scenario<Process[]> A_maxHeapInsertB_AB = () -> A_maxHeapInsertB_AB();
	
	/////////////////////////////////
	//XXX Tests for 0-element list
	/////////////////////////////////
	
	/** Run all tests on scenarios resulting in an empty list
	 * @param scenario lambda reference to scenario builder method
	 * @param scenarioName name of the scenario being tested
	 */
	private void testEmptyList(Scenario<Process[]> scenario, String scenarioName) {
		System.out.printf("\nSCENARIO: %s\n\n", scenarioName);
		try {
			printTest(scenarioName + "_testExtractMax", testExtractMax(scenario.build(), null, Result.HeapUnderFlow));
			printTest(scenarioName + "_testMaxHeapInsert", testMaxHeapInsert(scenario.build(), PROCESS_A, Result.NoException));

		
		
		
		
		
		
		} catch (Exception e) {
			System.out.printf("***UNABLE TO RUN/COMPLETE %s***\n", scenarioName + " TESTS");
			e.printStackTrace();
		} finally {
			if (printSectionSummaries) {
				printSectionSummary();
			}
		}
	}
	//////////////////////////////////
	//XXX Tests for 1-element list
	//////////////////////////////////
	
	/** Run all tests on scenarios resulting in a single element list
	 * @param scenario lambda reference to scenario builder method
	 * @param scenarioName name of the scenario being tested
	 * @param contents elements expected in the list after scenario has been set up
	 * @param contentsString contains character labels corresponding to values in contents
	 */
	private void testSingleElementList(Scenario<Process[]> scenario, String scenarioName, Process[] contents, String contentsString) {
		System.out.printf("\nSCENARIO: %s\n\n", scenarioName);
		try {
			printTest(scenarioName + "_testExtractMax", testExtractMax(scenario.build(), PROCESS_A, Result.MatchingValue));
			printTest(scenarioName + "_testMaxHeapInsert", testMaxHeapInsert(scenario.build(), PROCESS_B, Result.NoException));



		} catch (Exception e) {
			System.out.printf("***UNABLE TO RUN/COMPLETE %s***\n", scenarioName + " TESTS");
			e.printStackTrace();
		} finally {
			if (printSectionSummaries) {
				printSectionSummary();
			}
		}
	}
	
	/////////////////////////////////
	//XXX Tests for 2-element list
	/////////////////////////////////
	
	/** Run all tests on scenarios resulting in a two-element list
	 * @param scenario lambda reference to scenario builder method
	 * @param scenarioName name of the scenario being tested
	 * @param contents elements expected in the list after scenario has been set up
	 * @param contentsString contains character labels corresponding to values in contents 
	 */
	private void testTwoElementList(Scenario<Process[]> scenario, String scenarioName, Process[] contents, String contentsString) {
		System.out.printf("\nSCENARIO: %s\n\n", scenarioName);
		try {
			printTest(scenarioName + "_testExtractMax", testExtractMax(scenario.build(), PROCESS_A, Result.MatchingValue));
			printTest(scenarioName + "_testMaxHeapInsert", testMaxHeapInsert(scenario.build(), PROCESS_C, Result.NoException));



		} catch (Exception e) {
			System.out.printf("***UNABLE TO RUN/COMPLETE %s***\n", scenarioName + " TESTS");
			e.printStackTrace();
		} finally {
			if (printSectionSummaries) {
				printSectionSummary();
			}
		}
	}

	////////////////////////////
	// XXX LIST TEST METHODS
	////////////////////////////

	/** Runs extractMax() method on given list and checks result against expectedResult
	 * @param maxHeap a list already prepared for a given change scenario
	 * @param expectedElement element or null if expectedResult is an Exception
	 * @param expectedResult
	 * @return test success
	 */
	private boolean testExtractMax(MaxHeap maxHeap, Process expectedElement, Result expectedResult) {
		Result result;
		try {
			Process retVal = maxHeap.heapExtractMax();
			if (retVal.equals(expectedElement)) {
				result = Result.MatchingValue;
			} else {
				result = Result.Fail;
			}
		} catch (HeapUnderFlowException e) {
			result = Result.HeapUnderFlow;
		} catch (Exception e) {
			System.out.printf("%s caught unexpected %s\n", "testExtractMax", e.toString());
			e.printStackTrace();
			result = Result.UnexpectedException;
		}
		return result == expectedResult;
	}
	
	/** Runs maxHeapInsert() method on given list and checks result against expectedResult
	 * @param maxHeap a list already prepared for a given change scenario
	 * @param element or null if expectedResult is an Exception
	 * @param element
	 * @return test success
	 */
	private boolean testMaxHeapInsert(MaxHeap maxHeap, Process element, Result expectedResult) {
		Result result;
		try {
			maxHeap.maxHeapInsert(element);
			result = Result.NoException;
		} catch (Exception e) {
			System.out.printf("%s caught unexpected %s\n", "testMaxHeapInsert", e.toString());
			e.printStackTrace();
			result = Result.UnexpectedException;
		}
		return result == expectedResult;
	}
	
}
interface Scenario<T> {
	MaxHeap build();
}