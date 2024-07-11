import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {
   public GameFrame()
    {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(new GamePanel());
        pack();
        setTitle("Flappy Bird");
        setLocationRelativeTo(null);
        //setResizable(false);
        setBackground(Color.black);
        setVisible(true);
    }

}
