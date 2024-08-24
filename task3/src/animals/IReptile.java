package animals;
/**
 * The interface IReptile-Interface that describes reptile functionality
 */
public interface IReptile {
	static final int  MAX_SPEED= 5 ;
	void speedUp(int f); // make the reptile crawls faster , however, only it doesn't make it crawls faster than the MAX_SPEED value
}
