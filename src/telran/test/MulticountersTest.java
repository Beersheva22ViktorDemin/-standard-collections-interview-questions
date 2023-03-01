package telran.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.util.*;

class MulticountersTest {
   MultiCounters multiCounters;
	@BeforeEach
	void setUp() throws Exception {
		multiCounters = new MultiCountersImpl();
		multiCounters.addItem(10);
		multiCounters.addItem(10);
		multiCounters.addItem(10);
		multiCounters.addItem("abc");
		multiCounters.addItem("abc");
		multiCounters.addItem("lmn");
		multiCounters.addItem("lmn");
	}

	@Test
	void getMaxItemsTest() {
		runTest(Arrays.asList(10));
	}

	private void runTest(List<Object> list) {
		var set = multiCounters.getMaxItems();
		list.forEach((item)-> assertTrue(set.contains(item)));

	}
	@Test
	void getValueTest() {
		assertEquals(3, multiCounters.getValue(10));
		assertNull(multiCounters.getValue("kuku"));
	}
	@Test
	void addItemTest() {
		Object[] items = {10, "abc"};
		assertEquals(3,multiCounters.addItem("abc"));
		runTest(Arrays.asList(items));

	}
	@Test
	void removeItemTest() {
		Object[] items = {"abc", "lmn"};
		assertTrue(multiCounters.remove(10));
		runTest(Arrays.asList(items));
		assertFalse(multiCounters.remove(10));
	}


}