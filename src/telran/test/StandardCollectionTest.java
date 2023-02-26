package telran.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import telran.structure.MultiCounters;
import telran.structure.MultiCountersImpl;
import telran.util.StackInt;

import java.util.*;
import java.util.Map.Entry;
import java.util.function.Supplier;
import java.util.random.RandomGenerator;
import java.util.stream.Collectors;
import java.util.stream.Stream;
class StandardCollectionTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	@Disabled
	void SubListtest() {
		List<Integer> list = new ArrayList<>(Arrays.asList(1,2,3,4,5,6,70,-20));
		list.add(5);
		List<Integer> listSub = list.subList(6, 9);

		System.out.println(listSub);
		listSub.add(1, -2);
		listSub.sort(Integer::compare);
		listSub.clear();
		System.out.println(list);

	}
	@Test
	@Disabled
	void displayOccurrencesCount() {
		String [] strings = {"lmn", "abc", "abc", "lmn", "a", "lmn"};
		Arrays.stream(strings)
				.collect(Collectors.groupingBy(s -> s,Collectors.counting()))
				.entrySet().stream().sorted((e1, e2) -> Long.compare(e2.getValue(), e1.getValue()))
				.forEach(e -> System.out.printf("%s: %d\n", e.getKey(), e.getValue()));


	}
	@Test
	@Disabled
	void displayDigitStatistics() {
		//Generate 1000000 random numbers [1-Integer.MAX_VALUE)
		//Display digits and counts of their occurrences in descending order of the counts
		//consider using flatMap for getting many from one
		((new Random()).ints(1, 10000).limit(1000000))
		.flatMap(num -> Integer.toString(num).chars().map(c -> c-'0'))
		.boxed()
		.collect(Collectors.groupingBy(num -> num, Collectors.counting()))
		.entrySet().stream().sorted((e1, e2) -> Long.compare(e2.getValue(), e1.getValue()))
		.forEach(num -> System.out.println(num))
		;
	}
	
	@Test
	void testStackInt() {
		StackInt obj = new StackInt();
		assertTrue(obj.isEmpty());
		assertThrowsExactly(NoSuchElementException.class, () -> obj.getMax());
		
		obj.push(5);
		assertFalse(obj.isEmpty());
		
		obj.push(10);
		assertFalse(obj.isEmpty());
		
		assertEquals(10, obj.pop());
		assertFalse(obj.isEmpty());
		
		assertEquals(5, obj.pop());
		assertTrue(obj.isEmpty());
		
		assertThrowsExactly(NoSuchElementException.class, () -> obj.pop());
		
		//---
		assertThrowsExactly(NoSuchElementException.class, () -> obj.getMax());
		obj.push(5);
		assertEquals(5, obj.getMax());
		
		obj.push(10);
		assertEquals(10, obj.getMax());
		
		obj.push(7);
		assertEquals(10, obj.getMax());
		
		assertEquals(7, obj.pop());
		assertEquals(10, obj.getMax());
		
		assertEquals(10, obj.pop());
		assertEquals(5, obj.getMax());
	}
	
	@Test
	void maxNumberWithNegativeImageTest() {
		int ar[] = {10000000, 3, -2, -200, 200, -3, 2};
		int ar1[] = {1000000, -1000000000, 3, -4};
		assertEquals(200, maxNumberWithNegativeImage(ar));
		assertEquals(-1, maxNumberWithNegativeImage(ar1));
	}

	int maxNumberWithNegativeImage(int array[]) {
		//return maximal positive number having it negative image or -1 if none such numbers
		int[] max = {-1};
		HashSet<Integer> set = new HashSet<>();
		Arrays.stream(array).filter(n -> n < 0).forEach(set::add);
		Arrays.stream(array).filter(n -> n > 0).forEach(n -> {
			if (n > max[0] && set.contains(-n)) {
				max[0] = n;
			}
		});
		return max[0];
	}
	
	@Test
	void treeIteratingTest() {
		int array[] = {1, 11, 111, 32, 9, 1234, 99, 992};
		createAndIterateTreeInOrder(array);
	}

	private void createAndIterateTreeInOrder(int[] array) {
		//create tree, add in tree numbers from a given array
		//and iterate in the order of array defined in 69
		Comparator<Integer> comp = (a, b) -> {return Integer.compare(sumDigits(a), sumDigits(b));};
		TreeSet<Integer> tree = new TreeSet<>(comp);
		Arrays.stream(array).forEach(tree::add);
		tree.forEach(n -> System.out.printf("%d ", n));
		
	}
	
	private int sumDigits(Integer number) {
		return number.toString().chars().map(c -> c-'0').boxed().collect(Collectors.summingInt(Integer::intValue));
	}
	
	@Test
	void MultiCountersTest() {
		MultiCounters counter = new MultiCountersImpl();
		Integer item = 1;
		Integer otherItem = 10;
		assertEquals(null, counter.getValue(item));
		assertEquals(1, counter.addItem(item));
		assertEquals(2, counter.addItem(item));
		assertEquals(2, counter.getValue(item));
		
		assertEquals(3, counter.addItem(item));
		
		counter.addItem(otherItem);
		counter.addItem(otherItem);
		assertNotNull(counter.getMaxItems());
		assertEquals(1, counter.getMaxItems().size());
		assertEquals(item, counter.getMaxItems().toArray()[0]);
		
		assertEquals(true, counter.remove(item));
		assertEquals(false, counter.remove(item));
		
		assertNotNull(counter.getMaxItems());
		assertEquals(1, counter.getMaxItems().size());
		assertEquals(otherItem, counter.getMaxItems().toArray()[0]);
		
		assertEquals(true, counter.remove(otherItem));
		assertNotNull(counter.getMaxItems());
		assertEquals(0, counter.getMaxItems().size());
	}
}
