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

        if(detectCollission(snakeHead, food)) {
            // System.out.println("Added to snakeBody");
            snakeBody.add(new Tile(food.x, food.y, sPanel.tileSize, sPanel.tileSize));
            displaceFood();
        }

        // to draw snakeBody
        for(int i = 0; i < snakeBody.size(); i++) {
            Tile snakePart = snakeBody.get(i);
            g2D.fillRect(snakePart.x, snakePart.y,snakePart.height, snakePart.height);
        }

        // draw food
        g2D.setColor(Color.RED);
        g2D.fillRect(food.x, food.y, food.width, food.height);
    }

    public void update() {
        // snakeHead movement
        if(eventH.up || eventH.down) {
            snakeHead.y += eventH.velocityY;
        }
        
        if(eventH.left || eventH.right) {
            snakeHead.x += eventH.velocityX;
        }

        /* 
        for(int i = snakeBody.size() - 1; i >= 0; i--) {
            Tile snakePart = snakeBody.get(i);
            if(i == 0) {
                snakePart.x = snakeHead.x;
                snakePart.y = snakeHead.y;
            }
            else {
                Tile prevSnakePart = snakeBody.get(i - 1);
                snakePart.x = prevSnakePart.x;
                snakePart.y = prevSnakePart.y;
            }
        }
        */
    }

    public void displaceFood() {
        randomX = random.nextInt(sPanel.cols) * sPanel.tileSize;
        randomY = random.nextInt(sPanel.rows) * sPanel.tileSize;

        food.x = randomX;
        food.y = randomY;
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
