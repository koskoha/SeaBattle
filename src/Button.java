
import javax.swing.*;

public class Button extends JButton{
    private int x;
    private int y;


    public Button(String name, int x,int y) {
        super(name);
        this.x=x;
        this.y=y;
    }

    public int getaX() {
        return x;
    }

    public int getaY() {
        return y;
    }


}
