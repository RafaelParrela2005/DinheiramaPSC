import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Main extends JFrame {

    // Numeric variables
    private int currentMoney = 0;
    private int totalYear = 0;
    private int totalMonth = 1;
    private int totalDay = 1;

    // JLabel para exibição de dados
    private JLabel currentMoneyLabel;
    private JLabel yearLabel;
    private JLabel monthLabel;
    private JLabel dayLabel;

    private JProgressBar timeBar_Month;

    private final int[][] upgradeLevels = {{0}, {0}, {0}, {0}, {0}};
    private final int[][] upgradeCosts = {{100}, {200}, {300}, {400}, {500}};
    private final int[][] incomes = {{10}, {0}, {0}, {0}, {0}};
    private final String[] upgradeNames = {"Hora Extra", "Empreendimento", "Bolsa de Valores", "Mercado Imobiliário", "Economias"};

    public Main() {
        // JFrame configuration
        setTitle("Dinheirama");
        setSize(800, 500);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setBackground(new Color(234, 236, 238));

        // Menu Bar
        JMenuBar menuBar = new JMenuBar();

        currentMoneyLabel = new JLabel("  Dinheiro: R$ " + currentMoney + "  | ");
        menuBar.add(currentMoneyLabel);

        dayLabel = new JLabel(" Dia: " + totalDay + " | ");
        menuBar.add(dayLabel);

        monthLabel = new JLabel(" Mês: " + totalMonth + " | ");
        menuBar.add(monthLabel);

        yearLabel = new JLabel(" Ano: " + totalYear);
        menuBar.add(yearLabel);

        setJMenuBar(menuBar);

        Color buttonColor = new Color(133, 193, 233);

        // Upgrade buttons and progress bars
        for (int i = 0; i < upgradeNames.length; i++) {
            int yOffset = 20 + (i * 40);

            JButton upgradeButton = new JButton(upgradeNames[i]);
            upgradeButton.setFocusable(false);
            upgradeButton.setBounds(20, yOffset, 150, 30);
            upgradeButton.setBackground(buttonColor);
            upgradeButton.setToolTipText("Custo: R$ " + upgradeCosts[i][0] + " | Aumenta eficiência.");
            add(upgradeButton);

            JProgressBar upgradeBar = new JProgressBar(0, 10);
            upgradeBar.setValue(upgradeLevels[i][0]);
            upgradeBar.setStringPainted(true);
            upgradeBar.setBounds(180, yOffset, 150, 30);
            add(upgradeBar);

            updateUpgradeBar(upgradeBar, upgradeLevels[i][0], upgradeCosts[i][0], 10);

            int index = i;
            upgradeButton.addActionListener(e -> handleUpgrade(upgradeBar, upgradeLevels[index], upgradeCosts[index], incomes[index], 10));
        }

        // Work button
        JButton workButton = new JButton("Trabalhar");
        workButton.setFocusable(false);
        workButton.setBounds(615, 20, 150, 30);
        workButton.setBackground(buttonColor);
        workButton.addActionListener(e -> {
            currentMoney += incomes[0][0];
            currentMoneyLabel.setText("  Dinheiro: R$ " + currentMoney + "  | ");
        });
        add(workButton);

        // Time progress bar
        timeBar_Month = new JProgressBar(0, 31);
        timeBar_Month.setValue(totalDay);
        timeBar_Month.setBounds(615, 60, 150, 20);
        timeBar_Month.setString("Dia " + totalDay + " | Faltam " + (31 - totalDay) + " dias");
        timeBar_Month.setStringPainted(true);
        add(timeBar_Month);

        // In-game timer
        Timer timer = new Timer(1000, e -> {
            totalDay++;
            timeBar_Month.setValue(totalDay);
            timeBar_Month.setString("Dia " + totalDay + " | Faltam " + (31 - totalDay) + " dias");

            if (totalDay > 31) {
                totalDay = 1;
                totalMonth++;
                timeBar_Month.setValue(totalDay);
                timeBar_Month.setString("Dia " + totalDay + " | Faltam " + (31 - totalDay) + " dias");

                if (totalMonth > 12) {
                    totalMonth = 1;
                    totalYear++;
                }
            }

            dayLabel.setText(" Dia: " + totalDay + " | ");
            monthLabel.setText(" Mês: " + totalMonth);
            yearLabel.setText(" Ano: " + totalYear + " | ");
        });

        timer.start();
        setVisible(true);
    }

    // Generic Method for Upgrades
    private void handleUpgrade(JProgressBar upgradeBar, int[] upgradeLevel, int[] upgradeCost, int[] income, int maxLevel) {
        if (upgradeLevel[0] < maxLevel && currentMoney >= upgradeCost[0]) {
            currentMoney -= upgradeCost[0];
            upgradeLevel[0]++;
            income[0] += 10;

            currentMoneyLabel.setText("  Dinheiro: R$ " + currentMoney + "  | ");
            upgradeBar.setValue(upgradeLevel[0]);
            upgradeCost[0] += 50;

            updateUpgradeBar(upgradeBar, upgradeLevel[0], upgradeCost[0], maxLevel);
        }
    }

    // Generic Mehtod for Bar Handling
    private void updateUpgradeBar(JProgressBar upgradeBar, int upgradeLevel, int upgradeCost, int maxLevel) {
        if (upgradeLevel >= maxLevel) {
            upgradeBar.setString("Limite atingido");
        } else {
            upgradeBar.setString("Custo: R$ " + upgradeCost + " | Nível: " + upgradeLevel + "/" + maxLevel);
        }
    }

    // Main Method
    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::new);
    }
}
