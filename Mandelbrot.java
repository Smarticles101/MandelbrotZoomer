import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseMotionAdapter;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import java.awt.Color;
 
/**
 * TODO:
 * 		Implement panning, and zooming out.
 * 		Custom color schemes
 * 		Julia set compatibility?
 */

public class Mandelbrot extends JFrame {
    private final int MAX_ITER = 1000;
    private double zoom = 150;
    private BufferedImage I;
    private double zx, zy, cX, cY, tmp;
    private double xMove = 0, yMove = 0;
	Color[] colors = {
                                new Color(66, 30, 15),
                                new Color(25, 7, 26),
                                new Color(9, 1, 47),
                                new Color(4, 4, 73),
                                new Color(0, 7, 100),
                                new Color(12, 44, 138),
                                new Color(24, 84, 177),
                                new Color(57, 125, 209),
                                new Color(134, 181, 229),
                                new Color(211, 236, 248),
                                new Color(241, 233, 191),
                                new Color(248, 201, 95),
                                new Color(255, 170, 0),
                                new Color(204, 128, 0),
                                new Color(153, 87, 0),
                                new Color(106, 52, 3)
                        };

    
    public Mandelbrot() {
        super("Mandelbrot Set");
        setBounds(0, 0, 1000, 800);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        I = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
        mandel();
        
        addMouseListener(new MouseAdapter() {
        	public void mousePressed(MouseEvent e) {
        		zoom+=zoom;
        		xMove = ((e.getX()-getWidth()/2)/zoom)+xMove;
        		yMove = ((e.getY()-getHeight()/2)/zoom)+yMove;
        		mandel();
        		repaint();
        	}
        });
        
        
    }
    
    public void mandel() {
        for (int y = 0; y < getHeight(); y++) {
            for (int x = 0; x < getWidth(); x++) {
                zx = zy = 0;
                cX = ((x - getWidth()/2) / zoom)+xMove;
                cY = ((y - getHeight()/2) / zoom)+yMove;
                int iter = 0;
                while (zx * zx + zy * zy < 4 && iter < MAX_ITER) {
                    tmp = zx * zx - zy * zy + cX;
                    zy = 2.0 * zx * zy + cY;
                    zx = tmp;
                    iter++;
                }
		iter--;
		I.setRGB(x, y, colors[iter%colors.length].getRGB());
		}
        }
    }
 
    @Override
    public void paint(Graphics g) {
        g.drawImage(I, 0, 0, this);
    }
 
    public static void main(String[] args) {
        new Mandelbrot().setVisible(true);
    }
}
