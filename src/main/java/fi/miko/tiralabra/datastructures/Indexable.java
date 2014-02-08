package fi.miko.tiralabra.datastructures;

/**
 * Indexable is an interface implemented by all the classes that can be added to the minimum heap.
 */
public interface Indexable {
	/**
	 * Returns the index of the item in the heap array, or -1 if the item is not in the heap.
	 * 
	 * @return The index of the item in the heap array, or -1 if the item is not in the heap.
	 */
	public int getIndex();

	/**
	 * Returns the key used to position this item in the heap.
	 * 
	 * @return The key used to position this item in the heap.
	 */
	public double getKey();

	/**
	 * Sets the heap array index.
	 * 
	 * @param index
	 *            The index of this item in the heap array.
	 */
	public void setIndex(int index);
}
