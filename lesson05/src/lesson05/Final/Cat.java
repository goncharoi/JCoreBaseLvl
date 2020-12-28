package lesson05.Final;

import lesson05.Model.Animal;

public class Cat extends Animal {
    public Cat(int ivMutation){
        super(200,0,2,ivMutation);
    }

    @Override
    public void run(float ivLength) {
        if (ivLength <= mvMaxRunLength)
            System.out.println("Кот пробежал " + ivLength + " м");
        else
            System.out.println("Этот кот не может столько пробежать");
    }

    @Override
    public void swim(float ivLength) {
        System.out.println("Кот не умеет плавать");
    }

    @Override
    public void jump(float ivHeight) {
        if (ivHeight <= mvMaxJumpHeight)
            System.out.println("Кот подпрыгнул на " + ivHeight + " м");
        else
            System.out.println("Этот кот не способен на такой прыжок");
    }
}
