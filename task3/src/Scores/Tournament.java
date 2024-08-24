package Scores;

import animals.Animal;

import java.util.ArrayList;

/**
 * Represents an abstract base class for a tournament involving animals.
 * Defines the common functionality and properties for different types of tournaments.
 */
public abstract class Tournament {

	protected TournamentThread tournThread = null; // Thread responsible for managing the tournament
	private long dis; // Distance required for the tournament

	/**
	 * Constructs a Tournament with the specified distance.
	 *
	 * @param neededDistance The distance that animals need to cover in the tournament.
	 */
	public Tournament(long neededDistance) {
		this.dis = neededDistance;
	}

	/**
	 * Sets up the tournament with the provided list of animals.
	 * This method must be implemented by subclasses to configure the tournament.
	 *
	 * @param animals An ArrayList of arrays, where each array contains the animals competing in a group.
	 */
	public abstract void setup(ArrayList<Animal[]> animals);

	/**
	 * Gets the distance of the tournament.
	 *
	 * @return The distance required for the tournament.
	 */
	public double getDis() {
		return dis;
	}
}
