package elevator;

import java.util.HashSet;
import java.util.Set;

/**
 * Elevator Class
 * 
 * Defines an elevator object with a defined number of floors. The elevator has a set of passengers that are in the elevator
 * at any given time up to the elevator's capacity. 
 * 
 * The elevator can move between floor, and go to floors where passengers are waiting to board. Once the elevator
 * stops at a floor where passengers are waiting to go in the direction the elevator is traveling in, the elevator 
 * can board new passengers with a destination floor, and move between floors letting passengers off at their 
 * specified destination floors.
 * 
 * @author Philip Lin
 */
public class Elevator{
	
	public static final int CAPACITY = 10;
		
	private int currentFloor;
	private Direction direction;
	private enum Direction{ UP, DOWN };
	private Set<Passenger> passengers;	
	
	private Building building;
	
	/**
	 * Constructor.
	 * 
	 * @param building Building object that keeps track of the elevator object
	 */
	public Elevator(Building building){
		
		this.building = building;
		
		currentFloor = 1;
		direction = Direction.UP;
		passengers = new HashSet<Passenger>();
	}

	
	/**
	 * Updates the current floor by one level depending on the direction of travel of the elevator.
	 * If the ground floor (1) or top floor is reached, the direction of travel is changed.
	 * 
	 * Checks an array to see if the elevator has to stop at its current floor. If a stop is required, 
	 * the array entry tracking the number of passengers destined for the floor is cleared, and the 
	 * number of passengers on the elevator is reduced. 
	 * 
	 * All passengers waiting to board the elevator are boarded up to the elevator capacity, with extra
	 * passengers kept waiting.
	 * 
	 * The array entry indicating that a stop is required at a floor is set to false if all passengers
	 * destined for that floor have left and there are no passengers waiting on that floor.
	 */
	public void move(){
			
		//Sets used to keep track of passengers who board or disembark the elevator at a floor
		//These sets are used to update the list of passengers waiting for the elevator on each 
		//floor or the list of passengers in the elevator
		Set<Passenger> boardedPassengers = new HashSet<Passenger>();
		Set<Passenger> disembarkedPassengers = new HashSet<Passenger>();
		
		/*
		 * Step 1:
		 * Update current floor by one level depending on direction.
		 * Switch direction if top or bottom floor is reached.
		 */		
		if( goingUp() )
			currentFloor++;
		else if( goingDown() )
			currentFloor--;
		
		if( currentFloor == Building.FLOORS )
			direction = Direction.DOWN;
		if( currentFloor == 1 )
			direction = Direction.UP;
		
		/*
		 * Step 2:
		 * Disembark passengers in the elevator whose destination floor is the current floor.
		 * These passengers become residents of the floor with no destination floor.
		 */
		for( Passenger p : passengers ){
			
			if( p.destinationFloor() == currentFloor ){
				
				p.arrive();
				disembarkedPassengers.add(p);
				building.floor(currentFloor).residents.add(p);
			}
		}
		
		//Update passenger list
		for( Passenger p : disembarkedPassengers )
			passengers.remove(p);
		
		/*
		 * Step 3:
		 * Check the direction of the elevator and if there are any passengers waiting to board the elevator in the current direction.
		 * If so, board waiting passengers (up to capacity) and update lists keeping track of waiting passengers
		 */
		//If elevator is going up, board passengers on the floor who are waiting to go up
		if( goingUp() && building.floor(currentFloor).waitingUp.size() > 0 ){
			
			for( Passenger p : building.floor(currentFloor).waitingUp ){
				
				try{
					boardPassenger(p);
					boardedPassengers.add(p);
				}catch(ElevatorFullException e){
					System.out.println(e.getMessage());
					System.out.println("Moving on.");
				}
			}
		
			//Update list of passengers waiting to go up
			for( Passenger p : boardedPassengers )
				building.floor(currentFloor).waitingUp.remove(p);
		}
			
		//If elevator is going down, board passengers on the floor who are waiting to go down
		if( goingDown() && building.floor(currentFloor).waitingDown.size() > 0 ){
			
			for( Passenger p : building.floor(currentFloor).waitingDown ){
				
				try{
					boardPassenger(p);
					boardedPassengers.add(p);
				}catch(ElevatorFullException e){
					System.out.println(e.getMessage());
					System.out.println("Moving on.");
				}
			}
			
			//Update list of passengers waiting to go down
			for( Passenger p : boardedPassengers )
				building.floor(currentFloor).waitingDown.remove(p);
		}	
		
	}
	
	/**
	 * Adds 1 passenger to the elevator with a specified destination floor.
	 * 
	 * @param floor the destination floor of the added passenger
	 * @throws ElevatorFullException 
	 */
	public void boardPassenger(Passenger passenger) throws ElevatorFullException{
		
		if( passengers.size() + 1 > Elevator.CAPACITY )
			throw new ElevatorFullException();
		else{
			
			passenger.boardElevator();
			passengers.add(passenger);
		}
	}
	
	/**
	 * Return the set of passengers currently in the elevator.
	 * @return number of passengers
	 */
	public Set<Passenger> passengers(){
		
		return passengers;
	}
	
	/**
	 * Return the elevator's current floor number.
	 * @return current floor number
	 */
	public int currentFloor(){
		
		return currentFloor;
	}
	
	/**
	 * Checks if elevator's direction is up.
	 * @return boolean for up direction
	 */
	public boolean goingUp(){
		
		if( direction.equals(Direction.UP) )
			return true;
		else
			return false;
	}
	
	/**
	 * Checks if elevator's direction is down.
	 * @return boolean for down direction
	 */
	public boolean goingDown(){
		
		if( direction.equals(Direction.DOWN) )
			return true;
		else
			return false;
	}

}