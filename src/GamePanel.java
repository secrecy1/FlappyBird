import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GamePanel extends JPanel implements MouseListener,Runnable {

    BufferedImage zeroBufferedImage;
    BufferedImage oneBufferedImage;
    BufferedImage twoBufferedImage;
    BufferedImage threeBufferedImage;
    BufferedImage fourBufferedImage;
    BufferedImage fiveBufferedImage;
    BufferedImage sixBufferedImage;
    BufferedImage sevenBufferedImage;
    BufferedImage eightBufferedImage;
    BufferedImage nineBufferedImage;
    BufferedImage dayBackGroundBufferedImage;
    BufferedImage nightBackGroundBufferedImage;
    BufferedImage baseBufferedImage;
    BufferedImage bluebirddownflapBufferedImage;
    BufferedImage bluebirdmidflapBufferedImage;
    BufferedImage bluebirdupflapBufferedImage;
    BufferedImage gameoverBufferedImage;
    BufferedImage messageBufferedImage;
    BufferedImage greenpipeBufferedImage;
    BufferedImage redpipeBufferedImage;
    BufferedImage redbirddownflapBufferedImage;
    BufferedImage redbirdmidflapBufferedImage;
    BufferedImage redbirdupflapBufferedImage;
    BufferedImage yellowbirddownflapBufferedImage;
    BufferedImage yellowbirdmidflapBufferedImage;
    BufferedImage yellowbirdupflapBufferedImage;

   private int width;
   private int height;
   private int baseImagex;
   private int baseImagey;
   private int baseImagex2;
   private int x;
   private int y;
   private int centerx;
   private int centery;

    int yVelocity = 10;

    boolean gameStarted = false;
    private Thread gameThread;
    {
        try {

            zeroBufferedImage = ImageIO.read(new File("Sprites/0.png"));
            oneBufferedImage = ImageIO.read(new File("Sprites/1.png"));
            twoBufferedImage = ImageIO.read(new File("Sprites/2.png"));
            threeBufferedImage = ImageIO.read(new File("Sprites/3.png"));
            fourBufferedImage = ImageIO.read(new File("Sprites/4.png"));
            fiveBufferedImage = ImageIO.read(new File("Sprites/5.png"));
            sixBufferedImage = ImageIO.read(new File("Sprites/6.png"));
            sevenBufferedImage = ImageIO.read(new File("Sprites/7.png"));
            eightBufferedImage = ImageIO.read(new File("Sprites/8.png"));
            nineBufferedImage = ImageIO.read(new File("Sprites/9.png"));
            dayBackGroundBufferedImage = ImageIO.read(new File("Sprites/background-day.png"));
            nightBackGroundBufferedImage = ImageIO.read(new File("Sprites/background-night.png"));
            baseBufferedImage = ImageIO.read(new File("Sprites/base.png"));
            bluebirddownflapBufferedImage = ImageIO.read(new File("Sprites/bluebird-downflap.png"));
            bluebirdmidflapBufferedImage = ImageIO.read(new File("Sprites/bluebird-midflap.png"));
            bluebirdupflapBufferedImage = ImageIO.read(new File("Sprites/bluebird-upflap.png"));
            gameoverBufferedImage = ImageIO.read(new File("Sprites/gameover.png"));
            messageBufferedImage = ImageIO.read(new File("Sprites/message.png"));
            greenpipeBufferedImage = ImageIO.read(new File("Sprites/pipe-green.png"));
            redpipeBufferedImage = ImageIO.read(new File("Sprites/pipe-red.png"));
            redbirddownflapBufferedImage = ImageIO.read(new File("Sprites/redbird-downflap.png"));
            redbirdmidflapBufferedImage = ImageIO.read(new File("Sprites/redbird-midflap.png"));
            redbirdupflapBufferedImage = ImageIO.read(new File("Sprites/redbird-upflap.png"));
            yellowbirddownflapBufferedImage = ImageIO.read(new File("Sprites/yellowbird-downflap.png"));
            yellowbirdmidflapBufferedImage = ImageIO.read(new File("Sprites/yellowbird-midflap.png"));
            yellowbirdupflapBufferedImage = ImageIO.read(new File("Sprites/yellowbird-upflap.png"));


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    Image zeroImage = zeroBufferedImage.getScaledInstance(zeroBufferedImage.getWidth() * 3 / 2, zeroBufferedImage.getHeight() * 3 / 2, Image.SCALE_DEFAULT);
    Image oneImage = oneBufferedImage.getScaledInstance(oneBufferedImage.getWidth() * 3 / 2, oneBufferedImage.getHeight() * 3 / 2, Image.SCALE_DEFAULT);
    Image twoImage = twoBufferedImage.getScaledInstance(twoBufferedImage.getWidth() * 3 / 2, twoBufferedImage.getHeight() * 3 / 2, Image.SCALE_DEFAULT);
    Image threeImage = threeBufferedImage.getScaledInstance(threeBufferedImage.getWidth() * 3 / 2, threeBufferedImage.getHeight() * 3 / 2, Image.SCALE_DEFAULT);
    Image fourImage = fourBufferedImage.getScaledInstance(fourBufferedImage.getWidth() * 3 / 2, fourBufferedImage.getHeight() * 3 / 2, Image.SCALE_DEFAULT);
    Image fiveImage = fiveBufferedImage.getScaledInstance(fiveBufferedImage.getWidth() * 3 / 2, fiveBufferedImage.getHeight() * 3 / 2, Image.SCALE_DEFAULT);
    Image sixImage = sixBufferedImage.getScaledInstance(sixBufferedImage.getWidth() * 3 / 2, sixBufferedImage.getHeight() * 3 / 2, Image.SCALE_DEFAULT);
    Image sevenImage = sevenBufferedImage.getScaledInstance(sevenBufferedImage.getWidth() * 3 / 2, sevenBufferedImage.getHeight() * 3 / 2, Image.SCALE_DEFAULT);
    Image eightImage = eightBufferedImage.getScaledInstance(eightBufferedImage.getWidth() * 3 / 2, eightBufferedImage.getHeight() * 3 / 2, Image.SCALE_DEFAULT);
    Image nineImage = nineBufferedImage.getScaledInstance(nineBufferedImage.getWidth() * 3 / 2, nineBufferedImage.getHeight() * 3 / 2, Image.SCALE_DEFAULT);
    Image dayBackGroundImage = dayBackGroundBufferedImage.getScaledInstance(dayBackGroundBufferedImage.getWidth() * 3 / 2, dayBackGroundBufferedImage.getHeight() * 3 / 2, Image.SCALE_DEFAULT);
    Image nightBackGroundImage = nightBackGroundBufferedImage.getScaledInstance(nightBackGroundBufferedImage.getWidth() * 3 / 2, nightBackGroundBufferedImage.getHeight() * 3 / 2, Image.SCALE_DEFAULT);
    Image baseImage = baseBufferedImage.getScaledInstance(baseBufferedImage.getWidth() * 3 / 2, baseBufferedImage.getHeight() * 3 / 2, Image.SCALE_DEFAULT);
    Image bluebirddownflapImage = bluebirddownflapBufferedImage.getScaledInstance(bluebirddownflapBufferedImage.getWidth() * 3 / 2, bluebirddownflapBufferedImage.getHeight() * 3 / 2, Image.SCALE_DEFAULT);
    Image bluebirdmidflapImage = bluebirdmidflapBufferedImage.getScaledInstance(bluebirdmidflapBufferedImage.getWidth() * 3 / 2, bluebirdmidflapBufferedImage.getHeight() * 3 / 2, Image.SCALE_DEFAULT);
    Image bluebirdupflapImage = bluebirdupflapBufferedImage.getScaledInstance(bluebirdupflapBufferedImage.getWidth() * 3 / 2, bluebirdupflapBufferedImage.getHeight() * 3 / 2, Image.SCALE_DEFAULT);
    Image gameoverImage = gameoverBufferedImage.getScaledInstance(gameoverBufferedImage.getWidth() * 3 / 2, gameoverBufferedImage.getHeight() * 3 / 2, Image.SCALE_DEFAULT);
    Image messageImage = messageBufferedImage.getScaledInstance(messageBufferedImage.getWidth() * 3 / 2, messageBufferedImage.getHeight() * 3 / 2, Image.SCALE_DEFAULT);
    Image greenpipeImage = greenpipeBufferedImage.getScaledInstance(greenpipeBufferedImage.getWidth() * 3 / 2, greenpipeBufferedImage.getHeight() * 3 / 2, Image.SCALE_DEFAULT);
    Image redpipeImage = redpipeBufferedImage.getScaledInstance(redpipeBufferedImage.getWidth() * 3 / 2, redpipeBufferedImage.getHeight() * 3 / 2, Image.SCALE_DEFAULT);
    Image redbirddownflapImage = redbirddownflapBufferedImage.getScaledInstance(redbirddownflapBufferedImage.getWidth() * 3 / 2, redbirddownflapBufferedImage.getHeight() * 3 / 2, Image.SCALE_DEFAULT);
    Image redbirdmidflapImage = redbirdmidflapBufferedImage.getScaledInstance(redbirdmidflapBufferedImage.getWidth() * 3 / 2, redbirdmidflapBufferedImage.getHeight() * 3 / 2, Image.SCALE_DEFAULT);
    Image redbirdupflapImage = redbirdupflapBufferedImage.getScaledInstance(redbirdupflapBufferedImage.getWidth() * 3 / 2, redbirdupflapBufferedImage.getHeight() * 3 / 2, Image.SCALE_DEFAULT);
    Image yellowbirddownflapImage = yellowbirddownflapBufferedImage.getScaledInstance(yellowbirddownflapBufferedImage.getWidth() * 3 / 2, yellowbirddownflapBufferedImage.getHeight() * 3 / 2, Image.SCALE_DEFAULT);
    Image yellowbirdmidflapImage = yellowbirdmidflapBufferedImage.getScaledInstance(yellowbirdmidflapBufferedImage.getWidth() * 3 / 2, yellowbirdmidflapBufferedImage.getHeight() * 3 / 2, Image.SCALE_DEFAULT);
    Image yellowbirdupflapImage = yellowbirdupflapBufferedImage.getScaledInstance(yellowbirdupflapBufferedImage.getWidth() * 3 / 2, yellowbirdupflapBufferedImage.getHeight() * 3 / 2, Image.SCALE_DEFAULT);






    GamePanel()
    {
        initPanel();
    }
    public void initPanel()
    {
        width = dayBackGroundBufferedImage.getWidth() * 3/2;
        height = dayBackGroundBufferedImage.getHeight() * 3/2;
        x = (width / 2) - (yellowbirddownflapBufferedImage.getWidth() * 3 / 2) / 2;
        y = height / 2;
        baseImagex = 0;
        centerx = (yellowbirdmidflapBufferedImage.getWidth() * 3 /2) /2;
        centery = (yellowbirdmidflapBufferedImage.getHeight() * 3 /2) /2;
        baseImagey = (dayBackGroundBufferedImage.getHeight() * 3 / 2) - (baseBufferedImage.getHeight() * 3 /2);
        baseImagex2 = baseImagex+(baseBufferedImage.getWidth() * 3 /2);
        setPreferredSize(new Dimension(width,height));
        setFocusable(true); // Make the panel focusable
        requestFocusInWindow();
        addMouseListener(this);
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
       drawBackground(g);
        if(!gameStarted)
        {
            drawMessage(g);
        }
        if(gameStarted)
        {
            drawBird(g);

        }
    }
    private void drawBird(Graphics g)
    {
        g.drawImage(yellowbirddownflapImage, x, y, null);
    }
    private void drawMessage(Graphics g)
    {
        g.drawImage(messageImage, (width - (messageBufferedImage.getWidth() * 3 / 2)) / 2, (messageBufferedImage.getHeight() * 3 / 2) / 3 - 2, null);
    }
    private void drawBackground(Graphics g)
    {
        g.drawImage(dayBackGroundImage,0,0,null);
        g.drawImage(baseImage,baseImagex,baseImagey,null);
        g.drawImage(baseImage,baseImagex2,baseImagey,null);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("Mouse pressed");
        gameStarted = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void run() {
        while(gameThread != null) {
            baseImagex -=4;
            baseImagex2 = baseImagex+(baseBufferedImage.getWidth() * 3 /2);

            if(baseImagex2+(baseBufferedImage.getWidth() * 3 /2)<=width)
               baseImagex = baseImagex2;
            repaint();
           // if(gameStarted)
           //     y += yVelocity;
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
