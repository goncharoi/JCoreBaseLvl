package lesson07;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

/**
 * Created by Aleksandr Gladkov [Anticisco]
 * Date: 11.01.2021
 */

public class MainWindow extends JFrame {
    private static final int WIN_SIZE = 500;
    private static final int BTN_HEIGHT = 55;
    private static final int WIN_POSX = 650;
    private static final int WIN_POSY = 250;

    private Settings settingsWindow;
    private GameMap gameMap;

    MainWindow() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(WIN_SIZE, WIN_SIZE + BTN_HEIGHT);
        setLocation(WIN_POSX, WIN_POSY);
        setTitle("Игра: Крестики-Нолики");
        setResizable(false);

        settingsWindow = new Settings(this);
        settingsWindow.addComponentListener(new ComponentListener() {
                                                @Override
                                                public void componentResized(ComponentEvent e) { }

                                                @Override
                                                public void componentMoved(ComponentEvent e) { }

                                                @Override
                                                public void componentShown(ComponentEvent e) {
                                                    settingsWindow.setPos();
                                                }

                                                @Override
                                                public void componentHidden(ComponentEvent e) { }
                                            }
        );
        gameMap = new GameMap();

        JButton btnStartGame = new JButton("Новая игра");
        btnStartGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                settingsWindow.setVisible(true);
            }
        });

        JButton btnExit = new JButton("Выход из игры");
        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });


        JPanel panelBottom = new JPanel();
        panelBottom.setLayout(new GridLayout(1, 2));
        panelBottom.add(btnStartGame);
        panelBottom.add(btnExit);
        panelBottom.setSize(this.getWidth(), BTN_HEIGHT);
        add(panelBottom, BorderLayout.SOUTH);

        add(gameMap);
        setVisible(true);
    }

    void startNewGame(int mode, int fieldSizeX, int fieldSizeY, int winLength) {
        if (fieldSizeX > fieldSizeY){
            setSize(WIN_SIZE, WIN_SIZE * fieldSizeY / fieldSizeX + BTN_HEIGHT);
        } else if (fieldSizeX < fieldSizeY){
            setSize(WIN_SIZE * fieldSizeX / fieldSizeY, WIN_SIZE + BTN_HEIGHT);
        } else {
            setSize(WIN_SIZE, WIN_SIZE + BTN_HEIGHT);
        }
        revalidate();
        gameMap.startNewGame(mode, fieldSizeX, fieldSizeY, winLength);
    }

}
