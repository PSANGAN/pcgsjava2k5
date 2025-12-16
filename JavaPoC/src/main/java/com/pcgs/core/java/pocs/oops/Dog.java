package com.pcgs.core.java.pocs.oops;

class Dog extends Animal {
    @Override
    Animal getAnimal() {  // Covariant return - Dog is subtype of Animal
        return new Dog();
    }
}