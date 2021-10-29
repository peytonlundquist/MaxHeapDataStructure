/**
 * A unit test class for a MaxHeap data structure, testing
 * the following methods: heapExtractMax, maxHeapInsert, 
 * and maxHeapify.
 * 
 * @Notes Transformed from Mason Vail's LinkedList unit test class format.
 * @author mvail, mhthomas, amussell, Peyton Lundquist
 * @date 10/13/2021
 */
public class HeapTester {

	public static void main(String[] args) {
		HeapTester tester = new HeapTester(args);
		tester.runTests();
	}
	
	/** HeapTester constructor
	 * @param args command line args
	 */
	public HeapTester(String[] args) {
		for (String arg : args) {
			if (arg.equalsIgnoreCase("-a"))
				printFailuresOnly = false;
			if (arg.equalsIgnoreCase("-m"))
				printSectionSummaries = false;
		}
	}
	
	// All possible results from testing in an enum Result
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
	 * Each Process corresponds to their assigned letter
	 * in such manner as; A is to highest priority(10), 
	 * B is to middle priority(8), C is to lowest priority(6).
	 * 
	 * Most Processes' arrival time is irrelevant due to their
	 * unique priorities. Although, Process C and Process D
	 * have the same priority, and different arrival times. 
	 * Their arrival times are relevant for testing the 
	 * Max Heap's secondary comparison of arrival time.
	 * 
	 */
	private static final Process PROCESS_A = new Process(1, 1, 10);
	private static final Process PROCESS_B = new Process(1, 1, 8);
	private static final Process PROCESS_C = new Process(2, 1, 6); // *Note* The arrival time (or currentTime)
	private static final Process PROCESS_D = new Process(1, 1, 6); // *Note* The arrival time (or currentTime)

	// Tracking number of tests and test results
	private int passes = 0;
	private int failures = 0;
	private int totalRun = 0;

	private int secTotal = 0;
	private int secPasses = 0;
	private int secFails = 0;
	
	// Control output - modified by command-line args
	private boolean printFailuresOnly = true;
	private boolean printSectionSummaries = true;
	
	/////////////////////
	// XXX runTests()
	/////////////////////
	
	private void runTests() {
		//Possible heap contents after a scenario has been set up
		Process[] LIST_A = {PROCESS_A};
		String STRING_A = "A";
		Process[] LIST_B = {PROCESS_B};
		String STRING_B = "B";
		Process[] LIST_C = {PROCESS_C};
		String STRING_C = "C";
		Process[] LIST_D = {PROCESS_D};
		String STRING_D = "D";
		Process[] LIST_AB = {PROCESS_A, PROCESS_B};
		String STRING_AB = "AB";
		Process[] LIST_AC = {PROCESS_A, PROCESS_C};
		String STRING_AC = "AC";
		Process[] LIST_BC = {PROCESS_B, PROCESS_C};
		String STRING_BC = "BC";
		Process[] LIST_BD = {PROCESS_B, PROCESS_D};
		String STRING_BD = "BD";
		Process[] LIST_DC = {PROCESS_D, PROCESS_C};
		String STRING_DC = "DC";
		Process[] LIST_ABC = {PROCESS_A, PROCESS_B, PROCESS_C};
		String STRING_ABC = "ABC";
		Process[] LIST_ADC = {PROCESS_A, PROCESS_D, PROCESS_C};
		String STRING_ADC = "ADC";	
		Process[] LIST_BDC = {PROCESS_B, PROCESS_D, PROCESS_C};
		String STRING_BDC = "BDC";		
		// newly constructed empty heap
		testEmptyHeap(newList, "newList");
		// empty to 1-element heap
		testSingleElementHeap(emptyList_maxHeapInsertA_A, "emptyList_maxHeapInsertA_A", LIST_A, STRING_A);
		testSingleElementHeap(emptyList_maxHeapInsertB_B, "emptyList_maxHeapInsertB_B", LIST_B, STRING_B);
		testSingleElementHeap(emptyList_maxHeapInsertC_C, "emptyList_maxHeapInsertC_C", LIST_C, STRING_C);
		testSingleElementHeap(emptyList_maxHeapInsertD_D, "emptyList_maxHeapInsertD_D", LIST_D, STRING_D);
		// 1-element to empty heap
		testEmptyHeap(A_heapExtractMax_emptyHeap, "A_heapExtractMax_emptyHeap");
		testEmptyHeap(B_heapExtractMax_emptyHeap, "B_heapExtractMax_emptyHeap");
		testEmptyHeap(C_heapExtractMax_emptyHeap, "C_heapExtractMax_emptyHeap");
		testEmptyHeap(D_heapExtractMax_emptyHeap, "D_heapExtractMax_emptyHeap");
		// 1 to 2-element heap
		testTwoElementHeap(A_maxHeapInsertC_AC, "A_maxHeapInsertC_AC", LIST_AC, STRING_AC);
		testTwoElementHeap(A_maxHeapInsertB_AB, "A_maxHeapInsertB_AB", LIST_AB, STRING_AB);
		testTwoElementHeap(B_maxHeapInsertC_BC, "B_maxHeapInsertC_BC", LIST_BC, STRING_BC);
		testTwoElementHeap(C_maxHeapInsertD_DC, "C_maxHeapInsertD_DC", LIST_DC, STRING_DC);		
		// 2 to 1-element heap
		testSingleElementHeap(AB_heapExtractMax_B, "AB_heapExtractMax_B", LIST_B, STRING_B);
		testSingleElementHeap(DC_heapExtractMax_C, "DC_heapExtractMax_C", LIST_C, STRING_C);	
		testSingleElementHeap(AC_heapExtractMax_C, "AC_heapExtractMax_C", LIST_C, STRING_C);
		testSingleElementHeap(BD_heapExtractMax_D, "BD_heapExtractMax_D", LIST_D, STRING_D);
		// 2 to 3-element heap
		testThreeElementHeap(AB_maxHeapInsertC_ABC, "AB_maxHeapInsertC_ABC", LIST_ABC, STRING_ABC);	
		testThreeElementHeap(DC_maxHeapInsertA_ADC, "DC_maxHeapInsertA_ADC", LIST_ADC, STRING_ADC);	
		testThreeElementHeap(AC_maxHeapInsertD_ADC, "AC_maxHeapInsertD_ADC", LIST_ADC, STRING_ADC);	
		testThreeElementHeap(DC_maxHeapInsertB_BDC, "DC_maxHeapInsertB_BDC", LIST_BDC, STRING_BDC);	
		// 3 to 2-element heap
		testTwoElementHeap(ABC_heapExtractMax_BC, "ABC_heapExtractMax_BC", LIST_BC, STRING_BC);
		testTwoElementHeap(ADC_heapExtractMax_DC, "ADC_heapExtractMax_DC", LIST_DC, STRING_DC);
		testTwoElementHeap(BDC_heapExtractMax_DC, "BDC_heapExtractMax_DC", LIST_DC, STRING_DC);
		testTwoElementHeap(ABD_heapExtractMax_BD, "ABD_heapExtractMax_BD", LIST_BD, STRING_BD);
		// Final Summary
		printFinalSummary();
	}

	//////////////////////////////////////
	// XXX SCENARIO BUILDERS
	//////////////////////////////////////
	
	/**
	 * New heap creator
	 * @return New heap
	 */
	private MaxHeap newMaxHeap() {
		MaxHeap maxHeap = new MaxHeap();
		return maxHeap;
	}

	/** Lamda reference for scenarios */
	private Scenario<Process[]> newList = () -> newMaxHeap();

	/** Scenario: [ ] -> maxHeapInsert(A) -> [A] 
	 * @return [A] after maxHeapInsert(A)
	 */
	private MaxHeap emptyList_maxHeapInsertA_A() {
		MaxHeap heap = newMaxHeap(); //starting state
		try {
			heap.maxHeapInsert(PROCESS_A);//change (produces resulting heap)
		} catch (Exception e) {
			return heap;
		} 
		return heap; //return the resulting heap
	}
	private Scenario<Process[]> emptyList_maxHeapInsertA_A = () -> emptyList_maxHeapInsertA_A();
	
	/** Scenario: [ ] -> maxHeapInsert(B) -> [B] 
	 * @return [B] after maxHeapInsert(B)
	 */
	private MaxHeap emptyList_maxHeapInsertB_B() {
		MaxHeap heap = newMaxHeap(); //starting state
		try {
			heap.maxHeapInsert(PROCESS_B);//change (produces resulting heap)
		} catch (Exception e) {
			return heap;
		} 
		return heap; //return the resulting heap
	}
	private Scenario<Process[]> emptyList_maxHeapInsertB_B = () -> emptyList_maxHeapInsertB_B();
	
	/** Scenario: [ ] -> maxHeapInsert(C) -> [C] 
	 * @return [C] after maxHeapInsert(C)
	 */
	private MaxHeap emptyList_maxHeapInsertC_C() {
		MaxHeap heap = newMaxHeap(); //starting state
		try {
			heap.maxHeapInsert(PROCESS_C);//change (produces resulting heap)
		} catch (Exception e) {
			return heap;
		} 
		return heap; //return the resulting heap
	}
	private Scenario<Process[]> emptyList_maxHeapInsertC_C = () -> emptyList_maxHeapInsertC_C();
	
	/** Scenario: [ ] -> maxHeapInsert(D) -> [D] 
	 * @return [D] after maxHeapInsert(D)
	 */
	private MaxHeap emptyList_maxHeapInsertD_D() {
		MaxHeap heap = newMaxHeap(); //starting state
		try {
			heap.maxHeapInsert(PROCESS_D);//change (produces resulting heap)
		} catch (Exception e) {
			return heap;
		} 
		return heap; //return the resulting heap
	}
	private Scenario<Process[]> emptyList_maxHeapInsertD_D = () -> emptyList_maxHeapInsertD_D();
	
	/** Scenario: [A] -> heapExtractMax() -> [] 
	 * @return [] after heapExtractMax()
	 */
	private MaxHeap A_heapExtractMax_emptyHeap() {
		MaxHeap heap = emptyList_maxHeapInsertA_A(); //starting state
		try {
			heap.heapExtractMax();//change (produces resulting heap)
		} catch (Exception e) {
			return heap;
		} 
		return heap; //return the resulting heap
	}
	private Scenario<Process[]> A_heapExtractMax_emptyHeap = () -> A_heapExtractMax_emptyHeap();
	
	/** Scenario: [B] -> heapExtractMax() -> [] 
	 * @return [] after heapExtractMax()
	 */
	private MaxHeap B_heapExtractMax_emptyHeap() {
		MaxHeap heap = emptyList_maxHeapInsertB_B(); //starting state
		try {
			heap.heapExtractMax();//change (produces resulting heap)
		} catch (Exception e) {
			return heap;
		} 
		return heap; //return the resulting heap
	}
	private Scenario<Process[]> B_heapExtractMax_emptyHeap = () -> B_heapExtractMax_emptyHeap();
	
	/** Scenario: [C] -> heapExtractMax() -> [] 
	 * @return [] after heapExtractMax()
	 */
	private MaxHeap C_heapExtractMax_emptyHeap() {
		MaxHeap heap = emptyList_maxHeapInsertA_A(); //starting state
		try {
			heap.heapExtractMax();//change (produces resulting heap)
		} catch (Exception e) {
			return heap;
		} 
		return heap; //return the resulting heap
	}
	private Scenario<Process[]> C_heapExtractMax_emptyHeap = () -> C_heapExtractMax_emptyHeap();
	
	/** Scenario: [D] -> heapExtractMax() -> [] 
	 * @return [] after heapExtractMax()
	 */
	private MaxHeap D_heapExtractMax_emptyHeap() {
		MaxHeap heap = emptyList_maxHeapInsertD_D(); //starting state
		try {
			heap.heapExtractMax();//change (produces resulting heap)
		} catch (Exception e) {
			return heap;
		} 
		return heap; //return the resulting heap
	}
	private Scenario<Process[]> D_heapExtractMax_emptyHeap = () -> D_heapExtractMax_emptyHeap();
	
	/** Scenario: [A] -> maxHeapInsert(B) -> [A, B] 
	 * @return [A, B] after maxHeapInsert(B)
	 */
	private MaxHeap A_maxHeapInsertB_AB() {
		MaxHeap heap = emptyList_maxHeapInsertA_A(); //starting state
		try {
			heap.maxHeapInsert(PROCESS_B);//change (produces resulting heap)
		} catch (Exception e) {
			return heap;
		} 
		return heap; //return the resulting heap
	}
	private Scenario<Process[]> A_maxHeapInsertB_AB = () -> A_maxHeapInsertB_AB();
	
	/** Scenario: [B] -> maxHeapInsert(C) -> [B, C] 
	 * @return [B, C] after maxHeapInsert(B)
	 */
	private MaxHeap B_maxHeapInsertC_BC() {
		MaxHeap heap = emptyList_maxHeapInsertB_B(); //starting state
		try {
			heap.maxHeapInsert(PROCESS_C);//change (produces resulting heap)
		} catch (Exception e) {
			return heap;
		} 
		return heap; //return the resulting heap
	}
	private Scenario<Process[]> B_maxHeapInsertC_BC = () -> B_maxHeapInsertC_BC();
	
	/** Scenario: [A] -> maxHeapInsert(C) -> [A, C] 
	 * @return [A, C] after maxHeapInsert(C)
	 */
	private MaxHeap A_maxHeapInsertC_AC() {
		MaxHeap heap = emptyList_maxHeapInsertA_A(); //starting state
		try {
			heap.maxHeapInsert(PROCESS_C);//change (produces resulting heap)
		} catch (Exception e) {
			return heap;
		} 
		return heap; //return the resulting heap
	}
	private Scenario<Process[]> A_maxHeapInsertC_AC = () -> A_maxHeapInsertC_AC();
	
	
	/** Scenario: [C] -> maxHeapInsert(D) -> [D, C] 
	 * @return [D, C] after maxHeapInsert(D)
	 */
	private MaxHeap C_maxHeapInsertD_DC() {
		MaxHeap heap = emptyList_maxHeapInsertC_C(); //starting state
		try {
			heap.maxHeapInsert(PROCESS_D);//change (produces resulting heap)
		} catch (Exception e) {
			return heap;
		} 
		return heap; //return the resulting heap
	}
	private Scenario<Process[]> C_maxHeapInsertD_DC = () -> C_maxHeapInsertD_DC();
	
	/** Scenario: [B] -> maxHeapInsert(D) -> [B, D] 
	 * @return [B, D] after maxHeapInsert(D)
	 */
	private MaxHeap B_maxHeapInsertD_BD() {
		MaxHeap heap = emptyList_maxHeapInsertB_B(); //starting state
		try {
			heap.maxHeapInsert(PROCESS_D);//change (produces resulting heap)
		} catch (Exception e) {
			return heap;
		} 
		return heap; //return the resulting heap
	}
	@SuppressWarnings("unused")
	private Scenario<Process[]> B_maxHeapInsertD_BD = () -> B_maxHeapInsertD_BD();
	
	/** Scenario: [A, B] -> heapExtractMax() -> [B] 
	 * @return [B] after heapExtractMax()
	 */
	private MaxHeap AB_heapExtractMax_B() {
		MaxHeap heap = A_maxHeapInsertB_AB(); //starting state
		try {
			heap.heapExtractMax();//change (produces resulting heap)
		} catch (Exception e) {
			return heap;
		} 
		return heap; //return the resulting heap
	}
	private Scenario<Process[]> AB_heapExtractMax_B = () -> AB_heapExtractMax_B();
	
	/** Scenario: [A, C] -> heapExtractMax() -> [C] 
	 * @return [C] after heapExtractMax()
	 */
	private MaxHeap AC_heapExtractMax_C() {
		MaxHeap heap = A_maxHeapInsertC_AC(); //starting state
		try {
			heap.heapExtractMax();//change (produces resulting heap)
		} catch (Exception e) {
			return heap;
		} 
		return heap; //return the resulting heap
	}
	private Scenario<Process[]> AC_heapExtractMax_C = () -> AC_heapExtractMax_C();
	
	/** Scenario: [D, C] -> heapExtractMax() -> [C] 
	 * @return [D] after heapExtractMax()
	 */
	private MaxHeap DC_heapExtractMax_C() {
		MaxHeap heap = C_maxHeapInsertD_DC(); //starting state
		try {
			heap.heapExtractMax();//change (produces resulting heap)
		} catch (Exception e) {
			return heap;
		} 
		return heap; //return the resulting heap
	}
	private Scenario<Process[]> DC_heapExtractMax_C = () -> DC_heapExtractMax_C();
	
	/** Scenario: [B, D] -> heapExtractMax() -> [D] 
	 * @return [D] after heapExtractMax()
	 */
	private MaxHeap BD_heapExtractMax_D() {
		MaxHeap heap = B_maxHeapInsertD_BD(); //starting state
		try {
			heap.heapExtractMax();//change (produces resulting heap)
		} catch (Exception e) {
			return heap;
		} 
		return heap; //return the resulting heap
	}
	private Scenario<Process[]> BD_heapExtractMax_D = () -> BD_heapExtractMax_D();
	
	/** Scenario: [A, B] -> maxHeapInsert(C) -> [A, B, C] 
	 * @return [A, B, C] after maxHeapInsert(C)
	 */
	private MaxHeap AB_maxHeapInsertC_ABC() {
		MaxHeap heap = A_maxHeapInsertB_AB(); //starting state
		try {
			heap.maxHeapInsert(PROCESS_C);//change (produces resulting heap)
		} catch (Exception e) {
			return heap;
		} 
		return heap; //return the resulting heap
	}
	private Scenario<Process[]> AB_maxHeapInsertC_ABC = () -> AB_maxHeapInsertC_ABC();
	
	/** Scenario: [A, B] -> maxHeapInsert(C) -> [A, B, C] 
	 * @return [A, B, C] after maxHeapInsert(C)
	 */
	private MaxHeap AB_maxHeapInsertD_ABD() {
		MaxHeap heap = A_maxHeapInsertB_AB(); //starting state
		try {
			heap.maxHeapInsert(PROCESS_D);//change (produces resulting heap)
		} catch (Exception e) {
			return heap;
		} 
		return heap; //return the resulting heap
	}
	@SuppressWarnings("unused")
	private Scenario<Process[]> AB_maxHeapInsertD_ABD = () -> AB_maxHeapInsertD_ABD();
	
	/** Scenario: [A, B, C] -> heapExtractMax() -> [B, C] 
	 * @return [B, C] after heapExtractMax()
	 */
	private MaxHeap ABC_heapExtractMax_BC() {
		MaxHeap heap = AB_maxHeapInsertC_ABC(); //starting state
		try {
			heap.heapExtractMax();//change (produces resulting heap)
		} catch (Exception e) {
			return heap;
		} 
		return heap; //return the resulting heap
	}
	private Scenario<Process[]> ABC_heapExtractMax_BC = () -> ABC_heapExtractMax_BC();
	
	/** Scenario: [D, C] -> maxHeapInsert(A) -> [A, D, C] 
	 * @return [A, D, C] after maxHeapInsert(A)
	 */
	private MaxHeap DC_maxHeapInsertA_ADC() {
		MaxHeap heap = C_maxHeapInsertD_DC(); //starting state
		try {
			heap.maxHeapInsert(PROCESS_A);//change (produces resulting heap)
		} catch (Exception e) {
			return heap;
		} 
		return heap; //return the resulting heap
	}
	private Scenario<Process[]> DC_maxHeapInsertA_ADC = () -> DC_maxHeapInsertA_ADC();
	
	/** Scenario: [A, C] -> maxHeapInsert(D) -> [A, D, C] 
	 * @return [A, D, C] after maxHeapInsert(D)
	 */
	private MaxHeap AC_maxHeapInsertD_ADC() {
		MaxHeap heap = A_maxHeapInsertC_AC(); //starting state
		try {
			heap.maxHeapInsert(PROCESS_D);//change (produces resulting heap)
		} catch (Exception e) {
			return heap;
		} 
		return heap; //return the resulting heap
	}
	private Scenario<Process[]> AC_maxHeapInsertD_ADC = () -> AC_maxHeapInsertD_ADC();
	
	/** Scenario: [D, C] -> maxHeapInsert(B) -> [B, D, C] 
	 * @return [B, D, C] after maxHeapInsert(B)
	 */
	private MaxHeap DC_maxHeapInsertB_BDC() {
		MaxHeap heap = C_maxHeapInsertD_DC(); //starting state
		try {
			heap.maxHeapInsert(PROCESS_B);//change (produces resulting heap)
		} catch (Exception e) {
			return heap;
		} 
		return heap; //return the resulting heap
	}
	private Scenario<Process[]> DC_maxHeapInsertB_BDC = () -> DC_maxHeapInsertB_BDC();
	
	/** Scenario: [A, D, C] -> heapExtractMax() -> [D, C] 
	 * @return [D, C] after heapExtractMax()
	 */
	private MaxHeap ADC_heapExtractMax_DC() {
		MaxHeap heap = DC_maxHeapInsertA_ADC(); //starting state
		try {
			heap.heapExtractMax();//change (produces resulting heap)
		} catch (Exception e) {
			return heap;
		} 
		return heap; //return the resulting heap
	}
	private Scenario<Process[]> ADC_heapExtractMax_DC = () -> ADC_heapExtractMax_DC();
	
	/** Scenario: [B, D, C] -> heapExtractMax() -> [D, C] 
	 * @return [D, C] after heapExtractMax()
	 */
	private MaxHeap BDC_heapExtractMax_DC() {
		MaxHeap heap = DC_maxHeapInsertB_BDC(); //starting state
		try {
			heap.heapExtractMax();//change (produces resulting heap)
		} catch (Exception e) {
			return heap;
		} 
		return heap; //return the resulting heap
	}
	private Scenario<Process[]> BDC_heapExtractMax_DC = () -> BDC_heapExtractMax_DC();
	
	/** Scenario: [A, B, D] -> heapExtractMax() -> [B, D] 
	 * @return [B, D] after heapExtractMax()
	 */
	private MaxHeap ABD_heapExtractMax_BD() {
		MaxHeap heap = AB_maxHeapInsertD_ABD(); //starting state
		try {
			heap.heapExtractMax();//change (produces resulting heap)
		} catch (Exception e) {
			return heap;
		} 
		return heap; //return the resulting heap
	}
	private Scenario<Process[]> ABD_heapExtractMax_BD = () -> ABD_heapExtractMax_BD();
	
	/////////////////////////////////
	//XXX Tests for 0-element heap
	/////////////////////////////////
	
	/** Run all tests on scenarios resulting in an empty heap
	 * @param scenario lambda reference to scenario builder method
	 * @param scenarioName name of the scenario being tested
	 */
	private void testEmptyHeap(Scenario<Process[]> scenario, String scenarioName) {
		System.out.printf("\nSCENARIO: %s\n\n", scenarioName);
		try {
			// Tests for scenarios resulting in an empty heap
			printTest(scenarioName + "_testExtractMax", testExtractMax(scenario.build(), null, Result.HeapUnderFlow));
			printTest(scenarioName + "_testMaxHeapInsert", testMaxHeapInsert(scenario.build(), PROCESS_A, Result.NoException));
			printTest(scenarioName + "_testMaxHeapInsert", testMaxHeapInsert(scenario.build(), PROCESS_B, Result.NoException));
			printTest(scenarioName + "_testMaxHeapInsert", testMaxHeapInsert(scenario.build(), PROCESS_C, Result.NoException));
			printTest(scenarioName + "_testMaxHeapInsert", testMaxHeapInsert(scenario.build(), PROCESS_D, Result.NoException));
			printTest(scenarioName + "_testMaxHeapify", testMaxHeapify(scenario.build(), Result.NoException));
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
	//XXX Tests for 1-element heap
	//////////////////////////////////
	
	/** Run all tests on scenarios resulting in a single element heap
	 * @param scenario lambda reference to scenario builder method
	 * @param scenarioName name of the scenario being tested
	 * @param contents elements expected in the heap after scenario has been set up
	 * @param contentsString contains character labels corresponding to values in contents
	 */
	private void testSingleElementHeap(Scenario<Process[]> scenario, String scenarioName, Process[] contents, String contentsString) {
		System.out.printf("\nSCENARIO: %s\n\n", scenarioName);
		try {
			// Tests for scenarios resulting in a 1-element heap
			printTest(scenarioName + "_testExtractMax", testExtractMax(scenario.build(), contents[0], Result.MatchingValue));
			printTest(scenarioName + "_testMaxHeapInsert", testMaxHeapInsert(scenario.build(), PROCESS_A, Result.NoException));
			printTest(scenarioName + "_testMaxHeapInsert", testMaxHeapInsert(scenario.build(), PROCESS_B, Result.NoException));
			printTest(scenarioName + "_testMaxHeapInsert", testMaxHeapInsert(scenario.build(), PROCESS_C, Result.NoException));
			printTest(scenarioName + "_testMaxHeapInsert", testMaxHeapInsert(scenario.build(), PROCESS_D, Result.NoException));
			printTest(scenarioName + "_testMaxHeapify", testMaxHeapify(scenario.build(), Result.NoException));
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
	//XXX Tests for 2-element heap
	/////////////////////////////////
	
	/** Run all tests on scenarios resulting in a two-element heap
	 * @param scenario lambda reference to scenario builder method
	 * @param scenarioName name of the scenario being tested
	 * @param contents elements expected in the heap after scenario has been set up
	 * @param contentsString contains character labels corresponding to values in contents 
	 */
	private void testTwoElementHeap(Scenario<Process[]> scenario, String scenarioName, Process[] contents, String contentsString) {
		System.out.printf("\nSCENARIO: %s\n\n", scenarioName);
		try {
			// Tests for scenarios resulting in a 2-element heap
			printTest(scenarioName + "_testExtractMax", testExtractMax(scenario.build(), contents[0], Result.MatchingValue));
			printTest(scenarioName + "_testMaxHeapInsert", testMaxHeapInsert(scenario.build(), PROCESS_A, Result.NoException));
			printTest(scenarioName + "_testMaxHeapInsert", testMaxHeapInsert(scenario.build(), PROCESS_B, Result.NoException));
			printTest(scenarioName + "_testMaxHeapInsert", testMaxHeapInsert(scenario.build(), PROCESS_C, Result.NoException));
			printTest(scenarioName + "_testMaxHeapInsert", testMaxHeapInsert(scenario.build(), PROCESS_D, Result.NoException));
			printTest(scenarioName + "_testMaxHeapify", testMaxHeapify(scenario.build(), Result.NoException));
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
	//XXX Tests for 3-element heap
	//////////////////////////////////
	
	/** Run all tests on scenarios resulting in a three-element heap
	 * @param scenario lambda reference to scenario builder method
	 * @param scenarioName name of the scenario being tested
	 * @param contents elements expected in the heap after scenario has been set up
	 * @param contentsString contains character labels corresponding to values in contents 
	 */
	private void testThreeElementHeap(Scenario<Process[]> scenario, String scenarioName, Process[] contents, String contentsString) {
		System.out.printf("\nSCENARIO: %s\n\n", scenarioName);
		try {
			// Tests for scenarios resulting in a 3-element heap
			printTest(scenarioName + "_testExtractMax", testExtractMax(scenario.build(), contents[0], Result.MatchingValue));
			printTest(scenarioName + "_testMaxHeapInsert", testMaxHeapInsert(scenario.build(), PROCESS_A, Result.NoException));
			printTest(scenarioName + "_testMaxHeapInsert", testMaxHeapInsert(scenario.build(), PROCESS_B, Result.NoException));
			printTest(scenarioName + "_testMaxHeapInsert", testMaxHeapInsert(scenario.build(), PROCESS_C, Result.NoException));
			printTest(scenarioName + "_testMaxHeapInsert", testMaxHeapInsert(scenario.build(), PROCESS_D, Result.NoException));
			printTest(scenarioName + "_testMaxHeapify", testMaxHeapify(scenario.build(), Result.NoException));
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

	/** Runs extractMax() method on given heap and checks result against expectedResult
	 * @param maxHeap a heap already prepared for a given change scenario
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
	
	/** Runs maxHeapInsert() method on given heap and checks result against expectedResult
	 * @param maxHeap a heap already prepared for a given change scenario
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
	
	/** Runs maxHeapify() method on given heap and checks result against expectedResult
	 * @param maxHeap a heap already prepared for a given change scenario
	 * @param element or null if expectedResult is an Exception
	 * @param element
	 * @return test success
	 */
	private boolean testMaxHeapify(MaxHeap maxHeap, Result expectedResult) {
		Result result;
		try {
			maxHeap.maxHeapify(0);
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