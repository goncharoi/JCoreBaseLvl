package lesson03;


import java.util.Random;
import java.util.Scanner;

/**
 * Created by Aleksandr Gladkov [Anticisco]
 * Date of creation: 17.12.2020
 */

public class XOGame {

    public static final char HUMAN_DOT = 'X';
    public static final char PC_DOT = 'O';
    public static final char EMPTY_DOT = '_';

    public static final Scanner SCANNER = new Scanner(System.in);
    public static final Random RANDOM = new Random();

    public static char[][] map;
    public static int mapSizeX;
    public static int mapSizeY;
    public static int mapSizeWin;

    public static void initMap() {
        do {
            System.out.println("Введите число столбцов и строк:");
            mapSizeX = SCANNER.nextInt();
            mapSizeY = SCANNER.nextInt();
        } while (mapSizeX <= 2 && mapSizeY <= 2); //хотя бы одно измерение должно быть больше 2
        do {
            System.out.println("Введите длину победной комбинации:");
            mapSizeWin = SCANNER.nextInt();
        } while (mapSizeWin > mapSizeX && mapSizeWin > mapSizeY || mapSizeWin <= 2);
        //хотя бы одно измерение должно быть <= длины победной комбинации
        //длина должна быть больше 2
        map = new char[mapSizeY][mapSizeX];

        for (int y = 0; y < mapSizeY; y++) {
            for (int x = 0; x < mapSizeX; x++) {
                map[y][x] = EMPTY_DOT;
            }
        }
    }

    public static void printMap() {
        for (int y = 0; y < mapSizeY; y++) {
            System.out.print("|");
            for (int x = 0; x < mapSizeX; x++) {
                System.out.print(map[y][x] + "|");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void humanTurn() {
        int x;
        int y;

        do {
            System.out.println("Введите свои координаты: ");
            x = SCANNER.nextInt() - 1;
            y = SCANNER.nextInt() - 1;
        } while (!isValidCell(y, x) || !isEmptyCell(y, x));
        map[y][x] = HUMAN_DOT;
    }

    public static void aiTurn() {
        int lvX = -1;
        int lvY = -1;

        for (int y = 0; y < mapSizeY; y++) {
            for (int x = 0; x < mapSizeX; x++) {
                if (isEmptyCell(y, x)) {
                    //ищем выигрышный ход
                    map[y][x] = PC_DOT;
                    if (checkWin(PC_DOT)) {
                        return; //нашли - сразу сходили
                    } else map[y][x] = EMPTY_DOT;
                    //запоминаем проигрышный ход
                    map[y][x] = HUMAN_DOT;
                    if (checkWin(HUMAN_DOT)) {
                        lvX = x;
                        lvY = y;
                    }
                    map[y][x] = EMPTY_DOT;
                }
            }
        }
        if (lvX < 0 || lvY < 0) { //если следующим ходом человек выигрывает - захватываем клетку
            do { // иначе ходим куда попало
                lvX = RANDOM.nextInt(mapSizeX);
                lvY = RANDOM.nextInt(mapSizeY);
            } while (!isEmptyCell(lvY, lvX));
        }
        map[lvY][lvX] = PC_DOT;
    }


    public static boolean isValidCell(int y, int x) {
        return x >= 0 && x < mapSizeX && y >= 0 && y < mapSizeY;
    }

    public static boolean isEmptyCell(int y, int x) {
        return map[y][x] == EMPTY_DOT;
    }

    public static boolean checkWin(char inboxChar) {
        for (int y = 0; y < mapSizeY; y++) { //проверяем каждый столбец
            int lvHorLine = 0; //чило подряд идущих inboxChar
            for (int x = 0; x < mapSizeX && lvHorLine < mapSizeWin; x++) { //пока не кончились строки или не набралась победная линия
                if (map[y][x] != inboxChar) lvHorLine = 0; //если совпадения прервались - обнуляем счетчик
                lvHorLine += (map[y][x] == inboxChar) ? 1 : 0; //считаем подряд идущие inboxChar
            }
            if (lvHorLine == mapSizeWin) return true;
        }
        for (int x = 0; x < mapSizeX; x++) {
            int lvVertLine = 0;
            for (int y = 0; y < mapSizeY && lvVertLine < mapSizeWin; y++) {
                if (map[y][x] != inboxChar) lvVertLine = 0;
                lvVertLine += (map[y][x] == inboxChar) ? 1 : 0;
            }
            if (lvVertLine == mapSizeWin) return true;
        }
        //проверяем максимальные диагонали и все, что ниже максимальных
        for (int i = 0; i < mapSizeY; i++) {
            int lvDiagLine1 = 0; //счетчик по прямой диагонали
            int lvDiagLine2 = 0; //счетчик по обратной диагонали
            for (int j = 0; j < mapSizeX && (j + i) < mapSizeY && lvDiagLine1 < mapSizeWin && lvDiagLine2 < mapSizeWin; j++) {
                if (map[j + i][j] != inboxChar) lvDiagLine1 = 0;
                lvDiagLine1 += (map[j + i][j] == inboxChar) ? 1 : 0;
                if (map[j + i][mapSizeX - 1 - j] != inboxChar) lvDiagLine2 = 0;
                lvDiagLine2 += (map[j + i][mapSizeX - 1 - j] == inboxChar) ? 1 : 0;
            }
            if (lvDiagLine1 == mapSizeWin || lvDiagLine2 == mapSizeWin) return true;
        }
        //проверяем все диагонали, что выше максимальных
        for (int i = 1; i < mapSizeX; i++) {
            int lvDiagLine1 = 0;
            int lvDiagLine2 = 0;
            for (int j = 0; j < mapSizeY && (j + i) < mapSizeX && lvDiagLine1 < mapSizeWin && lvDiagLine2 < mapSizeWin; j++) {
                if (map[j][j + i] != inboxChar) lvDiagLine1 = 0;
                lvDiagLine1 += (map[j][j + i] == inboxChar) ? 1 : 0;
                if (map[j][mapSizeX - 1 - j - i] != inboxChar) lvDiagLine2 = 0;
                lvDiagLine2 += (map[j][mapSizeX - 1 - j - i] == inboxChar) ? 1 : 0;
            }
            if (lvDiagLine1 == mapSizeWin || lvDiagLine2 == mapSizeWin) return true;
        }

        return false;
    }

    public static boolean isMapFull() {
        for (int y = 0; y < mapSizeY; y++) {
            for (int x = 0; x < mapSizeX; x++) {
                if (map[y][x] == EMPTY_DOT) return false;
            }
        }
        return true;
    }

    public static boolean checkGameOver(char ivDot, String ivMsg) {
        if (checkWin(ivDot)) {
            System.out.println(ivMsg);
            return true;
        }
        if (isMapFull()) {
            System.out.println("Ничья!!!");
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        initMap();
        printMap();
        while (true) {
            humanTurn();
            printMap();
            if (checkGameOver(HUMAN_DOT, "Human win!!!")) break;

            aiTurn();
            printMap();
            if (checkGameOver(PC_DOT, "AI win!!! ^(((((")) break;
        }
    }


}

/**
 * Полностью разобраться с кодом, попробовать переписать с нуля;
 * ^ Усовершенствовать метод проверки победы (через циклы);
 * ^ Расширить поле до 5х5 и в качестве условий победы установить серию равной 4.
 * ^^ Проработать базовый искусственный интеллект, чтобы он мог блокировать ходы игрока.
 */