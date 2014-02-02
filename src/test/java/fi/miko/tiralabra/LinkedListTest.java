package fi.miko.tiralabra;

import static org.junit.Assert.*;

import java.util.List;
import org.junit.Before;
import org.junit.Test;

import fi.miko.tiralabra.datastructures.LinkedList;

public class LinkedListTest {
	private List<Integer> list;

	@Before
	public void setUp() {
		list = new LinkedList<>();
	}

	@Test
	public void testAddOneElement() {
		list.add(1);

		assertEquals(1, list.size());
		assertFalse(list.isEmpty());
	}

	@Test
	public void testAddTwoElements() {
		list.add(1);
		list.add(2);

		assertEquals(1, (int) list.get(0));
		assertEquals(2, (int) list.get(1));

		assertEquals(2, list.size());
	}

	@Test
	public void testClearEmptyList() {
		list.clear();

		testThatListIsEmpty();
	}

	@Test
	public void testClearWithOneElement() {
		list.add(1);
		list.clear();

		testThatListIsEmpty();
	}

	@Test
	public void testClearWithTwoElements() {
		list.add(1);
		list.add(2);
		list.clear();

		testThatListIsEmpty();
	}

	@Test
	public void testContainsEmptyList() {
		assertFalse(list.contains(1));
	}

	@Test
	public void testContainsNoSuchElement() {
		list.add(1);

		assertFalse(list.contains(2));
	}

	@Test
	public void testContainsOneElement() {
		list.add(1);

		assertTrue(list.contains(1));
	}

	@Test
	public void testContainsTwoElements() {
		list.add(1);
		list.add(2);

		assertTrue(list.contains(1));
		assertTrue(list.contains(2));
	}

	@Test
	public void testEqualsDifferent() {
		list.add(1);

		List<Integer> l2 = new LinkedList<Integer>();
		l2.add(2);

		assertFalse(list.equals(l2));
	}

	@Test
	public void testEqualsDifferentSize() {
		list.add(1);
		list.add(2);

		List<Integer> l2 = new LinkedList<Integer>();
		l2.add(1);

		assertFalse(list.equals(l2));
	}

	@Test
	public void testEqualsEmpty() {
		List<Integer> l2 = new LinkedList<Integer>();

		assertTrue(list.equals(l2));
	}

	@Test
	public void testEqualsNullElements() {
		list.add(null);

		List<Integer> l2 = new LinkedList<Integer>();
		l2.add(1);

		assertFalse(list.equals(l2));

		l2.clear();
		l2.add(null);

		assertTrue(list.equals(l2));
	}

	@Test
	public void testEqualsSame() {
		list.add(1);
		list.add(2);
		List<Integer> l2 = new LinkedList<Integer>();

		l2.add(1);
		l2.add(2);

		assertTrue(list.equals(l2));
	}

	@Test
	public void testGetAndIterator() {
		int size = 10;
		int[] elements = new int[size];

		for (int i = 0; i < size; ++i) {
			list.add(i);
			elements[i] = i;
		}

		int pos = 0;
		for (int n : list) {
			assertEquals(elements[pos], n);
			assertEquals(elements[pos], (int) list.get(pos));
			pos++;
		}
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testGetEmptyList() {
		testThatListIsEmpty();

		list.get(1);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testGetOutsideListSize() {
		list.add(1);

		list.get(2);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testGetWithNegativeIndex() {
		list.get(-1);
	}

	@Test
	public void testSizeNewList() {
		testThatListIsEmpty();
	}

	private void testThatListIsEmpty() {
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());
	}
}
