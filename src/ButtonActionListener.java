

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonActionListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        int x = ((Button) e.getSource()).getaX();
        int y = ((Button) e.getSource()).getaY();
        PrepareGame.gerInstanseUser().shot(PrepareGame.getInstanseComp(), x, y);
        if (((Button) e.getSource()).getBackground() != Color.red) {
            ((Button) e.getSource()).setBackground(Color.YELLOW);
        }
        ((Button) e.getSource()).setEnabled(false);
    }
}
