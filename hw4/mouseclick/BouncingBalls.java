//  https://codereview.stackexchange.com/questions/31434/bouncing-ball-program
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class BouncingBalls extends JPanel implements MouseListener {

    //  最多產生二十顆球，雖然可以多顆，但是spec有說最多就二十顆。
    protected ArrayList<Ball> balls = new ArrayList<Ball>(20);
    private Container myContainer;
    private DrawCanvas myCanvas;
    private int canvasWidth;
    private int canvasHeight;
    public static final int UPDATE_RATE = 30;
    private static final long serialVersionUID = 11111111;
    int BallCount = 0;

    public static int GetRandomInteger(int maxRange) {
        //  隨機產生一個0~maxRange的整數，直接回傳就好。
        return (int) Math.round((Math.random() * maxRange));
    }

    //  Default Constructor，傳入視窗的大小。
    public BouncingBalls(int width, int height) {

        canvasWidth = width;
        canvasHeight = height;

        myContainer = new Container();
        myCanvas = new DrawCanvas();
        //  監聽滑鼠點擊事件，點擊滑鼠才會開始放球。
        this.addMouseListener(this);

        this.setLayout(new BorderLayout());
        this.add(myCanvas, BorderLayout.CENTER);

        start();
    }

    public void start() {

        //  每一顆球都是自己獨立的thread
        Thread newThread = new Thread() {
            public void run() {
                //  每顆球就是一直在更新自己的位置，我用一個無窮迴圈去做。
                for(;;) {
                    //  更新球的位置。
                    updateEachBallPosition();
                    //  更新介面。
                    repaint();
                    try {
                        //  畫面更新率，UPDATE_RATE越高，畫面的更新速度就會越快
                        //  球跑的速度同時也會變快，是因為這邊thread.sleep代表
                        //  畫面停滯多久，而update_rate越大，畫面就會停滯比較短
                        //  的時間，所以更新率就會越快。
                        Thread.sleep(1000 / UPDATE_RATE);
                    }
                    catch (InterruptedException e) {
                        System.out.println("Start() Error");
                    }
                }
            }
        };
        newThread.start();
    }

    public void updateEachBallPosition() {
        //  更新每一顆球的位置。
        for (Ball temp : balls) {
            temp.refreshAllBalls(myContainer);
        }
    }

    class DrawCanvas extends JPanel {
        private static final long serialVersionUID = 11111111;

        public void paintComponent(Graphics myGraphics) {

            super.paintComponent(myGraphics);
            myContainer.draw(myGraphics);
            for (Ball temp : balls) {
                temp.draw(myGraphics);
            }
        }

        public Dimension getPreferredSize() {

            return (new Dimension(canvasWidth, canvasHeight));
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {

        if(BallCount < 20){
            //  滑鼠點擊，就增加球的數量，然後新增一顆球。
            System.out.printf("Ball Count = %d\n", ++BallCount);
            balls.add(new Ball());
        }
        else{
            System.out.println("Ball Count Limit 20 exceeded");
        }
    }

    public static class Ball {

        //  每一顆球自己的屬性，每當新增一顆球，隨機產生他的初始位置和速度。
        int speedX = GetRandomInteger(30);    //  每顆球在X方向上前進的速度
        int speedY = GetRandomInteger(30);    //  每顆球在Y方向上前進的速度
        int R = GetRandomInteger(255);        //  每一顆球的RGB中的R大小。
        int G = GetRandomInteger(255);        //  每一顆球的RGB中的G大小。
        int B = GetRandomInteger(255);        //  每一顆球的RGB中的B大小。
        int xPosition = GetRandomInteger(480);//  每顆球初始的X位置
        int yPostion = GetRandomInteger(480); //  每顆球初始的Y位置
        int RADIUS = GetRandomInteger(30)+10; //  每顆球的半徑，我後面給他加上10，因為我怕產生太小顆的球，很難觀察。


        public int GetRandomInteger(int maxRange) {

            return (int) Math.round(Math.random() * maxRange);
        }

        //  畫球
        public void draw(Graphics myGraphics) {

            myGraphics.setColor(new Color(R, G, B));
            myGraphics.fillOval(xPosition-RADIUS, yPostion-RADIUS, 2*RADIUS, 2*RADIUS);

        }

        public void refreshAllBalls(Container myContainer) {

            //  更新該球x, y的位置。
            xPosition += speedX;
            yPostion += speedY;

            //  在水平方向上撞到牆壁，按照物理原理，水平方向等速反向繼續移動。
            if (xPosition - RADIUS < 0) {

                speedX = -speedX;
                xPosition = RADIUS;
            }
            else if (xPosition + RADIUS > 1080) {

                speedX = -speedX;
                xPosition = 1080 - RADIUS;//  更新X位置，要同時減掉半徑大小，不然球會跑到框外面。
            }

            //  在垂直方向上撞到牆壁，按照物理原理，垂直方向等速反向繼續移動。
            if (yPostion - RADIUS < 0) {

                speedY = -speedY;
                yPostion = RADIUS;
            }
            else if (yPostion + RADIUS > 720) {

                speedY = -speedY;
                yPostion = 720 - RADIUS;//  更新Y位置，要同時減掉半徑大小，不然球會跑到框外面。
            }
        }
    }

    public static class Container {

        final int HEIGHT = 1080;
        final int WIDTH = 720;
        final Color BackgroundColor = Color.BLACK;

        //  製作背景畫面。
        public void draw(Graphics myGraphics) {
            myGraphics.setColor(BackgroundColor);
            //  背景就給方的。
            myGraphics.fillRect(0, 0, HEIGHT, WIDTH);
        }
    }

    public static void main(String[] args) {

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame myJFrame = new JFrame("JavaProgramming HW4 405410100 黃晉威 Bouncing Balls");
                myJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                myJFrame.setContentPane(new BouncingBalls(1080, 720));
                myJFrame.pack();
                myJFrame.setVisible(true);
            }
        });
    }

}
