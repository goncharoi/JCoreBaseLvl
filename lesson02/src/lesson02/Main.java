package lesson02;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
//        1 Задать целочисленный массив, состоящий из элементов 0 и 1. Например: [ 1, 1, 0, 0, 1, 0, 1, 1, 0, 0 ].
//        Написать метод, заменяющий в  принятом массиве 0 на 1, 1 на 0;
        System.out.println("Задание 1");
        int[] lsArr01 = getRandomArray((int) Math.round(Math.random() * 10) + 1, 1);
        System.out.println("Исходный массив: " + Arrays.toString(lsArr01));
        revertArray01(lsArr01);
        System.out.println("Итоговый массив: " + Arrays.toString(lsArr01));
//        2 Задать пустой целочисленный массив размером 8. Написать метод,
//        который c помощью цикла заполнит его значениями 1 4 7 10 13 16 19 22;
        System.out.println("Задание 2");
        int[] lsArrInt2 = new int[8];
        fillArray(lsArrInt2);
        System.out.println(Arrays.toString(lsArrInt2));
//        3 Задать массив [ 1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1 ], написать метод,
//        принимающий на вход массив и умножающий числа меньше 6 на 2;
        System.out.println("Задание 3");
        int[] lsArrInt3 = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        multiplyArray(lsArrInt3);
        System.out.println(Arrays.toString(lsArrInt3));
//        4 Задать одномерный массив. Написать методы поиска в нём минимального и максимального элемента;
        System.out.println("Задание 4");
        int[] lsArrInt4 = getRandomArray((int) Math.round(Math.random() * 15) + 1, 10);
        System.out.println("Исходный массив: " + Arrays.toString(lsArrInt4));
        System.out.println("Максимум = " + getMax(lsArrInt4) + ", Минимум = " + getMin(lsArrInt4));
//        5 * Создать квадратный целочисленный массив (количество строк и столбцов одинаковое),
//        заполнить его диагональные элементы единицами, используя цикл(ы);
        System.out.println("Задание 5");
        int[][] ltArrSqr = getRandomArraySqr((int) Math.round(Math.random() * 5) + 1, 15);
        System.out.println("Исходный массив: ");
        printArraySqr(ltArrSqr);
        fillDiag(ltArrSqr);
        System.out.println("Итоговый массив: ");
        printArraySqr(ltArrSqr);
//        6 ** Написать метод, в который передается не пустой одномерный целочисленный массив,
//        метод должен вернуть true если в массиве есть место, в котором сумма левой и правой части массива равны.
        System.out.println("Задание 6");
        int[] lsArrInt6 = getRandomArray((int) Math.round(Math.random() * 5) + 1, 10);
        System.out.println("Исходный массив: " + Arrays.toString(lsArrInt6));
        System.out.println(checkBalance(lsArrInt6));
//        7 *** Написать метод, которому на вход подаётся одномерный массив и число n
//        (может быть положительным, или отрицательным),
//        при этом метод должен циклически сместить все элементы массива на n позиций.
        System.out.println("Задания 7,8");
        int[] lsArrInt7 = getRandomArray((int) Math.round(Math.random() * 15) + 1, 10);
        System.out.println("Исходный массив: " + Arrays.toString(lsArrInt7));
        System.out.println("Введите сдвиг: ");
        shiftArray(lsArrInt7, scanner.nextInt());
        System.out.println("Итоговый массив: " + Arrays.toString(lsArrInt7));
    }
//----------------------------------------------------------------------------------------------------------------------
    //Метод, заменяющий в  принятом массиве 0 на 1, 1 на 0;
    private static void revertArray01(int[] csArray){
        for (int i = 0; i < csArray.length; i++){
            csArray[i] = (csArray[i] == 1) ? 0 : 1;
        }
    }
    //Метод, c помощью цикла заполняет массив значениями 1 4 7 10 13 16 19 22;
    private static void fillArray(int[] csArray){
        csArray[0] = 1;
        for (int i = 1; i < csArray.length; i++){
            csArray[i] = csArray[i - 1] + 3;
        }
    }
    //Метод, принимающий на вход массив и умножающий числа меньше 6 на 2;
    private static void multiplyArray(int[] csArray){
        for (int i = 0; i < csArray.length; i++){
            csArray[i] = (csArray[i] < 6) ? csArray[i] * 2 : csArray[i];
        }
    }
    //Методы поиска минимального и максимального элемента;
    private static int getMax(int[] isArray){
        int rvMax = isArray[0];
        for (int i = 1; i < isArray.length; i++){
            rvMax = (isArray[i] > rvMax) ? isArray[i] : rvMax;
        }
        return rvMax;
    }
    private static int getMin(int[] isArray){
        int rvMin = isArray[0];
        for (int i = 1; i < isArray.length; i++){
            rvMin = (isArray[i] < rvMin) ? isArray[i] : rvMin;
        }
        return rvMin;
    }
    //Метод, заполняющий диагонали квадратного массива единицами
    private static void fillDiag(int[][] itArray){
        for (int i = 0; i < itArray.length; i++){
            itArray[i][i] = 1;
            itArray[i][itArray.length - i - 1] = 1;
        }
    }
    //Метод, проверяющий баланс массива
    private static boolean checkBalance(int[] isArray){
        long lvSummRight = 0;
        for (int i = 0; i < isArray.length; i++){
            lvSummRight += isArray[i];
        }
        long lvSummLeft = 0;
        for (int i = 0; i < isArray.length; i++){
            lvSummLeft += isArray[i];
            lvSummRight -= isArray[i];
            if (lvSummLeft == lvSummRight) return true;
        }
        return false;
    }
    //Метод, сдвигающий массив
    private static void shiftArray(int[] csArray, int ivSteps){
        int lvSteps = ivSteps % csArray.length; //вводим шаг в предел массива
        int lvShifted = csArray[0]; //сдвигаемый элемент
        int lvBuffer = 0; //буфер для того элемента, на место которого сдвигаем
        for (int i = 0, lvTrgt = 0, lvOffset = 0; i < csArray.length; i++){
            if (lvTrgt == lvOffset) { //если в ходе сдвига мы вернулись в начальную точку (число элементов кратно сдвигу)
                lvTrgt += lvSteps / Math.abs(lvSteps); // смещаемся на единицу в сторону сдвига
                lvTrgt = (lvTrgt + csArray.length) % csArray.length; //на случай, если вышли за границы
                lvOffset = lvTrgt; //в следующий раз, когда круг завершится, мы окажемся на исходной
                lvShifted = csArray[lvOffset]; //сдвигаемый элемент надо переустановить
            }
            lvTrgt = (lvTrgt + lvSteps + csArray.length) % csArray.length; //номер целевого элемента на место которого происходит сдвиг

            lvBuffer = csArray[lvTrgt]; //сохраняем целевой элемент
            csArray[lvTrgt] = lvShifted; //ставим на его место сдвигаемый
            lvShifted = lvBuffer; //устанавливаем сохраненный элемент в качестве сдвигаемого
        }
    }
//----------------------------------------------------------------------------------------------------------------------
    //Вспомагательный метод генерации массива длины ivLength со случайными числами от 0 до ivMax
    private static int[] getRandomArray(int ivLength, int ivMax){
        int[] rsArr = new int[ivLength];
        for (int i = 0; i < ivLength; i++){
            rsArr[i] = (int) Math.round(Math.random() * ivMax);
        }
        return rsArr;
    }
    //Вспомагательный метод генерации квадратного массива ivLength x ivLength со случайными числами от 0 до ivMax
    private static int[][] getRandomArraySqr(int ivLength, int ivMax){
        int[][] rtArrSqr = new int[ivLength][];
        for (int i = 0; i < ivLength; i++){
            rtArrSqr[i] = getRandomArray(ivLength, ivMax);
        }
        return rtArrSqr;
    }
    //Вспомагательный метод вывода квадратного массива
    private static void printArraySqr(int[][] itArrSqr){
        for (int i = 0; i < itArrSqr.length; i++){
            for (int j = 0; j < itArrSqr[i].length; j++){
                System.out.print(itArrSqr[i][j] + "\t");
            }
            System.out.println();
        }
    }
}
