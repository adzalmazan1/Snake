import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setTitle("Snake");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        
        SnakePanel sPanel = new SnakePanel();
        frame.add(sPanel);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        sPanel.startGameThread();
    }
}
