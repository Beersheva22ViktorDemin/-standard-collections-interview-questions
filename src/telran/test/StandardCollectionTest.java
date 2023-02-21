package telran.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import telran.util.StackInt;

import java.util.*;
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
	void displayDigitStatistics() {
		//Generate 1000000 random numbers [1-Integer.MAX_VALUE)
		//Display digits and counts of their occurrences in descending order of the counts
		//consider using flatMap for getting many from one
		((new Random()).ints(1, Integer.MAX_VALUE).limit(1000000))
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


}
