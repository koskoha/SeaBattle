

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

public class GUI_SeaBattle extends JFrame {
    private static JButton[][] userShipLocatorGUI;
    private static JButton[][] enemyShipLocatorGUI;
    private ButtonActionListener listener = new ButtonActionListener();

    private BgPanel main = new BgPanel();

    private JPanel mainShipPanel = new JPanel();
    private JPanel infoPanel = new JPanel();
    private JPanel userShips = new JPanel();
    private JPanel enemyShips = new JPanel();
    static JTextArea batleLogText = new JTextArea("");
    private JPanel batleLogPanel = new JPanel();
    private JPanel battleInfo = new JPanel();
    static JLabel lableBatleinfo = new JLabel("Batle info");


    public GUI_SeaBattle() throws HeadlessException, IOException {
        super("Sea Battle");
        Border emptyBorder = BorderFactory.createEmptyBorder(0, 30, 30, 30);


        mainShipPanel.setLayout(new FlowLayout());
        mainShipPanel.setPreferredSize(new Dimension(1000, 525));
        mainShipPanel.setBorder(new CompoundBorder(emptyBorder, setTitleBorder("РџРѕР»Рµ Р±РѕСЏ")));
        mainShipPanel.setOpaque(false);


        userShips.setLayout(new GridLayout(0, 10, 5, 5));
        userShips.setPreferredSize(new Dimension(450, 450));
        userShips.setBorder(new CompoundBorder(emptyBorder, BorderFactory.createTitledBorder("Р’Р°С€Рё РєРѕСЂР°Р±Р»Рё")));
        userShips.setOpaque(false);

        enemyShips.setLayout(new GridLayout(0, 10, 5, 5));
        enemyShips.setPreferredSize(new Dimension(450, 450));
        enemyShips.setBorder(new CompoundBorder(emptyBorder, BorderFactory.createTitledBorder("Р”РѕСЃРєР° РІС‹СЃС‚СЂРµР»РѕРІ")));
        enemyShips.setOpaque(false);

        infoPanel.setLayout(new BorderLayout(5, 5));
        infoPanel.setPreferredSize(new Dimension(940, 200));
        infoPanel.setBorder(setTitleBorder("Р�РЅС„Рѕ РїР°РЅРµР»СЊ"));
        infoPanel.setOpaque(false);

        batleLogPanel.setBorder(BorderFactory.createTitledBorder("Р‘РѕРµРІРѕР№ Р¶СѓСЂРЅР°Р»"));
        batleLogPanel.setPreferredSize(new Dimension(450, 150));
        batleLogPanel.setOpaque(false);
        batleLogText.setBackground(new Color(209, 240, 255));
        JScrollPane scrollPane = new JScrollPane(batleLogText);
        scrollPane.setPreferredSize(new Dimension(400, 120));
        DefaultCaret caret = (DefaultCaret) batleLogText.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

        battleInfo.setBorder(BorderFactory.createTitledBorder("Р�РЅС„РѕСЂРјР°С†РёСЏ Рѕ СЃРѕСЃС‚РѕСЏРЅРёРё РєРѕСЂР°Р±Р»РµР№"));
        battleInfo.setPreferredSize(new Dimension(450, 150));
        battleInfo.setOpaque(false);

        main.setPreferredSize(new Dimension(1000, 750));
        main.setLayout(new FlowLayout());


        setJMenuBar(createMenue());

        add(main);

        main.add(infoPanel);
        main.add(mainShipPanel);

        battleInfo.add(lableBatleinfo);

        batleLogPanel.add(scrollPane);
        infoPanel.add(batleLogPanel, BorderLayout.WEST);
        // infoPanel.add(lableBatleinfo, BorderLayout.CENTER);
        infoPanel.add(battleInfo, BorderLayout.EAST);

        mainShipPanel.add(userShips);
        mainShipPanel.add(enemyShips);

        setEnemyShipLocatorGUI(batlefildGUI(enemyShips, true));
        setUserShipLocatorGUI(batlefildGUI(userShips, false));

    }

    public static JButton[][] getEnemyShipLocatorGUI() {
        return enemyShipLocatorGUI;
    }

    public static void setEnemyShipLocatorGUI(JButton[][] enemyShipLocatorGUI) {
        GUI_SeaBattle.enemyShipLocatorGUI = enemyShipLocatorGUI;
    }

    public static JButton[][] getUserShipLocatorGUI() {
        return userShipLocatorGUI;
    }

    public static void setUserShipLocatorGUI(JButton[][] userShipLocatorGUI) {
        GUI_SeaBattle.userShipLocatorGUI = userShipLocatorGUI;
    }

    public JMenuBar createMenue() {
        JMenuBar menuBar = new JMenuBar();
        JMenu game = new JMenu("Game");
        game.add("РќРѕРІР°СЏ РёРіСЂР°").addActionListener(e -> {

        });

        game.add("Exit").addActionListener(e -> System.exit(0));

        JMenu infoMenu = new JMenu("Info");
        infoMenu.add("About...").addActionListener(e -> JOptionPane.showMessageDialog(null, "Р�РіСЂСѓ РЅР°РїРёСЃР°Р» РљРѕРЅСЃС‚Р°РЅС‚РёРЅ РљРѕР±С‹Р»РёРЅСЃРєРёР№ \n РќРµ СЃСѓРґРёС‚Рµ СЃС‚СЂРѕРіРѕ Р·Р° Р±Р°РіРё, РёРіСЂР° РµС‰Рµ РІ РґРѕСЂР°Р±РѕС‚РєРµ!)", "About...", JOptionPane.PLAIN_MESSAGE));

        menuBar.add(game);
        menuBar.add(infoMenu);
        return menuBar;
    }


    private TitledBorder setTitleBorder(String titleText) {
        TitledBorder title = BorderFactory.createTitledBorder(titleText);
        title.setTitleFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));
        title.setTitleJustification(TitledBorder.CENTER);
        title.setBorder(BorderFactory.createLineBorder(Color.black));
        return title;
    }


    public JButton[][] batlefildGUI(JPanel panel, boolean isEnabled) {
        JButton[][] shipLocatorGUI = new Button[10][10];
        int decimal = 65;
        for (int i = 0; i < shipLocatorGUI.length; i++) {
            for (int j = 0; j < shipLocatorGUI.length; j++) {
                if (i == 0 && j == 0) {
                    panel.add(shipLocatorGUI[i][j] = new Button("", i, j));
                    shipLocatorGUI[i][j].setVisible(false);
                    continue;
                }
                if (i == 0) {
                    panel.add(shipLocatorGUI[i][j] = new Button("" + j, i, j));  /// Adding didits
                    Player.playerUsedPoints.add(new Point(i,j));
                    shipLocatorGUI[i][j].setFont(new Font("Arial", Font.BOLD, 16));
                    shipLocatorGUI[i][j].setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2));
                    shipLocatorGUI[i][j].setEnabled(false);
                    shipLocatorGUI[i][j].setBackground(Color.yellow);
                } else if (j == 0) {
                    panel.add(shipLocatorGUI[i][j] = new Button(Character.toString((char) decimal) + "", i, j)); // Adding Letters
                    Player.playerUsedPoints.add(new Point(i,j));
                    shipLocatorGUI[i][j].setFont(new Font("Arial", Font.BOLD, 16));
                    shipLocatorGUI[i][j].setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2));
                    shipLocatorGUI[i][j].setEnabled(false);
                    shipLocatorGUI[i][j].setBackground(Color.yellow);
                    decimal++;
                } else {
                    panel.add(shipLocatorGUI[i][j] = new Button("", i, j));
                    shipLocatorGUI[i][j].addActionListener(listener);
                    shipLocatorGUI[i][j].setBackground(Color.CYAN);
                    if (!isEnabled) {
                        shipLocatorGUI[i][j].setEnabled(false);
                    }
                }
            }
        }
        return shipLocatorGUI;
    }
}

class BgPanel extends JPanel {
    Image bg = new ImageIcon("Images/sb1.jpg").getImage();

    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
    }
}
