# Elevator Simulation

## Github URL
<https://github.com/philipjlin/Elevator>


## Description
This program simulates the operation of an elevator, where passengers get on and get off at various floors.


## Technologies
Developed in Java.


## High Level Components
    * Java objects representing simulation elements
    

## Class Overview
    Domain Objects
        - Building - keeps track of elevator objects and floor objects associated with the building. The elevator and floor objects are accessed through methods defined in the building class. <br>
        - Elevator - contains methods that define the movement of the elevator as well as the boarding and getting off of passengers going up and down the floors of the building. <br>
        - Floor - keeps track of the floors of the building and the number of passengers residing or waiting for the elevator on each particular floor of the building. <br>
        - Passenger - represents a passenger that can reside in a building, wait for the elevator, and board the elevator to travel up and down floors to a desired destination floor.
        
    Main
        - Main - initializes the simulation.
