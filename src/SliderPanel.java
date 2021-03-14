import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.font.TextAttribute;
import java.util.Collections;

import javax.swing.JPanel;

/**
 * 
 */

public class SliderPanel extends JPanel {
	private static final long serialVersionUID = -4524960359698300988L; //// unique identifier for serializable class.
	

	final static Color red = new Color(234,10,10,131);
	final static Color yellow = new Color(234,144,10,131);
	final static Color green = new Color(41,234,10,131);
	final static Color grey = new Color(162,162,162);
	final static Color shadow = new Color(100,100,100,85);
	
	final static Color handleColor1 = new Color(0,111,255);
	final static Color handleColor2 = new Color(23,0,87);
	Color handleColors[] = {handleColor1, handleColor2};


	
	final static int yBuffer = 20;
	final static int xBuffer = 15;
	
	int min, max, width, height;
	float handles[];
	
	
	
	public SliderPanel(int min, int max, int width, int height, float[] handles) {
		this.width = width;
		this.height = height;
		this.min = min;
		this.max = max;
		this.handles = handles;
	}
	
	private int nextColor(int colorIndex) {
		if(colorIndex >= handleColors.length) {
			return colorIndex % handleColors.length;
		}
		else {
			return colorIndex;
		}
		
	}
	
	private int scaleWeight() {
		return height/4;
	}
	
	private int lightScaleWeight() {
		return scaleWeight() /2;
	}
	
	private int divisionLength(int divider) {
		return width / divider;
	}
	
	private int getSpace(int space,int divider) {
		return xBuffer + (divisionLength(divider) * (space - 1));
	}
	
	private int centerScalePos() {
		return height/2-scaleWeight()/2;
	}
	
	private int midPoint(int start, int end) {
		return (start + end)/2;
	}
	
	private int length() {
		return max-min;
	}
	
	private float scale() {
		return 1.0f*width/ length() ;
	}
	
	private int handleLocation(float handle) {
		return (int) ((handle-min) * scale())+xBuffer-5;
	}

	@Override
	public void paintComponent(Graphics graphics) {
        paintComponent((Graphics2D) graphics);
        
	}
	
	public void paintComponent(Graphics2D graphics) {
        super.paintComponent(graphics);
        //drawSliderShadow(graphics);

        drawRegions(graphics);
        drawScale(graphics);

  
        drawHashMarks(graphics);
        drawScaleLabels(graphics);
        drawHandles(graphics);
        //drawPins(graphics);
  
	}

	private void drawHandles(Graphics2D graphics) {
		
    	graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		for(int i = 0;i<handles.length;i++) {
			graphics.setColor(shadow);
	        graphics.fillRoundRect(handleLocation(handles[i])+1, 2, 10, height+(2*yBuffer), 10, 10);
			graphics.setColor(handleColors[nextColor(i)]);
	        graphics.fillRoundRect(handleLocation(handles[i]), 0, 10, height+(2*yBuffer), 10, 10);
		}
		
		
	}
	
	private void drawPins(Graphics2D graphics) {
    	graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    	
    	for(int i=0; i<handles.length;i++) {
    		if(i%2 == 0) {
    			graphics.setColor(shadow);
    	        graphics.fillRect(handleLocation(handles[i])+1, scaleWeight()*-3, 3, height+yBuffer+5);
    			graphics.setColor(handleColors[nextColor(i)]);
    	        graphics.fillRect(handleLocation(handles[i]), scaleWeight()*-3, 3, height+yBuffer+5);
    	        graphics.fillOval(handleLocation(handles[i])-6, 0, 16, 16);
    		}
    		else {
	    		graphics.setColor(shadow);
	            graphics.fillRect(handleLocation(handles[i])+1, scaleWeight()*6, 3, height);
	    		graphics.setColor(handleColors[nextColor(i)]);
	            graphics.fillRect(handleLocation(handles[i]), scaleWeight()*6, 3, height);
	            graphics.fillOval(handleLocation(handles[i])-6, yBuffer+height+6, 16, 16);
    		}
    	}
//		graphics.setColor(shadow);
//        graphics.fillRect(handleLocation(handles[1])+1, 6, 3, height+yBuffer+5);
//		graphics.setColor(handleColor2);
//        graphics.fillRect(handleLocation(handles[1]), 5, 3, height+yBuffer+5);
//        graphics.fillOval(handleLocation(handles[1])-6, yBuffer+height+6, 16, 16);
//       // graphics.fillOval(max, yBuffer, width, height);//
//        
//		graphics.setColor(shadow);
//        graphics.fillRect(handleLocation(handles[0])+1, 6, 3, height+yBuffer+5);
//		graphics.setColor(handleColor1);
//        graphics.fillRect(handleLocation(handles[0]), 5, 3, height+yBuffer+5);
//        graphics.fillOval(handleLocation(handles[0])-6, 0, 16, 16);
//       // graphics.fillOval(max, yBuffer, width, height);//
		
	}
        

	private void drawScaleLabels(Graphics2D graphics) {
		graphics.setColor(grey);
		graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Font font = new Font("Segoe UI", Font.PLAIN, 14);
        
        font = font.deriveFont(
        	    Collections.singletonMap(
        	        TextAttribute.WEIGHT, TextAttribute.WEIGHT_LIGHT));
        
        graphics.setFont(font);
        graphics.drawString(String.valueOf(min), xBuffer/2, 2*yBuffer+height*2);
        graphics.drawString(String.valueOf(max), width, 2*yBuffer+height*2);
        graphics.drawString(String.valueOf(midPoint(min,max)), 5+(xBuffer/2+width)/2, 2*yBuffer+height*2);
	}

	private void drawRegions(Graphics graphics) {
		graphics.setColor(red);
        graphics.fillRect(getSpace(1,5), yBuffer, divisionLength(5), height);
        
        graphics.setColor(yellow);
        graphics.fillRect(getSpace(2,5),yBuffer, divisionLength(5), height);
        
        graphics.setColor(green);
        graphics.fillRect(getSpace(3,5),yBuffer, divisionLength(5), height);
        
        graphics.setColor(yellow);
        graphics.fillRect(getSpace(4,5), yBuffer, divisionLength(5), height);
        
        graphics.setColor(red);
        graphics.fillRect(getSpace(5,5),yBuffer, divisionLength(5), height);
	}

	private void drawScale(Graphics graphics) {
		graphics.setColor(grey);
        graphics.fillRect(xBuffer,centerScalePos()+yBuffer, width, scaleWeight());
        
      
	}
	
	private void drawSliderShadow(Graphics graphics) {
		graphics.setColor(Color.LIGHT_GRAY);
        graphics.fillRect(xBuffer,yBuffer+(height/2), width, height/2);
        
      
	}
	

	private void drawHashMarks(Graphics graphics) {
		graphics.setColor(grey);
        graphics.fillRect(xBuffer,yBuffer-(yBuffer/2), lightScaleWeight(), height+yBuffer);
        
        graphics.setColor(grey);
        graphics.fillRect(xBuffer+(width-lightScaleWeight()),yBuffer-(yBuffer/2), lightScaleWeight(), height+yBuffer);
        
        graphics.setColor(grey);
        graphics.fillRect(xBuffer+(midPoint(0, width)),yBuffer-(yBuffer/2), lightScaleWeight(), height+yBuffer);
        
        graphics.setColor(grey);
        graphics.fillRect(xBuffer+(midPoint(0, midPoint(0, width))),yBuffer-(yBuffer/2), lightScaleWeight(), height+yBuffer);
        
        graphics.setColor(grey);
        graphics.fillRect(xBuffer+(midPoint(midPoint(0, width),width-lightScaleWeight())),yBuffer-(yBuffer/2), lightScaleWeight(), height+yBuffer);
	}


	
}
