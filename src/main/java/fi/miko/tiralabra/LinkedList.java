package fi.miko.tiralabra;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * LinkedList implements a simple generic type linked list.
 */
public class LinkedList<E> implements List<E> {
	private class LinkedListIterator<V> implements Iterator<V> {
		private ListNode<V> node;

		public LinkedListIterator(ListNode<V> head) {
			this.node = head;
		}

		@Override
		public boolean hasNext() {
			return node.next != null;
		}

		@Override
		public V next() {
			node = node.next;
			return node.data;
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException("Not implemented");
		}
	}

	private class ListNode<V> {
		V data = null;
		ListNode<V> next = null;

		public ListNode() {
		}

		public ListNode(V data) {
			this.data = data;
		}
	}

	private int size;
	private ListNode<E> head, tail;

	public LinkedList() {
		head = new ListNode<E>();
		tail = head;
		size = 0;
	}

	@Override
	public boolean add(E e) {
		ListNode<E> n = new ListNode<>(e);
		tail.next = n;
		tail = n;
		size++;

		return true;
	}

	@Override
	public void clear() {
		head.next = null;
		tail = head;
		size = 0;
	}

	@Override
	public boolean contains(Object o) {
		ListNode<E> n = head.next;

		while (n != null) {
			if (n.data == o) {
				return true;
			}

			n = n.next;
		}

		return false;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public Iterator<E> iterator() {
		return new LinkedListIterator<E>(head);
	}

	@Override
	public int size() {
		return size;
	}

	/*
	 * Unimplemented stuff below this.
	 */

	@Override
	public void add(int index, E element) {
		throw new UnsupportedOperationException("Not implemented");
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		throw new UnsupportedOperationException("Not implemented");
	}

	@Override
	public boolean addAll(int index, Collection<? extends E> c) {
		throw new UnsupportedOperationException("Not implemented");
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		throw new UnsupportedOperationException("Not implemented");
	}

	@Override
	public E get(int index) {
		throw new UnsupportedOperationException("Not implemented");
	}

	@Override
	public int indexOf(Object o) {
		throw new UnsupportedOperationException("Not implemented");
	}

	@Override
	public int lastIndexOf(Object o) {
		throw new UnsupportedOperationException("Not implemented");
	}

	@Override
	public ListIterator<E> listIterator() {
		throw new UnsupportedOperationException("Not implemented");
	}

	@Override
	public ListIterator<E> listIterator(int index) {
		throw new UnsupportedOperationException("Not implemented");
	}

	@Override
	public E remove(int index) {
		throw new UnsupportedOperationException("Not implemented");
	}

	@Override
	public boolean remove(Object o) {
		throw new UnsupportedOperationException("Not implemented");
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		throw new UnsupportedOperationException("Not implemented");
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		throw new UnsupportedOperationException("Not implemented");
	}

	@Override
	public E set(int index, E element) {
		throw new UnsupportedOperationException("Not implemented");
	}

	@Override
	public List<E> subList(int fromIndex, int toIndex) {
		throw new UnsupportedOperationException("Not implemented");
	}

	@Override
	public Object[] toArray() {
		throw new UnsupportedOperationException("Not implemented");
	}

	@Override
	public <T> T[] toArray(T[] a) {
		throw new UnsupportedOperationException("Not implemented");
	}

}
