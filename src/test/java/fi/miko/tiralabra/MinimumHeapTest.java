package fi.miko.tiralabra;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import fi.miko.tiralabra.datastructures.Indexable;
import fi.miko.tiralabra.datastructures.MinimumHeap;

public class MinimumHeapTest {
	private class DummyObject extends Object implements Indexable {
		private int index;
		private double key;

		public DummyObject(double key) {
			this.key = key;
		}

		@Override
		public int getIndex() {
			return index;
		}

		@Override
		public double getKey() {
			return key;
		}

		@Override
		public void setIndex(int index) {
			this.index = index;
		}

		public void setKey(double key) {
			this.key = key;
		}
	}

	MinimumHeap<DummyObject> heap;

	private boolean containsKey(Object[] arr, double key) {
		for (Object o : arr) {
			DummyObject d = (DummyObject) o;
			if (d.getKey() == key) {
				return true;
			}
		}

		return false;
	}

	private double[] generateData(int size) {
		Random rand = new Random();
		double[] data = new double[size];

		for (int i = 0; i < size; ++i) {
			data[i] = rand.nextInt(100);
		}

		return data;
	}

	private void insertAndGetHelper(double[] data) {
		insertData(data);
		Arrays.sort(data);

		for (double element : data) {
			assertEquals(element, heap.getMin().getKey(), 0);
		}
	}

	private void insertData(double[] data) {
		for (int i = 0; i < data.length; ++i) {
			DummyObject o = new DummyObject(data[i]);
			heap.insert(o);

			assertEquals(i + 1, heap.size());
		}
	}

	@Before
	public void setUp() {
		heap = new MinimumHeap<>();
	}

	@Test
	public void testClearEmptyHeap() {
		assertEquals(0, heap.size());

		heap.clear();
		assertEquals(0, heap.size());
	}

	@Test
	public void testClearHeapWithData() {
		heap.insert(new DummyObject(1));
		assertEquals(1, heap.size());

		heap.clear();
		assertEquals(0, heap.size());
	}

	@Test
	public void testDecreaseKey() {
		int len = 20;

		double[] data = new double[len];
		for (int i = 0; i < len; ++i) {
			data[i] = i;
		}

		insertData(data);

		Object[] arr = heap.toArray();
		System.out.println(heap);

		DummyObject fake = new DummyObject(0);
		fake.setIndex(-1);
		heap.decreaseKey(fake);

		for (Object element : arr) {
			DummyObject o = (DummyObject) element;

			if (o.getKey() == 10 || o.getKey() == 11) {
				o.setKey(1);
			}

			heap.decreaseKey(o);
		}

		data[11] = 1;
		data[12] = 1;

		Arrays.sort(data);

		for (int i = 0; i < 10; ++i) {
			assertEquals(data[i], heap.getMin().getKey(), 0);
		}
	}

	@Test(expected = NoSuchElementException.class)
	public void testGetFromEmpty() {
		heap.getMin();
	}

	@Test
	public void testInsertAndGetMin() {
		for (int i = 0; i < 100; ++i) {
			insertAndGetHelper(generateData(100));
		}
	}

	@Test
	public void testToStringAndToArray() {
		double[] data = generateData(100);
		insertData(data);

		String s = heap.toString();
		Object[] arr = heap.toArray();

		assertEquals(data.length, arr.length);

		for (double d : data) {
			assertTrue(s.contains(String.valueOf(d)));
			assertTrue(containsKey(arr, d));
		}
	}
}
