import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.GeneralPath;
import javax.swing.*;

@SuppressWarnings("serial")
public class FirstTask extends JPanel implements ActionListener {
    private static int maxWidth;
    private static int maxHeight;
    Timer timer;
    private double scale = 1;
    private double delta = 0.01;
    private double dx = 1;
    private double tx = 300;
    private double dy = 1;
    private double ty = 200;
    private double edge = 400;
    private double sx = 300;
    private double sy = 200;

    public FirstTask() {
        timer = new Timer(10, this);
        timer.start();
    }

    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.setBackground(new Color(128, 128, 0));
        g2d.clearRect(0, 0, maxWidth, maxHeight);
        // малюємо рамку
        g2d.setColor(Color.ORANGE);
        BasicStroke bs = new BasicStroke(12, BasicStroke.CAP_ROUND,
                BasicStroke.JOIN_BEVEL);
        g2d.setStroke(bs);
        g2d.drawRect(20, 20, 960, 760);

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);

        g2d.translate(tx, ty);
        g2d.scale(scale, scale);

        g2d.setBackground(new Color(139, 0, 0));
        g2d.clearRect(-250, -150, 500, 300);

        g2d.setColor(Color.YELLOW);
        g2d.setStroke(new BasicStroke(10));
        g2d.drawLine(-250, 50, 250, 50);
        g2d.drawLine(-250, -50, 250, -50);
        g2d.drawLine(-100, -50, -100, 50);
        g2d.drawLine(100, -50, 100, 50);
        g2d.drawLine(0, 50, 0, 150);
        g2d.drawLine(0, -50, 0, -150);

        GradientPaint gp = new GradientPaint(0, 0,
                Color.PINK, 50, 50, Color.ORANGE, true);
        g2d.setPaint(gp);

        double points[][] = {
                {-90, -40}, {0, -25}, {90, -40}, {90, 40}, {0, 25}, {-90 , 40}, {-90, -40}
        };
        GeneralPath cRect = new GeneralPath();
        cRect.moveTo(points[0][0], points[0][1]);
        for (int i = 1; i < points.length; i++)
            cRect.lineTo(points[i][0], points[i][1]);
        cRect.closePath();
        g2d.fill(cRect);
    }
    public static void main(String[] args) {
        JFrame frame = new JFrame("First task");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 850);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.add(new FirstTask());
        frame.setVisible(true);

        Dimension size = frame.getSize();
        Insets insets = frame.getInsets();
        maxWidth = size.width - insets.left - insets.right;
        maxHeight = size.height - insets.top - insets.bottom;

    }
    public void actionPerformed(ActionEvent e) {
        if ( scale < 0.1 ) {
            delta = -delta;
        } else if (scale > 0.99) {
            delta = -delta;
        }

        if ( tx <= sx && ty < sy+edge) {
            ty += dy;
        } else if (ty >= sy+edge && tx < sx+edge) {
            tx += dx;
        } else if (ty > sy && tx >= sx+edge){
            ty -= dy;
        } else if (tx > sx && ty >= sy) {
            tx -= dx;
        }

        scale += delta;

        repaint();
    }
}