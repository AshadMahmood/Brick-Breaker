import java.awt.Color;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import java.awt.Rectangle;


import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;

public class BreakOut implements ActionListener, MouseListener, MouseMotionListener  {
	
	public static BreakOut breakout;
	public final int WIDTH = 800, HEIGHT = 600;
	public Renderer renderer;
	public Rectangle ball;
	public ArrayList<Rectangle> box;
	public Rectangle slab;
	public int xMotion;
	public int yMotion;
	public boolean mO;
	public int ballY =500;
	public int ballX =40;

	int rand = (int) ((Math.random()*((5-1)+1))+1);
	public int ballDx=-1;
	public int ballDy=-2;
	public int bDirection = 180;
	public int ballSpeed = 7;
	boolean start;
	boolean end = false;
	int score;

	
	public BreakOut() {
		
		JFrame jframe = new JFrame(); 
		JLabel label = new JLabel("My Label");
		label.setText("Click to start the game");
		label.setVisible(true);
		renderer = new Renderer();
		Timer timer = new Timer(10,this);
		jframe.add(renderer);
		//jframe.add(label);
		jframe.setName("Breakout");
		jframe.setSize(WIDTH,HEIGHT);
		jframe.setVisible(true);
		jframe.setResizable(true);
		jframe.addMouseListener(this);
		
		jframe.addMouseMotionListener(this);
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 ball = new Rectangle(ballX, ballY,20, 20);
		 if(mO=true) {
		 slab = new Rectangle(xMotion,530,100,20);
		 }
		 else {
			 slab = new Rectangle(20,530,100,20);
			
		 }
			 
			 box = new ArrayList<Rectangle>();
		
		 addBoxes();
		 timer.start();
		
	}
	
			void ballCollision() {
				 
				for(int x=0;x<box.size();x++) {
				Rectangle g = box.get(x);
				if(new Rectangle(ballX,ballY,20,20).intersects(g)){
					box.remove(g);
					score = score + 5;
					if(box.size() == 0) { 

						 start = false;
						 end = true;
						 renderer.repaint();}
					
						ballDy = -ballDy;
					 
				}
					 }
				
				
							}
		 void ballMovement() throws InterruptedException  {
		
		 if(new Rectangle(ballX,ballY,20,20).intersects(new Rectangle(xMotion,530,100,20))) {
			 ballDy = -ballDy;
			 }
	 

		 
		 ballX += ballDx;
		 ballY += ballDy;
		
			renderer.repaint();
		 if((ballX<0 ) || (ballY<0 || ballY > HEIGHT)) {
			 ballDx = -ballDx;
			 ballDx =  (int) ((Math.random()*((5-1)+1))+1);
		 }
			 if(ballY<0 ) {
				 ballDy = -ballDy;
			 }
			 if(ballX>750 ) {
				 ballDx = -ballDx;
				 }
			 if(ballY>=600 ) {
				 start =false;
				 end = true;
				 }
		 }


	 
	
	ArrayList<Rectangle> addBoxes() {
		
		int width = 100;
		int height = 20;
		int boxX = WIDTH/8;
		int boxY = HEIGHT/30;
		
		for (int z=0;z<3;z++) {
			
		for(int x=0;x<3;x++) {
			box.add(new Rectangle( boxX, boxY,width,height));
			boxX = boxX+110;
		}
		boxX = WIDTH/8;
		boxY= boxY+40;
		}
return box;
			
		
	}
	
	public void paintBox(Graphics g, Rectangle box) {
		g.setColor(Color.BLUE);
		g.fillRect(box.x, box.y, box.width, box.height);
	}
	
	public void repaint(Graphics g) {
		g.setColor(Color.YELLOW);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		g.setColor(Color.BLACK);
		g.fillOval(ballX, ballY, ball.width, ball.height);
	
	
		g.setColor(Color.RED);  
		g.fillRect(xMotion,530, slab.width, slab.height);
		for(Rectangle bo : box) {
			
		
			paintBox(g,bo);}
		
		if(start) {
			
			g.drawString("Score = " + score, 20, 50);
			}
			if(!start) {
			
			g.drawString("Click to Start", 350, 300);
			}
			if(!start && end==true) {
				g.drawString("Game Over,Click to Start again", 300, 350);
				g.drawString("Score = " + score, 350, 400);
			}
			
		
	}
public static void main(String[] args) throws InterruptedException {
		
	
		breakout = new BreakOut();

	
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
		start = true;
		if(start==true && end==true) {
			
		
			box.removeAll(box);
			addBoxes();
			ballY = 300;
			ballX =300;
			score = 0;
			end = false;
			
		}
		
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(start) {
		try {
			ballMovement();
			ballCollision();

		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}}
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		
		mO=true;

		xMotion = e.getX();
		yMotion = e.getY();
		renderer.repaint();
		
				
		
	}
	
	
}
