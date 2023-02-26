package telran.util;

import java.util.HashMap;

//all methods of the class MyArrayInt should have complexity O[1]
public class MyArrayInt {
int value;
int size;
HashMap<Integer, Integer> array;
	public MyArrayInt(int size, int value) {
		this.size = size;
		this.value = value;
	}
	public void set(int index, int value) {
		//sets a given value at a given index
		//throws exception IndexOutOfBoundsException
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		if (array == null) {
			array = new HashMap<>();
		}
		array.put(index, value);
	}
	public int get(int index) {
		//returns a value at a given index
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		int res = value;
		if (array != null) {
			res = array.getOrDefault(index, value);
		} 
		return res;
	}
	public void setAll(int value) {
		this.value = value;
		array = null;
	}
}