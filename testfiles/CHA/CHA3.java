interface Drivable {
    void drive();
}

interface Flyable {
    void fly();
}

class Vehicle {
    void start() {
    }
}

class Car extends Vehicle implements Drivable {
    public void drive() {
    }
    
    void accelerate() {
    }
}

class Airplane extends Vehicle implements Flyable {
    public void fly() {
    }
    
    void takeOff() {
    }
}