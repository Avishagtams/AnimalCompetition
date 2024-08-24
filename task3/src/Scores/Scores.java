package Scores;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Manages scores and the times at which names are added.
 * Stores name-time pairs in a thread-safe map.
 */
public class Scores {

	private Map<String, Date> scores; // A thread-safe map to store names and their corresponding times

	/**
	 * Constructs a Scores object with an empty, thread-safe map.
	 */
	public Scores() {
		scores = Collections.synchronizedMap(new HashMap<String, Date>());
	}

	/**
	 * Adds a name with the current date and time to the scores map.
	 * Prints a message indicating the addition of the name and the current time.
	 *
	 * @param name The name to be added to the scores map.
	 */
	public void add(String name) {
		synchronized (scores) {
			Date date = new Date(); // Current date and time
			scores.put(name, date);
			System.out.println(name + " successfully added time: " + date);
		}
	}

	/**
	 * Retrieves the map of all names and their corresponding times.
	 *
	 * @return A thread-safe map containing names and their associated times.
	 */
	public Map<String, Date> getAll() {
		return scores;
	}
}
