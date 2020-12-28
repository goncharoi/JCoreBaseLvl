package lesson01;

public class Main {

    public static void main(String[] args) {
	    System.out.println(calcExp(3,4,7,4));
        System.out.println(checkSumm(3,4) + ", " + checkSumm(5,14));

        printSign(-10);
        printSign(67);

        sayHello("Джава");

        System.out.println(isLeap(2000) + ", " + isLeap(2020) + ", " + isLeap(1900) + ", " + isLeap(1601) );
    }

//    1) Написать метод вычисляющий выражение a * (b + (c / d)) и возвращающий результат с плавающей точкой,
//    где a, b, c, d – целочисленные входные параметры этого метода;
    private static float calcExp(int a, int b, int c, int d){
        return a * (b + ((float) c / d));
    }
//    2) Написать метод, принимающий на вход два целых числа, и проверяющий что их сумма лежит в пределах
//    от 10 до 20(включительно), если да – вернуть true, в противном случае – false;
    private static boolean checkSumm(int ivA, int ivB){
        long lvSumm = ivA + ivB;
        return lvSumm >= 10 && lvSumm <= 20;
    }
//    3) Написать метод, которому в качестве параметра передается целое число, метод должен проверить
//    положительное ли число передали, или отрицательное.
//    Замечание: ноль считаем положительным числом. Результат работы метода вывести в консоль
    private static void printSign(int ivValue){
        if (ivValue >= 0)
            System.out.println(ivValue + " is positive");
        else
            System.out.println(ivValue + " is negative");
    }
//    4) Написать метод, которому в качестве параметра передается строка, обозначающая имя,
//    метод должен вернуть приветственное сообщение «Привет, переданное_имя!»; Вывести приветствие в консоль.
    private static void sayHello(String ivName){
        System.out.println("Привет, " + ivName + "!");
    }
//    5)*Написать метод, который определяет является ли год високосным. Каждый 4-й год является високосным,
//    кроме каждого 100-го, при этом каждый 400-й – високосный.
//    Для проверки работы вывести результаты работы метода в консоль
    private static boolean isLeap(int ivYear){
        return (ivYear % 4 == 0) && (ivYear % 100 != 0) || (ivYear % 400 == 0);
    }
}
