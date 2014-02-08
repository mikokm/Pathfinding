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

		if (i == -1) {
			return;
		}

		while (i > 0 && get(parent(i)).getKey() > e.getKey()) {
			swap(i, parent(i));
			i = parent(i);
		}
	}

	public void clear() {
		for (int i = 0; i < heapSize; ++i) {
			elements[i] = null;
		}

		heapSize = 0;
	}

	@SuppressWarnings("unchecked")
	private E get(int i) {
		return (E) elements[i];
	}

	public E getMin() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}

		E element = get(0);
		element.setIndex(-1);

		heapSize--;

		if (isEmpty()) {
			elements[0] = null;
		} else {
			assign(0, heapSize);
			heapify(0);
		}

		return element;
	}

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

	public void insert(E e) {
		heapSize++;

		if (heapSize > elements.length) {
			elements = Arrays.copyOf(elements, elements.length * 2);
		}

		int i = heapSize - 1;
		while (i > 0 && get(parent(i)).getKey() > e.getKey()) {
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

	private void updateIndex(int i) {
		get(i).setIndex(i);
	}
}
