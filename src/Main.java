import javax.swing.SwingUtilities;

public class Main {
	
	
	
	public static void main(String[] args) {

		PopupWindow window = new PopupWindow();
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				window.createAndShowGui();
       
			}
		});
	}

}
