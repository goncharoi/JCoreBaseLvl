package lesson05.Final;

import lesson05.Model.Animal;

public class Horse extends Animal {
    public Horse(int ivMutation){
        super(1500,100,3f, ivMutation);
    }

    @Override
    public void run(float ivLength) {
        if (ivLength <= mvMaxRunLength)
            System.out.println("Конь проскакал " + ivLength + " м");
        else
            System.out.println("Этот конь не может столько проскакать");
    }

    @Override
    public void swim(float ivLength) {
        if (ivLength <= mvMaxSwimLength)
            System.out.println("Конь проплыл " + ivLength + " м");
        else
            System.out.println("Этот конь не может столько проплыть");
    }

    @Override
    public void jump(float ivHeight) {
        if (ivHeight <= mvMaxJumpHeight)
            System.out.println("Конь подпрыгнул на " + ivHeight + " м");
        else
            System.out.println("Этот конь не способен на такой прыжок");
    }
}
