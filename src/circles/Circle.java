package circles;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;


public class Circle{    
    
    private static final Color FILL_COLOR = new Color( 248, 224, 206 );
    private static final Color EDGE_COLOR = new Color( 112, 144, 180 );
    private static final Stroke stroke = new BasicStroke( 8.0F );
   
    //Create instance
    private final double x;
    private final double y;
    private final double radius;
    private Color color;
    
    //Create constructor
    public Circle( double x, double y, double radius, Color color ) {
        this.x = x;
        this.y = y;
        this.radius = radius;        
        this.color = color;
    } // Circle( double, double, double )

    //getColor
    public Color getColor() {
        return this.color;
    } // getColor()
    
    //setColor
    public void setColor(Color color) {
        this.color = color;
    } // setColor( Color )
    
    //Boolean method: is circles intersets
    public boolean intersects (Circle other) {
        double xDiff = this.x - other.x;
        double yDiff = this.y - other.y;
        double d = xDiff * xDiff + yDiff * yDiff;
        double sumOfRadii = this.radius + other.radius;
        return Math.sqrt(d) < sumOfRadii;            
    } // intersects
    
    //draw circle
    public void draw( Graphics2D g2D, AffineTransform transform ) {
        Stroke previousStroke = g2D.getStroke();
        Color previousColor = g2D.getColor();
        
        double d = 2 * this.radius;
        double ulx = x - this.radius;
        double uly = y - this.radius;
        Ellipse2D ellipse = new Ellipse2D.Double( ulx, uly,  d, d );
        Shape shape = transform.createTransformedShape(ellipse);
        g2D.draw( shape ); 
        
        g2D.setStroke( stroke );
        g2D.setColor( FILL_COLOR );
        g2D.fill( shape );
        g2D.setColor( EDGE_COLOR );
        g2D.draw( shape );
        
        g2D.setColor( previousColor );
        g2D.setStroke( previousStroke );
    } // draw( Graphics2D, AffineTransform )
} // Circle