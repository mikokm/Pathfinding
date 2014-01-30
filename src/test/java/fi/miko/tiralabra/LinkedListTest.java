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
	public void testAddTwoDifferentElements() {
		list.add(1);
		list.add(2);

		assertTrue(1 == list.get(0));
		assertTrue(2 == list.get(1));

		assertEquals(2, list.size());
	}

	@Test
	public void testAddTwoSameElements() {
		list.add(1);
		list.add(1);

		assertTrue(1 == list.get(0));
		assertTrue(1 == list.get(1));

		assertEquals(2, list.size());
	}

	@Test
	public void testClearEmptyList() {
		list.clear();

		testThatListIsEmpty();
	}

	@Test
	public void testClearListWithOneElement() {
		list.add(1);
		list.clear();

		testThatListIsEmpty();
	}

	@Test
	public void testClearListWithTwoElements() {
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
	public void testGetAndIterator() {
		int size = 10;
		int[] elements = new int[size];

		for (int i = 0; i < size; ++i) {
			list.add(i);
			elements[i] = i;
		}

		int pos = 0;
		for (int n : list) {
			assertTrue(elements[pos] == n);
			assertTrue(elements[pos] == list.get(pos));
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
