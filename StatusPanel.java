import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class StatusPanel extends JPanel {
    private JLabel scoreStat;

    public StatusPanel() {
        this.setLayout(new BorderLayout());
        this.setBackground(Color.BLACK);

        JLabel desc = new JLabel("Play Snake", JLabel.CENTER);
        desc.setFont(new Font("Cambria", Font.BOLD, 25));
        desc.setForeground(Color.WHITE);

        scoreStat = new JLabel("Current Score: 0", JLabel.CENTER);
        scoreStat.setFont(new Font("Cambria", Font.BOLD, 15));
        scoreStat.setForeground(Color.WHITE);

        this.add(desc, BorderLayout.NORTH);
        this.add(scoreStat, BorderLayout.SOUTH);
    }
}
