package fi.miko.tiralabra.datastructures;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class MinimumHeap<E extends Indexable> {
	public class Element {
		E data = null;
		double key;

		public Element(E data, double key) {
			this.data = data;
			this.key = key;
		}

		@Override
		public String toString() {
			return "[" + key + "]";
		}
	}

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

	public void decreaseKey(E e, double key) {
		int i = e.getIndex();
		get(i).key = key;

		while (i > 0 && get(parent(i)).key > key) {
			swap(i, parent(i));
			i = parent(i);
		}
	}

	@SuppressWarnings("unchecked")
	private Element get(int i) {
		return (Element) elements[i];
	}

	private void heapify(int i) {
		// print();

		int left = left(i);
		int right = right(i);

		// Node i has two child nodes.
		if (right < heapSize) {
			int smallest;

			// Get the smallest element of the two
			if (get(left).key < get(right).key) {
				smallest = left;
			} else {
				smallest = right;
			}

			if (get(i).key > get(smallest).key) {
				swap(i, smallest);
				heapify(smallest);
			}

			// Node i has one child node.
		} else if (left == (heapSize - 1) && get(i).key > get(left).key) {
			swap(i, left);
		}
	}

	public void insert(E e, double key) {
		heapSize++;

		if (heapSize > elements.length) {
			elements = Arrays.copyOf(elements, elements.length * 2);
		}

		// print();

		int i = heapSize - 1;
		while (i > 0 && get(parent(i)).key > key) {
			assign(i, parent(i));
			i = parent(i);
		}

		elements[i] = new Element(e, key);
		updateIndex(i);

		// print();
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

	public E peek() {
		return get(0).data;
	}

	public E poll() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}

		E data = get(0).data;
		heapSize--;

		if (isEmpty()) {
			elements[0] = null;
		} else {
			assign(0, heapSize);
			heapify(0);
		}

		return data;
	}

	private void print() {
		for (int i = 0; i < heapSize; ++i) {
			System.out.print(get(i));
		}
		System.out.println();
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
		get(i).data.setIndex(i);
	}
}
