
public class TestStack {

	public static void main (String[] args) {

		// --------------- Test 1 --------------- [isEmpty and toString]
		
		ArrayStack<Double> S = new ArrayStack<Double>();

		boolean test1Success = false;

		if (S.isEmpty() && S.toString().equals("The stack is empty")) {
			test1Success = true;
		}

		if (test1Success) {
			System.out.println("Test 1 passed");
		} else {
			System.out.println("Test 1 failed");
		}


		// --------------- Test 2 --------------- [push and size]

		boolean test2Success = false;
		boolean t0 = false, t1 = false, t2 = false, t3 = false;
		
		if (S.getTop() == -1 && S.getLength() == 20) {
			t0 = true;
		}
		
		S.push(31.5);
		S.push(12.8);
		S.push(67.3);
		
		
		if (S.size() == 3) {
			t1 = true;
		}
		

		
		S.push(98.6);
		S.push(72.5);
		
		
		if (S.getTop() == 4) {
			t2 = true;

		}
		
		if (S.toString().equals("STACK: 72.5, 98.6, 67.3, 12.8, 31.5.")) {
			t3 = true;

		}
			
		if (t0 && t1 && t2 && t3) {
			test2Success = true;
		}


		
		if (test2Success) {
			System.out.println("Test 2 passed");
		} else {
			System.out.println("Test 2 failed");
		}

		// --------------- Test 3 --------------- [expandCapacity and getTop]

		boolean test3Success = false;

		for (double i = 15.0; i < 20.0; i+=0.1) {
			S.push(i);
		}
		if (S.getLength() == 60 && S.getTop() == 54) {
			test3Success = true;
		}

		if (test3Success) {
			System.out.println("Test 3 passed");
		} else {
			System.out.println("Test 3 failed");
		}


		// --------------- Test 4 --------------- [pop]

		boolean test4Success = false;
		t0 = t1 = t2 = false;

		Double dbl = null;
		
		for (int i = 1; i <= 25; i++) {
			dbl = S.pop();
		}
		if (Math.abs(dbl - 17.5) < 0.1) {
			t0 = true;
		}
		for (int i = 1; i <= 30; i++) {
			dbl = S.pop();
		}
		if (Math.abs(dbl - 31.5) < 0.1) {
			t1 = true;
		}
		try {
			S.pop();
		} catch (EmptyCollectionException e) {
			t2 = true;
		}
		
		if (t0 && t1 && t2) {
			test4Success = true;
		}

		if (test4Success) {
			System.out.println("Test 4 passed");
		} else {
			System.out.println("Test 4 failed");
		}

		// --------------- Test 5 --------------- [peek]

		boolean test5Success = false;
		t0 = t1 = t2 = false;

		try {
			S.peek();
		} catch (EmptyCollectionException e) {
			t0 = true;
		}
		
		for (double i = 5.0; i < 12.0; i+= 1.5) {
			S.push(i);
		}

		if (S.peek() == 11.0) {
			t1 = true;
		}
				
		S.pop();
		S.pop();
		S.pop();
		if (S.peek() == 6.5) {
			t2 = true;
		}
		

		if (t0 && t1 && t2) {
			test5Success = true;
		}
		
		if (test5Success) {
			System.out.println("Test 5 passed");
		} else {
			System.out.println("Test 5 failed");
		}

	}

}
