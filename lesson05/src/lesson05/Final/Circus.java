package lesson05.Final;

import lesson05.Model.Animal;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Circus {
    private static final int MUTATION = 50; //Цирк уродов

    private static Animal[] mtAnimals = new Animal[0];

    public static void newAnimal(String ivKind){
        Animal loAnimal;
        switch (ivKind){
            case "Кот":
                loAnimal = new Cat(MUTATION);
                break;
            case "Конь":
                loAnimal = new Horse(MUTATION);
                break;
            case "Пес":
                loAnimal = new Dog(MUTATION);
                break;
            case "Птица":
                loAnimal = new Bird(MUTATION);
                break;
            default:
                System.out.println("Животных типа " + ivKind + " - не держим, отказать");
                return;
        }
        System.out.println("Наш цирк пополнился новым животным: " + ivKind);
        loAnimal.print();
        Animal[] ltAnimalsTmp = Arrays.copyOf(mtAnimals, mtAnimals.length + 1);
        ltAnimalsTmp[mtAnimals.length] = loAnimal;
        mtAnimals = ltAnimalsTmp;
    }

    public static void allRun(float ivLength){
        for (Animal loAnimal:mtAnimals) {
            loAnimal.run(ivLength);
        }
    }

    public static void allJump(float ivHeight){
        for (Animal loAnimal:mtAnimals) {
            loAnimal.jump(ivHeight);
        }
    }

    public static void allSwim(float ivLength){
        for (Animal loAnimal:mtAnimals) {
            loAnimal.swim(ivLength);
        }
    }
}
