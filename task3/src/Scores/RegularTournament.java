package Scores;

import animals.Animal;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Represents a regular tournament where animals compete individually.
 * Extends the Tournament class and sets up the race with threads for each animal and referee.
 */
public class RegularTournament extends Tournament {

	/**
	 * Constructs a RegularTournament with the specified distance.
	 *
	 * @param neededDistance The distance each animal needs to travel in the tournament.
	 */
	public RegularTournament(long neededDistance) {
		super(neededDistance);
	}

	/**
	 * Sets up the tournament by initializing threads for each animal and referee.
	 *
	 * @param animals An ArrayList of arrays, where each array contains the animals competing in a group.
	 */
	@Override
	public void setup(ArrayList<Animal[]> animals) {
		AtomicBoolean startFlag = new AtomicBoolean(true);
		Scores scores = new Scores();

		Thread[] animalThreads = new Thread[animals.size()];
		Thread[] refereeThreads = new Thread[animals.size()];

		for (int i = 0; i < animals.size(); ++i) {
			AtomicBoolean finishFlag = new AtomicBoolean(false);

			// Create a thread for each animal
			animalThreads[i] = new Thread(new AnimalThread(animals.get(i)[0], this.getDis(), startFlag, finishFlag));

			// Create a thread for the referee for each group
			refereeThreads[i] = new Thread(new Referee("Group " + (i + 1), scores, finishFlag));
		}

		// Start all animal and referee threads
		for (int i = 0; i < animals.size(); ++i) {
			animalThreads[i].start();
			refereeThreads[i].start();
		}

		// Initialize the tournament thread
		tournThread = new TournamentThread(startFlag, scores, animals.size());

	}
}
