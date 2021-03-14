import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class PopupWindow {
	
	static JFrame outerFrame;
	
	JPanel divider;

	
	SliderPanel slider;
	
	public PopupWindow() {
	
	}
	
	public void createAndShowGui() {
		float handles[] = {100,110};
		
		slider = new SliderPanel(-1000,1000,500,20, handles); // (min,max,width,height,handleValues[]) 
		slider.setBackground(Color.WHITE);
		
		divider = new JPanel();
		divider.setLayout(new GridLayout(0,2));
		JPanel panel1 = new JPanel();
		divider.add(panel1);
		//JPanel panel2 = new JPanel();
		divider.add(slider);
		
		outerFrame = new JFrame();
		
		outerFrame.add(divider);
		outerFrame.setPreferredSize(new Dimension(windowWidth(), windowHeight()));
		outerFrame.setPreferredSize(new Dimension(windowWidth(), windowHeight()));
		outerFrame.setLocation(positionX(), positionY());
		outerFrame.pack();
		outerFrame.setVisible(true);
	}

	private int positionY() {
		return screenSize().height/4;
	}

	private int positionX() {
		return screenSize().width/8;
	}

	private int windowHeight() {
		return screenSize().height/2;
	}

	private int windowWidth() {
		return screenSize().width-(screenSize().width/4);
	}
	
	private Dimension screenSize() {
		return Toolkit.getDefaultToolkit().getScreenSize();
	}
	

}
