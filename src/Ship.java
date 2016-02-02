

import java.util.ArrayList;

public class Ship {
    private int lenght;
    private boolean horizontal;
    private boolean injured = false;
    private ArrayList<Point> coordinates = new ArrayList<>();

    public Ship(int lenght, boolean horizontal, ArrayList<Point> coordinates) {
        this.lenght = lenght;
        this.horizontal = horizontal;
        this.coordinates = coordinates;
    }

    public ArrayList<Point> getCoordinates() {
        return coordinates;
    }

    @Override
    public String toString() {

        return "Ship{" +
                "coordinates=" + coordinates +
                ", Ship is horizontal= " + horizontal +
                ", lenght=" + lenght +
                '}';
    }


    public boolean isKilled() {
        lenght--;
        if (lenght == 0) {
            GUI_SeaBattle.batleLogText.append("Congratulations, you've sunk ship \n**********************************************************************\n");
            return true;
        } else {
            injured = true;
            GUI_SeaBattle.batleLogText.append("The ship was hit \n**********************************************************************\n");
            return false;
        }
    }
}
