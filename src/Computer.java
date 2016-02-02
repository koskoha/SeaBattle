


import java.awt.Color;
import java.util.Random;

public class Computer extends Player {
    private String conputerName = "Computer";

    ////////////////////////////////////////////
    Point lastHitPoint = null;
    Point lastPoint = null;
    Ship hitedShip = null;
    int shotCounter = 1;
    boolean hitedShipHorizontal = Boolean.parseBoolean(null);
    ///////////////////////////////////

    public Computer() {
        super();
    }

    public void shot(Player player) {

        /////////////////////////////////////////////////////////////////////////
        if (hitedShip != null) {
            fatalityAlgoritm();
        } else {
            ///////////////////////////////////////////////////////////////////////

            boolean isIterapt = true;
            while (isIterapt) {
                int control = 0;
                Random random = new Random();
                int x, y;
                Point point;
                while (true) {
                    x = random.nextInt(9) + 1;
                    y = random.nextInt(9) + 1;
                    point = new Point(x, y);
                    if (!playerUsedPoints.contains(point)) {
                        break;
                    }
                }
                GUI_SeaBattle.batleLogText.append("Компютер выстрелил по [ " + Character.toString((char) (decimal + x - 1)) + "" + y + " } координате.\n");
                playerUsedPoints.add(new Point(x, y));
                playerShotsBoard[x][y] = " $ ";
                for (Ship ship : player.allPlayerShips) {
                    for (Point points : ship.getCoordinates()) {
                        if (points.equals(point)) {
                            if (ship.isKilled()) {
                                playerShipCounter--;
                                playerShotsBoard[x][y] = " X";
                                drowAroundKilledShip(ship);
                                return;
                            }
                            player.playerShips[x][y] = " W";
                            GUI_SeaBattle.getUserShipLocatorGUI()[x][y].setBackground(Color.red);
                            hitedShip = ship;
                            lastHitPoint = points;
                            fatalityAlgoritm();
                            return;
                        }
                    }
                }
                GUI_SeaBattle.batleLogText.append("Компьютер выстрелили мимо!!! \n**********************************************************************\n");
                GUI_SeaBattle.getUserShipLocatorGUI()[x][y].setBackground(Color.yellow);
                isIterapt = false;
            }
        }  /////////////////// smart;
    }

    public void fatalityAlgoritm() {
        Point nextPoint;
        int x = 0;
        int y = 0;
        if (shotCounter == 1 && !(playerUsedPoints.contains(new Point(lastHitPoint.getX() - 1, lastHitPoint.getY())))) {
            nextPoint = new Point(lastHitPoint.getX() - 1, lastHitPoint.getY());
            x = nextPoint.getX();
            y = nextPoint.getY();
            GUI_SeaBattle.getUserShipLocatorGUI()[nextPoint.getX()][nextPoint.getY()].setBackground(Color.yellow);
            GUI_SeaBattle.batleLogText.append(" Компютер выстрелил по [ " + Character.toString((char) (decimal + x - 1)) + "" + y + " } координате.\n");
            hitedShipHorizontal = true;
            shotCounter++;
        } else if (shotCounter == 2 && !(playerUsedPoints.contains(new Point(lastHitPoint.getX(), lastHitPoint.getY() - 1)))) {
            nextPoint = new Point(lastHitPoint.getX(), lastHitPoint.getY() - 1);
            x = nextPoint.getX();
            y = nextPoint.getY();
            GUI_SeaBattle.getUserShipLocatorGUI()[nextPoint.getX()][nextPoint.getY()].setBackground(Color.yellow);
            GUI_SeaBattle.batleLogText.append(" Компютер выстрелил по [ " + Character.toString((char) (decimal + x - 1)) + "" + y + " } координате.\n");
            hitedShipHorizontal = false;
            shotCounter++;
        } else if (shotCounter == 3 && !(playerUsedPoints.contains(new Point(lastHitPoint.getX() + 1, lastHitPoint.getY())))) {
            nextPoint = new Point(lastHitPoint.getX() + 1, lastHitPoint.getY());
            x = nextPoint.getX();
            y = nextPoint.getY();
            GUI_SeaBattle.getUserShipLocatorGUI()[nextPoint.getX()][nextPoint.getY()].setBackground(Color.yellow);
            GUI_SeaBattle.batleLogText.append(" Компютер выстрелил по [ " + Character.toString((char) (decimal + x - 1)) + "" + y + " } координате.\n");
            hitedShipHorizontal = true;
            shotCounter++;
        } else if (shotCounter == 4 && !(playerUsedPoints.contains(new Point(lastHitPoint.getX(), lastHitPoint.getY() + 1)))) {
            nextPoint = new Point(lastHitPoint.getX(), lastHitPoint.getY() + 1);
            x = nextPoint.getX();
            y = nextPoint.getY();
            GUI_SeaBattle.getUserShipLocatorGUI()[nextPoint.getX()][nextPoint.getY()].setBackground(Color.yellow);
            GUI_SeaBattle.batleLogText.append(" Компютер выстрелил по [ " + Character.toString((char) (decimal + x - 1)) + "" + y + " } координате.\n");
            hitedShipHorizontal = false;
            shotCounter++;
        } else {
            nextPoint = lastHitPoint;
        }

        if (hitedShip.getCoordinates().contains(nextPoint)) {
            playerUsedPoints.add(new Point(x, y));
            GUI_SeaBattle.getUserShipLocatorGUI()[x][y].setBackground(Color.red);
            GUI_SeaBattle.batleLogText.append(" Компютер выстрелил по [ " + Character.toString((char) (decimal + x - 1)) + "" + y + " } координате.\n");
            if (hitedShip.isKilled()) {
                shipKilled();
                return;
            }
            while (true) {
                if (playerUsedPoints.contains(new Point(x, y))) {
                    if (hitedShipHorizontal) {
                        x++;
                    } else
                        y++;
                } else {
                    GUI_SeaBattle.batleLogText.append(" Компютер выстрелил по [ " + Character.toString((char) (decimal + x - 1)) + "" + y + " } координате.\n");
                    if (hitedShip.getCoordinates().contains(new Point(x, y))) {
                        GUI_SeaBattle.getUserShipLocatorGUI()[x][y].setBackground(Color.red);
                        playerUsedPoints.add(new Point(x, y));
                        if (hitedShipHorizontal) {
                            x++;
                        } else
                            y++;
                        if (hitedShip.isKilled()) {
                            shipKilled();
                            return;
                        }
                    } else {
                        GUI_SeaBattle.getUserShipLocatorGUI()[x][y].setBackground(Color.red);
                        playerUsedPoints.add(new Point(x, y));
                        if (hitedShipHorizontal) {
                            x--;
                        } else
                            y--;
                        if (hitedShip.isKilled()) {
                            shipKilled();
                            return;
                        }
                    }
                }
            }
        } else {
            playerUsedPoints.add(new Point(nextPoint.getX(), nextPoint.getY()));
            GUI_SeaBattle.batleLogText.append("Компьютер выстрелили мимо!!! \n**********************************************************************\n");
        }
    }

    public void shipKilled() {
        drowAroundKilledShip(hitedShip);
        lastHitPoint = null;
        lastPoint = null;
        hitedShip = null;
        shotCounter = 1;
        hitedShipHorizontal = Boolean.parseBoolean(null);
    }

    @Override
    public void drowAroundKilledShip(Ship ship) {
        int x;
        int y;
        for (Point point : ship.getCoordinates()) {
            x = point.getX();
            y = point.getY();
            if (x == 9 && y == 9) {
                setPreferenses(GUI_SeaBattle.getUserShipLocatorGUI()[x - 1][y - 1], new Point(x - 1, y - 1));
                setPreferenses(GUI_SeaBattle.getUserShipLocatorGUI()[x - 1][y], new Point(x - 1, y));
                setPreferenses(GUI_SeaBattle.getUserShipLocatorGUI()[x][y - 1], new Point(x, y - 1));
            } else if (y == 9) {
                setPreferenses(GUI_SeaBattle.getUserShipLocatorGUI()[x + 1][y], new Point(x + 1, y));
                setPreferenses(GUI_SeaBattle.getUserShipLocatorGUI()[x - 1][y - 1], new Point(x - 1, y - 1));
                setPreferenses(GUI_SeaBattle.getUserShipLocatorGUI()[x][y - 1], new Point(x, y - 1));
                setPreferenses(GUI_SeaBattle.getUserShipLocatorGUI()[x + 1][y - 1], new Point(x + 1, y - 1));
                setPreferenses(GUI_SeaBattle.getUserShipLocatorGUI()[x - 1][y], new Point(x - 1, y));
            } else if (x == 9) {
                setPreferenses(GUI_SeaBattle.getUserShipLocatorGUI()[x - 1][y], new Point(x - 1, y));
                setPreferenses(GUI_SeaBattle.getUserShipLocatorGUI()[x - 1][y - 1], new Point(x - 1, y - 1));
                setPreferenses(GUI_SeaBattle.getUserShipLocatorGUI()[x - 1][y + 1], new Point(x - 1, y + 1));
                setPreferenses(GUI_SeaBattle.getUserShipLocatorGUI()[x][y - 1], new Point(x, y - 1));
                setPreferenses(GUI_SeaBattle.getUserShipLocatorGUI()[x][y + 1], new Point(x, y + 1));
            } else {
                setPreferenses(GUI_SeaBattle.getUserShipLocatorGUI()[x - 1][y - 1], new Point(x - 1, y - 1));
                setPreferenses(GUI_SeaBattle.getUserShipLocatorGUI()[x - 1][y + 1], new Point(x - 1, y + 1));
                setPreferenses(GUI_SeaBattle.getUserShipLocatorGUI()[x - 1][y], new Point(x - 1, y));
                setPreferenses(GUI_SeaBattle.getUserShipLocatorGUI()[x + 1][y], new Point(x + 1, y));
                setPreferenses(GUI_SeaBattle.getUserShipLocatorGUI()[x + 1][y - 1], new Point(x + 1, y - 1));
                setPreferenses(GUI_SeaBattle.getUserShipLocatorGUI()[x + 1][y + 1], new Point(x + 1, y + 1));
                setPreferenses(GUI_SeaBattle.getUserShipLocatorGUI()[x][y - 1], new Point(x, y - 1));
                setPreferenses(GUI_SeaBattle.getUserShipLocatorGUI()[x][y + 1], new Point(x, y + 1));

            }
        }
        for (Point point : ship.getCoordinates()) {
            x = point.getX();
            y = point.getY();
            GUI_SeaBattle.getUserShipLocatorGUI()[x][y].setBackground(Color.red);
        }
    }
}
