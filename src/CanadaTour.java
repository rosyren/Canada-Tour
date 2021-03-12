/**
 * this is the entry point to the program 
 * @author Rosy Ren (251080052) CS 1027
 *
 */

public class CanadaTour {
	
	/**
	 * initialize cityQueue containing all the cities within the file
	 * initializes a map
	 * initializes the first city of the cityQueue
	 */
	CircularArrayQueue<City> cityQueue;
	Map map;
	City startCity;
	
	/**
	 * constructor that initializes the map object 
	 * calls loadData to read the file
	 * @param fileName 		input for which tour to run
	 */

	public CanadaTour(String fileName) {
		
		// initializes the cityQueue
		cityQueue = new CircularArrayQueue<City>();
		// initializes the map
		map = new Map();
		// calls loadData on the inputed file
		loadData(fileName);
		
		
	}
	
	/**
	 * Use myFileReader to read through the file
	 * creating city objects with the info read from the file 
	 * set first city in the cityQueue to startCity 
	 * add city to map
	 * @param fileName		inputed tour file with cities
	 */
	private void loadData(String fileName) {
		
		MyFileReader reader = new MyFileReader(fileName);
		
		// skips the header first line
		reader.readString();
		
		int index = 0;
		
		while (!reader.endOfFile()) {
					
			// this reads each line into a variable in the order of ID, name, population, x, y, and earnings
			int cityID = index;
			String name = reader.readString();
			int x = reader.readInt();
			int y = reader.readInt();
			double earnings = reader.readDouble();
			
			// inputs read variables into the city object
			City newCity = new City(cityID, name, x, y, earnings);
			// enqueue the new city object into the cityQueue
			cityQueue.enqueue(newCity);
			// add the new city into the map
			map.addCity(newCity);
			
			// sets the first city to StartCity
			if (index == 0) {
				startCity = newCity;
			}
			
			// increment index which is cityID
			index++;
				
			}

	}
	
	/**
	 * 
	 * Go through each element in the cityQueue to find the closest city to the current city
	 * if it is afforadable, return the city 
	 * if not, return null
	 * if all cities have been marked, return null
	 * 
	 * @param currentCity		the city we want to find the closest to 
	 * @param money			the budget of the band
	 * @return City		 returns the city that's the closest to the inputted city
	 */
	
	public City findNextCity(City currentCity, double money) {
		
		// set return result to null;
		City result = null;
		
		double distance = 100000000000000000000000.0;
		
		// keeps track of where the front is
		int front = cityQueue.getFront();
		// i keeps track of the increment counter
		int i = cityQueue.getFront();
		// increment i to traverse through cityQueue
		i = (i+1) % cityQueue.getLength();

		// while the cityQueue is not entirely traversed 
		while (i != front) {
			
			// if it is the current city, and the city is marked in or marked out, do nothing
			if (cityQueue.first() == currentCity || cityQueue.first().isMarkedInStack() || cityQueue.first().isMarkedOutOfStack()) {
			} 
			
			
			else {
				
				// finds the distance between the current city and the next one in the queue
				double newDistance = distBetweenCities(currentCity, cityQueue.first());
				
				// find the cost of traveling to that city
				double cost = calcFlightCost(newDistance);

				// if the new distance is shorter than the original distance and the cost is within the budget
				if (newDistance < distance && cost <= money) {
					
					// we set original distance to equal to new distance
					distance = newDistance;
					// return result equals to the city 
					result = cityQueue.first();
					
		
				}

				
			}
			// increment counter to keep track of the front pointer
			
			i = (i+1) % cityQueue.getLength();

			// move the first element to the back so the first pointer will point to the next one in line
			cityQueue.enqueue(cityQueue.dequeue());

			
		}
		
		// return the city or null
		return result;
	
	
	}
	
	/**
	 * calculate the Euclidean distance between the two inputted cities
	 * @param cityA
	 * @param cityB
	 * @return double distance		 returns the distance between the cities
	 */
	public double distBetweenCities(City cityA, City cityB) {
		
		double vertDist = Math.abs(cityA.getY() - cityB.getY());
		double horzDist = Math.abs(cityA.getX() - cityB.getX());
		double dist = Math.sqrt((Math.pow(vertDist, 2)) + (Math.pow(horzDist, 2)));
		
		return dist;
		
	}
	
	/**
	 * 
	 * calculates the cost of flying from one city to another 
	 * @param distance
	 * @return double cost of flying to that city
	 */

	public double calcFlightCost(double distance) {
		
		double cost = 0.0;
		
		// if under 100, it's a fixed rate 
		if (distance < 100.0) {
			cost = 127.00;
		}
		
		else {
			cost = (1.25* distance) + 32.0;
			
		}
	
		return cost;
		
	}
	
	/**
	 * 
	 * main algorithm for traversing the map 
	 * creats two stack objects, one containing cities, one containing money 
	 * @return int 		maximum cities visited on the map
	 */
	
	public int travel() {
		
		// initialize the cityStack and moneyStack
		ArrayStack<City> cityStack = new ArrayStack<City>();
		ArrayStack<Double> moneyStack = new ArrayStack<Double>();
		
		// keeps track of how many cities we mark in and out
		int count = 1;
		// int of the max amount of cities visited 
		int max = count; 
		
		
		// push the first city of the queue onto the stack
		cityStack.push(startCity);
		// mark in stack the startcity
		startCity.markInStack();

		// keeps track of total money
		double money = startCity.getEarnings();
		// push the amount onto the money stack
		moneyStack.push(startCity.getEarnings());
	
		
		
		// while there are still elements within the cityStack
		while (!cityStack.isEmpty()) {
			
			// update max if count is increasing past max
			if(count >max) {
				max = count; 
			}
				
			// if all of the cities are read, it's a success
			if (cityStack.size() == cityQueue.size()) {
				System.out.println("Success");
				return cityStack.size();
			}
			
			// find the top of the city and the city after that one
			City top = cityStack.peek();
			
			City nextCity = findNextCity(top, money);

			// if it returns null, we pop it off the city stack and money stack and mark out of stack 
			if (nextCity == null) {
				
				cityStack.pop().markOutOfStack();
				moneyStack.pop();
				count--; 

				
			}
			
			// if it doesn't return null, we push it to the city stack and money stack and mark in stack
			
			else {

				cityStack.push(nextCity);
				moneyStack.push(nextCity.getEarnings());
				
				nextCity.markInStack();
					
				// increment our total budget accordingly 
				money = money + nextCity.getEarnings() - calcFlightCost(distBetweenCities(top, nextCity));
				count ++;
			
			}
					
			
		}
		// print out fail if it does not hit all the cities on the map
		System.out.println("fail");
		// print out max number of cities visited 
		return max;	
		
	}
	
	/**
	 * takes in an argument and runs the file
	 * @param args
	 */
	public static void main(String[] args) {
		
		// ensure there is only one txt file read at one time
		if (args.length != 1) {
			
			System.out.println("Please input the right number of arguments");
			
		}
		// initialize and run travel
		CanadaTour newTour = new CanadaTour(args[0]);
		System.out.println("Max: " + newTour.travel() + " cities");
		
		
		
	}
	
}


