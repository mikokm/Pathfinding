package fi.miko.tiralabra.datastructures;

import java.util.Iterator;

/**
 * LinkedList implements a simple generic type linked list.
 */
public class LinkedList<E> implements Iterable<E> {
	public class LinkedListIterator implements Iterator<E> {
		private Node node;

		public LinkedListIterator(Node head) {
			this.node = head;
		}

		@Override
		public boolean hasNext() {
			return node.next != null;
		}

		@Override
		public E next() {
			node = node.next;
			return node.data;
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}

	/**
	 * Node represents a single node in the linked list.
	 */
	private class Node {
		E data = null;
		Node next = null;

		public Node() {
		}

		public Node(E data) {
			this.data = data;
		}
	}

	private final Node head;
	private int size;
	private Node tail;

	/**
	 * Creates a new linked list.
	 */
	public LinkedList() {
		head = new Node();
		tail = head;
		size = 0;
	}

	/**
	 * Adds the element to the end of the list.
	 * 
	 * @param e
	 *            The element to be added.
	 * @return True if the item was added; otherwise false.
	 */
	public boolean add(E e) {
		Node n = new Node(e);
		tail.next = n;
		tail = n;
		size++;

		return true;
	}

	/**
	 * Clears the list.
	 */
	public void clear() {
		head.next = null;
		tail = head;
		size = 0;
	}

	/**
	 * Returns true if the list contains the given object.
	 * 
	 * @param o
	 *            The object to look for.
	 * @return True if the list contains the given object; otherwise false.
	 */
	public boolean contains(Object o) {
		Node n = head.next;

		while (n != null) {
			if (n.data.equals(o)) {
				return true;
			}

			n = n.next;
		}

		return false;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (obj == null) {
			return false;
		}

		if (getClass() != obj.getClass()) {
			return false;
		}

		@SuppressWarnings("unchecked")
		LinkedList<E> other = (LinkedList<E>) obj;

		if (size != other.size) {
			return false;
		}

		Node n = head.next;
		Node m = other.head.next;

		while (n != null) {
			E e1 = n.data, e2 = m.data;

			// Check if one element is null and the another one isn't.
			if (e1 == null && e2 != null) {
				return false;
			}

			// Check if the both elements are null, or if the elements aren't equal.
			if (e1 != null && e1.equals(e2) == false) {
				return false;
			}

			n = n.next;
			m = m.next;
		}

		return true;
	}

	/**
	 * Returns the item item at the given index.
	 * 
	 * @param index
	 *            The index of the item.
	 * @return The item item at the given index.
	 */
	public E get(int index) {
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException();
		}

		Node node = head.next;
		for (int i = 0; i < index; ++i) {
			node = node.next;
		}

		return node.data;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;

		for (E e : this) {
			result = prime * result + e.hashCode();
		}

		return result;
	}

	/**
	 * Returns true if the list is empty.
	 * 
	 * @return True if the list is empty.
	 */
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public Iterator<E> iterator() {
		return new LinkedListIterator(head);
	}

	/**
	 * Returns the size of the list.
	 * 
	 * @return The size of the list.
	 */
	public int size() {
		return size;
	}

	/**
	 * Returns an array representation of the list.
	 * 
	 * @return An array representation of the list.
	 */
	public Object[] toArray() {
		Object[] array = new Object[size];
		int index = 0;

		for (E e : this) {
			array[index] = e;
			index++;
		}

		return array;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("[");

		Node n = head.next;

		while (n != null) {
			sb.append(n.data.toString());

			if (n.next == null) {
				break;
			}

			sb.append(", ");
			n = n.next;
		}

		sb.append("]");

		return sb.toString();
	}
}
