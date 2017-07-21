package bankersAlgorithm;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class Program {

	static Stack<Proc> history = new Stack<Proc>();
	final static int NUM_PROCS = 6; // How many concurrent processes
	final static int TOTAL_RESOURCES = 30; // Total resources in the system
	final static int MAX_PROC_RESOURCES = 13; // Highest amount of resources any
												// process could need
	final static int ITERATIONS = 30; // How long to run the program
	static Random rand = new Random();

	public static void main(String[] args) {
		Integer totalHeldResources = 0;

		// The list of processes:
		ArrayList<Proc> processes = new ArrayList<Proc>();
		boolean state = true;

		for (int i = 0; i < NUM_PROCS; i++)
			// Initialize to a new Proc, with some small range for its max
			processes.add(new Proc(MAX_PROC_RESOURCES - rand.nextInt(3)));

		// Run the simulation:
		System.out.println("\n***** STATUS *****");
		System.out.println("Total Available: " + (TOTAL_RESOURCES - totalHeldResources));
		for (int k = 0; k < processes.size(); k++) {
			System.out.println("Process " + k + " holds: " + processes.get(k).getHeldResources() + ", max: "
					+ processes.get(k).getMaxResources() + ", claim: "
					+ (processes.get(k).getMaxResources() - processes.get(k).getHeldResources()));
			System.out.println("***** STATUS *****\n");
		}
		for (int i = 0; i < ITERATIONS; i++) {
			// loop through the processes and for each one get its request
			for (int j = 0; j < processes.size(); j++) {
				for (int k = 0; k < processes.size(); k++) {
					processes.get(k).addResources(-processes.get(k).getHeldResources());
				}
				// Get the request
				int currRequest = processes.get(j).resourceRequest(TOTAL_RESOURCES - totalHeldResources);
				// just ignore processes that don't ask for resources
				if (currRequest == 0)
					continue;
				state = determineState(currRequest, processes, processes.get(j), totalHeldResources, history, j);
				if (state) {
					System.out.println("Safe State");
					return;
				}
			}
		}
		if (state) {
			System.out.println("Safe State");
		}
		if (!state) {
			System.out.println("Unsafe state");
		}
	}

	public static boolean determineState(int currRequest, ArrayList<Proc> procList, Proc process,
			Integer totalHeldResources, Stack<Proc> history, int counter) {
		procList.add(procList.remove(0));
		// code to determine whether or not the request
		// can be granted,
		// and then grant the request if possible.
		while (!procList.isEmpty() && totalHeldResources != TOTAL_RESOURCES) {
			for (int i = 0; i < procList.size(); i++) {
				// At the end of each iteration, give a summary of the current
				// status:
				System.out.println("\n***** STATUS *****");
				System.out.println("Total Available: " + (TOTAL_RESOURCES - totalHeldResources));
				for (int k = 0; k < procList.size(); k++)
					System.out.println("Process " + k + " holds: " + procList.get(k).getHeldResources() + ", max: "
							+ procList.get(k).getMaxResources() + ", claim: "
							+ (procList.get(k).getMaxResources() - procList.get(k).getHeldResources()));
				System.out.println("***** STATUS *****\n");

				currRequest = procList.get(i).resourceRequest(TOTAL_RESOURCES - totalHeldResources);
				if (currRequest <= procList.get(i).getMaxResources()) {
					if (!procList.isEmpty()) {
						totalHeldResources += currRequest;
						if (currRequest > 0) {
							procList.get(i).addResources(currRequest);
						} else if (currRequest < 0) {
							history.push(procList.get(i));
							procList.remove(procList.get(i));
						}
					} else {
						continue;
					}
				}
			}
		}
		if (procList.isEmpty()) {
			return true;
		}
		return false;
	}
}
