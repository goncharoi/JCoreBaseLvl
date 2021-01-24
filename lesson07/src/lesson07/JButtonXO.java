package lesson07;

import org.w3c.dom.events.Event;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class JButtonXO extends JButton {

    private int dotType;
    private GameMap gameMap;

    JButtonXO(GameMap gameMap) {
        dotType = GameMap.EMPTY_DOT;
        this.gameMap = gameMap;
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (getDotType() == GameMap.EMPTY_DOT) {
                    setDotType(GameMap.HUMAN_DOT);
                    gameMap.round();
                }
            }
        });
    }

    public void setDotType(int dotType) {
        this.dotType = dotType;
        repaint();
    }

    public int getDotType() {
        return dotType;
    }

    private void render(Graphics g) {
        if (dotType == GameMap.HUMAN_DOT) {
            g.setColor(new Color(1, 1, 255));
            g.fillOval(0, 0, getWidth(), getHeight());
        } else if (dotType == GameMap.PC_DOT) {
            g.setColor(Color.RED);
            g.fillOval(0, 0, getWidth(), getHeight());
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        render(g);
    }
}
