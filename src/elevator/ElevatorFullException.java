package elevator;

/**
 * ElevatorFullException.java
 * Exception class for handling events that occur when the elevator is full.
 * 
 * @author Philip Lin
 */
@SuppressWarnings("serial")

public class ElevatorFullException extends Exception{
		
	/**
	 * Constructor
	 * Default with message
	 */
	public ElevatorFullException(){
		
		super("Exception: Elevator Full.");
	}
	
	/**
	 * Constructor
	 * @param message message for exception
	 */
	public ElevatorFullException(String message){
		
		super(message);
	}
	
}