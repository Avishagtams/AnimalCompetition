package graphics;

import java.awt.*;

/**
 * The  interface IDrawable
 *
 */

public interface IDrawable {
	public final static String PICTURE_PATH = "task3/src/Images/";
	/**
	 * The function loadImages
	*/
	public void loadImages(String nm);
	/**
	 * The function drawObject
	 * @param g
	 */
	public void drawObject (Graphics g);
}
