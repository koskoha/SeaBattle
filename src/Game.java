


public class Game {
    public static void main(String[] args) {
        User user = new User();
        Initializing.greeting(user);
        Computer computer = new Computer();
        System.out.println("\nAll Ships was created\n-----------------------------------------------------------------------");
        while (true) {
            user.shot(computer);
            if (user.playerShipCounter == 0) {
                System.out.println(user.getUserName() + " Победил!!! :-)");
                break;
            }
            computer.shot(user);
            if (computer.playerShipCounter == 0) {
                System.out.println(computer.getConputerName() + " Победил!!! :-(");
                break;
            }
        }
    }

}
