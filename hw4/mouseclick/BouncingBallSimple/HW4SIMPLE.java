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


public class HW4SIMPLE{
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame myJFrame = new JFrame("JavaProgramming HW4 405410100 黃晉威 Bouncing Balls 簡單版");
                myJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                myJFrame.setContentPane(new BouncingBalls(1080, 720));
                myJFrame.pack();
                myJFrame.setVisible(true);
            }
        });
    }
}
