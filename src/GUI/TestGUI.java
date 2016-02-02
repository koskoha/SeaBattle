package GUI;

import javax.swing.*;
import java.io.IOException;

public class TestGUI {
    /*
    * Реализован алгоритм дабивания компьютеров кораблей противника при попадании (еще имеются недоработки)
     */


    public static void main(String[] args) throws InterruptedException, IOException {
        GUI_SeaBattle seaBattle = new GUI_SeaBattle();
        seaBattle.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        seaBattle.setSize(1000, 750);
        seaBattle.setVisible(true);


        PrepareGame start = new PrepareGame();
    }
}

