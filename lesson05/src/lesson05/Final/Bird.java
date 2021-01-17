package lesson05.Final;

import lesson05.Model.Animal;

public class Bird extends Animal {
    public Bird(int ivMutation){
        super(5,0,0.2f,ivMutation);
    }

    @Override
    public void run(float ivLength) {
        if (ivLength <= mvMaxRunLength)
            System.out.println("Птица проскакала " + ivLength + " м");
        else
            System.out.println("Эта птица не может столько проскакать");
    }

    @Override
    public void swim(float ivLength) {
        System.out.println("Птица не умеет плавать");
    }

    @Override
    public void jump(float ivHeight) {
        if (ivHeight <= mvMaxJumpHeight)
            System.out.println("Птица подпрыгнула на " + ivHeight + " м");
        else
            System.out.println("Эта птица не способна на такой прыжок");
    }
}
