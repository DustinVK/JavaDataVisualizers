import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class BarGraph extends JPanel{

	int min, max, width, height;
	float values[];
	
	private static final long serialVersionUID = -2369006631657945589L;
	
	public BarGraph(int min, int max, int width, int height, float[] values) {
		this.width = width;
		this.height = height;
		this.min = min;
		this.max = max;
		this.values = values;
	}
	
	@Override
	public void paintComponent(Graphics graphics) {
        paintComponent((Graphics2D) graphics);
        
	}
	
	public void paintComponent(Graphics2D graphics) {
        super.paintComponent(graphics);
       
  
	}

}
