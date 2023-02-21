package telran.util;

import java.util.LinkedList;

public class StackInt {
	private LinkedList<Integer> list = new LinkedList<>();
	private LinkedList<Integer> maxStack = new LinkedList<>();
	
	//Write the following methods
	//All methods should have complexity O[1]
	public void push(int num) {
		//adds num into top of stack
		list.add(num);
		if (maxStack.isEmpty() || num > maxStack.getLast()) {
			maxStack.add(num);
		}
	}
	public int pop() {
		//returns a number from top of stack or throws NoSuchElementException
		//if the stack is empty		
		Integer result = list.removeLast();
		if (result.equals(maxStack.getLast())) {
			maxStack.removeLast();
		}
		return result;
	}
	public boolean isEmpty () {
		//returns true if the stack is empty, otherwise false
		return list.isEmpty();
	}
	public int getMax() {
		//returns maximal value of the stack or throws NoSuchElementException
		//if the stack is empty
		return maxStack.getLast();
	}
}