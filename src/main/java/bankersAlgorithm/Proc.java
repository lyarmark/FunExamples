package bankersAlgorithm;
import java.util.Random;

public class Proc {
	private int maxResources; // Most resources this process can need
	private int heldResources; // Currently held resources
	private boolean running; // Is the process running
	private Random rand = new Random();

	// Constructor, accepts the maxResources for this process
	public Proc(int maxResources) {
		this.maxResources = maxResources;
		this.heldResources = 0;
		this.running = true;
	}

	// The OS can grant a request for resources through this method
	public void addResources(int r) {
		heldResources += r;
	}

	// Getter
	public int getMaxResources() {
		return maxResources;
	}

	// Getter
	public int getHeldResources() {
		return heldResources;
	}

	// OS polls the process to know how many resources it wants to request
	// Will only request up to the currently available amount, will not bother
	// with requests it knows it can't be granted.
	public int resourceRequest(int available) {
		if (running) {
			// For simplicity we assume a process will only complete when it has
			// acquired
			// the maximum resources it can need even though in reality a
			// process can complete
			// without the full amount. If all resources are held, complete with
			// some probability.
			if (heldResources == maxResources && rand.nextDouble() < 0.65) {
				running = false;
				int currHeld = heldResources;
				heldResources = 0; // Relinquish all resources, reset
									// heldResources
				return -currHeld; // Let OS know it can have back the resources
			} else {
				// request a random number of resources up to the max, or the
				// available number
				int min = Math.min(rand.nextInt(maxResources - heldResources + 1), available);
				return min;
			}
		} else // not running, restart the process with some probability
		{
			if (rand.nextDouble() < .2)
				running = true;
			return 0;
		}
	}
}
