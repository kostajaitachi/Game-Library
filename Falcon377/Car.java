import javax.swing.*;
import java.awt.*;
import javax.swing.event.*;
import java.awt.event.*;

public class Car extends JFrame implements KeyListener,ActionListener
{

// presently our cars

Rectangle car=new Rectangle(10,430,50,50);
Rectangle left=new Rectangle(10,45,50,50);
Rectangle right=new Rectangle(80,45,50,50);

Move m;

static int score;

int randomLocation,randomLocation1;

JLabel score1 = new JLabel("Score : ");


	public Car()
	{
	
	// setting the frame
	setLocation(200,200);
	setResizable(false);
	setSize(220,500);
	setVisible(true);
	setLayout(null);
	//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setBackground(Color.BLACK);
	//score1.setBounds(10,400,30,30);
	//add(score1);
	addKeyListener(this);
	
	// setting the menubar with different levels for varying speed of blocks
	JMenuBar menubar=new JMenuBar();
	JMenu menu=new JMenu("Options");
	JMenuItem item1=new JMenuItem("Levels");
	JMenuItem again = new JMenuItem("Play Again");
	menu.add(again);
	menu.add(item1);
	menubar.add(menu);
	setJMenuBar(menubar);
	
	// setting action to playagain
	again.addActionListener(this);
		
	// running the enemy cars coming to hit us 
	m=new Move();
	m.start();
	
	System.out.println(score);
	}
	
	// graphics of blocks
	public void paint(Graphics g)
	{
	super.paint(g);
	g.setColor(Color.BLUE);
	g.fillRect(car.x,car.y,car.width,car.height);
	g.setColor(Color.RED);
	g.fillRect(left.x,left.y,left.width,left.height);
	g.fillRect(right.x,right.y,right.width,right.height);
	
	// colours of additonal rectangles of left and right
	
	
	}
	
	
	// the main thread enabling the rival cars to move
	private class Move extends Thread 
	{
		public void run() 
		{
			while(true)
			{
			int i = (int)(Math.random()*2);
				if(i==1)
				{
				left.y = left.y+100;
					try
					{
					repaint();
					Thread.sleep(500);
					}
					catch(Exception e){}
				}
				else if(i==0)
				{
				right.y=right.y+100;
					try
					{
					repaint();
					Thread.sleep(500);
					}
					catch(Exception e){}
				}
				
				if(car.intersects(left) || car.intersects(right))
				{
					JOptionPane.showMessageDialog(null,"You Lost Sorry ");
					try
					{
					m.stop();
					}
					catch(Exception ie)
					{
					ie.printStackTrace();
					}
					
				}
				
				
				
				if(left.y >500)
				{
				randomLocation = (int)(Math.random()*3);
					if(randomLocation == 0)
					{
						left.y=45;
						left.x=10;
					}
					
					else if(randomLocation == 1)
					{
						left.y=45;
						left.x=80;
					}
					
					else if(randomLocation ==2)
					{
						left.y=45;
						left.x=140;
					}
					score+=10;
				}
				
				if(right.y > 500)
				{
				randomLocation1 = (int)(Math.random()*3);
					while(randomLocation1==randomLocation)
					{
						randomLocation1 = (int)(Math.random()*3);
					}
					if(randomLocation1 == 0)
					{
						right.y=45;
						right.x=10;
					}
					
					else if(randomLocation1 == 1)
					{
						right.y=45;
						right.x=80;
					}
					
					else if(randomLocation1==2)
					{
						right.y=45;
						right.x=140;
					}
					score+=10;
				}
			}
		}
		
	
	}
	
	// keylisteners for ourd car to move left and right to counter via using a and d

	public void keyPressed(KeyEvent event){}
	public void keyReleased(KeyEvent event){}
	public void keyTyped(KeyEvent event)
	{
		
		if(event.getKeyChar() == 'a')
		{
				car.x=car.x-70;
		}
		if(event.getKeyChar() == 'd')
		{
			car.x =car.x+70;
		}
		
		//score1.setText("Score : " +score);
			
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		
		setVisible(false);
		new Car();
		
	}
	
	// for showing the final score
	public void showScore()
	{
	JOptionPane.showMessageDialog(null,"Your Score is " + score);
	}
	
	// main method of program
	public static void main(String args[])
	{
	new Car();
	}
	
}

/*
Problems being faced - 
maintain atleast a difference of 60 between cars when they crosses 350 cross mark
play again button not working properly
*/