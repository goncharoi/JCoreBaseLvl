package lesson05.Model;

public abstract class Animal {
//Животные могут выполнять действия: бежать, плыть, перепрыгивать препятствие.
//У каждого животного есть ограничения на действия
    protected float mvMaxRunLength;
    protected float mvMaxSwimLength;
    protected float mvMaxJumpHeight;

    protected Animal(float ivMaxRunLength, float ivMaxSwimLength, float ivMaxJumpHeight, int ivMutation){
        mvMaxRunLength = ivMaxRunLength;
        mvMaxSwimLength = ivMaxSwimLength;
        mvMaxJumpHeight = ivMaxJumpHeight;
        mvMaxRunLength = mutate(mvMaxRunLength,ivMutation);
        mvMaxSwimLength = mutate(mvMaxSwimLength,ivMutation);
        mvMaxJumpHeight = mutate(mvMaxJumpHeight,ivMutation);
    }
//В качестве параметра каждому методу передается величина,
//означающая или длину препятствия (для бега и плавания), или высоту (для прыжков).
    public abstract void run(float ivLength);
    public abstract void swim(float ivLength);
    public abstract void jump(float ivHeight);
//Добавить животным разброс в ограничениях.
//Механизм мутации заложен в самом организме как ответ на вшнешнее влияние
    private float mutate(float ivProperty, int ivMutation){
        //Если животное не может, то и не сможет, иначе - изменение в пределах ivMutation %
        return (float) (ivProperty * (100 + 2 * (0.5f - Math.random()) * ivMutation) / 100);
    }

    public void print(){
        System.out.println("Предельная дальность забега - " +  String.format("%.2f", mvMaxRunLength)
                + " м, заплыва - "+ String.format("%.2f", mvMaxSwimLength)
                + " м, высота прыжка - " + String.format("%.2f", mvMaxJumpHeight) + " м");
    }
}
