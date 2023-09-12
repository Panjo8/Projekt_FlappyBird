import javax.swing.JFrame;

public class FlappyFrame extends JFrame{
	

	public FlappyFrame() {
		
		add(new FlappyPanel());
		
		setSize(500,600);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		new FlappyFrame();
	}
}