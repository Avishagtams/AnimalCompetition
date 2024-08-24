package mobility;

/**
 * Mobile - An abstract class that defines space movement and implements the ILocatable interface.
 */
public abstract class Mobile implements ILocatable {

	private Point location;  // Current location
	private double totalDistance;  // Distance the object traveled, [>0]

	/**
	 * Constructor to initialize location and total distance.
	 *
	 * @param location initial location of the mobile object
	 */
	public Mobile(Point location) {
		this.location = location;
		this.totalDistance = 0;
	}

	/**
	 * Sets the location of the mobile object.
	 *
	 * @param location the new location to set
	 */
	public void setLocation(Point location) {
		this.location = location;
	}

	/**
	 * Adds distance to the total distance traveled, only if the distance is non-negative.
	 *
	 * @param distance the distance to add
	 */
	private void addTotalDistance(double distance) {
		if (distance >= 0) {
			totalDistance += distance;
		}
	}

	/**
	 * Calculates the distance between the current location and a specified point.
	 *
	 * @param point the point to calculate the distance to
	 * @return the distance between the current location and the point
	 */
	public double calcDistance(Point point) {
		return Math.sqrt(Math.pow(this.location.getX() - point.getX(), 2) +
				Math.pow(this.location.getY() - point.getY(), 2));
	}

	/**
	 * Moves the object to a new location and updates the total distance traveled.
	 *
	 * @param point the new location to move to
	 * @return the distance traveled, or 0 if the object did not move
	 */
	public double move(Point point) {
		if (point.equals(location)) {
			return 0;
		}

		double distance = calcDistance(point);
		setLocation(point);
		addTotalDistance(distance);

		return distance;
	}

	/**
	 * Returns the current location of the mobile object.
	 *
	 * @return the current location
	 */
	public Point getLocation() {
		return location;
	}

	/**
	 * Returns the total distance traveled by the mobile object.
	 *
	 * @return the total distance traveled
	 */
	public double getTotalDistance() {
		return totalDistance;
	}

	/**
	 * Provides a string representation of the mobile object, including its location and total distance traveled.
	 *
	 * @return a string representation of the mobile object
	 */
	@Override
	public String toString() {
		return "Location: " + location + " Total Distance: " + totalDistance + "\n";
	}
}
