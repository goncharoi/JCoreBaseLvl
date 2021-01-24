package lesson05.Final;

import lesson05.Model.Animal;

public class Dog extends Animal {
    public Dog(int ivMutation){
        super(500,10,0.5f, ivMutation);
    }

    @Override
    public void run(float ivLength) {
        if (ivLength <= mvMaxRunLength)
            System.out.println("Пес пробежал " + ivLength + " м");
        else
            System.out.println("Этот пес не может столько пробежать");
    }

    @Override
    public void swim(float ivLength) {
        if (ivLength <= mvMaxSwimLength)
            System.out.println("Пес проплыл " + ivLength + " м");
        else
            System.out.println("Этот пес не может столько проплыть");
    }

    @Override
    public void jump(float ivHeight) {
        if (ivHeight <= mvMaxJumpHeight)
            System.out.println("Пес подпрыгнул на " + ivHeight + " м");
        else
            System.out.println("Этот пес не способен на такой прыжок");
    }
}
