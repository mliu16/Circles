package circles;

import java.awt.Container;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;


public class CircleFrame extends JFrame {

    public static final int FRAME_WIDTH = 768;
    public static final int FRAME_HEIGHT = 768;
    public static final String TITLE = "Circles";
    
    public CircleFrame() {
        this.setSize( FRAME_WIDTH, FRAME_HEIGHT );
        this.setTitle( TITLE );
        this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE);
        
        Container pane = this.getContentPane();
        CirclePanel panel = new CirclePanel();   
        pane.add( panel );
        
        this.setVisible( true  );
        
    } // CircleFrame()
    
    public static void main(String[] args) {
        CircleFrame circle = new CircleFrame();
    } // main( String [] )
    
} // CircleFrame