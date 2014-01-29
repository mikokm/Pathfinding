package fi.miko.tiralabra.datastructures;

import java.util.Arrays;

public class BinaryHeap<E> {
	private class Element {
		E data = null;
		int key;

		public Element(E data, int key) {
			this.data = data;
			this.key = key;
		}

		@Override
		public String toString() {
			return "[" + key + "]";
		}
	}

	private Object[] elements = null;
	private int size = 0, heapSize = 0;

	public BinaryHeap(int initialSize) {
		size = initialSize;
		elements = new Object[size];
	}

	private void print() {
		for (int i = 0; i < heapSize; ++i) {
			System.out.print(get(i));
		}
		System.out.println();
	}

	private void swap(int i, int j) {
		assert (i <= heapSize && j <= heapSize);

		Object element = elements[i];
		elements[i] = elements[j];
		elements[j] = element;
	}

	@SuppressWarnings("unchecked")
	private Element get(int i) {
		return (Element) elements[i];
	}

	private void heapify(int i) {
		print();

		int left = left(i);
		int right = right(i);

		if (right <= heapSize) {
			int largest;

			if (get(left).key > get(right).key) {
				largest = left;
			} else {
				largest = right;
			}

			if (get(i).key < get(largest).key) {
				swap(i, largest);
				heapify(largest);
			}

		} else if (left == heapSize && get(i).key < get(left).key) {
			swap(i, left);
		}
	}

	private int parent(int i) {
		return i / 2;
	}

	private int left(int i) {
		return 2 * i;
	}

	private int right(int i) {
		return 2 * i + 1;
	}

	public void insert(E e, int key) {
		heapSize++;

		if (heapSize > size) {
			size = size * 2;
			elements = Arrays.copyOf(elements, size);
		}

		print();

		int i = heapSize - 1;
		while (i > 0 && get(parent(i)).key < key) {
			elements[i] = elements[parent(i)];
			i = parent(i);
		}

		elements[i] = new Element(e, key);

		print();
	}

	public E peek() {
		return get(0).data;
	}

	public E poll() {
		E data = get(0).data;

		elements[0] = elements[heapSize - 1];
		heapSize--;
		heapify(0);

		return data;
	}

	public void modifyKey(E e, int key) {

	}
}
