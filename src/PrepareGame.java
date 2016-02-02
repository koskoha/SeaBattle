public class PrepareGame {
    static User user;
    static Computer comp;
    static boolean isShotDone = false;

    public PrepareGame() throws InterruptedException {
        user = new User();
        Player.pleaseShipsOnGUIboard(user);
        comp = new Computer();

        while (true) {
            if (isShotDone) {
                isShotDone=false;
                comp.shot(user);
            }
            if (user.playerShipCounter == 0) {
                GUI_SeaBattle.lableBatleinfo.setText("Вы победили!!!))");
                break;
            }
            if (comp.playerShipCounter == 0) {
                GUI_SeaBattle.lableBatleinfo.setText("Компьютер победил!!!(((");
                break;
            }
        }

    }

    static Player gerInstanseUser() {
        return user;
    }

    static Player getInstanseComp() {
        return comp;
    }
}
