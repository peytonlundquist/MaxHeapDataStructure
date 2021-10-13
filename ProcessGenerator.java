import java.util.Random;

public class ProcessGenerator implements ProcessGeneratorInterface{
	private double probability;
	Random rand;

	public ProcessGenerator(double probability, long seed) {
		this.probability = probability;
		rand = new Random(seed);
	}

	public ProcessGenerator(double probability) {
		this.probability = probability;
		rand = new Random();
	}

	@Override
	public Process getNewProcess(int currentTime, int maxProcessTime, int maxPriority) {
		int priority = rand.nextInt(maxPriority) + 1;
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
