import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Dimension;

public class SnakePanel extends JPanel implements Runnable {
    protected int tileSize = 25;

    protected int rows = 20;
    protected int cols = 20;

    private int panelWidth = tileSize * rows;
    private int panelHeight = tileSize * cols;

    private StatusPanel statPanel;
    private GamePanel gamePanel;
    private Thread gameThread;

    public SnakePanel() {
        this.setPreferredSize(new Dimension(panelWidth, panelHeight));
        this.setLayout(new BorderLayout());

        statPanel = new StatusPanel();
        gamePanel = new GamePanel(this);
        
        this.add(statPanel, BorderLayout.NORTH);
        this.add(gamePanel, BorderLayout.CENTER);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    // threadJob
    @Override
    public void run() {
        while(gameThread != null) {
            gamePanel.update();
            gamePanel.repaint();
            try {
                Thread.sleep(16);
            }
            catch(InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
