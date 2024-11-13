import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Dinheirama");

        JButton test_button = new JButton("Teste");
        test_button.setFocusable(false);
        test_button.setBounds(10, 10, 100, 30);

        frame.setSize(800, 500);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.add(test_button);

        frame.setVisible(true);
    }
}
