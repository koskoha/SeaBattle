import java.util.Scanner;

public class Initializing {

    static Scanner sc = new Scanner(System.in, "UTF-8");

    static void greeting(User player) {
        System.out.printf("Введите ваше имя/ник: ");
        player.setUserName(sc.nextLine());
        System.out.println("Здравствуйте " + player.getUserName() + ". Приветствую вас в игре Sea Battle.");
    }

}
