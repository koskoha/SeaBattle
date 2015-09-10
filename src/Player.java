import java.util.ArrayList;
import java.util.Random;


public class Player {
    String[][] playerShips = creatingBatleBoard();
    String[][] playerShotsBoard = creatingBatleBoard();
    ArrayList<Ship> allPlayerShips = new ArrayList<>();
    ArrayList<Point> playerUsedPoints = new ArrayList<>();
    int playerShipCounter = 10;

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
        int decimal = 65;
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

    public void shot(Player player) {
        testPrint();
        boolean isIterapt = true;
        while (isIterapt) {
            int control = 0;
            try {
                System.out.println("-----------------------------------------------------------------------");
                System.out.println("Введите координаты выстрела в виде: A1 (cheat - показать корабли):  ");
                String entrance = Initializing.sc.nextLine();
                if (entrance.equals("cheat")) {
                    player.testPrint();
                } else {
                    char[] xy = entrance.toCharArray();
                    if (xy.length > 2) {
                        throw new Exception();
                    }
                    int x = ((int) xy[0]) - 64;
                    int y = Character.getNumericValue(xy[1]);
                    Point point = new Point(x, y);
                    if (playerUsedPoints.contains(point)) {
                        System.out.println("Вы уже вводили эту коорданату, повторите попытку. ");
                    } else {
                        playerUsedPoints.add(new Point(x, y));
                        playerShotsBoard[x][y] = " $";
                        for (Ship ship : player.allPlayerShips) {
                            for (Point points : ship.getCoordinates()) {
                                if (points.equals(point)) {
                                    if (ship.isKilled()) {
                                        playerShipCounter--;
                                        isIterapt=false;
                                    }
                                    playerShotsBoard[x][y] = " X";
                                    control++;
                                    testPrint();
                                }
                            }
                        }
                        if (control == 0) {
                            System.out.println("Вы выстрелили мимо!!! \n");
                            isIterapt = false;
                        }
                    }
                }
            } catch (Exception e) {
                System.out.println("Неправельный ввод! Повторите попытку. ");
            }
        }
    }

    public void testPrint(){
        System.out.println("          Ваши корабли                         Доска выстрелов");
        int endOfLoop = playerShips.length >  playerShotsBoard.length ? playerShips.length : playerShotsBoard.length;
        for(int i = 0; i < endOfLoop; i++){
            if(playerShips.length > i){
                printLine(playerShips[i]);
                System.out.print("     ");
            } else {
                printBlankLine(playerShips[0].length);
            }

            if(playerShotsBoard.length > i){
                printLine(playerShotsBoard[i]);
            }
            System.out.println();
        }
    }

    private static void printLine(String[] line){
        for(String number : line){
            System.out.print(number + " ");
        }
    }

    private static void printBlankLine(int lenght){
        for(int i=0; i < lenght; i++) {
            System.out.print("     ");
        }
    }

  /*  public void printShipBoard() {
        for (int i = 0; i < playerShips.length; i++) {
            for (int j = 0; j < playerShips[i].length; j++) {
                System.out.print(playerShips[i][j]);
            }
            System.out.println();
        }
    }

    public void printShotBoard() {
        for (int i = 0; i < playerShotsBoard.length; i++) {
            for (int j = 0; j < playerShotsBoard[i].length; j++) {
                System.out.print(playerShotsBoard[i][j]);
            }
            System.out.println();
        }
    }
*/
}
