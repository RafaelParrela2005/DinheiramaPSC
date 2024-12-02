import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

public class Main extends JFrame {

    //Numeric variables
    private int currentMoney = 0;
    private int monthlyEarnings = 0;
    private int monthlyExpenses = 0;
    private int totalYear = 0;
    private int totalMonth = 1;
    private int totalDay = 1;

    //income variables
    private int workIncome = 10;
    private int enterpriseIncome = 10;
    
    private int realEstateMarketIncome = 10;


    //upgrading variables
    private int upgradeCost_Work = 100;
    private int upgradeLevel_Work = 0;

    private int upgradeCost_Enterprise = 200;
    private int upgradeLevel_Enterprise = 0;

    

    private int upgradeCost_RealEstateMarket = 400;
    private int upgradeLevel_RealEstateMarket = 0;

    private int upgradeCost_Economy = 500;
    private int upgradeLevel_Economy = 0;

    private JLabel currentMoneyLabel;
    private JLabel yearLabel;
    private JLabel monthLabel;
    private JLabel dayLabel;

    private JProgressBar upgradeBar_Work;
    private JProgressBar upgradeBar_Enterprise;
    
    private JProgressBar upgradeBar_RealEstateMarket;
    private JProgressBar upgradeBar_Economy;
    private JProgressBar timeBar_Month;
    private int currentInvestment = 0;


    private JMenuBar menuBar;

    
    
    
    
    
    public Main() throws ClassNotFoundException, SQLException {
        //JFrame Config
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

        JButton upgradeButton_Work = new JButton("Hora Extra");
        upgradeButton_Work.setFocusable(false);
        upgradeButton_Work.setBounds(20, 20, 150, 30);
        upgradeButton_Work.setBackground(buttonColor);
        upgradeButton_Work.setToolTipText("+ 10R$ ao clicar em Trabalho. No nível máximo, +100R$.");
        add(upgradeButton_Work);

        upgradeBar_Work = new JProgressBar(0, 10);
        upgradeBar_Work.setValue(upgradeLevel_Work);
        upgradeBar_Work.setStringPainted(true);
        upgradeBar_Work.setBounds(180, 20, 150, 30);
        add(upgradeBar_Work);
        updateUpgradeBar(upgradeBar_Work, upgradeLevel_Work, upgradeCost_Work, 10, upgradeButton_Work);

        upgradeButton_Work.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (upgradeLevel_Work < 10 && currentMoney >= upgradeCost_Work) {
                    currentMoney -= upgradeCost_Work;
                    monthlyExpenses += upgradeCost_Work;
                    upgradeLevel_Work++;

                    switch (upgradeLevel_Work) {
                        case 0: workIncome = 5; break;
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

                    currentMoneyLabel.setText("  Dinheiro: R$ " + currentMoney + "  | ");
                    upgradeBar_Work.setValue(upgradeLevel_Work);

                    upgradeCost_Work += 50;
                    updateUpgradeBar(upgradeBar_Work, upgradeLevel_Work, upgradeCost_Work, 10, upgradeButton_Work);
                }
            }
        });

        JButton upgradeButton_Enterprise = new JButton("Empreendimento");
        upgradeButton_Enterprise.setFocusable(false);
        upgradeButton_Enterprise.setBounds(20, 60, 150, 30);
        upgradeButton_Enterprise.setBackground(buttonColor);
        upgradeButton_Enterprise.setToolTipText("Investir num negócio gera dinheiro passivo ao passar os meses. No nível máximo, +100R$ por mês.");
        add(upgradeButton_Enterprise);

        upgradeBar_Enterprise = new JProgressBar(0, 10);
        upgradeBar_Enterprise.setValue(upgradeLevel_Enterprise);
        upgradeBar_Enterprise.setStringPainted(true);
        upgradeBar_Enterprise.setBounds(180, 60, 150, 30);
        add(upgradeBar_Enterprise);
        updateUpgradeBar(upgradeBar_Enterprise, upgradeLevel_Enterprise, upgradeCost_Enterprise, 10, upgradeButton_Enterprise);

        upgradeButton_Enterprise.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (upgradeLevel_Enterprise < 10 && currentMoney >= upgradeCost_Enterprise) {
                    currentMoney -= upgradeCost_Enterprise;
                    monthlyExpenses += upgradeCost_Enterprise;
                    upgradeLevel_Enterprise++;

                    switch (upgradeLevel_Enterprise) {
                        case 0: enterpriseIncome = 5; break;
                        case 1: enterpriseIncome = 10; break;
                        case 2: enterpriseIncome = 20; break;
                        case 3: enterpriseIncome = 30; break;
                        case 4: enterpriseIncome = 40; break;
                        case 5: enterpriseIncome = 50; break;
                        case 6: enterpriseIncome = 60; break;
                        case 7: enterpriseIncome = 70; break;
                        case 8: enterpriseIncome = 80; break;
                        case 9: enterpriseIncome = 90; break;
                        case 10: enterpriseIncome = 100; break;
                    }

                    currentMoneyLabel.setText("  Dinheiro: R$ " + currentMoney + "  | ");
                    upgradeBar_Enterprise.setValue(upgradeLevel_Enterprise);

                    upgradeCost_Enterprise += 50;
                    updateUpgradeBar(upgradeBar_Enterprise, upgradeLevel_Enterprise, upgradeCost_Enterprise, 10, upgradeButton_Enterprise);

                }
            }
        });

        JButton upgradeButton_StockMarket = new JButton("Bolsa de Valores");
        upgradeButton_StockMarket.setFocusable(false);
        upgradeButton_StockMarket.setBounds(20, 100, 150, 30);
        upgradeButton_StockMarket.setBackground(buttonColor);
        upgradeButton_StockMarket.setToolTipText("Clique para aumentar o valor a ser investido em múltiplos de 100.");
        add(upgradeButton_StockMarket);

        upgradeButton_StockMarket.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentMoney >= currentInvestment + 100) {
                    currentInvestment += 100;
                    currentMoney -= 100; // Desconta do saldo
                    currentMoneyLabel.setText("  Dinheiro: R$ " + currentMoney + "  | ");
                    JOptionPane.showMessageDialog(null, "Você aumentou o valor a ser investido para R$" + currentInvestment + ".");
                } else {
                    JOptionPane.showMessageDialog(null, "Você não tem saldo suficiente para aumentar o investimento.");
                }
            }
        });
        JButton upgradeButton_RealEstateMarket = new JButton("Mercado Imobiliario");
        upgradeButton_RealEstateMarket.setFocusable(false);
        upgradeButton_RealEstateMarket.setBounds(20, 140, 150, 30);
        upgradeButton_RealEstateMarket.setBackground(buttonColor);
        upgradeButton_RealEstateMarket.setToolTipText("Investir no mercado imobiliario gera dinheiro passivo ao passar os meses. No nível máximo, +100R$ por mês.");
        add(upgradeButton_RealEstateMarket);

        upgradeBar_RealEstateMarket = new JProgressBar(0, 10);
        upgradeBar_RealEstateMarket.setValue(upgradeLevel_RealEstateMarket);
        upgradeBar_RealEstateMarket.setStringPainted(true);
        upgradeBar_RealEstateMarket.setBounds(180, 140, 150, 30);
        add(upgradeBar_RealEstateMarket);
        updateUpgradeBar(upgradeBar_RealEstateMarket, upgradeLevel_RealEstateMarket, upgradeCost_RealEstateMarket, 10, upgradeButton_RealEstateMarket);

        upgradeButton_RealEstateMarket.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (upgradeLevel_RealEstateMarket < 10 && currentMoney >= upgradeCost_RealEstateMarket) {
                    currentMoney -= upgradeCost_RealEstateMarket;
                    monthlyExpenses += upgradeCost_RealEstateMarket;
                    upgradeLevel_RealEstateMarket++;

                    switch (upgradeLevel_RealEstateMarket) {
                        case 0: realEstateMarketIncome = 5; break;
                        case 1: realEstateMarketIncome = 10; break;
                        case 2: realEstateMarketIncome = 20; break;
                        case 3: realEstateMarketIncome = 30; break;
                        case 4: realEstateMarketIncome = 40; break;
                        case 5: realEstateMarketIncome = 50; break;
                        case 6: realEstateMarketIncome = 60; break;
                        case 7: realEstateMarketIncome = 70; break;
                        case 8: realEstateMarketIncome = 80; break;
                        case 9: realEstateMarketIncome = 90; break;
                        case 10: realEstateMarketIncome = 100; break;
                    }

                    currentMoneyLabel.setText("  Dinheiro: R$ " + currentMoney + "  | ");
                    upgradeBar_RealEstateMarket.setValue(upgradeLevel_RealEstateMarket);

                    upgradeCost_RealEstateMarket += 50;
                    updateUpgradeBar(upgradeBar_RealEstateMarket, upgradeLevel_RealEstateMarket, upgradeCost_RealEstateMarket, 10, 
                    		upgradeButton_RealEstateMarket);
                }
            }
        });

        JButton upgradeButton_Economy = new JButton("Economizar");
        upgradeButton_Economy.setFocusable(false);
        upgradeButton_Economy.setBounds(20, 180, 150, 30);
        upgradeButton_Economy.setBackground(buttonColor);
        upgradeButton_Economy.setToolTipText("Reduz seus custos mensais. No nível máximo, reduz 75% dos custos.");
        add(upgradeButton_Economy);

        upgradeBar_Economy = new JProgressBar(0, 10);
        upgradeBar_Economy.setValue(upgradeLevel_Economy);
        upgradeBar_Economy.setStringPainted(true);
        upgradeBar_Economy.setBounds(180, 180, 150, 30);
        add(upgradeBar_Economy);
        updateUpgradeBar(upgradeBar_Economy, upgradeLevel_Economy, upgradeCost_Economy, 10, upgradeButton_Economy);
      //INSERT No Banco de Dados
        //Sql Query
        String insertQuery = "insert into player(saldo_atual) values(?)";
        String upsertQuery = "INSERT INTO player (saldo_atual) VALUES (?) ON DUPLICATE KEY UPDATE saldo_atual = ?";
        
      //Registrando o Driver
      	Class.forName("com.mysql.cj.jdbc.Driver");
      //Fazendo a Conexao
      		
      Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dinheirama","root","123456");
      PreparedStatement pstmt = con.prepareStatement(insertQuery);
      PreparedStatement pstmt2 = con.prepareStatement(upsertQuery);
      System.out.println("Conexão feita com sucesso");
        
       //Gerar um Id 
        
        
        upgradeButton_Economy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (upgradeLevel_Economy < 10 && currentMoney >= upgradeCost_Economy) {
                    currentMoney -= upgradeCost_Economy;
                    monthlyExpenses += upgradeCost_Economy;
                    upgradeLevel_Economy++;

                    currentMoneyLabel.setText("  Dinheiro: R$ " + currentMoney + "  | ");
                    upgradeBar_Economy.setValue(upgradeLevel_Economy);

                    upgradeCost_Economy += 50;
                    updateUpgradeBar(upgradeBar_Economy, upgradeLevel_Economy, upgradeCost_Economy, 10, upgradeButton_Economy);
                }
            }
        });

        JButton workButton = new JButton("Trabalhar");
        workButton.setFocusable(false);
        workButton.setBounds(615, 20, 150, 30);
        workButton.setBackground(buttonColor);
        workButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentMoney += workIncome;
                monthlyEarnings += workIncome;
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

        // Ingame Timer Method
        Timer timer = new Timer(1000, new ActionListener() {
            private int totalDiscount = 500;

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

                    if (totalMonth % 6 == 1) {
                        totalDiscount += 500;
                    }
                    currentMoney -= totalDiscount;
                    monthlyExpenses += totalDiscount;

                    if (upgradeLevel_Enterprise >= 1) {
                        currentMoney += enterpriseIncome;
                        monthlyEarnings += enterpriseIncome;
                    }

                    if (upgradeLevel_RealEstateMarket >= 1) {
                        currentMoney += realEstateMarketIncome;
                        monthlyEarnings += realEstateMarketIncome;
                    }

                    currentMoneyLabel.setText("  Dinheiro: R$ " + currentMoney + "  | ");

                    if (totalMonth > 12) {
                        totalMonth = 1;
                        totalYear++;
                    }

                    int netProfit = monthlyEarnings - monthlyExpenses;
                    if (netProfit >= 0) {
                        JOptionPane.showMessageDialog(null, "Neste mês, você lucrou R$" + netProfit + ". O total de dinheiro que você tem é R$" + 
                    currentMoney + ".");
                    } else {
                        JOptionPane.showMessageDialog(null, "Neste mês, você perdeu R$" + Math.abs(netProfit) + ". O total de dinheiro que você tem é R$" +
                    currentMoney + ".");
                    }
                    
                    monthlyEarnings = 0;
                    monthlyExpenses = 0;
                    
                    if (currentInvestment > 0) {
                        Random random = new Random();
                        double multiplier = 0.8 + (1.2 - 0.8) * random.nextDouble(); // Gera o multiplicador aleatório
                        int profit = (int) (currentInvestment * multiplier); // Calcula o lucro
                        currentMoney += profit; // Adiciona o lucro ao saldo
                        JOptionPane.showMessageDialog(null, "O investimento de R$" + currentInvestment +
                                " resultou em um retorno de R$" + profit + " (Multiplicador: " + String.format("%.2f", multiplier) + ").");
                        currentInvestment = 0; // Reseta o valor do investimento
                        currentMoneyLabel.setText("  Dinheiro: R$ " + currentMoney + "  | ");
                    }
                    try {
                    	
                   	 String insertQuery = "INSERT INTO players (mes, ano, saldo_atual) VALUES (?, ?, ?)";
                        PreparedStatement pstmt = con.prepareStatement(insertQuery);
                        pstmt.setInt(1, totalMonth); // Define o mês
                        pstmt.setInt(2, totalYear); // Define o ano
                        pstmt.setDouble(3, currentMoney); // Define o saldo atual

                        pstmt.executeUpdate(); // Executa a inserção
                        System.out.println("Registro adicionado ao banco de dados: Mês " + totalMonth + ", Ano " + totalYear + ", Saldo: R$ " + currentMoney);
   				} catch (SQLException e1) {
   					// TODO Auto-generated catch block
   					e1.printStackTrace();
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

    // Upgrade Method
    private void updateUpgradeBar(JProgressBar upgradeBar, int upgradeLevel, int upgradeCost, int maxLevel, JButton button) {
        if (upgradeLevel >= maxLevel) {
            upgradeBar.setString("Limite atingido");
            button.setEnabled(false);
        } else {
            upgradeBar.setString("Custo: R$ " + upgradeCost + " | Nível: " + upgradeLevel + "/" + maxLevel);
        }
    }


    // Main Method
    public static void main(String[] args)  {
        SwingUtilities.invokeLater(() -> {
			try {
				new Main();
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
        
    }
}