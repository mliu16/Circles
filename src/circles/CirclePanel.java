package circles;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Arc2D;
import java.awt.geom.CubicCurve2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

public class CirclePanel extends JPanel implements ActionListener {

    //Create instances
    public static final Color BG_COLOR = new Color(180, 224, 248);
    public static double MAX_RADIUS = 0.25;
    public static int LIMIT = 50000;
    public static double MIN_RADIUS = 0.0005;

    private double nearness = 0.0;
    private int phase = 0;
    private double bankAngle = 0.0;
    private double climb = 0.0;

    //Constructor
    public CirclePanel() {
        this.setBackground(BG_COLOR);
    } // CirclePanel()

    //paint component
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;

        int w = this.getWidth();
        int h = this.getHeight();

        AffineTransform makeCloser = new AffineTransform();
        makeCloser.setToScale(nearness, nearness);

        AffineTransform scale = new AffineTransform();
        scale.setToScale(w / 2, h / 2);

        AffineTransform translate = new AffineTransform();
        translate.setToTranslation(w / 2, h / 2);

        AffineTransform bank = new AffineTransform();
        bank.setToRotation(bankAngle);

        AffineTransform rise = new AffineTransform();
        rise.setToTranslation(0, climb);

        AffineTransform transform = new AffineTransform();
        transform.concatenate(translate);
        transform.concatenate(scale);

        //Create array to hold circles
        List<Circle> shapes = new ArrayList<>();

        double currentRadius = MAX_RADIUS;
        while (currentRadius > MIN_RADIUS) {

            //Try for LIMIT times
            int count = 0;
            
            while (count <= LIMIT) {
                boolean intersect = false;
                
                //Random coordinates
                double x0 = Math.random() * 2 - 1;
                double y0 = Math.random() * 2 - 1;
                
                //Random color
                int red = (int) (1 + Math.random() * 254);
                int green = (int) (1 + Math.random() * 254);
                int blue = (int) (1 + Math.random() * 254);
                Color color = new Color(red, green, blue);

                //Draw circles
                Circle c = new Circle(x0, y0, currentRadius, Color.RED);
 
                for (Circle shape : shapes) {
                    if (c.intersects(shape)) {
                        intersect = true;
                        break;
                    } //if
                } //for
                
                if (intersect) {
                    count++;
                } //if
                else {
                    shapes.add(c);
                    c.draw(g2D, transform);
                } //else

            } // while   
            currentRadius = currentRadius - 0.01;
        } //while
    } // paintComponent( Graphics )
    

    private Ellipse2D ellipse(double cx, double cy,
            double width, double height) {
        // Compute coordinates of upper-left corner of bounding box.
        double ulx = cx - width / 2;
        double uly = cy - height / 2;
        return new Ellipse2D.Double(ulx, uly, width, height);
    } // ellipse( double, double, double, double )

    private Arc2D arc(double cx, double cy, double width, double height,
            double startAngle, double angularSpan) {
        // Compute coordinates of upper-left corner of bounding box.
        double ulx = cx - width / 2;
        double uly = cy - height / 2;

        return new Arc2D.Double(ulx, uly, width, height,
                startAngle, angularSpan, Arc2D.PIE);
    } // arc( double, double, double, double, double, double, int )

    @Override
    public void actionPerformed(ActionEvent e) {
        this.nearness += 0.02;

        if (2.0 < this.nearness && this.nearness <= 4.0) {
            this.bankAngle += 0.01;
            this.climb += -0.01;
        } // if
        else if (this.nearness > 4.0) {
            this.nearness = 0.0;
            this.bankAngle = 0.0;
            this.climb = 0.0;
        } // if

        this.phase += 8;
        this.phase = this.phase % 360;
        this.repaint();
    } // actionPerformed( ActionEvent )
} // Spitfire
