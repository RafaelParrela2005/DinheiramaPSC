import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame {

    //Numeric variables
    private int currentMoney = 0;
    private int totalYear = 0;
    private int totalMonth = 1;
    private int totalDay = 1;

    //income variables
    private int workIncome = 10;

    //upgrading variables
    private int upgradeCost_Freelancer = 100;
    private int upgradeLevel_Freelancer = 0;

    private int upgradeCost_Enterprise = 200;
    private int upgradeLevel_Enterprise = 0;

    private int upgradeCost_StockMarket = 300;
    private int upgradeLevel_StockMarket = 0;

    private int upgradeCost_RealEstateMarket = 400;
    private int upgradeLevel_RealEstateMarket = 0;

    private int upgradeCost_Economy = 500;
    private int upgradeLevel_Economy = 0;

    private JLabel currentMoneyLabel;
    private JLabel yearLabel;
    private JLabel monthLabel;
    private JLabel dayLabel;

    private JProgressBar upgradeBar_Freelancer;
    private JProgressBar upgradeBar_Enterprise;
    private JProgressBar upgradeBar_StockMarket;
    private JProgressBar upgradeBar_RealEstateMarket;
    private JProgressBar upgradeBar_Economy;
    private JProgressBar timeBar_Month;


    private JMenuBar menuBar;

    public Main() {
        // JFrame Config
        setTitle("Dinheirama");
        setSize(800, 500);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        getContentPane().setBackground(new Color(234, 236, 238));

        menuBar = new JMenuBar();

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

        JButton upgradeButton_Freelancer = new JButton("Hora Extra");
        upgradeButton_Freelancer.setFocusable(false);
        upgradeButton_Freelancer.setBounds(20, 20, 150, 30);
        upgradeButton_Freelancer.setBackground(buttonColor);
        upgradeButton_Freelancer.setToolTipText("+ 10R$ ao clicar em Trabalho. No nível máximo, +100R$.");
        add(upgradeButton_Freelancer);

        upgradeBar_Freelancer = new JProgressBar(0, 10);
        upgradeBar_Freelancer.setValue(upgradeLevel_Freelancer);
        updateUpgradeBar_Freelancer();
        upgradeBar_Freelancer.setStringPainted(true);
        upgradeBar_Freelancer.setBounds(180, 20, 150, 30);
        add(upgradeBar_Freelancer);

        upgradeButton_Freelancer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (upgradeLevel_Freelancer < 10 && currentMoney >= upgradeCost_Freelancer) {
                    currentMoney -= upgradeCost_Freelancer;
                    upgradeLevel_Freelancer++;

                    switch (upgradeLevel_Freelancer) {
                        case 1: workIncome = 10; break;
                        case 2: workIncome = 20; break;
                        case 3: workIncome = 30; break;
                        case 4: workIncome = 40; break;
                        case 5: workIncome = 50; break;
                        case 6: workIncome = 60; break;
                        case 7: workIncome = 70; break;
                        case 8: workIncome = 80; break;
                        case 9: workIncome = 90; break;
                        case 10: workIncome = 100; break;
                    }

                    // Update current money and upgrade bar
                    currentMoneyLabel.setText("  Dinheiro: R$ " + currentMoney + "  | ");
                    upgradeBar_Freelancer.setValue(upgradeLevel_Freelancer);

                    updateUpgradeBar_Freelancer();

                    upgradeCost_Freelancer += 50;
                }
            }
        });


        JButton upgradeButton_Enterprise = new JButton("Empreendimento");
        upgradeButton_Enterprise.setFocusable(false);
        upgradeButton_Enterprise.setBounds(20, 60, 150, 30);
        upgradeButton_Enterprise.setBackground(buttonColor);
        add(upgradeButton_Enterprise);

        upgradeBar_Enterprise = new JProgressBar(0, 10);
        upgradeBar_Enterprise.setValue(upgradeLevel_Enterprise);
        upgradeBar_Enterprise.setBounds(180, 60, 150, 30);
        add(upgradeBar_Enterprise);

        JButton upgradeButton_StockMarket = new JButton("Bolsa de Valores");
        upgradeButton_StockMarket.setFocusable(false);
        upgradeButton_StockMarket.setBounds(20, 100, 150, 30);
        upgradeButton_StockMarket.setBackground(buttonColor);
        add(upgradeButton_StockMarket);

        upgradeBar_StockMarket = new JProgressBar(0, 10);
        upgradeBar_StockMarket.setValue(upgradeLevel_StockMarket);
        upgradeBar_StockMarket.setBounds(180, 100, 150, 30);
        add(upgradeBar_StockMarket);

        JButton upgradeButton_RealEstateMarket = new JButton("Mercado Imobiliario");
        upgradeButton_RealEstateMarket.setFocusable(false);
        upgradeButton_RealEstateMarket.setBounds(20, 140, 150, 30);
        upgradeButton_RealEstateMarket.setBackground(buttonColor);
        add(upgradeButton_RealEstateMarket);

        upgradeBar_RealEstateMarket = new JProgressBar(0, 10);
        upgradeBar_RealEstateMarket.setValue(upgradeLevel_RealEstateMarket);
        upgradeBar_RealEstateMarket.setBounds(180, 140, 150, 30);
        add(upgradeBar_RealEstateMarket);

        JButton upgradeButton_Economy = new JButton("Economizar");
        upgradeButton_Economy.setFocusable(false);
        upgradeButton_Economy.setBounds(20, 180, 150, 30);
        upgradeButton_Economy.setBackground(buttonColor);
        add(upgradeButton_Economy);

        upgradeBar_Economy = new JProgressBar(0, 10);
        upgradeBar_Economy.setValue(upgradeLevel_Economy);
        upgradeBar_Economy.setBounds(180, 180, 150, 30);
        add(upgradeBar_Economy);

        JButton workButton = new JButton("Trabalhar");
        workButton.setFocusable(false);
        workButton.setBounds(615, 20, 150, 30);
        workButton.setBackground(buttonColor);
        workButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentMoney += workIncome;
                currentMoneyLabel.setText("  Dinheiro: R$ " + currentMoney + "  | ");
            }
        });
        add(workButton);

        timeBar_Month = new JProgressBar(0, 31);
        timeBar_Month.setValue(totalDay);
        timeBar_Month.setBounds(615, 60, 150, 20);
        timeBar_Month.setString("Dia " + totalDay + " | Faltam " + (31 - totalDay) + " dias");
        timeBar_Month.setStringPainted(true);
        add(timeBar_Month);

        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
            }
        });

        timer.start();

        setVisible(true);
    }

    private void updateUpgradeBar_Freelancer() {
        if (upgradeLevel_Freelancer >= 10) {
            upgradeBar_Freelancer.setString("Limite atingido");
        } else {
            upgradeBar_Freelancer.setString("Custo: R$ " + upgradeCost_Freelancer + " | Nível: " + upgradeLevel_Freelancer + "/10");
        }
    }

    // Main Method
    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::new);
    }
}
