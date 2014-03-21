import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.util.*;
import java.io.*;
import javax.imageio.*;
import java.applet.*;
import java.net.*;

public class Earth extends JFrame implements MouseMotionListener,KeyListener
{
Container cont;

URL url =null;
AudioClip au =null;

int currentLevel = 1;
int numOfEnemies =1;

ArrayList bullets = new ArrayList();
ArrayList enemies = new ArrayList();

ImageIcon goku_bullets = new ImageIcon("gokubullet.png");
ImageIcon gokuimg = new ImageIcon("goku.png");
ImageIcon gokuhit = new ImageIcon("gokuhit.png");
ImageIcon friezaimg = new ImageIcon("frieza.png");
ImageIcon friezahit = new ImageIcon("friezahit.png");

JLabel goku = new JLabel(gokuimg);

	public Earth()
	{
	super("Earth Invasion");
	
	try
	{
	
	setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("plains.png")))));
	url = this.getClass().getResource("cell1.wav");
	au=JApplet.newAudioClip(url);
	au.loop();
	
    }
	catch (IOException e) 
	{
    e.printStackTrace();
   	}
	setVisible(true);
	//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	addWindowListener(new WindowAdapter()
	{
		public void windowClosing(WindowEvent e)
		{
		au.stop();
		setVisible(false);
		}
	});
	setSize(500,700);
	cont=getContentPane();
	cont.setLayout(null);
	//ImageIcon i2 = new ImageIcon("p.png");
	//cont.setBackground();
	cont.add(goku);
	goku.setBounds(225,550,50,50);
	
	addKeyListener(this);
	addMouseMotionListener(this);
	populateEnemies();
	
	Play play = new Play();
	play.start();
	
	setContentPane(cont);
	}
	
	public void populateEnemies()
	{
		for(int i=0;i<=numOfEnemies;i++)
		{
		JLabel tempfrieza = new JLabel(friezaimg);
		int randLocation = (int)(Math.random()*500);
		enemies.add(tempfrieza);
		cont.add((JLabel)enemies.get(i));
		tempfrieza.setBounds(randLocation,10,30,30);
		//cont.setComponentZOrder((JLabel)enemies.get(i));
		}
	}
	
	public class Play extends Thread 
	{
		public void run()
		{
			while(true)
			{
				try
				{
					for(int i=0;i<enemies.size();i++)
					{
					JLabel tempfrieza = (JLabel)enemies.get(i);
					int distance = (int)(Math.random()*2);
					tempfrieza.setBounds(tempfrieza.getX(),tempfrieza.getY()+distance,30,90);
						if(tempfrieza.getBounds().intersects(goku.getBounds()))
						{
						cont.remove(tempfrieza);
						}
					if(tempfrieza.getY()>550)
						tempfrieza.setBounds(tempfrieza.getX(),10,30,30);
						
					}
					
					boolean breakAll = false;
					for(int i=0;i<bullets.size();i++)
					{
					JLabel temp = (JLabel)(bullets.get(i));
					temp.setBounds(temp.getX(),temp.getY()-8,10,20);
						if(temp.getY()<0)
						{
						cont.remove(temp);
						bullets.remove(i);
						i--;
						}
						
						for(int j=0;j<enemies.size();j++)
						{
						JLabel tempfrieza = (JLabel)(enemies.get(j));
							if(temp.getBounds().intersects(tempfrieza.getBounds()))
							{
								//tempfrieza.setIcon(friezahit);
								//Thread.sleep(1000);
								enemies.remove(j);
								cont.remove(tempfrieza);
								numOfEnemies--;
							
								if(numOfEnemies<=0)
								{
								currentLevel++;
								numOfEnemies = currentLevel *currentLevel;
								populateEnemies();
								breakAll = true;
								break;
								}
							
							}
						
						}
						if(breakAll)
							break;
						
					
					}
				cont.repaint();
				Thread.sleep(10);
				}
				catch(Exception e){}
			}
		
		}
	}
	
	public void mouseMoved(MouseEvent event)
	{
	goku.setBounds(event.getX()-25,event.getY()-40,50,100);
	}
	
	public void mouseDragged(MouseEvent event){}
	
	public void keyPressed(KeyEvent event)
	{
		if(event.getKeyChar() == ' ')
		{
		JLabel tempBullet = new JLabel(goku_bullets);
		tempBullet.setBounds(goku.getX()+20,goku.getY()-20,10,20);
		bullets.add(tempBullet);
		cont.add((JLabel)(bullets.get(bullets.size()-1)));
		cont.setComponentZOrder((JLabel)(bullets.get(bullets.size()-1)),0);
		}
	}
	
	public void keyTyped(KeyEvent event){}
	public void keyReleased(KeyEvent event){}
	
	public static void main(String args[])
	{
	new Earth();
	}
}