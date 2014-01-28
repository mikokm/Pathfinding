package fi.miko.tiralabra.algorithms;

/**
 * IllegalGraphException is thrown if a pathfinding algorithm is being run on an
 * invalid graph.
 */
public class InvalidGraphException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	/**
	 * Creates a new IllegalGraphException with the given message.
	 * 
	 * @param message
	 *            The exception description.
	 */
	public InvalidGraphException(String message) {
		super(message);
	}
}
