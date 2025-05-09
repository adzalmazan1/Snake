import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class EventHandler implements KeyListener {
    protected boolean up, down, left, right;
    protected int velocityX, velocityY;
    protected int speed = 1;
    
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        switch (code) {
            // up
            case 87:
                up = true;
                velocityY = -speed;
                velocityX = 0;
                break;
            // down
            case 83:
                down = true;
                velocityY = speed;
                velocityX = 0;
                break;
            // left
            case 65:
                left = true;
                velocityX = -speed;
                velocityY = 0;
                break;
            // right
            case 68:
                right = true;
                velocityX = speed;
                velocityY = 0;
                break;
            default:
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
