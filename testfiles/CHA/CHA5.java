interface SoundProducer {
    void makeSound();
}

interface Movable {
    void move();
}

class LivingThing {
    void breathe() {
        System.out.println("Living thing is breathing.");
    }
}

class Animal extends LivingThing implements SoundProducer {
    public void makeSound() {
        System.out.println("Animal is making a sound.");
    }
}

class Vehicle implements Movable {
    public void move() {
        System.out.println("Vehicle is moving.");
    }
}

class Cat extends Animal {
    public void makeSound() {
        System.out.println("Cat is meowing.");
    }
}

class Car extends Vehicle {
    public void move() {
        System.out.println("Car is driving.");
    }
}

class Robot implements Movable {
    public void move() {
        System.out.println("Robot is walking.");
    }
    
    void speak() {
        System.out.println("Robot is speaking.");
    }
}
