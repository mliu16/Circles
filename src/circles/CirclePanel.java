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
import javafx.scene.shape.Circle;
import javax.swing.JPanel;

public class CirclePanel extends JPanel implements ActionListener {

    public static final Color BG_COLOR = new Color(180, 224, 248);
    private double nearness = 0.0;
    private int phase = 0;
    private double bankAngle = 0.0;
    private double climb = 0.0;

    public CirclePanel() {
        this.setBackground(BG_COLOR);
    } // CirclePanel()

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;

        int w = this.getWidth();
        int h = this.getHeight();
        
//        double x0 = 0.0;
//        double y0 = 2 * h/3;
//        double x1 = w/3;
//        double y1 = 2 * h/3 + h/5;
//        double x2 = 2 * w/3;
//        double y2 = 2 * h/3 - h/7;
//        double x3 = w;
//        double y3 = 2 * h/3;
//        
//        CubicCurve2D curve = new CubicCurve2D.Double( x0, y0, x1, y1,
//                x2, y2, x3, y3 );
//        
//        GeneralPath path = new GeneralPath();
//        path.moveTo( 0, h);
//        path.lineTo( 0, 2 * h/3);
//        path.append(curve, true);
//        path.lineTo(w, h);
//        path.closePath();
//        
//        g2D.setColor( new Color( 160, 192, 172 ));
//        g2D.fill( path );
        
        AffineTransform makeCloser = new AffineTransform();
        makeCloser.setToScale(nearness, nearness);

        AffineTransform scale = new AffineTransform();
        scale.setToScale(w / 2, h / 2);

        AffineTransform translate = new AffineTransform();
        translate.setToTranslation(w / 2, h / 2);
        
        AffineTransform bank = new AffineTransform();
        bank.setToRotation( bankAngle );
        
        AffineTransform rise = new AffineTransform();
        rise.setToTranslation( 0, climb);

        AffineTransform transform = new AffineTransform();
        transform.concatenate(translate);
        transform.concatenate(scale);
//        transform.concatenate(makeCloser);
//        transform.concatenate(bank);
//        transform.concatenate(rise);
        
//        Circle c = new Circle( 0.0, 0.0, 0.5 );
//        c.draw( g2D, transform);
        
        Ellipse2D circle = new Ellipse2D.Double( -0.5, -0.5, 1.0, 1.0 );

//        Ellipse2D fuselage = ellipse(0, 0, 0.20, 0.32);
//        Ellipse2D canopy = ellipse(0, -0.14, 0.08, 0.12);
//        Ellipse2D wing = ellipse(0, 0.12, 1.6, 0.06);
//        Ellipse2D rudder = ellipse(0, -0.14, 0.03, 0.60);
//        Ellipse2D stabilizer = ellipse(0, -0.06, 0.8, 0.04);
//
        List<Shape> shapes = new ArrayList<>();
//        shapes.add(fuselage);
//        shapes.add(canopy);
//        shapes.add(wing);
//        shapes.add(stabilizer);
//        shapes.add(rudder);
        shapes.add( circle );

//        for (int i = 0; i < 4; i++) {
//            Arc2D blade = arc(0, 0, 0.8, 0.8, phase + 45 + i * 90, 12);
//            shapes.add(blade);
//        } // for

        g2D.setColor(new Color(64, 56, 48));
        for (Shape s : shapes) {
            Shape shape = transform.createTransformedShape(s);
            g2D.setColor( Color.RED );
            g2D.fill(shape);
            g2D.setColor( Color.BLUE );
            Stroke stroke = new BasicStroke( 8.0F );
            g2D.setStroke( stroke );
            g2D.draw( shape );
        } // for

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
        
        if( 2.0 < this.nearness && this.nearness <= 4.0 ) {
            this.bankAngle += 0.01;
            this.climb += -0.01;
        } // if
        else if( this.nearness > 4.0 ) {
            this.nearness = 0.0;
            this.bankAngle = 0.0;
            this.climb = 0.0;
        } // if
        
        this.phase += 8;
        this.phase = this.phase % 360;
        this.repaint();
    } // actionPerformed( ActionEvent )
} // Spitfire
