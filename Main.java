import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {

    private int totalMoney = 1000;
    private JLabel moneyLabel;

    private int upgradeCost_Investimento = 100;
    private int upgradeLevel_Investimento = 0;
    private JProgressBar upgradeBar_Investimento;

    private JMenuBar menuBar;

    public Main() {
        // JFrame Config
        setTitle("Dinheirama");
        setSize(800, 500);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        menuBar = new JMenuBar();

        moneyLabel = new JLabel("  Dinheiro: R$ " + totalMoney + " | ");
        menuBar.add(moneyLabel);

        setJMenuBar(menuBar);

        JButton upgradeButton = new JButton("Investir");
        upgradeButton.setFocusable(false);
        upgradeButton.setBounds(20, 100, 100, 30);
        add(upgradeButton);

        upgradeBar_Investimento = new JProgressBar(0, 10);
        upgradeBar_Investimento.setValue(upgradeLevel_Investimento);
        upgradeBar_Investimento.setBounds(130, 100, 150, 30);
        add(upgradeBar_Investimento);

        JButton nextMonthButton = new JButton("Passar o Mês");
        nextMonthButton.setFocusable(false);
        nextMonthButton.setBounds(615, 20, 150, 30);
        add(nextMonthButton);

        setVisible(true);
    }

    //Main Method
    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::new);
    }
}
