import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Random;
import java.util.ArrayList;

import javax.swing.JPanel;

public class GamePanel extends JPanel {
    class Tile {
        protected int x;
        protected int y;
        protected int width;
        protected int height;

        public Tile(int x, int y, int width, int height) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
        }
    }

    private SnakePanel sPanel;
    private EventHandler eventH = new EventHandler();
    
    private Tile snakeHead;

    private Tile food;
    private Random random;
    private int randomX;
    private int randomY;
    
    private ArrayList<Tile> snakeBody = new ArrayList<Tile>();

    public GamePanel(SnakePanel sPanel) {
        this.sPanel = sPanel;
        this.addKeyListener(eventH);
        this.setFocusable(true);
        this.snakeHead = new Tile(sPanel.tileSize * 2, sPanel.tileSize * 4, sPanel.tileSize, sPanel.tileSize);
        this.food = new Tile(sPanel.tileSize * 10, sPanel.tileSize * 12, sPanel.tileSize, sPanel.tileSize);
        this.random = new Random();
    }
    
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;
        draw(g2D);
    }

    public void draw(Graphics2D g2D) {
        // background tiles
        Boolean isDarker;
        for(int i = 0; i < sPanel.rows; i++) {
            if(i % 2 == 0) {
                isDarker = true;
            }
            else {
                isDarker = false;
            }
            for(int j = 0; j < sPanel.cols; j++) {
                if(isDarker) {
                    if(j % 2 == 0) {
                        g2D.setColor(new Color(170, 225, 87));
                        g2D.fillRect(j * sPanel.tileSize, i * sPanel.tileSize, sPanel.tileSize, sPanel.tileSize);
                    }
                    else {
                        g2D.setColor(new Color(170, 240, 87));
                        g2D.fillRect(j * sPanel.tileSize, i * sPanel.tileSize, sPanel.tileSize, sPanel.tileSize);
                    }
                }
                else {
                    if(j % 2 == 0) {
                        g2D.setColor(new Color(170, 240, 87));
                        g2D.fillRect(j * sPanel.tileSize, i * sPanel.tileSize, sPanel.tileSize, sPanel.tileSize);
                    }
                    else {
                        g2D.setColor(new Color(170, 225, 87));
                        g2D.fillRect(j * sPanel.tileSize, i * sPanel.tileSize, sPanel.tileSize, sPanel.tileSize);
                    }
                }
            }
        }

        // custom snake head
        g2D.setColor(new Color(75, 122, 242));
        g2D.fillRect(snakeHead.x, snakeHead.y, snakeHead.width, snakeHead.height);

        for(Tile i : snakeBody) {
            g2D.fillRect(snakeHead.x - sPanel.tileSize, snakeHead.y - sPanel.tileSize, i.width, i.height);
        }

        // draw food
        g2D.setColor(Color.RED);
        g2D.fillRect(food.x, food.y, food.width, food.height);
    }

    public void update() {
        // update code here
        if(eventH.up || eventH.down) {
            snakeHead.y += eventH.velocityY;
        }
        
        if(eventH.left || eventH.right) {
            snakeHead.x += eventH.velocityX;
        }

        for(Tile i : snakeBody) {
            i.x = snakeHead.x;
            i.y = snakeHead.y;
        }

        if(detectCollission(snakeHead, food)) {
            randomX = random.nextInt(sPanel.cols) * sPanel.tileSize; // grid-aligned
            randomY = random.nextInt(sPanel.rows) * sPanel.tileSize;

            food.x = randomX;
            food.y = randomY;

            addSnakeBody();
        }
    }

    public void addSnakeBody() {
        snakeBody.add(new Tile(10, 10, sPanel.tileSize, sPanel.tileSize));
    }

    // collission detection formula
    public boolean detectCollission(Tile a, Tile b) {
        return 
        a.x < b.x + b.width &&  
        a.x + a.width > b.x &&
        a.y < b.y + b.height && 
        a.y + a.height > b.y;
    }
}
