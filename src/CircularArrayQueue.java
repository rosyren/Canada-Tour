/**
 * 
 * This class creates a circular queue with an array implementation which gives part of the structure
 * @author Rosy Ren (251080052) CS 1027
 * @param <T> generic type 
 */
public class CircularArrayQueue<T> implements QueueADT<T> {

	/**
	 * declaring the variables within the circular array queue
	 * front keeps track of the beginning of the queue
	 * rear keeps track of the back of the queue
	 * count keeps track of how many elements there are in the queue
	 * set a default capacity if not given an initial capacity
	 */
	private int front;
	private int rear;
	private int count;
	private T[] queue; 
	private final int DEFAULT_CAPACITY = 20;

	/**
	 * constructor intializes the front to 1 
	 * initializes rear to 0 
	 * count to 0
	 * create a new array with default capacity
	 */
	
	public CircularArrayQueue() {
		front = 1;
		rear = 0;
		count = 0;
		queue = (T[])(new Object[DEFAULT_CAPACITY]);
	}
	
	/**
	 * constructor intializes the front to 1 
	 * initializes rear to 0 
	 * count to 0
	 * @param initialCapacity		input initial queue capacity 
	 */
	
	public CircularArrayQueue(int initialCapacity) {
		front = 1;
		rear = 0;
		count = 0;
		queue = (T[])(new Object[initialCapacity]);

	}
	
	/**
	 * takes in an element and adds the element to the rear of the queue
	 * if queue is full call expand capacity  
	 * @param T element 		element of generic type
	 */
	public void enqueue (T element) {
		
		/*
		 * expand capacity is queue is full
		 */
		if (count == queue.length) {
			expandCapacity();
		}
		
		// increment queue rear count
		rear = (rear+1) % queue.length;
		// set rear to new element
		queue[rear] = element;
		// increment count
		count++;
	
		
		
	}
	
	/**
	 * rremoves and returns the first element fo the queue 
	 * throws an empty collection if the queue is empty and has nothing to return 
	 * @return T element of the generic type 		front element of the queue 
	 * 
	 */
	
	public T dequeue() throws EmptyCollectionException{
		
		if (isEmpty()) {
			throw new EmptyCollectionException("queue");
		}
		
		// gets the front of the queue
		
		T result = queue[front];
		
		// sets the front to null
		queue[front] = null;
		
		// increment front 
		front = (front+1)% queue.length;
		
		// decrease counter
		count --;
		return result;
	}
	
	/**
	 * return the first item of the queue
	 * throws an empty collection if the queue is empty and has nothing to return 
	 * @return T element 	first element of the queue
	 */
	
	public T first() throws EmptyCollectionException{
		
		if (isEmpty()) {
			throw new EmptyCollectionException("Queue");
		}
		
		return queue[front];
	}
	/**
	 * boolean to determine if the queue is empty 
	 * @return true if queue is empty
	 */
	public boolean isEmpty() {
		return (count == 0);
	}
	
	/**
	 * returns the number of items in the queue
	 * @return int		size of the queue
	 */
	
	public int size() {
		return count;
	}
	
	/**
	 * return the front index value
	 * @return int 		index of the front element
	 */
	
	public int getFront() {
		return front;
	}
	
	/**
	 * return the rear index value
	 * @return int 		index of the rear element 
	 */
	
	public int getRear() {
		return rear;
	}
	
	/**
	 * 
	 * returns the current capacity of the queue
	 * @return int 		returns the current capacity of the queue
	 */
	
	public int getLength() {
		return queue.length;
	}
	
	/**
	 * returns a string representation of the queue
	 * @return String representation 
	 */
	
	public String toString() {
		
		if (count == 0) return "The queue is empty";
		
		String str = "QUEUE: ";
		
		for (int i = front; i < count; i ++) {
			str += queue[i] + ", ";
			
		}
		
		// prints the last one if it wraps around
		if (count == queue.length) {
			str+= queue[0] + ".";
			return str;
		}

		str += queue[count] + ".";

		return str;
		
	}
	
	/**
	 * 
	 * create a new array that has 20 more slots than the current array 
	 * transfer the contents to new array 
	 * point new array back to original array
	 */
	
	private void expandCapacity() {
		T[] newQueue = (T[])(new Object[getLength() + 20]);
		
		for (int i = 1; i < count; i ++) {
			newQueue[i] = queue[i];
			
		}
		
		// this accounts for when it wraps around the circular array
		
		if (count == queue.length) {
			newQueue[count] = queue[0];
		}
		
		front = 1;
		rear = count;
		queue = newQueue;
		
	}
	
	
	
	
	
	
}
