import java.util.Random;

public class Computer extends Player {
    private String conputerName = "Computer";

    private int userShipCounter = 10;

    public Computer() {
        super();
    }

    public String getConputerName() {
        return conputerName;
    }

    @Override
    public void shot(Player player) {
        boolean isIterapt = true;
        while (isIterapt) {
            int control = 0;
            Random random = new Random();
            int x = 0;
            int y = 0;
            Point point;
            while (true) {
                x = random.nextInt(9) + 1;
                y = random.nextInt(9) + 1;
                point = new Point(x, y);
                if (!playerUsedPoints.contains(point)) {
                    break;
                }
            }
            System.out.println("***********************************************************************");
            System.out.println("Компютер выстрелил по координате: " + Character.toString((char) (x + 64)) + "" + y);
            playerUsedPoints.add(new Point(x, y));
            playerShotsBoard[x][y] = " $ ";
            for (Ship ship : player.allPlayerShips) {
                for (Point points : ship.getCoordinates()) {
                    if (points.equals(point)) {
                        if (ship.isKilled()) {
                            playerShipCounter--;
                            isIterapt =false;
                            playerShotsBoard[x][y] = " X";
                        }
                        player.playerShips[x][y] = " W";
                        control++;
                        break;
                    }
                }
            }
            if (control == 0) {
                System.out.println("Компютер выстрелил миио!!!");
                System.out.println("***********************************************************************");
                System.out.println();
                isIterapt=false;
            }
        }
    }
}
