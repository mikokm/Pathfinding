package fi.miko.tiralabra.datastructures;

import java.util.NoSuchElementException;

/**
 * MinimumHeap implements a generic minimum heap structure.
 */
public class MinimumHeap<E extends Indexable> {
	private Object[] elements = null;
	private int heapSize = 0;

	/**
	 * Constructs a new MinimumHeap.
	 */
	public MinimumHeap() {
		this(1);
	}

	/**
	 * Constructs a new MinimumHeap with a given initial size.
	 */
	public MinimumHeap(int initialSize) {
		elements = new Object[initialSize];
	}

	/**
	 * Assigns the element in index j to index i.
	 * 
	 * @param i
	 *            The index to assign to.
	 * @param j
	 *            The index to assign from.
	 */
	private void assign(int i, int j) {
		elements[i] = elements[j];
		updateIndex(i);
	}

	/**
	 * Clears the heap.
	 */
	public void clear() {
		for (int i = 0; i < heapSize; ++i) {
			elements[i] = null;
		}

		heapSize = 0;
	}

	/**
	 * Returns a copy of the given source array.
	 * 
	 * @param src
	 *            The source array.
	 * @param length
	 *            The length of the new array.
	 * @return A copy of the give source array.
	 */
	private Object[] copyArray(Object[] src, int length) {
		Object[] dst = new Object[length];

		int len = Math.min(src.length, length);
		for (int i = 0; i < len; ++i) {
			dst[i] = src[i];
		}

		return dst;
	}

	/**
	 * Changes the position of the given element in the heap if needed.
	 * 
	 * @param e
	 *            The element to reposition.
	 */
	public void decreaseKey(E e) {
		int i = e.getIndex();

		// The item is not in the heap.
		if (i == -1) {
			return;
		}

		while (i > 0 && get(parent(i)).getKey() > e.getKey()) {
			swap(i, parent(i));
			i = parent(i);
		}
	}

	/**
	 * Returns and typecasts the element at the given position.
	 * 
	 * @param i
	 *            The index of the element.
	 * @return The element at the given index, typecasted to E.
	 */
	@SuppressWarnings("unchecked")
	private E get(int i) {
		return (E) elements[i];
	}

	/**
	 * Returns the element at the top of the heap (the one with the smallest key).
	 * 
	 * @return The element at the top of the heap
	 */
	public E getMin() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}

		// Get the element from the heap and mark it as removed.
		E element = get(0);
		element.setIndex(-1);

		heapSize--;

		if (isEmpty()) {
			// If the element is the last one in the heap, replace it with null.
			elements[0] = null;
		} else {
			// Move the last element in the heap to the top and heapify.
			assign(0, heapSize);
			heapify(0);
		}

		return element;
	}

	/**
	 * Fixes the heap property starting from the given index.
	 * 
	 * @param i
	 *            The index to fix.
	 */
	private void heapify(int i) {
		int left = left(i);
		int right = right(i);

		if (right < heapSize) {
			// Node i has two child nodes.
			int smallest;

			// Get the smallest element of the two
			if (get(left).getKey() < get(right).getKey()) {
				smallest = left;
			} else {
				smallest = right;
			}

			if (get(i).getKey() > get(smallest).getKey()) {
				swap(i, smallest);
				heapify(smallest);
			}

		} else if (left == (heapSize - 1) && get(i).getKey() > get(left).getKey()) {
			// Node i has one child node and it's the smaller than that parent node.
			swap(i, left);
		}
	}

	/**
	 * Inserts the given element to the heap.
	 * 
	 * @param e
	 *            The element to insert.
	 */
	public void insert(E e) {
		heapSize++;

		// Resize the array if needed.
		if (heapSize > elements.length) {
			elements = copyArray(elements, elements.length * 2);
		}

		int i = heapSize - 1;
		while (i > 0 && get(parent(i)).getKey() > e.getKey()) {
			assign(i, parent(i));
			i = parent(i);
		}

		elements[i] = e;
		updateIndex(i);
	}

	/**
	 * Returns true if the heap is empty; otherwise false.
	 * 
	 * @return True if the heap is empty; otherwise false.
	 */
	public boolean isEmpty() {
		return heapSize == 0;
	}

	/**
	 * Returns the index of the left child for the given index.
	 * 
	 * @param i
	 *            The index of the parent.
	 * @return The index of the left child for the given index.
	 */
	private int left(int i) {
		return 2 * i;
	}

	/**
	 * Returns the parent of the given index.
	 * 
	 * @param i
	 *            The index of the child.
	 * @return The parent of the given index.
	 */
	private int parent(int i) {
		return i / 2;
	}

	/**
	 * Returns the index of the right child for the given index.
	 * 
	 * @param i
	 *            The index of the parent.
	 * @return The index of the right child for the given index.
	 */
	private int right(int i) {
		return 2 * i + 1;
	}

	/**
	 * Returns the size of the heap.
	 * 
	 * @return The size of the heap.
	 */
	public int size() {
		return heapSize;
	}

	/**
	 * Swaps the given indexes in the heap array.
	 * 
	 * @param i
	 *            The index to swap.
	 * @param j
	 *            The index to swap.
	 */
	private void swap(int i, int j) {
		Object element = elements[i];
		elements[i] = elements[j];
		elements[j] = element;

		updateIndex(i);
		updateIndex(j);
	}

	/**
	 * Returns the elements contained in the heap as an array.
	 * 
	 * @return The elements contained in the heap as an array.
	 */
	public Object[] toArray() {
		return copyArray(elements, heapSize);
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();

		sb.append("[");

		for (int i = 0; i < heapSize; ++i) {
			sb.append(get(i).getKey());

			if (i < heapSize - 1) {
				sb.append(", ");
			}
		}

		sb.append("]");

		return sb.toString();
	}

	/**
	 * Updates the heap array index of the item at the given index.
	 * 
	 * @param i
	 *            The index of the item to update.
	 */
	private void updateIndex(int i) {
		get(i).setIndex(i);
	}
}
