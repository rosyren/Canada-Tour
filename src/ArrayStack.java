/**
 * This class creates a stack with an array implementation which gives part of the structure
 * @author Rosy Ren (251080052) CS 1027
 * @param <T> generic type
 */
		
public class ArrayStack<T> implements StackADT<T>{
	/**
	 * declaring the variables within the ArrayStack
	 * top keeps track of the the highest indexed element within the stack 
	 * declare a stack
	 * set a default capacity if not given an initial capacity
	 */
	private int top;
	private T[] stack;
	private final int DEFAULT_CAPACITY = 20;
	
	/**
	 * constructor that initializes top to -1 
	 * sets the stackarray to default capacity 
	 */
	
	public ArrayStack(){
		
		top = -1;
		stack = (T[])(new Object[DEFAULT_CAPACITY]);
		
		
	}
	
	/**
	 * constructor overloading with an initial capacity input 
	 * top is still set to -1
	 * @param initialCapacity		used to set stackarray's size 
	 */
	
	public ArrayStack(int initialCapacity) {
		
		top = -1;
		stack = (T[])(new Object[initialCapacity]);
		
	}
	
	/**
	 * take an element of generic type and push/add it to the top of the stack 
	 * if the stack is full, we call expand capacity 
	 * @param element of generic type
	 */
	
	public void push (T element) {
		if (top+1 == stack.length) {
			expandCapacity();
		}
		
		// because our indexing is at -1, we have to add 1 to ensure all of our indexing is positive 
		stack[top+1] = element;
		top ++;
		
	}
	
	/**
	 * remove and return the top element of the stack
	 * throw an exception if the stack is empty 
	 * @return element of generic type
	 */
	public T pop() throws EmptyCollectionException {
		if (top == -1) {
			throw new EmptyCollectionException("array stack");
		}
		
		top--;
		// returns the top of the stack
		T result = stack[top+1];
		// sets the top of the stack to null
		stack[top+1] = null;
		return result;
		
		
	}
	
	/**
	 * returns the top of the stack without any altercations 
	 * throws new exception if the stack is empty 
	 * @return element of the generic type
	 */
	
	public T peek() throws EmptyCollectionException {
		
		if (top == -1) {
			throw new EmptyCollectionException("array stack");
		}
		
		return stack[top];
		
	}
	
	/**
	 * returns true if stack if empty 
	 * @return boolean : true if stack is empty, false otherwise
	 */
	
	public boolean isEmpty() {
		// if top equals to -1, it will be empty 
		return (top == -1);
		
	}
	
	/**
	 * returns the size of the stack – the number of elements within the stack
	 * @return int of the number of elements within the stack
	 */
	
	public int size() {
		
		return (top+1);
	}
	
	
	/**
	 * returns the top index value of the stack, will return -1 if the stack is empty 
	 * @return int top index value
	 */
	public int getTop() {
		return top;
	}
	
	
	/**
	 * this will return the capacity of the stack, regardless of how many items are within the stack 
	 * @return int capacity of the stack
	 */
	public int getLength() {
		return stack.length;
	}
	
	
	/**
	 * This will return a string representation of the stack, if stack is empty, will also print out a message
	 * @return String representation of the stack
	 */
	public String toString() {
		
		// if stack is empty
		if (top == -1) {
			return "The stack is empty";
		}
		
		// for every item besides the last one
		String str = "STACK: ";
		for (int i = top; i > 0; i--) {
			str += stack[i] + ", ";
		}
		// ends the string with a period 
		str += stack[0] + ".";
		
		return str;
		
	}
	
	/**
	 * calls expandCapacity when the array is full creating 20 more slots than the current array
	 * and transferring the contents over to the new array and then pointing it back
	 */
	private void expandCapacity() {
		T[] newStack = (T[])(new Object[getLength() + 20]);
		
		for (int i = -1; i < top; i++) {
			newStack[i+1] = stack[i+1];
		}
		
		stack = newStack;
		
		
		
	}
	
	
	
	
}	
