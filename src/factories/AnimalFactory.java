package factories;

import animal.AbsAnimal;
import animal.birds.Duck;
import animal.pets.Cat;
import animal.pets.Dog;
import data.AnimalTypeData;

public class AnimalFactory {
    public AbsAnimal create(AnimalTypeData type) {
        switch (type) {
            case CAT -> {
                return new Cat();
            }
            case DOG -> {
                return new Dog();
            }
            case DUCK -> {
                return new Duck();
            }
        }
        return null;
    }
}