package GUI;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;


public class Player {
    String[][] playerShips = creatingBatleBoard();
    String[][] playerShotsBoard = creatingBatleBoard();
    ArrayList<Ship> allPlayerShips = new ArrayList<>();
    static ArrayList<Point> playerUsedPoints = new ArrayList<>();
    int playerShipCounter = 10;
    int decimal = 65;
    static int single = 4;
    static int doubl = 3;
    static int treeple = 2;
    static int fourth = 1;

    public Player() {
        initializingShips();

    }

    private void placeShip(ArrayList<Point> coordinates) {
        for (Point point : coordinates) {
            this.playerShips[point.getX()][point.getY()] = " X";
        }
    }

    private void initializingShips() {
        int shipType = 4;
        while (true) {
            for (int i = 0; i < 5 - shipType; i++) {
                Random random = new Random();
                boolean horizontal;
                Point point;
                ArrayList<Point> coordinates;
                while (true) {
                    horizontal = random.nextBoolean();
                    point = new Point(validationXY(shipType, random), validationXY(shipType, random));
                    coordinates = setCoordinates(point, shipType, horizontal);
                    if (isAvailiabeCoordinates(coordinates)) {
                        break;
                    }
                }
                placeShip(coordinates);
                Ship ship = new Ship(shipType, horizontal, coordinates);
                allPlayerShips.add(ship);
            }
            shipType--;
            if (shipType == 0)
                break;
        }
    }

    static void pleaseShipsOnGUIboard(Player player) {
        for (Ship ship : player.allPlayerShips) {
            for (Point point : ship.getCoordinates()) {
                GUI_SeaBattle.getUserShipLocatorGUI()[point.getX()][point.getY()].setBackground(Color.green);
            }
        }
    }

    private boolean isAvailiabeCoordinates(ArrayList<Point> coordinates) {
        for (Point point : coordinates) {
            if (playerShips[point.getX()][point.getY()].equals(" X") ||
                    playerShips[point.getX() - 1][point.getY() - 1].equals(" X") ||
                    playerShips[point.getX()][point.getY() - 1].equals(" X") ||
                    playerShips[point.getX() + 1][point.getY() - 1].equals(" X") ||
                    playerShips[point.getX() - 1][point.getY()].equals(" X") ||
                    playerShips[point.getX() + 1][point.getY()].equals(" X") ||
                    playerShips[point.getX() - 1][point.getY() + 1].equals(" X") ||
                    playerShips[point.getX()][point.getY() + 1].equals(" X") ||
                    playerShips[point.getX() + 1][point.getY() + 1].equals(" X")) {
                return false;
            }
        }
        return true;
    }

    private ArrayList<Point> setCoordinates(Point point, int lenght, boolean horizontal) {
        ArrayList<Point> coordinates = new ArrayList<>();
        int x = point.getX();
        int y = point.getY();
        for (int i = 0; i < lenght; i++) {
            coordinates.add(new Point(x, y));
            if (horizontal) {
                y++;
            } else {
                x++;
            }
        }
        return coordinates;
    }

    private int validationXY(int shipeType, Random random) {
        int xy;
        while (true) {
            xy = random.nextInt(9) + 1;
            if (xy + (shipeType - 1) < 10) {
                return xy;
            }
        }
    }


    private String[][] creatingBatleBoard() {
        String[][] games = new String[11][11];

        for (int i = 0; i < games.length; i++) {
            for (int j = 0; j < games[i].length; j++) {
                if (i == 0 && j == 0)
                    games[i][j] = "  ";
                else if (i == 0) {
                    if (j == 10) {
                        games[i][j] = "   ";
                    } else
                        games[i][j] = " " + j + "";
                } else if ((i == 10 && j == 0))
                    games[i][j] = "   ";
                else if (j == 0) {
                    games[i][j] = Character.toString((char) decimal) + " ";
                    decimal++;
                } else if (i == 10)
                    games[i][j] = "   ";
                else if (j == 10)
                    games[i][j] = "   ";
                else
                    games[i][j] = " .";
            }
        }
        return games;
    }

    public void shot(Player player, int x, int y) {
        boolean isIterapt = true;
        while (isIterapt) {
            int control = 0;
            GUI_SeaBattle.batleLogText.append("Вы выстрелили по [ " + Character.toString((char) (decimal + x - 1)) + "" + y + " } координате.\n");
            Point point = new Point(x, y);
            playerUsedPoints.add(new Point(x, y));
            playerShotsBoard[x][y] = " $";
            for (Ship ship : player.allPlayerShips) {
                for (Point points : ship.getCoordinates()) {
                    if (points.equals(point)) {
                        if (ship.isKilled()) {
                            playerShipCounter--;
                            drowAroundKilledShip(ship);

                        }
                        playerShotsBoard[x][y] = " X";
                        GUI_SeaBattle.getEnemyShipLocatorGUI()[x][y].setBackground(Color.red);
                        control++;
                    }
                }
            }
            isIterapt = false;
            if (control == 0) {
                GUI_SeaBattle.batleLogText.append("К сожалению Вы выстрелили мимо!!! \n**********************************************************************\n");
                isIterapt = false;
                PrepareGame.isShotDone = true;
            }
        }
    }

    public void drowAroundKilledShip(Ship ship) {
        int x;
        int y;
        for (Point point : ship.getCoordinates()) {
            x = point.getX();
            y = point.getY();
            if (x == 9 && y == 9) {
                setPreferenses(GUI_SeaBattle.getUserShipLocatorGUI()[x - 1][y - 1], null);
                setPreferenses(GUI_SeaBattle.getUserShipLocatorGUI()[x - 1][y], null);
                setPreferenses(GUI_SeaBattle.getUserShipLocatorGUI()[x][y - 1], null);
            } else if (y == 9) {
                setPreferenses(GUI_SeaBattle.getEnemyShipLocatorGUI()[x + 1][y], null);
                setPreferenses(GUI_SeaBattle.getEnemyShipLocatorGUI()[x - 1][y - 1], null);
                setPreferenses(GUI_SeaBattle.getEnemyShipLocatorGUI()[x][y - 1], null);
                setPreferenses(GUI_SeaBattle.getEnemyShipLocatorGUI()[x + 1][y - 1], null);
                setPreferenses(GUI_SeaBattle.getEnemyShipLocatorGUI()[x - 1][y], null);
            } else if (x == 9) {
                setPreferenses(GUI_SeaBattle.getEnemyShipLocatorGUI()[x - 1][y], null);
                setPreferenses(GUI_SeaBattle.getEnemyShipLocatorGUI()[x - 1][y - 1], null);
                setPreferenses(GUI_SeaBattle.getEnemyShipLocatorGUI()[x - 1][y + 1], null);
                setPreferenses(GUI_SeaBattle.getEnemyShipLocatorGUI()[x][y - 1], null);
                setPreferenses(GUI_SeaBattle.getEnemyShipLocatorGUI()[x][y + 1], null);
            } else {
                setPreferenses(GUI_SeaBattle.getEnemyShipLocatorGUI()[x - 1][y - 1], null);
                setPreferenses(GUI_SeaBattle.getEnemyShipLocatorGUI()[x - 1][y + 1], null);
                setPreferenses(GUI_SeaBattle.getEnemyShipLocatorGUI()[x - 1][y], null);
                setPreferenses(GUI_SeaBattle.getEnemyShipLocatorGUI()[x + 1][y], null);
                setPreferenses(GUI_SeaBattle.getEnemyShipLocatorGUI()[x + 1][y - 1], null);
                setPreferenses(GUI_SeaBattle.getEnemyShipLocatorGUI()[x + 1][y + 1], null);
                setPreferenses(GUI_SeaBattle.getEnemyShipLocatorGUI()[x][y - 1], null);
                setPreferenses(GUI_SeaBattle.getEnemyShipLocatorGUI()[x][y + 1], null);

            }
        }
        for (Point point: ship.getCoordinates()){
            x = point.getX();
            y = point.getY();
            GUI_SeaBattle.getEnemyShipLocatorGUI()[x][y].setBackground(Color.red);
        }
    }

    public void setPreferenses(JButton button, Point point) {
        button.setEnabled(false);
        button.setBackground(Color.yellow);
        playerUsedPoints.add(point);
    }
}
