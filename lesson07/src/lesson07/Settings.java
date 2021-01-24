package lesson07;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Aleksandr Gladkov [Anticisco]
 * Date: 11.01.2021
 */

public class Settings extends JFrame {

    private static final int WIN_WIDTH = 350;
    private static final int WIN_HEIGHT = 270;

    private static final int MIN_WIN_LENGTH = 3;
    private static final int MIN_FIELD_LENGTH = 3;
    private static final int MAX_FIELD_LENGTH = 10;

    private static final String FIELD_SIZE_TEXT_PREFIX_X = "Размер поля по X: ";
    private static final String FIELD_SIZE_TEXT_PREFIX_Y = "Размер поля по Y: ";
    private static final String WIN_LENGTH_TEXT_PREFIX = "Выигрышная длина: ";

    private MainWindow mainWindow;
    private JRadioButton humVsAI;
    private JRadioButton humVsHum;
    private JSlider sliderWinLen;
    private JSlider sliderFieldSizeX;
    private JSlider sliderFieldSizeY;

    Settings(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
        setSize(WIN_WIDTH, WIN_HEIGHT);

        setPos();
        setResizable(false);
        setTitle("Настройки новой игры");

        setLayout(new GridLayout(12,1));
        addGameModeSettings();
        addFieldSizeControl();

        JButton btnStartGame = new JButton("Начать новую игру");

        btnStartGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnStartClick();
            }
        });

        add(btnStartGame);
    }

    public void setPos(){
        Rectangle gameWindowBounds = mainWindow.getBounds();
        int posX = (int)gameWindowBounds.getCenterX() - WIN_WIDTH / 2;
        int posY = (int) gameWindowBounds.getCenterY() - WIN_HEIGHT / 2;
        setLocation(posX, posY);
    }

    private void addGameModeSettings() {
        add(new JLabel("Выберите режим игры:"));
        humVsAI = new JRadioButton("Human vs. AI", true);
        humVsHum = new JRadioButton("Human vs. Human");
        ButtonGroup gameMode = new ButtonGroup();
        gameMode.add(humVsAI);
        gameMode.add(humVsHum);
        add(humVsAI);
        add(humVsHum);
    }

    private void addFieldSizeControl() {
        JLabel lbFieldSizeX = new JLabel(FIELD_SIZE_TEXT_PREFIX_X + MIN_FIELD_LENGTH);
        JLabel lbFieldSizeY = new JLabel(FIELD_SIZE_TEXT_PREFIX_Y + MIN_FIELD_LENGTH);
        JLabel lbWinLength = new JLabel(WIN_LENGTH_TEXT_PREFIX + MIN_WIN_LENGTH);

        sliderFieldSizeX = new JSlider(MIN_FIELD_LENGTH, MAX_FIELD_LENGTH, MIN_FIELD_LENGTH);
        sliderFieldSizeX.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int currentValue = sliderFieldSizeX.getValue();
                lbFieldSizeX.setText(FIELD_SIZE_TEXT_PREFIX_X + currentValue);
                sliderWinLen.setMaximum(Math.max(sliderFieldSizeX.getValue(), sliderFieldSizeY.getValue()));
            }
        });
        sliderFieldSizeY = new JSlider(MIN_FIELD_LENGTH, MAX_FIELD_LENGTH, MIN_FIELD_LENGTH);
        sliderFieldSizeY.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int currentValue = sliderFieldSizeY.getValue();
                lbFieldSizeY.setText(FIELD_SIZE_TEXT_PREFIX_Y + currentValue);
                sliderWinLen.setMaximum(Math.max(sliderFieldSizeX.getValue(), sliderFieldSizeY.getValue()));
            }
        });

        sliderWinLen = new JSlider(MIN_WIN_LENGTH, MIN_FIELD_LENGTH, MIN_FIELD_LENGTH);
        sliderWinLen.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                lbWinLength.setText(WIN_LENGTH_TEXT_PREFIX + sliderWinLen.getValue());
            }
        });

        add(new JLabel("Выберите размер поля"));
        add(lbFieldSizeX);
        add(sliderFieldSizeX);
        add(lbFieldSizeY);
        add(sliderFieldSizeY);
        add(new JLabel("Выберите выигрышную позицию"));
        add(lbWinLength);
        add(sliderWinLen);

    }

    private void btnStartClick() {
        int gameMode;

        if (humVsAI.isSelected()) {
            gameMode = GameMap.GAME_MODE_HVA;
        } else if (humVsHum.isSelected()) {
            gameMode = GameMap.GAME_MODE_HVH;
        } else {
            throw new RuntimeException("Неизвестный режим игры");
        }

        mainWindow.startNewGame(gameMode, sliderFieldSizeX.getValue(), sliderFieldSizeY.getValue(), sliderWinLen.getValue());
        setVisible(false);
    }


}
