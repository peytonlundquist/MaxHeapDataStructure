import java.util.Random;

/**
 * A Process generator which implements the ProcessGenerator Interface
 * @author Peyton Lundquist
 * @date 10/13/2021
 */
public class ProcessGenerator implements ProcessGeneratorInterface{
	private double probability;
	Random rand;

	/**
	 * Constuctor for seeded ProcessGenerator
	 * @param probability
	 * @param seed
	 */
	public ProcessGenerator(double probability, long seed) {
		this.probability = probability;
		rand = new Random(seed);
	}

	/**
	 * Constructor for seedless ProcessGenerator
	 * @param probability
	 */
	public ProcessGenerator(double probability) {
		this.probability = probability;
		rand = new Random();
	}

	@Override
	public Process getNewProcess(int currentTime, int maxProcessTime, int maxPriority) {
		int priority = rand.nextInt(maxPriority) + 1; // From 1 to Max
		int processTime = rand.nextInt(maxProcessTime) + 1; // From 1 to Max
		Process newProcess = new Process(currentTime, processTime, priority);
		return newProcess;
	}

	@Override
	public boolean query() {
		if(rand.nextDouble() < probability) {
			return true;
		}
		return false;
	}

}
