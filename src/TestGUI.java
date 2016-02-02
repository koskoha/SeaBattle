

import javax.swing.*;
import java.io.IOException;

public class TestGUI {



    public static void main(String[] args) throws InterruptedException, IOException {
        GUI_SeaBattle seaBattle = new GUI_SeaBattle();
        seaBattle.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        seaBattle.setSize(1000, 750);
        seaBattle.setVisible(true);

        System.out.println("test branches");

        PrepareGame start = new PrepareGame();
    }
}

