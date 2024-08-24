package mobility;

/**
 * ILocatable -Interface describing location functionality
 */

public interface ILocatable {
	/**
	 * The function getLocation
	 * @return the location of the object
	 */
	Point getLocation();
	/**
	 * The function setLocation
	 * @param point
	 */
	void setLocation(Point point);

}
