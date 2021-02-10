package elevator;

public class Passenger{
	
	public static final int UNDEFINED_FLOOR = -1;
	
	private int id;
	private int currentFloor;
	private int destinationFloor;
	
	/**
	 * Constructor
	 * @param id identifier of the passenger
	 */
	public Passenger(int id){
		
		this.id = id;
		this.currentFloor = 1;
		this.destinationFloor = UNDEFINED_FLOOR;
	}
	
	/**
	 * Setter for passenger's destination floor.
	 * @param newDestinationFloor destination floor number
	 */
	public void waitForElevator(int destinationFloor){
		
		this.destinationFloor = destinationFloor;
	}
	
	/**
	 * Sets passenger's current floor to be undefined.
	 * Called when a passenger boards the elevator from waiting on a floor.
	 */
	public void boardElevator(){
		
		this.currentFloor = UNDEFINED_FLOOR;
	}
	
	/**
	 * Method called when a passenger arrives at the destination floor and they get off.
	 * The current floor becomes the destination floor value, and the destination floor 
	 * is set to be undefined.
	 */
	public void arrive(){
		
		this.currentFloor = destinationFloor;
		this.destinationFloor = UNDEFINED_FLOOR;
	}
	
	/**
	 * Getter for passenger's current floor
	 * @return int current floor number
	 */
	public int currentFloor(){
		
		return currentFloor;
	}
	
	/**
	 * Getter for passenger's destination floor
	 * @return int destination floor number
	 */
	public int destinationFloor(){
		
		return destinationFloor;
	}
	
	@Override
	public String toString(){
		
		return "\nPassenger: " + id + "\nCurrent Floor: " + currentFloor + "\nDestination Floor: " + destinationFloor;
	}

}