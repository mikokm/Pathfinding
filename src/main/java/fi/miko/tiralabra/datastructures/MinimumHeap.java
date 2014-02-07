package fi.miko.tiralabra.datastructures;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class MinimumHeap<E extends Indexable> {
	private Object[] elements = null;
	private int heapSize = 0;

	public MinimumHeap() {
		this(1);
	}

	public MinimumHeap(int initialSize) {
		elements = new Object[initialSize];
	}

	private void assign(int i, int j) {
		elements[i] = elements[j];
		updateIndex(i);
	}

	public void decreaseKey(E e) {
		int i = e.getIndex();

		while (i > 0 && get(parent(i)).getKey() > e.getKey()) {
			swap(i, parent(i));
			i = parent(i);
		}
	}

	@SuppressWarnings("unchecked")
	private E get(int i) {
		return (E) elements[i];
	}

	private void heapify(int i) {
		int left = left(i);
		int right = right(i);

		// Node i has two child nodes.
		if (right < heapSize) {
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

			// Node i has one child node.
		} else if (left == (heapSize - 1) && get(i).getKey() > get(left).getKey()) {
			swap(i, left);
		}
	}

	public void insert(E e, double key) {
		heapSize++;

		if (heapSize > elements.length) {
			elements = Arrays.copyOf(elements, elements.length * 2);
		}

		int i = heapSize - 1;
		while (i > 0 && get(parent(i)).getKey() > key) {
			assign(i, parent(i));
			i = parent(i);
		}

		elements[i] = e;
		updateIndex(i);
	}

	public boolean isEmpty() {
		return heapSize == 0;
	}

	private int left(int i) {
		return 2 * i;
	}

	private int parent(int i) {
		return i / 2;
	}

	public E poll() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}

		E element = get(0);
		heapSize--;

		if (isEmpty()) {
			elements[0] = null;
		} else {
			assign(0, heapSize);
			heapify(0);
		}

		return element;
	}

	public void remove(E e) {
		int index = e.getIndex();

		if (index == -1) {
			return;
		}

		assign(index, heapSize - 1);
		heapSize--;
		heapify(index);
	}

	private int right(int i) {
		return 2 * i + 1;
	}

	public int size() {
		return heapSize;
	}

	private void swap(int i, int j) {
		Object element = elements[i];
		elements[i] = elements[j];
		elements[j] = element;

		updateIndex(i);
		updateIndex(j);
	}

	public Object[] toArray() {
		return Arrays.copyOf(elements, heapSize);
	}

	private void updateIndex(int i) {
		get(i).setIndex(i);
	}
}
