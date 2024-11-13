import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {

    private int currentMoney = 1000;
    private int totalYear = 0;
    private int totalMonth = 1;
    private JLabel currentMoneyLabel;
    private JLabel yearLabel;
    private JLabel monthLabel;

    private int upgradeCost_Investimento = 100;
    private int upgradeLevel_Investimento = 0;
    private JProgressBar upgradeBar_Investimento;

    private int upgradeCost_Tecnologia = 200;
    private int upgradeLevel_2 = 0;
    private JProgressBar upgradeBar_2;

    private int upgradeCost_Infraestrutura = 300;
    private int upgradeLevel_3 = 0;
    private JProgressBar upgradeBar_3;

    private int upgradeCost_4 = 400;
    private int upgradeLevel_4 = 0;
    private JProgressBar upgradeBar_4;

    private int upgradeCost_5 = 500;
    private int upgradeLevel_5 = 0;
    private JProgressBar upgradeBar_5;

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

        currentMoneyLabel = new JLabel("  Dinheiro: R$ " + currentMoney + "  | ");
        menuBar.add(currentMoneyLabel);

        yearLabel = new JLabel(" Ano: " + totalYear + " | ");
        menuBar.add(yearLabel);

        monthLabel = new JLabel("  Mês: " + totalMonth);
        menuBar.add(monthLabel);

        setJMenuBar(menuBar);

        Color buttonColor = new Color(133, 193, 233);

        JButton upgradeButton_Investimento = new JButton("Investir");
        upgradeButton_Investimento.setFocusable(false);
        upgradeButton_Investimento.setBounds(20, 20, 100, 30);
        upgradeButton_Investimento.setBackground(buttonColor);
        add(upgradeButton_Investimento);

        upgradeBar_Investimento = new JProgressBar(0, 10);
        upgradeBar_Investimento.setValue(upgradeLevel_Investimento);
        upgradeBar_Investimento.setBounds(130, 20, 150, 30);
        add(upgradeBar_Investimento);

        JButton testButton_2 = new JButton("Teste 2");
        testButton_2.setFocusable(false);
        testButton_2.setBounds(20, 60, 100, 30);
        testButton_2.setBackground(buttonColor);
        add(testButton_2);

        upgradeBar_2 = new JProgressBar(0, 10);
        upgradeBar_2.setValue(upgradeLevel_2);
        upgradeBar_2.setBounds(130, 60, 150, 30);
        add(upgradeBar_2);

        JButton testButton_3 = new JButton("Teste 3");
        testButton_3.setFocusable(false);
        testButton_3.setBounds(20, 100, 100, 30);
        testButton_3.setBackground(buttonColor);
        add(testButton_3);

        upgradeBar_3 = new JProgressBar(0, 10);
        upgradeBar_3.setValue(upgradeLevel_3);
        upgradeBar_3.setBounds(130, 100, 150, 30);
        add(upgradeBar_3);

        JButton testButton_4 = new JButton("Teste 4");
        testButton_4.setFocusable(false);
        testButton_4.setBounds(20, 140, 100, 30);
        testButton_4.setBackground(buttonColor);
        add(testButton_4);

        upgradeBar_4 = new JProgressBar(0, 10);
        upgradeBar_4.setValue(upgradeLevel_4);
        upgradeBar_4.setBounds(130, 140, 150, 30);
        add(upgradeBar_4);

        JButton testButton_5 = new JButton("Teste 5");
        testButton_5.setFocusable(false);
        testButton_5.setBounds(20, 180, 100, 30);
        testButton_5.setBackground(buttonColor);
        add(testButton_5);

        upgradeBar_5 = new JProgressBar(0, 10);
        upgradeBar_5.setValue(upgradeLevel_5);
        upgradeBar_5.setBounds(130, 180, 150, 30);
        add(upgradeBar_5);

        JButton nextMonthButton = new JButton("Passar o Mês");
        nextMonthButton.setFocusable(false);
        nextMonthButton.setBounds(615, 20, 150, 30);
        nextMonthButton.setBackground(buttonColor);
        add(nextMonthButton);

        setVisible(true);
    }

    // Main Method
    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::new);
    }
}
