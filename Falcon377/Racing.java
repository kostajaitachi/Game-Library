
// One player rACING game
import javax.swing.*;
import java.awt.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.applet.*;

public class Racing extends JFrame
{
final int UP=0,DOWN=2,RIGHT=1,LEFT=3;
int p1direction=UP;
Image img=null;
URL url,eng;
AudioClip sound;
Rectangle left = new Rectangle(20,20,100,700);
Rectangle right=new Rectangle(680,20,100,700);
Rectangle top =new Rectangle(120,20,560,100);
Rectangle bottom=new Rectangle(120,620,560,100);
Rectangle obs1 = new Rectangle(120,120,560,500);
Rectangle obs2=new Rectangle(0,0,20,720);
Rectangle obs3=new Rectangle(0,720,800,80);
Rectangle obs4=new Rectangle(20,0,780,20);
Rectangle obs5=new Rectangle(780,20,20,700);
Rectangle p1=new Rectangle(70,620,30,30);

double p1speed =0.5;
	public Racing()
	{
	super("One PLAYER Racing GAME");
	setSize(800,800);
	setVisible(true);
	//setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		try
		{
		url=this.getClass().getResource("car.png");
		img=Toolkit.getDefaultToolkit().getImage(url);
		}
		catch(Exception ex){}
	addWindowListener(new WindowAdapter()
	{
		public void windowClosing(WindowEvent e)
		{
		sound.stop();
		setVisible(false);
		}
	});
	Move1 m1=new Move1();
	m1.start();
	
		
	}
	public void paint(Graphics g)
	{
	super.paint(g);
	g.setColor(Color.DARK_GRAY);
	g.fillRect(left.x,left.y,left.width,left.height);
	g.fillRect(right.x,right.y,right.width,right.height);
	g.fillRect(top.x,top.y,top.width,top.height);
	g.fillRect(bottom.x,bottom.y,bottom.width,bottom.height);
	//g.setColor(Color.RED);
	g.drawImage(img,p1.x,p1.y,this);
	//g.fillRect(p1.x,p1.y,p1.width,p1.height);
	g.setColor(Color.YELLOW);
	g.fillRect(obs1.x,obs1.y,obs1.width,obs1.height);
	g.fillRect(obs2.x,obs2.y,obs2.width,obs2.height);
	g.fillRect(obs3.x,obs3.y,obs3.width,obs3.height);
	g.fillRect(obs4.x,obs4.y,obs4.width,obs4.height);
	g.fillRect(obs5.x,obs5.y,obs5.width,obs5.height);
	
	
	}
	private class Move1 extends Thread implements KeyListener
	{
		public void run()
		{
		addKeyListener(this);
		try
		{
		eng=this.getClass().getResource("snd.wav");
		sound=JApplet.newAudioClip(eng);
		sound.loop();
		}
		catch(Exception e){}
			while(true)
			{
				if(p1.intersects(obs1) || p1.intersects(obs2) || p1.intersects(obs3) || p1.intersects(obs4) || p1.intersects(obs5))
					p1speed= -8;
				
					try
					{
					repaint();
					if(p1speed<5)
						p1speed += 0.2;
					if(p1direction == UP)
						p1.y = p1.y - (int)p1speed;
					if(p1direction == DOWN)
						p1.y = p1.y + (int)p1speed;
					if(p1direction == LEFT)
						p1.x = p1.x - (int)p1speed;
					if(p1direction == RIGHT)
						p1.x = p1.x + (int)p1speed;
					Thread.sleep(500);
					}
					catch(Exception e)
					{
					break;
					}
				
			}
		}
		public void keyPressed(KeyEvent event){}
		public void keyReleased(KeyEvent event){}
		public void keyTyped(KeyEvent event)
		{
			if(event.getKeyChar() == 'r')
			p1direction = UP;
			if(event.getKeyChar() == 'd')
			p1direction = LEFT;
			if(event.getKeyChar() == 'f')
			p1direction = RIGHT;
			if(event.getKeyChar() == 'c')
			p1direction = DOWN;
			
		}
	}
	
	
	
	public static void main(String args[])
	{
	
	new Racing();
	}
}