package circles;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;


public class Circle implements Colorable {
    private double x;
    private double y;
    private double radius;
    private Color color;
    
    public Circle( double x, double y, double radius ) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.color = Color.BLACK;
    } // Circle( double, double, double )

    @Override
    public Color getColor() {
        return this.color;
    } // getColor()
    
    @Override
    public void setColor(Color color) {
        this.color = color;
    } // setColor( Color )
    
    public void draw( Graphics2D g2D, AffineTransform transform ) {
        double d = 2 * this.radius;
        double ulx = x - this.radius;
        double uly = y - this.radius;
        Ellipse2D ellipse = new Ellipse2D.Double( ulx, uly, d, d );
        Shape shape = transform.createTransformedShape(ellipse);
        g2D.draw( shape );
    } // draw( Graphics2D, AffineTransform )
} // Circle