package Scores;

import animals.Animal;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Thread class that represents an animal participating in a race.
 * It handles the movement of the animal and updates the scores when the race finishes.
 */
public class AnimalThread implements Runnable {

	private Animal participant;
	private double needDistance;
	private AtomicBoolean startFlag, finishFlag;
	private Scores scores = new Scores();

	/**
	 * Constructs an AnimalThread with the specified animal, distance, start flag, and finish flag.
	 *
	 * @param a The animal participating in the race.
	 * @param n_d The distance the animal needs to travel.
	 * @param _startFlag The flag indicating whether the race has started.
	 * @param _finishFlag The flag indicating whether the race has finished.
	 */
	public AnimalThread(Animal a, double n_d, AtomicBoolean _startFlag, AtomicBoolean _finishFlag) {
		participant = a;
		needDistance = n_d;
		this.startFlag = _startFlag;
		this.finishFlag = _finishFlag;
	}

	/**
	 * Runs the thread which simulates the animal's movement until the required distance is covered.
	 * Updates the scores and animal details once the animal finishes the race.
	 */
	@Override
	public void run() {
		// Wait until the start flag is set
		while (!startFlag.get()) {
			synchronized (startFlag) {
				try {
					startFlag.wait();
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt(); // Restore interrupted status
				}
			}
		}

		synchronized (participant) {
			// Simulate the movement of the animal
			while (participant.getTotalDistance() < needDistance) {
				Calendar calendar = Calendar.getInstance();
				calendar.add(Calendar.HOUR, 1);

				participant.MoveAnimal();

				participant.setEnergylevel(participant.getEnergyPerMeter() * participant.getSpeed() / participant.speedFac + participant.getEnergylevel());
				try {
					Thread.sleep(1000 / participant.speedFac);
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt(); // Restore interrupted status
				}

				participant.getPan().repaint();
			}

			// Notify that the race is finished and update scores
			synchronized (finishFlag) {
				finishFlag.set(true);
				finishFlag.notifyAll();
				scores.add(participant.getName());
				Date currentDate = new Date();
				SimpleDateFormat dateFormat = new SimpleDateFormat("d\\M\\yy  HH:mm:ss");
				participant.setDate(dateFormat.format(currentDate));
				scores.add(participant.getDate());
			}
		}
	}

	/**
	 * Gets the participant animal.
	 *
	 * @return The animal participating in the race.
	 */
	public synchronized Animal getParticipant() {
		return participant;
	}

	/**
	 * Sets the participant animal.
	 *
	 * @param participant The animal participating in the race.
	 */
	public synchronized void setParticipant(Animal participant) {
		this.participant = participant;
	}
}
