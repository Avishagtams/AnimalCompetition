package Scores;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Represents a referee that monitors the finish status of the animals in a race.
 * Once the finish flag is set, the referee updates the scores.
 */
public class Referee implements Runnable {

	private Scores score;
	private String name;
	private AtomicBoolean FlagF;

	/**
	 * Constructs a Referee with the specified name, scores, and finish flag.
	 *
	 * @param name The name of the referee.
	 * @param score The Scores object to update when the race finishes.
	 * @param finishFlag The flag indicating whether the race has finished.
	 */
	public Referee(String name, Scores score, AtomicBoolean finishFlag) {
		this.score = score;
		this.name = name;
		this.FlagF = finishFlag;
	}

	/**
	 * Waits for the finish flag to be set and then updates the scores.
	 */
	@Override
	public void run() {
		synchronized (this) {
			while (!FlagF.get()) {
				try {
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

		synchronized (score) {
			score.add(name);
		}
	}
}
