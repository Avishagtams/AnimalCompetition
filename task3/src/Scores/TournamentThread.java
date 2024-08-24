package Scores;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Manages the tournament execution by coordinating the start signal and handling the overall flow.
 * Implements Runnable to be executed in a separate thread.
 */
public class TournamentThread implements Runnable {

	private Scores scores;
	private AtomicBoolean startSignal;
	private int groups;

	/**
	 * Constructs a TournamentThread with the specified start signal, scores, and number of groups.
	 *
	 * @param flag The flag used to signal the start of the tournament.
	 * @param scores The Scores object used to track the results.
	 * @param numGroup The number of groups participating in the tournament.
	 */
	public TournamentThread(AtomicBoolean flag, Scores scores, int numGroup) {
		this.groups = numGroup;
		this.startSignal = flag;
		this.scores = scores;
	}

	/**
	 * Runs the thread, initializes the start signal, and notifies all waiting threads.
	 */
	@Override
	public void run() {
		synchronized (startSignal) {
			startSignal.set(true); // Set the start signal to true
			startSignal.notifyAll(); // Notify all waiting threads
		}
	}
}
