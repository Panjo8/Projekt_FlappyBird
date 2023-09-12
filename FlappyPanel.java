import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


//Alen Pandžić & Elias Podbregar
public class FlappyPanel extends JPanel implements KeyListener, ActionListener {

	final int SIRINA = 525, VISINA = 550;
	final int ZIDVELOCITY = 5;
	final int ZIDSIRINA = 65;
	int flappyVisina = VISINA/4;
	int flappyV = 0;
	int flappyA = 9;
	int flappyI = 1;
	int[] zid = {SIRINA, SIRINA + SIRINA/2};
	int[] gap = {(int)(Math.random() * (VISINA-100)), (int)(Math.random() * (VISINA-100))};
	boolean gameOver = false;
	int score = 0;
	int key = 0;
	Timer time = new Timer(20,this);
	
	
	
	public FlappyPanel() {
		
		setSize(SIRINA, VISINA);
		setFocusable(true);
		addKeyListener(this);
		setBackground(Color.cyan);
		
		
	}
	
	
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		if (!gameOver) {
			drawWall(g);
			g.setColor(Color.black);
			g.drawString("SCORE: " +score, SIRINA/2 -50, 15); //Med igro rezultat
			logic();
			drawFlappy(g);
			if (key == 0) {
			g.setColor(Color.black);
			g.drawString("PRESS SPACE TO START", SIRINA/2 - 80, VISINA/2 + 15);
			}
		} else {
			g.setColor(Color.black);//Po igri
			g.drawString("SCORE: " +score, SIRINA/2- 50, 15);
			g.drawString("GAME OVER", SIRINA/2 - 50, VISINA/2);
			g.drawString("PRESS R TO RESTART", SIRINA/2 - 80, VISINA/2 + 15);
	        time.stop();
			
		}
	}
	
	
	
	private void drawFlappy(Graphics g){
		//Kvadrat(ptica)
		
		g.setColor(Color.blue);
		
		g.fillRect( 75, flappyVisina+ flappyV, 25, 25);
	}
	
	
	
	private void drawWall(Graphics g) {
		//Zidovi
		for (int i=0; i<2; i++) {
		g.setColor(Color.GREEN.brighter());
		g.fillRect(zid[i], 0, ZIDSIRINA, VISINA);
		
		g.setColor(Color.cyan);
		g.fillRect(zid[i], gap[i], ZIDSIRINA, 100);
		
		//Tla
		g.setColor(Color.gray);
        g.fillRect(0, 550, getWidth(), 500);  
		}
	}
	
	
	
	
	private void logic() {
		
		//Konec igre ce se dotakne tal
		if (flappyVisina + flappyV + 25 >= VISINA || flappyVisina + flappyV <= 0) {
	        gameOver = true;
	    }
		
		for (int i = 0; i<2; i++) {
			//Konec igre ce se dotakne stene
			if (zid[i] <= 100 && zid[i] + ZIDSIRINA >= 100 || zid[i] <= 75 && zid[i] + ZIDSIRINA >= 75) {
				if((flappyVisina + flappyV) >= 0 && (flappyVisina + flappyV) <= gap[i] || (flappyVisina +flappyV + 25) >= gap[i] + 100 && (flappyVisina + flappyV + 25) <= VISINA){
					gameOver = true;
				}
			}
			//Sestevanje tock
			if (zid[i] + ZIDSIRINA <= 1) {
	            score++;
	        }


			//Randomizirane luknje
			if (zid[i] + ZIDSIRINA <= 0) {
				zid[i] = SIRINA;
				gap[i] = (int)(Math.random() *(VISINA -150));
				}
			}
		}
	
	
	
	
	public void actionPerformed(ActionEvent e) {
		
		flappyA += flappyI;//Gravitacija
		flappyV += flappyA;//Letenje
		zid[0] -= ZIDVELOCITY;
		zid[1] -= ZIDVELOCITY;
		repaint();
	}
	
	
	
	public void keyPressed(KeyEvent e) {
		
		int code = e.getKeyCode();
		
		//Gor	
		if (code == KeyEvent.VK_SPACE) {
			key = 1;
			flappyA = -9;
		}
		//Začetek igre
		if (code == e.VK_SPACE) {
			time.start(); 
		}
		//Restart
		if(code == e.VK_R) {
			time.stop();
			flappyVisina = VISINA/4;
			flappyV = 0;
			flappyA = 9;
			flappyI = 1;
			score = 0;
			zid[0] = SIRINA;
			zid[1] = SIRINA + SIRINA/2;
			gap[0] = (int)(Math.random() * (VISINA-150));
			gap[1] = (int)(Math.random() * (VISINA-150));
			gameOver = false;;
			repaint();
		}
	}
	
	
	
	public void keyReleased(KeyEvent e) {
		
	}

	public void keyTyped(KeyEvent e) {
		
	}
	
}

