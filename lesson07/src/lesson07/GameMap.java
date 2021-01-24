package lesson07;

import org.ietf.jgss.MessageProp;

import javax.annotation.processing.Messager;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by Aleksandr Gladkov [Anticisco]
 * Date: 11.01.2021
 */

public class GameMap extends JPanel {

    public static final int GAME_MODE_HVA = 0;
    public static final int GAME_MODE_HVH = 1;

    public static final int HUMAN_DOT = 2;
    public static final int PC_DOT = 1;
    public static final int EMPTY_DOT = 0;

    private int fieldSizeX;
    private int fieldSizeY;
    private int mode;
    private int winLength;
    private String winMessage;

    public static final Random RANDOM = new Random();

    private JButtonXO[][] itBtnMap;

    GameMap() {
        setBackground(Color.BLACK);
        winMessage = "";
    }

    public void round(){
        if (checkGameOver(HUMAN_DOT, "Human win!!!")) return;

        aiTurn();
        if (checkGameOver(PC_DOT, "AI win!!! ^(((((")) return;
        revalidate();
    }

    void startNewGame(int mode, int fieldSizeX, int fieldSizeY, int winLength) {
        this.fieldSizeX = fieldSizeX;
        this.fieldSizeY = fieldSizeY;
        this.mode = mode;
        this.winLength = winLength;

        removeAll();
        setLayout(new GridLayout(fieldSizeY,fieldSizeX));
        itBtnMap = new JButtonXO[fieldSizeY][fieldSizeX];
        for (int j = 0; j < fieldSizeY; j++) {
            for (int i = 0; i < fieldSizeX; i++) {
                itBtnMap[j][i] = new JButtonXO(this);
//                itBtnMap[j][i].addMouseListener(new MouseAdapter() {
//                    @Override
//                    public void mouseClicked(MouseEvent e) {
//                        super.mouseClicked(e);
//                        round();
//                    }
//                });
                add(itBtnMap[j][i]);
            }
        }
        revalidate();
    }

    public void aiTurn() {
        int lvX = -1;
        int lvY = -1;

        for (int y = 0; y < fieldSizeY; y++) {
            for (int x = 0; x < fieldSizeX; x++) {
                if (isEmptyCell(y, x)) {
                    //ищем выигрышный ход
                    itBtnMap[y][x].setDotType(PC_DOT);
                    if (checkWin(PC_DOT)) {
                        return; //нашли - сразу сходили
                    } else itBtnMap[y][x].setDotType(EMPTY_DOT);
                    //запоминаем проигрышный ход
                    itBtnMap[y][x].setDotType(HUMAN_DOT);
                    if (checkWin(HUMAN_DOT)) {
                        lvX = x;
                        lvY = y;
                    }
                    itBtnMap[y][x].setDotType(EMPTY_DOT);
                }
            }
        }
        if (lvX < 0 || lvY < 0) { //если следующим ходом человек выигрывает - захватываем клетку
            do { // иначе ходим куда попало
                lvX = RANDOM.nextInt(fieldSizeX);
                lvY = RANDOM.nextInt(fieldSizeY);
            } while (!isEmptyCell(lvY, lvX));
        }
        itBtnMap[lvY][lvX].setDotType(PC_DOT);
    }


    public boolean isValidCell(int y, int x) {
        return x >= 0 && x < fieldSizeX && y >= 0 && y < fieldSizeY;
    }

    public boolean isEmptyCell(int y, int x) {
        return itBtnMap[y][x].getDotType() == EMPTY_DOT;
    }

    public boolean checkWin(int inboxDot) {
        for (int y = 0; y < fieldSizeY; y++) { //проверяем каждый столбец
            int lvHorLine = 0; //чило подряд идущих inboxDot
            for (int x = 0; x < fieldSizeX && lvHorLine < winLength; x++) { //пока не кончились строки или не набралась победная линия
                if (itBtnMap[y][x].getDotType() != inboxDot) lvHorLine = 0; //если совпадения прервались - обнуляем счетчик
                lvHorLine += (itBtnMap[y][x].getDotType() == inboxDot) ? 1 : 0; //считаем подряд идущие inboxDot
            }
            if (lvHorLine == winLength) return true;
        }
        for (int x = 0; x < fieldSizeX; x++) {
            int lvVertLine = 0;
            for (int y = 0; y < fieldSizeY && lvVertLine < winLength; y++) {
                if (itBtnMap[y][x].getDotType() != inboxDot) lvVertLine = 0;
                lvVertLine += (itBtnMap[y][x].getDotType() == inboxDot) ? 1 : 0;
            }
            if (lvVertLine == winLength) return true;
        }
        //проверяем максимальные диагонали и все, что ниже максимальных
        for (int i = 0; i < fieldSizeY; i++) {
            int lvDiagLine1 = 0; //счетчик по прямой диагонали
            int lvDiagLine2 = 0; //счетчик по обратной диагонали
            for (int j = 0; j < fieldSizeX && (j + i) < fieldSizeY && lvDiagLine1 < winLength && lvDiagLine2 < winLength; j++) {
                if (itBtnMap[j + i][j].getDotType() != inboxDot) lvDiagLine1 = 0;
                lvDiagLine1 += (itBtnMap[j + i][j].getDotType() == inboxDot) ? 1 : 0;
                if (itBtnMap[j + i][fieldSizeX - 1 - j].getDotType() != inboxDot) lvDiagLine2 = 0;
                lvDiagLine2 += (itBtnMap[j + i][fieldSizeX - 1 - j].getDotType() == inboxDot) ? 1 : 0;
            }
            if (lvDiagLine1 == winLength || lvDiagLine2 == winLength) return true;
        }
        //проверяем все диагонали, что выше максимальных
        for (int i = 1; i < fieldSizeX; i++) {
            int lvDiagLine1 = 0;
            int lvDiagLine2 = 0;
            for (int j = 0; j < fieldSizeY && (j + i) < fieldSizeX && lvDiagLine1 < winLength && lvDiagLine2 < winLength; j++) {
                if (itBtnMap[j][j + i].getDotType() != inboxDot) lvDiagLine1 = 0;
                lvDiagLine1 += (itBtnMap[j][j + i].getDotType() == inboxDot) ? 1 : 0;
                if (itBtnMap[j][fieldSizeX - 1 - j - i].getDotType() != inboxDot) lvDiagLine2 = 0;
                lvDiagLine2 += (itBtnMap[j][fieldSizeX - 1 - j - i].getDotType() == inboxDot) ? 1 : 0;
            }
            if (lvDiagLine1 == winLength || lvDiagLine2 == winLength) return true;
        }

        return false;
    }

    public boolean isMapFull() {
        for (int y = 0; y < fieldSizeY; y++) {
            for (int x = 0; x < fieldSizeX; x++) {
                if (itBtnMap[y][x].getDotType() == EMPTY_DOT) return false;
            }
        }
        return true;
    }

    public boolean checkGameOver(int ivDot, String ivMsg) {
        boolean rv_res = false;
        if (checkWin(ivDot)) {
            winMessage = ivMsg;
            rv_res = true;
        }
        if (isMapFull()) {
            winMessage = "Ничья!!!";
            rv_res = true;
        }
        if (rv_res) {
            JOptionPane.showMessageDialog(null, winMessage);
            removeAll();
        }
        return rv_res;
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        render(g);
    }

    private void render(Graphics g) {
        if(winMessage == "") return;
        g.setColor(Color.GRAY);
        g.fillRect(0, 200, getWidth(), 70);
        g.setColor(Color.ORANGE);
        g.setFont(new Font("Times New Roman", Font.BOLD, 45));

        g.drawString(winMessage, 80, getHeight() / 2);
    }
}
