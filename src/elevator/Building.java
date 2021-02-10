package elevator;

/**
 * Building Class
 * 
 * Defines a Building object that keeps track of an elevator and floor objects that are 
 * a part of a building. Elevator and Floor objects are associated with a Building object
 * through composition.
 * 
 * The building class uses an array to keep track of the different floor objects, with one 
 * floor object associated to each floor in the building.
 * 
 * Elevator and Floor objects can be returned by the Building object.
 * 
 * @author Philip Lin
 */
public class Building{
	
	public static final int FLOORS = 7;
	
	private Elevator elevator;
	private Floor[] floors = new Floor[FLOORS + 1];
		
	/**
	 * Constructor
	 */
	public Building(){
		
		elevator = new Elevator(this);
		
		for( int floorNumber = 1 ; floorNumber <= FLOORS ; floorNumber++)
			floors[floorNumber] = new Floor(floorNumber);
	}
	
	/**
	 * Returns the elevator.
	 * @return elevator associated with the building
	 */
	public Elevator elevator(){
		
		return elevator;
	}
	
	/**
	 * Returns a floor object of the building. 
	 * @param floorNumber the floor number that identifies floor object to return 
	 * @return the floor object to return
	 */
	public Floor floor(int floorNumber){
		
		return floors[floorNumber];
	}
	
	/**
	 * Adds a resident to the building on the ground floor.
	 * @param passenger resident that entered the building
	 */
	public void enter(Passenger passenger){
		
		floors[1].enterGroundFloor(passenger);
	}

}