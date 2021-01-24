package lesson06;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	// write your code here
        final String FILE1NAME = "File1.txt";
        final String FILE2NAME = "File2.txt";
        Scanner lvScanner = new Scanner(System.in);

        createFile(FILE1NAME);
        createFile(FILE2NAME);

        System.out.println("Первый файл:");
        System.out.println(getFile(FILE1NAME));

        System.out.println("Второй файл:");
        System.out.println(getFile(FILE2NAME));

        appendFile(FILE1NAME,FILE2NAME);
        System.out.println("Итоговый файл:");
        System.out.println(getFile(FILE1NAME));

        System.out.println("Введите строку для поиска в " + FILE1NAME);
        String lvWord = lvScanner.next();
        System.out.println(checkWord(FILE1NAME, lvWord));

        System.out.println("Введите папку для поиска строки " + lvWord);
        String lvPathName = lvScanner.next();
        System.out.println(checkWordDeep(lvPathName, lvWord));

    }

    public static void createFile(String ivFileName){
        try {
            FileOutputStream fos = new FileOutputStream(ivFileName);
            int lvFileLength = 50 + (int)(Math.random() * 50);
            for (int i = 0; i < lvFileLength; i++) {
                fos.write((byte)(Math.random() * 255));
            }
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static  boolean checkWordDeep(String ivPathName, String ivWord){
        try {
            File pathDir = new File(ivPathName);
            if (pathDir.isFile()) return checkWord(ivPathName, ivWord); //если файл - запускаем обычную проверку
            String[] pathsFilesAndDir = pathDir.list(); // Иначе (если папка) - получаем массив имен файлов и подпапок
            if (pathsFilesAndDir == null) return false; //если он пустой - ничего не нашли

            for(String path:pathsFilesAndDir) {
                if (checkWordDeep(ivPathName + path + "/",ivWord)) return true; //проверяем каждую подпапку
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
        return false; //если до сих пор ничего не нашли - значит ничего и нет
    }

    public static boolean checkWord(String ivFileName, String ivWord){
        return getFile(ivFileName).contains(ivWord);
    }

    public static String getFile(String ivFileName){
        String rvFile = "";
        try {
            FileInputStream fis = new FileInputStream(ivFileName);
            int outbox;
            while ((outbox = fis.read()) != -1) {
                rvFile += (char) outbox;
            }
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rvFile;
    }

    public static void appendFile(String ivFileBaseName, String ivFileAppendName){
        try {
            FileOutputStream fos = new FileOutputStream(ivFileBaseName, true);
//при такой реализации кодировка, видимо, не та подтягивается:
//            PrintStream ps = new PrintStream(fos);
//            ps.print(getFile(ivFileAppendName));
//            ps.close();
            String lvFileAppend = getFile(ivFileAppendName);
            for (int i = 0; i < lvFileAppend.length(); i++) {
                fos.write((byte)lvFileAppend.charAt(i));
            }
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
