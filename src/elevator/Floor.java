package elevator;

import java.util.ArrayList;
import java.util.List;

/**
 * Floor Class
 * 
 * Defines an individual floor of a building. Each floor in a building has a floor number, and 
 * keeps track of the number of people on that floor who are residents, or who are waiting for
 * an elevator to take them up or down the building.
 * 
 * Passengers on a floor should be able to call the elevator to that floor, where they will wait
 * to board the elevator to go to a destination floor if there is space.
 * 
 * @author Philip Lin
 */
public class Floor{
		
	private int floorNumber;
	
	public List<Passenger> residents = new ArrayList<Passenger>();
	public List<Passenger> waitingUp = new ArrayList<Passenger>();
	public List<Passenger> waitingDown = new ArrayList<Passenger>();
	
	/**
	 * Constructor
	 * @param floorNumber specified floor number of constructed Floor object
	 */
	public Floor(int floorNumber){
		
		this.floorNumber = floorNumber;
	}
	
	/**
	 * Called when a passenger on the floor wants to wait for the elevator. 
	 * Calling this adds the passenger to a list of passengers waiting to go up or down 
	 * depending on the destination floor of the passenger relative to the floor number.
	 * When a passenger is waiting for the elevator on a floor, they are no longer 
	 * considered a resident of the floor.
	 * @param passenger that is waiting to travel in the elevator
	 * @param destinationFloor floor number the waiting passenger wishes to go to
	 */
	public void waitForElevator(Passenger passenger, int destinationFloor){
			
		passenger.waitForElevator(destinationFloor);
		residents.remove(passenger);
		
		if( destinationFloor > floorNumber )
			waitingUp.add(passenger);
		
		if( destinationFloor < floorNumber )
			waitingDown.add(passenger);
	}
	
	/**
	 * Checks if a passenger is a resident of a floor, meaning that they are currently on the floor,
	 * and are not waiting to travel in the elevator (they have no destination floor).
	 * @param passenger to check
	 * @return boolean result of check
	 */
	public boolean isResident(Passenger passenger){
		
		if( residents.contains(passenger) )
			return true;
		else
			return false;
	}
	
	/**
	 * Adds a passenger to the list of residents of the building on the ground floor. 
	 * @param passenger that enters on the ground floor
	 */
	public void enterGroundFloor(Passenger passenger){
		
		residents.add(passenger);
	}

}