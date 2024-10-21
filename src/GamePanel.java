import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class GamePanel extends JPanel implements MouseListener {

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
    private double baseImagex;
    private int baseImagey;
    private double baseImagex2;
    private int x;
    private int y;
    private int tickCount = 0;

    private int centerx;
    private int centery;
    private double deltaTime;
    private AffineTransform Default = new AffineTransform();
    private int gapSize;
    private int maxPipeHeight;
    private int minPipeHeight;
    private int bottomPipeHeight;
    private int topPipeHeight;
    private double angle = 0;
    boolean mousePressed = false;
    private int[] bottomPipeY;
    private int[] topPipeY;
    private int[] bottomPipeX;
    private int[] topPipeX;
    private boolean displayHitBoxes = false;
    private boolean pipeDeath = false;

    double yVelocity = 0;
    Thread render;
    Thread ticker;

    boolean gameStarted = false;
    boolean gameOver = false;
    int i = 0;
    private int completedPipeIndex = 1;
    private int score = 0;
    File pointAudio;




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
            pointAudio = new File("audio/point.wav");

            double scaleMultiplier = 1.5; // Scaling factor


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
    Image[] yellowBirds = new Image[12];
    Image[] blueBirds = new Image[12];
    Image[] redBirds  =  new Image[12];
    Image[] Birds  =  new Image[12];
    Image[] numbers = new Image[10];
    Random r = new Random();


    GamePanel() {
        initPanel();
        start();
    }

    public void initPanel() {
        yellowBirds[0] = yellowbirddownflapImage;
        yellowBirds[1] = yellowbirddownflapImage;
        yellowBirds[2] = yellowbirddownflapImage;
        yellowBirds[3] = yellowbirddownflapImage;

        yellowBirds[4] =  yellowbirdmidflapImage;
        yellowBirds[5] =  yellowbirdmidflapImage;
        yellowBirds[6] =  yellowbirdmidflapImage;
        yellowBirds[7] =  yellowbirdmidflapImage;

        yellowBirds[8] = yellowbirdupflapImage;
        yellowBirds[9] = yellowbirdupflapImage;
        yellowBirds[10] = yellowbirdupflapImage;
        yellowBirds[11] = yellowbirdupflapImage;

        blueBirds[0] = bluebirddownflapImage;
        blueBirds[1] = bluebirddownflapImage;
        blueBirds[2] = bluebirddownflapImage;
        blueBirds[3] = bluebirddownflapImage;


        blueBirds[4] =  bluebirdmidflapImage;
        blueBirds[5] =  bluebirdmidflapImage;
        blueBirds[6] =  bluebirdmidflapImage;
        blueBirds[7] =  bluebirdmidflapImage;


        blueBirds[8] = bluebirdupflapImage;
        blueBirds[9] = bluebirdupflapImage;
        blueBirds[10] = bluebirdupflapImage;
        blueBirds[11] = bluebirdupflapImage;


        redBirds[0] = redbirddownflapImage;
        redBirds[1] = redbirddownflapImage;
        redBirds[2] = redbirddownflapImage;
        redBirds[3] = redbirddownflapImage;


        redBirds[4] =  redbirdmidflapImage;
        redBirds[5] =  redbirdmidflapImage;
        redBirds[6] =  redbirdmidflapImage;
        redBirds[7] =  redbirdmidflapImage;


        redBirds[8] = redbirdupflapImage;
        redBirds[9] = redbirdupflapImage;
        redBirds[10] = redbirdupflapImage;
        redBirds[11] = redbirdupflapImage;
        numbers[0] = zeroImage;
        numbers[1] = oneImage;
        numbers[2] = twoImage;
        numbers[3] = threeImage;
        numbers[4] = fourImage;
        numbers[5] = fiveImage;
        numbers[6] = sixImage;
        numbers[7] = sevenImage;
        numbers[8] = eightImage;
        numbers[9] = nineImage;




        Birds = yellowBirds;
        width = dayBackGroundBufferedImage.getWidth() * 3 / 2;
        height = dayBackGroundBufferedImage.getHeight() * 3 / 2;
        gapSize = yellowbirddownflapBufferedImage.getHeight() * 3 /2*15/4;
        minPipeHeight = gapSize;
        maxPipeHeight = (height-(baseBufferedImage.getHeight() * 3 /2)) - 2*gapSize;
        topPipeHeight = r.nextInt(minPipeHeight,maxPipeHeight);
        bottomPipeHeight = topPipeHeight + gapSize;
        bottomPipeY = new int[2];
        topPipeY = new int[2];
        bottomPipeX = new int[2];
        topPipeX = new int[2];
        bottomPipeY[0] = bottomPipeHeight;
        topPipeY[0] = topPipeHeight;
        topPipeX[0] = width + greenpipeBufferedImage.getWidth() * 3 / 2;
        bottomPipeX[0] = width;
        topPipeY[1] =  r.nextInt(minPipeHeight,maxPipeHeight);
        bottomPipeY[1] = topPipeY[1] + gapSize;
        topPipeX[1] = width + (greenpipeBufferedImage.getWidth() * 3 / 2)+174 + (greenpipeBufferedImage.getWidth() * 3 / 2);
        bottomPipeX[1] = width+174 + (greenpipeBufferedImage.getWidth() * 3 / 2);

        x = (width / 3) - (yellowbirddownflapBufferedImage.getWidth() * 3 / 2) +(yellowbirdmidflapBufferedImage.getHeight() * 3 / 6) + 6;
        y = (dayBackGroundBufferedImage.getHeight() *3 / 2 /2);
        baseImagex = 0;
        centerx = (yellowbirdmidflapBufferedImage.getWidth() * 3 / 2) / 2 + x;
        centery = (yellowbirdmidflapBufferedImage.getHeight() * 3 / 2) / 2 + y;
        baseImagey = (dayBackGroundBufferedImage.getHeight() * 3 / 2) - (baseBufferedImage.getHeight() * 3 / 2);
        baseImagex2 = baseImagex + (baseBufferedImage.getWidth() * 3 / 2);
        setPreferredSize(new Dimension(width, height));
        setFocusable(true); // Make the panel focusable
        requestFocusInWindow();
        addMouseListener(this);

        setDoubleBuffered(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawBackground(g);

        if(displayHitBoxes)
            drawHitBoxes(g);




        if(!gameStarted)
        {
            drawMessage(g);
        }


        if(gameStarted) {
            drawPipes(g);
            drawScore(g);
        }
        drawBird(g);
        drawBase(g);
        if(gameOver)
        {
            paintGameOverScreen(g);
        }
    }
    private void drawHitBoxes(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;
        g2.setTransform(Default);
        g2.drawLine(x,y,x+yellowbirddownflapBufferedImage.getWidth()*3/2,y);
        g2.drawLine(x,y+yellowbirddownflapBufferedImage.getHeight()*3/2,x+yellowbirddownflapBufferedImage.getWidth()*3/2,y+yellowbirddownflapBufferedImage.getHeight()*3/2);
        g2.drawLine(x,y,x,y+yellowbirddownflapBufferedImage.getHeight()*3/2);
        g2.drawLine(x+yellowbirddownflapBufferedImage.getWidth()*3/2,y,x+yellowbirddownflapBufferedImage.getWidth()*3/2,y+yellowbirddownflapBufferedImage.getHeight()*3/2);
    }

    private void drawBird(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setTransform(rotateBird());
        g2.drawImage(Birds[i], x, y, null);



    }




    private void drawScore(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setTransform(Default);

        // Convert the score to a char array
        char[] s = String.valueOf(score).toCharArray();

        // Width of each digit image after scaling by 3/2
        int digitImageWidth = zeroBufferedImage.getWidth() * 3 / 2;

        // Calculate the total width of the score images
        int totalWidth = 0;
        for(char ch: s)
        {
            if(ch != '1')
                totalWidth += digitImageWidth - 3;
            else
                totalWidth += digitImageWidth / 2 + 3;

        }

        // Calculate the initial position to center the score images
        int ImagePosition = width / 2 - totalWidth / 2;

        // Draw each digit's image
        for (char c : s) {
            g2.drawImage(numbers[c - '0'], ImagePosition, 30, null);
            if(c - '0' != 1)
                ImagePosition += digitImageWidth -3;
            else
                ImagePosition += digitImageWidth / 2 + 3;// Move to the next position, avoiding overlap
        }
    }














        public AffineTransform rotateBird()
    {
        AffineTransform transform = new AffineTransform();
        transform.rotate(Math.toRadians(angle), centerx, centery);
        return transform;
    }
    public AffineTransform rotatePipe(int a,int i)
    {
        AffineTransform transform = new AffineTransform();
        transform.rotate(Math.toRadians(a), topPipeX[i], topPipeY[i]);
        return transform;
    }
    private void drawPipes(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;
        g2.setTransform(Default);
        g2.drawImage(greenpipeImage,bottomPipeX[0], bottomPipeY[0],null);
        g2.setTransform(rotatePipe(180,0));
        g2.drawImage(greenpipeImage,topPipeX[0], topPipeY[0],null);
        g2.setTransform(Default);
        g2.drawImage(greenpipeImage,bottomPipeX[1], bottomPipeY[1],null);
        g2.setTransform(rotatePipe(180,1));
        g2.drawImage(greenpipeImage,topPipeX[1], topPipeY[1],null);

    }




    private void drawMessage(Graphics g) {
        g.drawImage(messageImage, (width - (messageBufferedImage.getWidth() * 3 / 2)) / 2 + 1, dayBackGroundBufferedImage.getHeight() * 1 / 4 - (yellowbirdmidflapBufferedImage.getHeight() * 3 / 2) + 3, null);
    }

    private void drawBackground(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setTransform(Default);
        g2.drawImage(dayBackGroundImage, 0, 0, null);
    }
    private void drawBase(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;
        g2.setTransform(Default);
        g2.drawImage(baseImage, (int) baseImagex, baseImagey, null);
        g2.drawImage(baseImage, (int) baseImagex2, baseImagey, null);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(!mousePressed && !gameOver) {
            gameStarted = true;
                playAudio("audio/wing.wav");
                angle = -25;
                yVelocity = -8;
                mousePressed = true;

        }


    }
    public static void playAudio(String location)
    {
        try{
            File musicPath = new File(location);
            if(musicPath.exists())
            {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInput);
                clip.start();

            }
            else
                System.out.println("File not Found");
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        mousePressed = false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
    public static boolean isOverlap(int x, int y, int a, int b) {
        return Math.max(x, y) >= Math.min(a, b) && Math.min(x, y) <= Math.max(a, b);
    }


    public void start() {
        render = new Thread(new Render());
        ticker = new Thread(new Ticker());
        render.start();
        ticker.start();
    }


    private void update() {
        System.out.println(angle);

        if(gameStarted)
        {
            CollisionAndScoreDetection();
        }
        int speed = 3;
        // Speed of the base movement
        if(pipeDeath)
        {
            yVelocity += 0.5;

            y += yVelocity;
            if(yVelocity >= 8) {
                angle += 5.625;
                i = 4;
            }
            else{
                i++;
                i = i % 12;
            }
            if(angle > 90)
                angle = 90;
            if(yVelocity > 10)
                yVelocity = 10;
            centerx = (yellowbirdmidflapBufferedImage.getWidth() * 3 / 2) / 2 + x;
            centery = (yellowbirdmidflapBufferedImage.getHeight() * 3 / 2) / 2 + y;
        }
        if(!gameOver) {
            baseImagex -= speed;
            baseImagex2 -= speed;

            if(!gameStarted) {
                tickCount = tickCount % 2;
                if (tickCount % 2 == 0) {  // Increment only every other tick
                    i++;
                    i = i % 12;
                    if(i < 4)
                        y+=2;
                    if(i >7)
                        y-=2;
                }



            }
            if (gameStarted) {
                for(int j = 0; j < 2;j++)
                {
                    bottomPipeX[j] -= speed;
                    topPipeX[j] -= speed;
                    if(bottomPipeX[j] + greenpipeBufferedImage.getWidth() * 3 /2 <= 0)
                    {
                        topPipeY[j] =  r.nextInt(minPipeHeight,maxPipeHeight);
                        bottomPipeY[j] = topPipeY[j] + gapSize;
                        topPipeX[j] = width + greenpipeBufferedImage.getWidth() * 3 / 2;
                        bottomPipeX[j] = width;
                    }

                }
                yVelocity += 0.5;

                y += yVelocity;
                if(yVelocity >= 8) {
                    angle += 5.625;
                    i = 4;
                }
                else{
                    i++;
                    i = i % 12;
                }
                if(angle > 90)
                    angle = 90;
                if(yVelocity > 10)
                    yVelocity = 10;



                centerx = (yellowbirdmidflapBufferedImage.getWidth() * 3 / 2) / 2 + x;
                centery = (yellowbirdmidflapBufferedImage.getHeight() * 3 / 2) / 2 + y;
            }




            // Wrap around logic
            if (baseImagex + baseBufferedImage.getWidth() * 3 / 2 <= 0) {
                baseImagex = baseImagex2 + baseBufferedImage.getWidth() * 3 / 2;
            }
            if (baseImagex2 + baseBufferedImage.getWidth() * 3 / 2 <= 0) {
                baseImagex2 = baseImagex + baseBufferedImage.getWidth() * 3 / 2;
            }
        }

    }
    private void CollisionAndScoreDetection()
    {
        if(isOverlap(y,y + (yellowbirdmidflapBufferedImage.getHeight()) * 3 /2,baseImagey,baseImagey))
        {

            y = baseImagey - (yellowbirdmidflapBufferedImage.getHeight() * 3 / 2);
            if(!gameOver)
                playAudio("audio/hit.wav");
            gameOver = true;
        }
       for(int i =0; i < 2;i++) {
           // Pipe Collision
            if (isOverlap(x,x+yellowbirddownflapBufferedImage.getWidth()*3/2,bottomPipeX[i],bottomPipeX[i] + greenpipeBufferedImage.getWidth() * 3/2)) {
                if (isOverlap(y, y + yellowbirddownflapBufferedImage.getHeight() * 3 / 2, bottomPipeY[i], bottomPipeY[i] + greenpipeBufferedImage.getHeight() * 3 / 2))
                {
                    if(!gameOver) {
                        playAudio("audio/hit.wav");
                        playAudio("audio/die.wav");
                    }
                    gameOver = true;
                    pipeDeath = true;

                } else if (isOverlap(y, y + yellowbirddownflapBufferedImage.getHeight() * 3 / 2, topPipeY[i], topPipeY[i] - greenpipeBufferedImage.getHeight() * 3 / 2))
                {
                    if(!gameOver) {
                        playAudio("audio/hit.wav");
                        playAudio("audio/die.wav");
                    }
                    gameOver = true;
                    pipeDeath = true;

                }
                else if(i != completedPipeIndex && x >= bottomPipeX[i])
                {
                    score++;
                    playAudio("audio/point.wav");
                    completedPipeIndex = i;
                }

            }
        }


    }
    private void paintGameOverScreen(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;
        g2.setTransform(Default);
        g2.drawImage(gameoverImage, (width - (gameoverBufferedImage.getWidth() * 3/ 2))/2, height / 3 - gameoverBufferedImage.getHeight() * 3 /2,null);

    }


    private class Ticker implements Runnable {

        @Override
        public void run() {
            tick();
        }

        public synchronized void tick() {

            double updateRate = 1.0d / 60.0d;
            double accumulator = 0;
            double lastFrameTime = 0.0;
            while (true) {

                double time = Time.getTime();
                deltaTime = time - lastFrameTime;
                accumulator += deltaTime;
                lastFrameTime = time;
                while (accumulator > updateRate) {
                    tickCount++;
                    update();
                    accumulator -= updateRate;
                }

            }
        }
    }

    private class Render implements Runnable {
        double fpsCap = 100;
        int fps = 0;

        @Override
        public void run() {
            render();
        }

        public synchronized void render() {
            double lastFrameTimeF = Time.getTime(); // Initialize lastFrameTimeF
            double wait = 1000.0 / fpsCap; // Wait time in milliseconds
            double fpsCountDown = 0;
            fps = 0;

            while (true) {
                double frameStartTimeF = Time.getTime(); // Capture frame start time

                repaint(); // Perform your rendering here

                double frameEndTimeF = Time.getTime(); // Capture frame end time
                double frameTime = (frameEndTimeF - frameStartTimeF) * 1000; // Frame time in milliseconds

                // Calculate how long to sleep
                long sleepTime = (long) (Math.max(0, wait - frameTime)); // Remaining time in milliseconds

                if (sleepTime > 0) {
                    try {
                        long sleepStart = System.nanoTime(); // Get the start time of sleep
                        Thread.sleep((sleepTime)); // Sleep for the remaining time
                        long sleepEnd = System.nanoTime(); // Get the end time of sleep
                        long actualSleepTime = (sleepEnd - sleepStart) / 1_000_000; // Actual sleep time in milliseconds

                        // Adjust sleep time for any discrepancy
                        long adjustment = sleepTime - actualSleepTime;


                       if (adjustment > 0) {
                            // Sleep for the remaining nanoseconds if necessary
                            Thread.sleep(adjustment,0 );
                        }
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }

                double postSleepTimeF = Time.getTime(); // Capture time after sleep
                double actualFrameTime = (postSleepTimeF - frameStartTimeF) * 1000; // Total frame time in milliseconds

                // Calculate the FPS count down
                fpsCountDown += (postSleepTimeF - frameStartTimeF);
                fps += 1;

                if (fpsCountDown >= 1.0) {
                    //System.out.println(fps); // Print FPS
                    fps = 0;
                    fpsCountDown -= 1.0;
                }
                lastFrameTimeF = postSleepTimeF; // Update lastFrameTimeF
            }
        }
    }
}
