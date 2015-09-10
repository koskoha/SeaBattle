import java.util.ArrayList;

public class Ship {
    private int lenght;
    private boolean horizontal;
    private boolean isAlive = true;
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
            this.isAlive = false;
            System.out.println("Корабль убит!!!");
            System.out.println("-----------------------------------------------------------------------");
            return true;
        }else{
            injured =true;
            System.out.println("Корабль ранен!!!");
            System.out.println("-----------------------------------------------------------------------");
            return false;
        }
    }

    public boolean isInjured(){
        if (injured){
            return true;
        }
        return false;
    }


}
